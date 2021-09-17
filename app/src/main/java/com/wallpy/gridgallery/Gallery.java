package com.wallpy.gridgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity implements OnitemClcikListerner {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<String> Mlist;
    ArrayList<File> imgFile;
    ImageAdpater adpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            Log.d("ok","hide");
        }

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sta));
        }

        recyclerView = findViewById(R.id.rvgallery);
        Checkparmsions();
        Mlist=new ArrayList<>();

//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        ArrayList imgstorelist = dataOfImg();
//        ImageAdpater adpater = new ImageAdpater(imgstorelist, getApplicationContext());
//        recyclerView.setAdapter(adpater);

    }

    private void Checkparmsions() {
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
       display();

    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    display();//init the contect list
                }
                else {
                    //Parmission denied
                    Toast.makeText(this,"your message",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
  }


    private ArrayList<File> findImage(File file){

        ArrayList<File> imageList=new ArrayList<>();
        File[] imageFile=file.listFiles();

        for(File singleimg: imageFile){

            if(singleimg.isDirectory() && ! singleimg.isHidden()){
                imageList.addAll(findImage(singleimg));

            }
            else{

                if(singleimg.getName().endsWith(".jpg")||
                        singleimg.getName().endsWith(".png")||
                        singleimg.getName().endsWith(".webp") ){
                    imageList.add(singleimg);

                }
            }
        }
        return imageList;
    }

    private void display() {

        imgFile =findImage(Environment.getExternalStorageDirectory());

        for(int j=0;j<imgFile.size();j++){
            Mlist.add(String.valueOf(imgFile.get(j)));
            adpater= new ImageAdpater(Mlist,this);
            recyclerView.setAdapter(adpater);
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        }

    }
  @Override
    public void onClick(int position) {
        Toast.makeText(this,"Postin: "+ position,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Gallery.this,FullImage.class);
        intent.putExtra("image",String.valueOf(imgFile.get(position)));
        intent.putExtra("pos",position);
        intent.putExtra("imageList",imgFile);
        startActivity(intent);

    }


//    private ArrayList dataOfImg() {
//
//
//        Sting[] Img[] = {
//
//    //            "https://th.bing.com/th/id/OIP.xnDbyEGbHgqL2meRKEyOEAHaEo?pid=ImgDet&rs=1"
//      //          ,"https://th.bing.com/th/id/OIP.xnDbyEGbHgqL2meRKEyOEAHaEo?pid=ImgDet&rs=1"
//        };
//
//
//
//        ArrayList list = new ArrayList<>();
//        for (int i = 0; i < Img.length; i++) {
//            ImgStore store = new ImgStore();
//            store.setIv(Img[i]);
//            list.add(store);
//        }
//         Log.d("Gallery","List items:-"+list.size());
//        return list;
//    }

}