package com.srsakib.fdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {
    Button mCameraBtn;
    Button mFileBtn;
    public static int IMAGE_PICK_CODE=1000;
    public static final int PERMISSION_CODE=1001;
    public static int CAPTURE_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connector
        mCameraBtn = findViewById(R.id.camera_btn);
        mFileBtn = findViewById(R.id.file_btn);

        //Camera Button

        mCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();

            }
        });

        //File Button

        mFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //test
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){

                    }
                    //permission  already granted
                    else {
                        pickImageFromGallery();
                    }

                }
                //system OS bew of Android 6.0
                pickImageFromGallery();
            }
        });



    }

          //img function

    private void captureImage(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAPTURE_IMAGE);
    }


        //file function

    private void pickImageFromGallery() {
        //pick img
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE: {
                if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){


                    pickImageFromGallery();
                }
                //permission was denied
                else{

                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    // handle result of pic img


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK){
            if (requestCode == CAPTURE_IMAGE) {
                if(data!=null) {

                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                      Intent intentCamera = new Intent(MainActivity.this, ShowImage.class);
                     intentCamera.putExtra("BitmapImage", bitmap);
                    startActivity(intentCamera);


                   /* ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();


                    Intent in1 = new Intent(MainActivity.this, ShowImage.class);
                    in1.putExtra("imageCamera", byteArray);
                    startActivity(in1);*/

                    try {
                    } catch (ActivityNotFoundException anfe) {
                        String errorMessage = "Your device doesn't support the crop section";
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }


            } else if (requestCode == IMAGE_PICK_CODE) {
                if(data!=null) {
                    //set img to show_img
                    Uri uri = data.getData();
                    Intent intentImg = new Intent(MainActivity.this, ShowImage.class);
                    intentImg.putExtra("imageUri", uri.toString());
                    startActivity(intentImg);

                }

            }

        }
    }
    }









