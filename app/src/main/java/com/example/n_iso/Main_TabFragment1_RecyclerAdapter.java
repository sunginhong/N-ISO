package com.example.n_iso;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.squareup.picasso.Picasso;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment1_RecyclerAdapter extends RecyclerView.Adapter<Main_TabFragment1_RecyclerAdapter.MyHolder>  implements Filterable ,View.OnClickListener {

    Context context;
    List<Main_TabFragment1_DataRecycle> list = new ArrayList<>();
    List<Main_TabFragment1_DataRecycle> list1 = new ArrayList<>();

    public Main_TabFragment1_RecyclerAdapter(Context context, List<Main_TabFragment1_DataRecycle> list) {
        this.context = context;
        this.list = list;
        this.list1 = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_tab_fragment_1_item_row,viewGroup,false);
        view.setId(i);
        view.setOnClickListener(this);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.setId(i);
        myHolder.linearlayout.setId(i);

        String categoryStr = list.get(i).getCategory().trim();
        String[] strArray = categoryStr.split(" ");
        StringBuilder categoryStr_builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            categoryStr_builder.append(cap + " ");
        }
        myHolder.categoryTextView.setText(categoryStr_builder.toString());

        Glide.with(context).clear(myHolder.imageView);
        Glide.with(context)
                .load(list.get(i).getImage())
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myHolder.imageView);


        myHolder.imageViewContain.setId(i);
        myHolder.titleTextView.setText(list.get(i).getTitle());
        myHolder.subtTextView.setText(list.get(i).getSubtitle());

        int row_top_margin = (int)context.getResources().getDimension(R.dimen.main_card_image_margin_left);

        //// 카드이미지포지션 정렬
        if(i%2 == 1) {
            if (i == myHolder.imageViewContain.getId()){
                myHolder.imageViewContain.setX(-row_top_margin);
            }
        } else {
            if (i == myHolder.imageViewContain.getId()){
                myHolder.imageViewContain.setX(0);
            }
        }
        /// 카테고리 컬러
//        System.out.println(list.get(i).getCategory());
        switch (list.get(i).getCategory().trim()) {
            case "Interaction":
                myHolder.categoryTextView.setTextColor(context.getResources().getColor(R.color.travel));
                break;
            case "motion":
                myHolder.categoryTextView.setTextColor(context.getResources().getColor(R.color.lifestyle));
                break;
            case "logo":
                myHolder.categoryTextView.setTextColor(context.getResources().getColor(R.color.food));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        View view_d = view;
        int selectIndex = view_d.getId();
        Log.d("sssssss", "sss"+list.get(selectIndex).getUrl());
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        LinearLayout linearlayout;
        TextView categoryTextView;
        FrameLayout imageViewContain;
        ImageView imageView;
        TextView titleTextView;
        TextView subtTextView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            linearlayout = itemView.findViewById(R.id.main_tab_fragment_1_item_row_ll);
            categoryTextView = itemView.findViewById(R.id.main_tab_fragment_1_item_row_category);
            imageViewContain = itemView.findViewById(R.id.main_tab_fragment_1_item_row_imgView_contain);
            imageView = itemView.findViewById(R.id.main_tab_fragment_1_item_row_imgView);
            titleTextView = itemView.findViewById(R.id.main_tab_fragment_1_item_row_textView);
            subtTextView = itemView.findViewById(R.id.main_tab_fragment_1_item_row_subTextView);
        }

        public void setId(int i) {
            itemView.setId(i);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()){
                    list = list1;
                }else{
                    List<Main_TabFragment1_DataRecycle> filterList = new ArrayList<>();

                    for (Main_TabFragment1_DataRecycle data : list1){
                        // 타이틀 조회
                        if (data.getTitle().toLowerCase().contains(charString)){
                            filterList.add(data); }
                        // 카테고리 조회
                        if (data.getCategory().toLowerCase().contains(charString)){
                            filterList.add(data); }
                    }
                    list = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Main_TabFragment1_DataRecycle>) results.values;
                notifyDataSetChanged();
            }
        };

    }


}