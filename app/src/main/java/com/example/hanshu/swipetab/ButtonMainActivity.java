package com.example.hanshu.swipetab;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ButtonMainActivity extends FragmentActivity {
    ViewPager vp;
    ButtonFragment buttonFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
        ButtonFragment buttonFragment=new ButtonFragment(getSupportFragmentManager());
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        vp= (ViewPager) findViewById(R.id.viewPage);
        vp.setAdapter(buttonFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {

                    TaskStackBuilder.from(this)
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {

                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }




        return super.onOptionsItemSelected(item);
    }

    public static class ButtonFragment extends FragmentPagerAdapter {
        public ButtonFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ButtonFrag();
            Bundle args = new Bundle();
            args.putInt(ButtonFrag.OBJECT, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "object"+position;
        }
    }
    public static class ButtonFrag extends Fragment{
        public static final String OBJECT="object";
        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container,
                                 Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.b_fra_layout,container,false);
            Bundle bundle=getArguments();

            ((TextView) view.findViewById(R.id.textView)).setText(Integer.toString
                    (bundle.getInt(OBJECT)));
            return view;
        }
    }
}
