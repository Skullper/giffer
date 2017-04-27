package com.a2lab.project.giftest.preview

import android.graphics.Bitmap
import android.graphics.drawable.AnimationDrawable
import android.support.annotation.DrawableRes
import android.widget.Toast
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.arch.BaseActivity
import com.a2lab.project.giftest.preview.presentation.PreviewPresenter
import com.a2lab.project.giftest.preview.presentation.PreviewView
import com.a2lab.project.giftest.share.ShareActivity
import com.a2lab.project.giftest.utils.Constantaz
import com.a2lab.project.giftest.utils.Constantaz.EXTRAS.SHARE_LINK
import com.a2lab.project.giftest.utils.FrameObtainer
import com.a2lab.project.giftest.utils.SimpleMessage
import kotlinx.android.synthetic.main.activity_preview.*
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity

/**
 * Created by pugman on 28.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
class PreviewActivity : BaseActivity<PreviewPresenter>(), PreviewView {

    @DrawableRes
    private val DEFAULT_FRAME = android.R.color.transparent

    private var pathToVideo = ""

    override fun providePresenter(): PreviewPresenter = PreviewPresenter(this, FrameObtainer(this), resources)

    override fun getLayoutResource(): Int = R.layout.activity_preview

    override fun onCreate() {
        pathToVideo = intent.getStringExtra(Constantaz.EXTRAS.PATH_TO_VIDEO)
        presenter.convertVideoToGif(pathToVideo)

        var selectedFrame = DEFAULT_FRAME
        previewAct_framer.setItems(getFramesFromResources()) {
            selectedFrame = it
            previewAct_frameIV.imageResource = selectedFrame
        }
        previewAct_shareBut.setOnClickListener { presenter.saveGif(selectedFrame) }
        previewAct_cancelBut.setOnClickListener { onBackPressed() }
    }

    fun getFramesFromResources(): ArrayList<Int> {
        val frames = resources.obtainTypedArray(R.array.frames)
        val tempList = ArrayList<Int>()
        (0..frames.length() - 1).mapTo(tempList) { frames.getResourceId(it, DEFAULT_FRAME) }
        frames.recycle()
        return tempList
    }

    override fun setPreview(bitmap: Bitmap) {
        runOnUiThread { previewAct_gifIV.imageBitmap = bitmap }
    }

    override fun provideGif(animation: AnimationDrawable) {
        runOnUiThread {
            previewAct_gifIV.setImageDrawable(animation)
            animation.start()
        }
    }

    override fun showMessage(message: SimpleMessage) {
        notify(message)
    }

    override fun onFrameObtainError(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun openShareAct(url: String) {
        startActivity<ShareActivity>(SHARE_LINK to url)
        finish()
    }
}