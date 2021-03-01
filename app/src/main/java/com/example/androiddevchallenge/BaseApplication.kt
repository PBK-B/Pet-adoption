package com.example.androiddevchallenge

import android.app.Application
import android.content.Context

class BaseApplication : Application {

    companion object {
        var _context: Context? = null

        fun getContext(): Context {
            return _context!!
        }
    }

    constructor() : super()

    override fun onCreate() {
        super.onCreate()
        _context = this.applicationContext
    }

}