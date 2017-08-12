package com.mlab.mechanixlab.austimapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GallaryActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
      //  gridView.setAdapter(gridAdapter);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                playSound(item.getSoundPath());
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1,
                                           int position, long arg3) {

                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                //Create intent
                Intent intent = new Intent(GallaryActivity.this, ImaheDetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                //Start details activity
                startActivity(intent);

                return false;
            }
        });}

    private void playSound(String path) {
        MediaPlayer mpintro = MediaPlayer.create(this, Uri.parse(path));
        mpintro.setLooping(false);
        mpintro.start();
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        //TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        List<CapturedInfo> capturedInfoList = CapturedInfo.listAll(CapturedInfo.class);

        for (CapturedInfo info : capturedInfoList) {
            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            //imageItems.add(new ImageItem(bitmap, "Image#" + i));
           File imageFile = new File(info.imagePath);
            Bitmap bmp = ImageHelper.getImageFromFIle(imageFile);

            ImageItem imageItem = new ImageItem();
            imageItem.setImage(bmp);
            imageItem.setTitle(info.imageName);
            imageItem.setSoundPath(info.soundPath);

            imageItems.add(imageItem);
        }
        return imageItems;
    }
}
