package com.vikas.optimumuse.commons

import android.app.Application
import java.lang.ref.WeakReference

class OUApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Injection.context = WeakReference(this.applicationContext)
    }
}