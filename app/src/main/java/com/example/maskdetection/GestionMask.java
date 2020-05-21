package com.example.maskdetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.maskdetection.Mediator.MediatorMask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GestionMask {
    MediatorMask mediatorMask;

    public GestionMask(MediatorMask mediatorMask) {
        this.mediatorMask = mediatorMask;
    }

    public GestionMask() {
    }

    public void setImage(Bitmap bitmap,int rot) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap image = ScaleBitmap.scale(bitmap,256,256);
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody postBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                .addFormDataPart("rot",String.valueOf(rot))
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Singleton.urlServer+"/setImage")
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                System.out.println(e);
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                String jsonData = response.body().string();
                JSONObject Jobject = null;
                System.out.println(jsonData);
                try {
                   Jobject = new JSONObject(jsonData);
                    Mask mask = new Mask(Jobject.get("res1").toString(),Jobject.get("res2").toString(),Jobject.get("name").toString());
                    mediatorMask.setMaskInfo(mask);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void valResponse(String val,String clas,String name){
        OkHttpClient client = new OkHttpClient();
        RequestBody postBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("clas",clas)
                .addFormDataPart("val",String.valueOf(val))
                .addFormDataPart("imagename",name)
                .build();

        Request request = new Request.Builder()
                .url(Singleton.urlServer+"/validate")
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                System.out.println(e);
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()


            }
        });
    }

}
