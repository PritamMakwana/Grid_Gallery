package com.wallpy.gridgallery;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdpater extends RecyclerView.Adapter<ImageAdpater.ViewHolder> {
     ArrayList<ImgStore> imgStore;
     List<String> mList;
    OnitemClcikListerner listerner;


    public ImageAdpater(List<String> mList, OnitemClcikListerner listerner) {
        this.mList = mList;
        this.listerner=listerner;
    }

    //    public ImageAdpater(ArrayList<ImgStore> imgStore, Context context) {
//        this.imgStore = imgStore;
//        this.context = context;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.imggridview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img1.setImageURI(Uri.parse(mList.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listerner.onClick(position);

            }
        });

      //  Glide.with(context).load(imgStore.get(position).getImageStore()).into(ViewHolder.img1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public static ImageView img1;
         ImageView img1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.iconiv);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    .onClick(getAdapterPosition());
//                }
//            });
        }
    }
}
