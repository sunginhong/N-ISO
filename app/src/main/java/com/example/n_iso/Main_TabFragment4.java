package com.example.n_iso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment4 extends Fragment {

    LinearLayout layout;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public Main_TabFragment4() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data_parse();
        layout = (LinearLayout) inflater.inflate(R.layout.main_tab_fragment_4, container, false);
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

                                }
                            });
                        }
                    }).start();
                } catch (Exception e) {
                }

            }
        }.start();
    }
}