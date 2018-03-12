package com.android.asplabs.thestorywriter;


import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;

public class EditImage extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    Button Conti;
    ImageView img;
    ConstraintLayout layout;
    EditText textMain;
    String text="";
    Bitmap originalImage,editedImage;
    SeekBar YaxisBar;
    LinearLayout seekLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        Bundle data=getIntent().getExtras();
         img=(ImageView)findViewById(R.id.MainImage);
        int id=data.getInt("img")+data.getInt("offset");
        img.setImageResource(id);
        Conti=(Button)findViewById(R.id.Conti);
        layout=(ConstraintLayout)findViewById(R.id.input);
        originalImage=BitmapFactory.decodeResource(getResources(),id);
        editedImage = originalImage.copy(originalImage.getConfig(), true);
        YaxisBar=(SeekBar)findViewById(R.id.YaxisBar);
        YaxisBar.setOnSeekBarChangeListener(this);
        seekLayout=(LinearLayout)findViewById(R.id.seekLayout);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int y=(int) (editedImage.getHeight()*progress)/100;
        imageEdit(y);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void Continue(View v)
    {
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyymmddhhmmss");
        String fileName="The Story Writer "+dateFormatter.format(now).toString()+"pic";
        File dir = new File(Environment.getExternalStorageDirectory()+"/TSW/");
        if(dir.exists()==false)
            dir.mkdirs();
        File file = new File(dir, fileName+".jpg");
        try {
            OutputStream fOut = new FileOutputStream(file);
            editedImage.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
        Intent intent=new Intent(this,LastActivity.class);
        startActivity(intent);

    }

    public void imageEdit(int y){
        editedImage = originalImage.copy(originalImage.getConfig(), true);
        int fi=0;
        Canvas newCanvas = new Canvas(editedImage);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        Rect bounds = new Rect();
        for(int i=1;i<text.length();i++)
        {
            if(text.charAt(i-1)=='\n'&& text.charAt(i)!='\0') {
                String sub=text.substring(fi,i-1).replace("\n","");
                //Toast.makeText(this, sub, Toast.LENGTH_SHORT).show();
                fi=i;
                paint.getTextBounds(sub, 0, sub.length(), bounds);
                newCanvas.drawText(sub, 300, y, paint);
                y+=50;
            }
            else if(text.charAt(i)=='\n')
                y+=70;

        }
        img.setImageBitmap(editedImage);
        textMain.setText(text);
    }


    public void Next(View view) throws InterruptedException {

        textMain=(EditText)findViewById(R.id.textMain);
        text=textMain.getText().toString();
        Conti.setVisibility(View.VISIBLE);
        seekLayout.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        text+="\n ";

        imageEdit(300);
    }
    public void Editing(View v)
    {
        Conti.setVisibility(View.GONE);
        seekLayout.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);

    }


}
