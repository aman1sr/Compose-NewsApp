package com.pahadi.composenewsapp.common.logger

import android.util.Log

class AppLogger : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag,msg)          // todo: add a check of debug build
    }
}