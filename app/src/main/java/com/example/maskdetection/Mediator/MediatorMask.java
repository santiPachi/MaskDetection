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


    public void setMaskInfo(String res) {
        Intent intent = new Intent(mainActivity.getApplicationContext(),MaskDetection.class);
        intent.putExtra("res",res);

        intent.putExtra("imageUri", imageUri.toString());
        mainActivity.startActivity(intent);


    }
}
