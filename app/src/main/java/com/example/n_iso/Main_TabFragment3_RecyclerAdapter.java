package com.example.n_iso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment3_RecyclerAdapter extends RecyclerView.Adapter<Main_TabFragment3_RecyclerAdapter.MyHolder>  implements Filterable, View.OnClickListener {

    Context context;
    List<Main_TabFragment3_DataRecycle> list = new ArrayList<>();
    List<Main_TabFragment3_DataRecycle> list1 = new ArrayList<>();

    public Main_TabFragment3_RecyclerAdapter(Context context, List<Main_TabFragment3_DataRecycle> list) {
        this.context = context;
        this.list = list;
        this.list1 = list;
    }

    @NonNull
    @Override
    public Main_TabFragment3_RecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_tab_fragment_3_item_row,viewGroup,false);
        view.setId(i);
        view.setOnClickListener(this);

        return new Main_TabFragment3_RecyclerAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Main_TabFragment3_RecyclerAdapter.MyHolder myHolder, int i) {
        myHolder.setId(i);
        myHolder.titleTextView.setText(list.get(i).getTitle());
        myHolder.subtTextView.setText(list.get(i).getSubtitle());

        Glide.with(context).clear(myHolder.imageView);
        Glide.with(context)
//                .asGif()
                .load(list.get(i).getImage())
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(myHolder.imageView);

        myHolder.categoryTextView.setText(list.get(i).getCategory());
        myHolder.synopsisTextView.setText(list.get(i).getSynopsis());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        View view_d = view;
        int selectIndex = view_d.getId();

        Intent intent_motion = new Intent(view.getContext(), Main_TabFragment3_DetailView.class);
        intent_motion.putExtra("URL" , list.get(selectIndex).getUrl());
        intent_motion.putExtra("TITLE" , list.get(selectIndex).getTitle());
        view.getContext().startActivity(intent_motion);

        ((Activity) context).overridePendingTransition(R.anim.activity_slide_in_100p_forward, R.anim.activity_slide_out_50p_forward);
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView subtTextView;
        TextView categoryTextView;
        TextView synopsisTextView;
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.main_tab_fragment_3_item_row_textView);
            subtTextView = itemView.findViewById(R.id.main_tab_fragment_3_item_row_subTextView);
            categoryTextView = itemView.findViewById(R.id.main_tab_fragment_3_item_row_category);
            synopsisTextView = itemView.findViewById(R.id.main_tab_fragment_3_item_row_synopsis);
            imageView = itemView.findViewById(R.id.main_tab_fragment_3_item_row_imgView);
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
                    List<Main_TabFragment3_DataRecycle> filterList = new ArrayList<>();

                    for (Main_TabFragment3_DataRecycle data : list1){
                        // 타이틀 조회
                        if (data.getTitle().toLowerCase().contains(charString)){
                            filterList.add(data); }
                        // 서브타이틀 조회
                        if (data.getSubtitle().toLowerCase().contains(charString)){
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
                list = (List<Main_TabFragment3_DataRecycle>) results.values;
                notifyDataSetChanged();
            }
        };

    }

}