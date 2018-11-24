package com.wukj.general.library.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.wukj.general.library.R;
import com.wukj.general.library.fragment.ArticleFragment;
import com.wukj.general.library.fragment.CategoryFragment;
import com.wukj.general.library.fragment.HomeFragment;
import com.wukj.general.library.fragment.MineFragment;

public class MainActivity extends AppCompatActivity {


    private ViewPager mVP;
    private PAdapter mPAdapter;

    private BottomNavigationBar mBottomNavigationBar;
    private TextBadgeItem mTextBadgeItem;
    private ShapeBadgeItem mShapeBadgeItem;

    private Fragment[] fs = {new HomeFragment()
            , new CategoryFragment()
            , new ArticleFragment()
            , new MineFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVP = this.findViewById(R.id.vp);
        mBottomNavigationBar = this.findViewById(R.id.bottom_navigation_bar);

        mPAdapter = new PAdapter(getSupportFragmentManager());
        mVP.setAdapter(mPAdapter);

        mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setAnimationDuration(200)
                .setText("3")
                .setHideOnSelect(false);

        mShapeBadgeItem = new ShapeBadgeItem()
                .setShapeColorResource(R.color.colorPrimary)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomNavigationBar
                .setActiveColor(R.color.red)//选中颜色 图标和文字
                .setInActiveColor("#8e8e8e")//默认未选择颜色
                .setBarBackgroundColor(R.color.white);//默认背景色

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.icon_one, "首页")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_one_normal))
                        .setBadgeItem(mShapeBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.icon_two, "分类")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_two_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.icon_three, "文章")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_three_normal))
                        .setBadgeItem(mTextBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.icon_four, "我的")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_four_normal)))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mVP.setCurrentItem(position);
            }
        });
        mVP.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }
        });

    }

    private class PAdapter extends FragmentPagerAdapter {

        public PAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fs[position];
        }

        @Override
        public int getCount() {
            return fs.length;
        }

    }


}
