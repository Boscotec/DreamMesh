package com.boscotec.deezerm;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicTabPager extends FragmentStatePagerAdapter {
   private int[] imageResId = {R.drawable.ic_home_24dp, R.drawable.ic_person_24dp, R.drawable.ic_circle_24dp, R.drawable.ic_search_24dp};
   private String tabTitle[] = new String[] {"HOME", "MY MUSIC", "FLOW", "SEARCH"};
    private Context context;

   MusicTabPager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
   }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return HomeFragment.newInstance();
            case 1: return PageFragment.newInstance(position);
            case 2: return FlowFragment.newInstance();
            case 3: return PageFragment.newInstance(position);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return null;
    }

    public View getTabView(int position){
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ImageView icon = v.findViewById(R.id.icon);
        icon.setImageResource(imageResId[position]);
        TextView tv = v.findViewById(R.id.tv);
        tv.setText(tabTitle[position]);
        return v;
    }
}