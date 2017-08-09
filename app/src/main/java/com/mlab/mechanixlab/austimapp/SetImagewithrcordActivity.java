package com.mlab.mechanixlab.austimapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SetImagewithrcordActivity extends AppCompatActivity {


    Button recoding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_imagewithrcord);
        recoding = (Button) findViewById(R.id.buttonrcoding);
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ImageView imageView = (ImageView)findViewById(R.id.imv_selected);
        imageView.setImageBitmap(bmp);

    }
}
