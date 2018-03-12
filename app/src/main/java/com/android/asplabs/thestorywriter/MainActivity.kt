package com.android.asplabs.thestorywriter



import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import com.tomer.fadingtextview.FadingTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        textFader.setTimeout(2.5,FadingTextView.SECONDS)


    }
    fun DoThis(view: View)
    {

        val intent = Intent(this,SelectTemplate::class.java)
        startActivity(intent)

    }
}
