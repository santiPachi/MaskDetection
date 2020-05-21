package com.example.maskdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MaskDetection extends AppCompatActivity {
    public ImageView imgView;

    public TextView txRes1;
    public Mask mask;
    Button btBad;
    Button btOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_detection);
        imgView = findViewById(R.id.img_per);
        txRes1 = findViewById(R.id.tx_res1);

        btBad = findViewById(R.id.bt_bad);
        btOk = findViewById(R.id.bt_ok);
        mask = (Mask)getIntent().getSerializableExtra("mask");
        Uri imageUri = Uri.parse(getIntent().getExtras().getString("imageUri"));
        txRes1.setText(mask.res1 +" "+ mask.res2);

        Bitmap bitmapGloabal = null;
        try {
            bitmapGloabal = new TratarImagenesGiro().handleSamplingAndRotationBitmap(getApplicationContext(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setImageBitmap(bitmapGloabal);


        btBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }

        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmResonse();
            }

        });
    }

    private void confirmResonse(){


        if (mask.res2.equals(mask.res1) ){
            GestionMask gestionMask = new GestionMask();
            gestionMask.valResponse("ok",mask.res1,mask.name);
        }
        finish();
        onBackPressed();

    }
}
