package com.a2lab.project.giftest.arch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.a2lab.project.giftest.R
import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.arch.presentation.BaseView
import com.a2lab.project.giftest.extensions.gone
import com.a2lab.project.giftest.extensions.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.widget_progress_view.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        addContentView(getProgressView(), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
        presenter = providePresenter()
        bindViews()
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

    private fun getProgressView(): View = layoutInflater.inflate(R.layout.widget_progress_view, activity_container, false)

}