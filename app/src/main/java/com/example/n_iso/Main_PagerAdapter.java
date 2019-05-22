package com.example.n_iso;

import android.support.v4.app.FragmentStatePagerAdapter;

public class Main_PagerAdapter extends FragmentStatePagerAdapter {

    static int pageCount = 4;

    public Main_PagerAdapter(android.support.v4.app.FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new Main_TabFragment1();
            case 1:
                return new Main_TabFragment2();
            case 2:
                return new Main_TabFragment3();
            case 3:
                return new Main_TabFragment4();
            default:
                return null;
        }
    }
    @Override
    public int getCount()
    {
        return pageCount;
    }
}
