package com.example.mvvmpostlast

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface

class WebAppBridge(
    private val openPostScreen: () -> Unit
) {

    @JavascriptInterface
    fun openPostList() {
        Handler(Looper.getMainLooper()).post {
            openPostScreen()
        }
    }
}
