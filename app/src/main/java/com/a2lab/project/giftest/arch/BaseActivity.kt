package com.a2lab.project.giftest.arch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.arch.presentation.BaseView
import com.a2lab.project.giftest.extensions.gone
import com.a2lab.project.giftest.extensions.visible
import com.a2lab.project.giftest.utils.Constantaz
import com.a2lab.project.giftest.utils.Resource
import com.a2lab.project.giftest.utils.SimpleMessage
import com.a2lab.project.giftest.utils.Text
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pugman on 06.12.16.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), BaseView {

    abstract fun providePresenter(): T

    abstract fun getLayoutResource(): Int

    abstract fun bindViews()

    lateinit var presenter: T
    private var progressView: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        addContentView(getProgressView(), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
        presenter = providePresenter()
        bindViews()
    }

    fun createMessage(message: SimpleMessage): Snackbar = when (message) {
        is Text -> Snackbar.make(activity_container, message.asText, Constantaz.SNACK_BAR_DURATION)
        is Resource -> Snackbar.make(activity_container, message.asResourceId, Constantaz.SNACK_BAR_DURATION)
    }

    override fun showProgress() {
        runOnUiThread {
            progressView?.visible()
        }
    }

    override fun hideProgress() {
        runOnUiThread {
            progressView?.gone()
        }
    }

    private fun getProgressView(): View {
        val view = layoutInflater.inflate(R.layout.widget_progress_view, activity_container, false)
        progressView = view.findViewById(R.id.progressView) as FrameLayout
        return view
    }

}