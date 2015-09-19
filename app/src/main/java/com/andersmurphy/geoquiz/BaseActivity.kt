package com.andersmurphy.geoquiz

import android.os.Bundle
import android.support.v7.app.ActionBarActivity

abstract class BaseActivity : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(javaClass.getAnnotation(ContentView::class.java).value)
    }
}
