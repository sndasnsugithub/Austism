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
   public String image;
   public String sound;

   // Default constructor is necessary for SugarRecord
   public CapturedInfo() {

   }

   public CapturedInfo(int id, String image, String sound) {
      this.id = id;
      this.image = image;
      this.sound = sound;
   }
}
