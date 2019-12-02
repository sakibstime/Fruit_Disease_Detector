package com.srsakib.fdd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends AppCompatActivity {


    Button mCaptureImgBtn;
    ImageView mCapturedImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //connection
        mCaptureImgBtn = (Button)findViewById(R.id.capture_btn);
        mCapturedImg = (ImageView)findViewById(R.id.capured_img);


        mCaptureImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap =(Bitmap) data.getExtras().get("data");

        mCapturedImg.setImageBitmap(bitmap);

        try {
        }
        catch (ActivityNotFoundException anfe){
            String errorMessage= "Your device doesn't support the crop section";
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
        }



    }



}
