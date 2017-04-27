package com.a2lab.project.giftest.utils

/**
 * Created by pugman on 27.04.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

sealed class SimpleMessage

data class Text(val asText: String) : SimpleMessage()
data class Resource(val asResourceId: Int) : SimpleMessage()