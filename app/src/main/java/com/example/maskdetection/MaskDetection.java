package com.example.maskdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MaskDetection extends AppCompatActivity {
    public ImageView imgView;

    public TextView txRes1;
    public TextView txAcc1;
    public TextView txRes2;
    public TextView txAcc2;
    Button btReintentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_detection);
        imgView = findViewById(R.id.img_per);
        txRes1 = findViewById(R.id.tx_res1);
        txAcc1 = findViewById(R.id.tx_acc1);
        txRes2 = findViewById(R.id.tx_res2);
        txAcc2 = findViewById(R.id.tx_acc2);
        btReintentar = findViewById(R.id.bt_reintentar);
        String res1 = (String)getIntent().getStringExtra("res1");
        String acc1 = (String)getIntent().getStringExtra("acc1");
        String res2 = (String)getIntent().getStringExtra("res2");
        String acc2 = (String)getIntent().getStringExtra("acc2");
        String path = (String) getIntent().getStringExtra("path");
        txRes1.setText(res1);
        txAcc1.setText(acc1);
        txRes2.setText(res2);
        txAcc2.setText(acc2);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();

        matrix.postRotate(90);
        Bitmap rotatedBirmap= Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Bitmap scaledBitmap = ScaleBitmap.scale(rotatedBirmap , 600, 600);
        imgView.setImageBitmap(scaledBitmap);


        btReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }

        });
    }
}
