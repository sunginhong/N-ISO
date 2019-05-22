package com.example.n_iso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main_TabFragment1_VpFragment extends Fragment {
    private static final String ARG_COUNT = "count";
    private static final String ARG_PARAM2 = "param2";

    private String mCount;


    public Main_TabFragment1_VpFragment() {
        // Required empty public constructor
    }

    public static Main_TabFragment1_VpFragment newInstance(String param1) {
        Main_TabFragment1_VpFragment fragment = new Main_TabFragment1_VpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCount = getArguments().getString(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_fragment_1_item_pager, container, false);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getActivity().setTitle("count : " + mCount);
        }
    }

}