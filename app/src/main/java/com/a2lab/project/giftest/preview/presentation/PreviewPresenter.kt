package com.a2lab.project.giftest.preview.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.support.annotation.DrawableRes
import android.webkit.MimeTypeMap
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.api.responses.UploadingResponse
import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.extensions.log
import com.a2lab.project.giftest.gif.AnimatedGifEncoder
import com.a2lab.project.giftest.res
import com.a2lab.project.giftest.utils.FrameObtainer
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class PreviewPresenter(override val view: PreviewView, val frameObtainer: FrameObtainer) : BasePresenter() {

    private val model = PreviewModel()

    private val FOLDER_NAME = "GifferGifs"
    private val GIF_QUALITY = 20
    private val GIF_DELAY = 100

    private var gifByteArray: ByteArray? = null


    fun convertVideoToGif(pathToVideo: String) = launch(CommonPool) {
        if (pathToVideo.isNullOrEmpty())
            view.onFrameObtainError(res.getString(R.string.previewAct_videoRecordException))
        //Channel created with purpose to get first retrieved frame and set it like preview
        val bitmapChanel = Channel<Bitmap>()
        val file = File(pathToVideo)
        frameObtainer.setSource(file)
        //Blocking user interactions
        view.showProgress()
        val frames = frameObtainer.retrieveFrames(file, bitmapChanel)
        launch(CommonPool) {
            //getting first frame
            view.setPreview(bitmapChanel.receive())
            //closes when first frame has been retrieved
            bitmapChanel.close()
        }
        //shows gif using AnimationDrawable. Why not Glide with arrayBytes? Because created gif had bad quality for preview
        showGif(convertBitmapsToDrawables(frames.await()))
    }

    private fun convertBitmapsToDrawables(frames: ArrayList<Bitmap>): List<Drawable> {
        view.hideProgress()
        return frames.map { bitmap ->
            BitmapDrawable(res, bitmap)
        }
    }

    private fun showGif(frames: List<Drawable>) {
        val animation = AnimationDrawable()
        for (i in 0..frames.size - 1) {
            val step = i + GIF_DELAY
            animation.addFrame(frames[i], step)
        }
        animation.isOneShot = false
        view.provideGif(animation)
    }

    fun saveGif(@DrawableRes frameId: Int) = launch(CommonPool) {
        view.showProgress()
        val gif = generateGifAsync(addPictureOnEachFrame(frameId))
        val bos = gif.await()
        gifByteArray = bos.toByteArray()
        writeGifOnSdCard()
        bos.close()
    }

    private fun generateGifAsync(bitmaps: List<Bitmap>) = async(CommonPool) {
        val bos = ByteArrayOutputStream()
        val encoder = AnimatedGifEncoder()
        encoder.setQuality(GIF_QUALITY)
        encoder.setDelay(GIF_DELAY)
        encoder.setRepeat(0)
        encoder.start(bos)
        bitmaps.forEach { bitmap ->
            encoder.addFrame(bitmap)
        }
        encoder.finish()
        bos
    }

    private fun writeGifOnSdCard() = launch(CommonPool) {
        val gifPath = writeGifOnSdCardAsync().await()
        view.showMessage(res.getString(R.string.previewAct_gifCreatedMessage, gifPath))
        uploadFile(gifPath)
        //use this to write each frame
//        for(i in 0..frameObtainer.getFrames().size-1){
//            saveToInternalStorage(frameObtainer.getFrames().toList()[i], i)
//        }
    }

    private fun writeGifOnSdCardAsync() = async(CommonPool) {
        val name = System.currentTimeMillis()
        val folder = File(Environment.getExternalStorageDirectory().absolutePath, FOLDER_NAME)
        if (!folder.exists())
            folder.mkdir()
        val createdGifPath = folder.absolutePath + "/$name.gif"
        val outputStream = FileOutputStream(createdGifPath)
        outputStream.write(gifByteArray)
        outputStream.close()
        //stop blocking user interactions
        view.hideProgress()
        createdGifPath
    }

    private fun addPictureOnEachFrame(@DrawableRes frame: Int): List<Bitmap> {
        //if user didn't select any frame return raw frames
        if (frame == android.R.color.transparent)
            return frameObtainer.getFrames()

        //adding overlay
        var frameBitmap = BitmapFactory.decodeResource(res, frame)
        frameBitmap = Bitmap.createScaledBitmap(frameBitmap, frameObtainer.getFrames().toList()[0].width,
                frameObtainer.getFrames().toList()[0].height, false)
        try {
            return frameObtainer.getFrames().map { frame ->
                addCustomFrameOnGif(frame, frameBitmap)
            }
        } finally {
            frameBitmap.recycle()
        }
    }

    /**
     * Draws a picture on each frame
     */
    fun addCustomFrameOnGif(gif: Bitmap, frame: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(gif.width, gif.height, gif.config)
        val canvas = Canvas(result)
        canvas.drawBitmap(gif, 0f, 0f, null)
        canvas.drawBitmap(frame, 0f, 0f, null)
        return result
    }

    //uploading file to admin panel
    fun uploadFile(pathToGif: String) {
        if (pathToGif.isNullOrEmpty())
            view.showMessage(res.getString(R.string.previewAct_cannotFindFileException))
        val gifFile = File(pathToGif)
        val extension = MimeTypeMap.getFileExtensionFromUrl(pathToGif)
        var fileType = ""
        if (extension != null)
            fileType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        val requestBody = RequestBody.create(MediaType.parse(fileType), gifFile)
        model.upload(requestBody)
                .enqueue(object : Callback<UploadingResponse> {
                    override fun onFailure(call: Call<UploadingResponse>?, t: Throwable?) {
                        "UPLOAD_FAILURE".log(throwable = t)
                    }

                    override fun onResponse(call: Call<UploadingResponse>?, response: Response<UploadingResponse>?) {
                        if (response != null && response.isSuccessful) {
                            view.showMessage(res.getString(R.string.previewAct_fileUploadedMessage))
                            val shareUrl = response.body()?.url
                            if (shareUrl != null && shareUrl.isNotEmpty())
                                view.openShareAct(shareUrl)
                        }
                    }
                })
    }

    //test function
    private fun saveToInternalStorage(bitmapImage: Bitmap, number: Int): String {
        val directory = File(Environment.getExternalStorageDirectory().absolutePath + "/GifferGifs/")
        // Create imageDir
        val mypath = File(directory, "profile$number.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return directory.absolutePath
    }

}