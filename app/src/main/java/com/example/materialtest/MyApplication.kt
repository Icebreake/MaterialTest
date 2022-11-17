package com.example.materialtest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        //将Context设置成静态变量很容易会产生内存泄露的问题，所以这是一种有风险的做法
        /*
        但是由于这里获取的不是Activity或Service中的Context，而是Application中的Context，它全局只会存在一份实例
        并且在整个应用程序的生命周期内都不会回收，因此是不存在内存泄露风险的
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        //调用getApplicationContext()方法得到的返回值赋值给context变量，就可以以静态变量的形式获取Context对象了
        context = applicationContext
    }

}