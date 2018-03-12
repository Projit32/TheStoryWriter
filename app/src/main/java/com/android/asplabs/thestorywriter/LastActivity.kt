package com.android.asplabs.thestorywriter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.last)
    }


    fun goToTemp(view: View) {
        val i = Intent(this, SelectTemplate::class.java)
        startActivity(i)
    }

    fun ExitApp(view: View) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask()
        } else {
            finish()
        }
    }
}
