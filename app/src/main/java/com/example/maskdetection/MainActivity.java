package com.example.maskdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maskdetection.Mediator.MediatorMask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btCamaraMascara;
    Uri imageUri = null;
    String currentPhotoPath = null;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCamaraMascara = findViewById(R.id.bt_camera_mask);

        btCamaraMascara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);

                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    File imageFile = getImageFile();

                    if(imageFile!=null){
                        imageUri = FileProvider.getUriForFile(getApplicationContext(),"com.example.maskdetection.fileprovider",imageFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
                    }
                }

            }
        });
    }
    private File getImageFile(){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timestamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile("image",".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();

        }
        currentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);

                int rot = new TratarImagenesGiro().getRotarionrotateIfRequired(getApplicationContext(),bitmap,imageUri);
                MediatorMask mediatorMask = new MediatorMask(bitmap,this, imageUri,rot);
                mediatorMask.notificar("maskdetect");
            } catch (IOException e) {
                e.printStackTrace();
            }



            //

        }
    }

    }
}