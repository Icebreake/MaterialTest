package com.example.materialtest

import android.content.Context
import android.widget.Toast

//增加了一个显示时长参数，但同时也给它指定了一个参数默认值，默认使用 Toast.LENGTH_SHORT类型的显示时长
fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}