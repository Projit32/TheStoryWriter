package com.android.asplabs.thestorywriter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_select_template.*

class SelectTemplate : AppCompatActivity(), View.OnClickListener {
    private var imgId: Int = 0
    private var imgbase: Int = 0
    private var drawimgbase: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_template)
        imgbase = R.id.img1
        drawimgbase = R.drawable.tsw0

    }


    fun startEdit(view: View) {
        val intent = Intent(this, EditImage::class.java)
        intent.putExtra("offset", imgId)
        intent.putExtra("img", drawimgbase)
        startActivity(intent)
    }

    fun reset(v: View) {

        //Toast.makeText(getApplicationContext(),"touched",Toast.LENGTH_SHORT).show();
        if (GoForIt.visibility == View.VISIBLE)
            GoForIt.visibility = View.INVISIBLE

    }

    override fun onClick(v: View) {

        imgId = v.id - imgbase
        Toast.makeText(this, "Template No. " + Integer.toString(imgId + 1) + " Selected", Toast.LENGTH_SHORT).show()
        GoForIt.visibility = View.VISIBLE

    }
}
