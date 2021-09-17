package com.wallpy.gridgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class FullImage extends AppCompatActivity {
    int position;
    ImageView left,right,fullview;
    ArrayList<File> myImage;
    String imglink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sta));
        }

        left=findViewById(R.id.left_iv);
        right=findViewById(R.id.right_iv);
        fullview=findViewById(R.id.full_image_iv);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            myImage=(ArrayList)bundle.getParcelableArrayList("imageList");
            position=bundle.getInt("pos",0);
            imglink=bundle.getString("image");
            Log.d("ok","full");

        }
        fullview.setImageURI(Uri.parse(imglink));

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=((position -1 )<0)? (myImage.size()-1) :(position-1);
                fullview.setImageURI(Uri.parse(myImage.get(position).toString()));
                Log.d("ok","left");

            }
        });


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = ((position + 1) % myImage.size());
                fullview.setImageURI(Uri.parse(myImage.get(position).toString()));
                Log.d("ok","right");

            }
        });



    }
}