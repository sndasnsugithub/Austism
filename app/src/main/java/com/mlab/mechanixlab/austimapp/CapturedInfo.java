package com.mlab.mechanixlab.austimapp;

import android.graphics.Bitmap;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

/**
 * Created by tarun on 8/11/17.
 */

public class CapturedInfo extends SugarRecord{

   @Unique
   public int id;
   public String imagePath;
   public String soundPath;
   public String imageName;

   // Default constructor is necessary for SugarRecord
   public CapturedInfo() {

   }

   public CapturedInfo(int id, String imagePath, String soundPath,String imageName) {
      this.id = id;
      this.imagePath = imagePath;
      this.soundPath = soundPath;
      this.imageName =imageName;
   }
}
