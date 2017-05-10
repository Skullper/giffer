package com.a2lab.project.giftest.arch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a2lab.project.giftest.arch.presentation.BasePresenter
import com.a2lab.project.giftest.utils.SNACK_BAR_DURATION
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pugman on 27.12.16.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */
abstract class BaseFragment<T : BasePresenter> : Fragment(){

    protected var presenter : T? = null

    abstract fun providePresenter() : T

    abstract fun getLayoutId() : Int

    abstract fun onCreate()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(getLayoutId(), container, false)
        presenter = providePresenter()
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        onCreate()
    }

    fun notify(message: Any){
        when (message) {
            is Int -> Snackbar.make(activity_container, message, SNACK_BAR_DURATION).show()
            is String -> Snackbar.make(activity_container, message, SNACK_BAR_DURATION).show()
        }
    }

}