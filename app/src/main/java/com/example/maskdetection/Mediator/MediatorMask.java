package com.example.maskdetection.Mediator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.example.maskdetection.GestionMask;
import com.example.maskdetection.MainActivity;
import com.example.maskdetection.Mask;
import com.example.maskdetection.MaskDetection;

public class MediatorMask {
    public Bitmap rotatedBitmap;
    MainActivity mainActivity;
    String path;
    Uri imageUri = null;
    public MediatorMask(Bitmap rotatedBitmap, MainActivity mainActivity, Uri uri) {
        this.rotatedBitmap = rotatedBitmap;
        this.mainActivity =  mainActivity;
        this.path = path;
        this.imageUri = uri;
    }

    public void notificar(String evento) {
        if (evento.equals("maskdetect")) {

            GestionMask gestionMask = new GestionMask(this);

            gestionMask.setImage(rotatedBitmap);
        }
    }


    public void setMaskInfo(Mask mask1,Mask mask2) {
        Intent intent = new Intent(mainActivity.getApplicationContext(),MaskDetection.class);
        intent.putExtra("res1",mask1.res);
        intent.putExtra("acc1",mask1.acc);
        intent.putExtra("res2",mask2.res);
        intent.putExtra("acc2",mask2.acc);
        intent.putExtra("imageUri", imageUri.toString());
        mainActivity.startActivity(intent);


    }
}
