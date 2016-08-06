package com.example.hanshu.swipetab;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
    ViewPager viewPager;
    DemoSwipe demoSwipe;
    Button intentButon,externalB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoSwipe=new DemoSwipe(getSupportFragmentManager());
        final ActionBar actionBar=getActionBar();
      actionBar.setHomeButtonEnabled(false);
      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        viewPager= (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(demoSwipe);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i=0;i<demoSwipe.getCount();i++){
            ActionBar.Tab bar=actionBar.newTab();
            bar.setText(demoSwipe.getPageTitle(i));
            bar.setTabListener(this);
            actionBar.addTab(bar);
        }


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
    public static class DemoSwipe extends FragmentPagerAdapter {

        public DemoSwipe(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:

                    return new SectionFragment01();
                default:
                    Fragment fragment=new SectionFragment02();
                    Bundle bundle=new Bundle();
                    bundle.putInt(SectionFragment02.NUMBER,position+1);
                    fragment.setArguments(bundle);
                    return fragment;
            }

        }

    public static class SectionFragment01 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_layout_1,container,false);
            view.findViewById(R.id.intent)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), ButtonMainActivity.class);
                            startActivity(intent);
                        }
                    });

            return view;
        }

    }
    public static class SectionFragment02 extends Fragment {
        public static final String NUMBER="sectionNumber";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_layout_2,container,false);
            Bundle bundle=getArguments();
           // Log.i("", "onCreateView: "+getString();
            ((TextView)view.findViewById(R.id.tv)).setText(Integer.toString(bundle.getInt(SectionFragment02.NUMBER)));
            return view;
        }

    }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "fragment"+position+1;
        }
    }
}
