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

import java.io.Serializable;

public class MediatorMask {
    public Bitmap bitmap;
    MainActivity mainActivity;
    String path;
    Uri imageUri = null;
    int rot;
    public MediatorMask(Bitmap bitmap, MainActivity mainActivity, Uri uri,int rot) {
        this.bitmap = bitmap;
        this.mainActivity =  mainActivity;
        this.path = path;
        this.imageUri = uri;
        this.rot = rot;
    }

    public void notificar(String evento) {
        if (evento.equals("maskdetect")) {

            GestionMask gestionMask = new GestionMask(this);

            gestionMask.setImage(bitmap,rot);
        }
    }


    public void setMaskInfo(Mask mask) {
        Intent intent = new Intent(mainActivity.getApplicationContext(),MaskDetection.class);
        intent.putExtra("mask", mask);

        intent.putExtra("imageUri", imageUri.toString());
        mainActivity.startActivity(intent);


    }
}
