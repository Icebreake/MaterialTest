package com.example.materialtest

import android.view.View
import com.google.android.material.snackbar.Snackbar

//都增加了一个函数类型参数，还增加了一个传递给setAction()方法的字符串
//新增加的两个参数都设置为可空的类型，并将默认值都设置为空
fun View.showSnackbar(text: String, actionText: String? = null,
                      duration: Int = Snackbar.LENGTH_SHORT,block: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, text, duration)
    //只有当两个参数都不为空的时候
    if (actionText != null && block != null) {
        //才去调用setAction()方法来设置额外的点击事件，如果触发了点击事件，只需要调用函数类型参数将事件传递给外部的Lambda表达式即可
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}

//增加了一个传递给setAction()方法的字符串资源id
fun View.showSnackbar(resId: Int, actionResId: Int? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)?) {
    val snackbar = Snackbar.make(this, resId, duration)
    if (actionResId != null && block != null) {
        snackbar.setAction(actionResId) {
            block()
        }
    }
    snackbar.show()
}