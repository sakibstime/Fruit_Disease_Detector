package com.srsakib.fdd;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ShowImage extends AppCompatActivity {

    ImageView mPickerImage;
    Button mPickerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);

        // initialize resources
        mPickerImage = findViewById(R.id.show_img);
        mPickerBtn = findViewById(R.id.result_btn);

        // button setup
        mPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }


        });



            //for file
            Intent myIntent = getIntent();
            String image_path = myIntent.getStringExtra("imageUri");
            if(image_path!=null) {
                Uri myUri = Uri.parse(image_path);
                mPickerImage.setImageURI(myUri);
            }




        /*Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");*/

        /* Getting ImageBitmap from Camera from Main Activity */

       Intent intent_camera = getIntent();
      Bitmap camera_img_bitmap = (Bitmap) intent_camera
                .getParcelableExtra("BitmapImage");
        if (camera_img_bitmap != null) {
            mPickerImage.setImageBitmap(camera_img_bitmap);
        }


       /* byte[] byteArray = getIntent().getByteArrayExtra("imageCamera");
        Bitmap camera_img_bitmap = BitmapFactory.decodeByteArray(byteArray, 1, byteArray.length);
        if (camera_img_bitmap != null) {
           mPickerImage.setImageBitmap(camera_img_bitmap);
        }*/



    }


}
