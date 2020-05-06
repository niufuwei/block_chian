package com.hjq.demo.ui.activity;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.base.BaseFragmentAdapter;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.helper.ActivityStackManager;
import com.hjq.demo.helper.DoubleClickHelper;
import com.hjq.demo.other.KeyboardWatcher;
import com.hjq.demo.ui.fragment.HomeFragment;
import com.hjq.demo.ui.fragment.TestFragmentA;
import com.hjq.demo.ui.fragment.TestFragmentD;
import com.hjq.demo.ui.fragment.contract.ContractFragment;
import com.hjq.demo.ui.fragment.otc.OTCFragment;

import butterknife.BindView;

/**
 * 首页activity
 * 五个标签均为fragment
 */
public final class HomeActivity extends MyActivity
        implements KeyboardWatcher.SoftKeyboardStateListener,
        BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        KeyboardWatcher.with(this)
                .setListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(OTCFragment.newInstance()); //TestFragmentB.newInstance()
        mPagerAdapter.addFragment(ContractFragment.newInstance());//TestFragmentC.newInstance()
        mPagerAdapter.addFragment(TestFragmentA.newInstance());
        mPagerAdapter.addFragment(TestFragmentD.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    /**
     * {@link BottomNavigationView.OnNavigationItemSelectedListener}
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mPagerAdapter.setCurrentItem(HomeFragment.class);
//                mPagerAdapter.setCurrentItem(TestFragmentA.class);
                return true;
            case R.id.home_found:
                mPagerAdapter.setCurrentItem(OTCFragment.class); // TestFragmentB.class
                return true;
            case R.id.home_message:
                mPagerAdapter.setCurrentItem(ContractFragment.class);
                return true;
            case R.id.home_money:
                mPagerAdapter.setCurrentItem(TestFragmentA.class);
                return true;
            case R.id.home_me:
                mPagerAdapter.setCurrentItem(TestFragmentD.class);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */
    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 回调当前 Fragment 的 onKeyDown 方法
        if (mPagerAdapter.getCurrentFragment().onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}