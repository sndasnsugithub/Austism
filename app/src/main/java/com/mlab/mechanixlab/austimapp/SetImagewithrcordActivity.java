package com.mlab.mechanixlab.austimapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.orm.SugarRecord;

import java.io.File;


public class SetImagewithrcordActivity extends AppCompatActivity {


    Button btn_rcoding,btn_save;
    File imageFile;
    String soundPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_imagewithrcord);
        btn_rcoding = (Button) findViewById(R.id.btn_rcoding);
        btn_save = (Button) findViewById(R.id.btn_save);
        //byte[] byteArray = getIntent().getByteArrayExtra("img");
        //Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageFile = (File) getIntent().getExtras().get("img");
        Bitmap bmp = ImageHelper.getImageFromFIle(imageFile);

        ImageView imageView = (ImageView)findViewById(R.id.imv_selected);
        if (bmp!=null){
            imageView.setImageBitmap(bmp);

        }

        btn_rcoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);

                //startActivityForResult(recordIntent, RESULT_OK);

                startActivityForResult(new Intent(SetImagewithrcordActivity.this,AudioRecordTest.class),1);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapturedInfo capturedInfo = new CapturedInfo(1,imageFile.getAbsolutePath().toString(),soundPath);

                SugarRecord.save(capturedInfo);


                //CapturedInfo book = CapturedInfo.findById(CapturedInfo.class, 1);
                //Log.e("Values: ", book.id+" , "+book.image+" , "+book.sound);
            }
        });
        //CapturedInfo capturedInfo = new CapturedInfo(1,"TKB","Value");
        //capturedInfo.save();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            soundPath = data.getStringExtra("sound");
            Log.e("Music Path: ",soundPath);
        }
    }
}
