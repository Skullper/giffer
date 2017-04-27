package com.a2lab.project.giftest.share

import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.arch.BaseActivity
import com.a2lab.project.giftest.extensions.log
import com.a2lab.project.giftest.share.presentation.SharePresenter
import com.a2lab.project.giftest.share.presentation.ShareView
import com.a2lab.project.giftest.utils.Constantaz.EXTRAS.SHARE_LINK

/**
 * Created by pugman on 03.04.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

//Should be an activity where user inputs number of his friend and shares link to his just created gif. But project was closed at this phase
class ShareActivity : BaseActivity<SharePresenter>(), ShareView {

    override fun providePresenter(): SharePresenter = SharePresenter(this)

    override fun getLayoutResource(): Int = R.layout.activity_share

    override fun onCreate() {
        val gifUrl = intent.getStringExtra(SHARE_LINK)
        "SHARE".log("Gif url: $gifUrl")
    }

    override fun showMessage(message: Any) {
    }
}