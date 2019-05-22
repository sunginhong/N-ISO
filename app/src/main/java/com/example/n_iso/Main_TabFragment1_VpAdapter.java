package com.example.n_iso;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment1_VpAdapter extends PagerAdapter implements View.OnClickListener {
    List<String> items = new ArrayList<String>();
    Context context;
    List<Main_TabFragment1_DataVp> list = new ArrayList<>();

    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static final int MAX_POOL_SIZE = 10;


    public Main_TabFragment1_VpAdapter(Context context, List<Main_TabFragment1_DataVp> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mMyViewPool = new Pools.SynchronizedPool< >(MAX_POOL_SIZE);
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public Fragment getItem(int position) { // (3)
        return Main_TabFragment1_VpFragment.newInstance("" + position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public float getPageWidth(int i) {
        return 1.0f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        View view = null;
        view = mInflater.inflate(R.layout.main_tab_fragment_1_item_pager, null);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ((ViewPager)container).addView(view, i);
        ((ViewPager)container).addView(view);

        RelativeLayout main_tab_fragment_1_item_pager_item = view.findViewById(R.id.main_tab_fragment_1_item_pager_item);
        main_tab_fragment_1_item_pager_item.setId(i);
        main_tab_fragment_1_item_pager_item.setOnClickListener(this);

        TextView main_tab_fragment_1_item_pager_titleView = view.findViewById(R.id.main_tab_fragment_1_item_pager_titleView);
        main_tab_fragment_1_item_pager_titleView.setText(list.get(i).getTitle());

        TextView main_tab_fragment_1_item_pager_category = view.findViewById(R.id.main_tab_fragment_1_item_pager_description);
        main_tab_fragment_1_item_pager_category.setText(list.get(i).getDescription());

        ImageView main_tab_fragment_1_item_pager_imgView = view.findViewById(R.id.main_tab_fragment_1_item_pager_imgView);
        Glide.with(context)
                .load(list.get(i).getImage())
                .into(main_tab_fragment_1_item_pager_imgView);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager)container).removeView((ViewGroup)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void onClick(View view) {
        View view_d = view;
        int selectIndex = view_d.getId();
        Log.d("sssssss", "sss"+list.get(selectIndex).getLink());
    }

}