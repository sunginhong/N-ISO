package com.example.n_iso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment2 extends Fragment {

    LinearLayout layout;
    Main_TabFragment2_RecyclerAdapter Main_TabFragment2_RecyclerAdapter;
    List<Main_TabFragment2_DataRecycle> recyclelist = new ArrayList<>();

    RecyclerView main_tab_fragment_2_recycler;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public Main_TabFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data_parse();
        layout = (LinearLayout) inflater.inflate(R.layout.main_tab_fragment_2, container, false);
        return layout;
    }

    private void data_parse(){
        new Thread() {
            @Override
            public void run() {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            (getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ///// 네이버 디자인 블로그 View
                                    int n = -1;
                                    while(n < MainActivity_Splash.mainList_n-1) {
                                        n = n + 1;
                                        String str = MainActivity_Splash.mainListCategoryList.get(n);
                                        String[] array = str.split(",");

                                        for(int k = 0; k < array.length; k++) {
                                            if (array[k].equals(" Interaction")){
                                                ///// Interaction
                                                recyclelist.add(new Main_TabFragment2_DataRecycle(MainActivity_Splash.mainListTitle.get(n), MainActivity_Splash.mainListSubTitle.get(n), MainActivity_Splash.mainListThumb.get(n), MainActivity_Splash.mainListCategoryList.get(n), MainActivity_Splash.mainListUrl.get(n)));
                                            }
                                        }
                                    }
                                    main_tab_fragment_2_recycler = layout.findViewById(R.id.main_tab_fragment_2_recycler);
                                    main_tab_fragment_2_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                                    Main_TabFragment2_RecyclerAdapter = new Main_TabFragment2_RecyclerAdapter(getContext(),recyclelist);
                                    main_tab_fragment_2_recycler.setAdapter(Main_TabFragment2_RecyclerAdapter);
                                }
                            });
                        }
                    }).start();
                } catch (Exception e) {
                }

            }
        }.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("타이틀을 입력해주세요");
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (Main_TabFragment2_RecyclerAdapter != null){
                    Main_TabFragment2_RecyclerAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return;
    }
}