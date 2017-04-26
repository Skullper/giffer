package com.a2lab.project.giftest

import android.content.Context
import android.content.res.Resources

/**
 * Created by pugman on 07.03.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

/**
 * Retrieve app resources
 */
val res: Resources
    get() = ApplicationWrapper.context.resources

/**
 * Retrieve app context
 */
val context: Context
    get() = ApplicationWrapper.context