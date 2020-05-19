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

    Button btReintentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_detection);
        imgView = findViewById(R.id.img_per);
        txRes1 = findViewById(R.id.tx_res1);

        btReintentar = findViewById(R.id.bt_reintentar);
        String res = (String)getIntent().getStringExtra("res");
        Uri imageUri = Uri.parse(getIntent().getExtras().getString("imageUri"));
        txRes1.setText(res);

        Bitmap bitmapGloabal = null;
        try {
            bitmapGloabal = new TratarImagenesGiro().handleSamplingAndRotationBitmap(getApplicationContext(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgView.setImageBitmap(bitmapGloabal);


        btReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }

        });
    }
}
