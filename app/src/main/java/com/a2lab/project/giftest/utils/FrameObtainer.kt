package com.a2lab.project.giftest.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import java.io.File


/**
 * Created by pugman on 27.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class FrameObtainer(val context: Context) {

    private val retriever: MediaMetadataRetriever by lazy { MediaMetadataRetriever() }
    private val frameList: ArrayList<Bitmap> by lazy { ArrayList<Bitmap>() }

    /**
     * Set the video file from which you want to receive frames
     */
    fun setSource(file: File) {
        retriever.setDataSource(context, Uri.fromFile(file))
    }

    private fun getVideoDurationInSeconds(): Double {
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        return time.toDouble() / 1000
    }

    private fun getVideoFrame(frameTime: Long): Bitmap? = retriever.getFrameAtTime(frameTime, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)

    fun retrieveFrames(file: File, chanel: Channel<Bitmap>) = async(CommonPool) {
        val step = 333333L //tried to get each third frame
        val duration = getVideoDurationInSeconds().toInt()
        for (i in (step..duration) /*step 33333L*/) { //For some reason getting compilation error when using step function
            val looper = step + i
            val bitmap = getVideoFrame(looper)
            if (bitmap != null) {
                if (!chanel.isClosedForReceive)
                    chanel.send(bitmap)
                frameList.add(bitmap)
            } else Log.e("FRAMER", "Crashed frame number $i step $looper")
        }

        deleteFile(file)
        retriever.release()
        frameList
    }

    fun getFrames(): ArrayList<Bitmap> = frameList

    /**
     * deletes useless video file
     */
    private fun deleteFile(file: File) {
        val folder = File(file.parent)
        if (file.isDirectory) {
            for (child in file.listFiles()) {
                deleteFile(child)
            }
        }
        if (file.delete())
            folder.delete()
        else Log.e("TAGA", "File not deleted")
    }

}