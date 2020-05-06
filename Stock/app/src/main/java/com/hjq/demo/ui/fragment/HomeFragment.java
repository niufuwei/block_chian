package com.hjq.demo.ui.fragment;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hjq.demo.R;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.http.glide.GlideApp;
import com.hjq.demo.http.request.BannerApi;
import com.hjq.demo.http.response.BannerBean;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.http.EasyHttp;
import com.hjq.http.EasyLog;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.toast.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import butterknife.BindView;

/**
 * by lk
 */
public final class HomeFragment extends MyFragment<HomeActivity> {

    private static final String TAG = "HomeFragment";
    //    @BindView(R.id.t_test_title)
    //    Toolbar mToolbar;
    //
    //    @BindView(R.id.tv_test_address)
    //    TextView mAddressView;
    //    @BindView(R.id.tv_test_hint)
    //    TextView mHintView;
    //    @BindView(R.id.iv_test_search)
    //    ImageView mSearchView;

    @BindView(R.id.banner)
    Banner banner;
    BannerBean bannerBean;
    ImageAdapter imageAdapter;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        //        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);

        initBanner();

    }

    void initBanner() {
        imageAdapter =new ImageAdapter(bannerBean.getData());
        banner.setAdapter(imageAdapter)
                .setIndicator(new CircleIndicator(getAttachActivity()))
                .start();
    }

    /**
     * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
     */
    public class ImageAdapter extends BannerAdapter<BannerBean.DataBean, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<BannerBean.DataBean> mDatas) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(mDatas);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new BannerViewHolder(imageView);
        }

        @Override
        public void onBindView(BannerViewHolder holder, BannerBean.DataBean data, int position, int size) {
            //            holder.imageView.setImageResource(data.imageRes);
            GlideApp.with(getAttachActivity())
                    .load(data.getImg_app())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .centerCrop()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                            Log.i(TAG, "图片加载失败 ");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                            Log.i(TAG, "图片加载完成: ");
                            return false;
                        }
                    })
                    .into(holder.imageView);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public BannerViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }

    @Override
    protected void initData() {
        ToastUtils.show("欢迎来到Stock中心");

        getBanner();
    }

    void getBanner() {
        //        测试用的host已经设置好了
        //        http://eva7base.com:99
        //                * 首页activity
        //                * 五个标签均为fragment

        //        AssetsBean 为资产的实体类
        //        LogonBean  为登录信息的实体类
        // BannerApi 中设置url及参数
        // BannerBean泛型设置解析bean类型
        // onSucceed(BannerBean data)回调 返回正确的数据
        // onFail为请求失败的回调

        EasyHttp.post(this)
                .api(new BannerApi())
                .request(new HttpCallback<BannerBean>(new OnHttpListener() {
                    @Override
                    public void onSucceed(Object result) {
                        EasyLog.print(result + "");
                    }

                    @Override
                    public void onFail(Exception e) {
                        // 失败的回调
                        EasyLog.print(e.toString());
                    }
                }) {
                    @Override
                    public void onSucceed(BannerBean data) {
                        // 成功解析的回调
                        EasyLog.print("接口返回 banner data=" + data.toString());
                        //                        Log.i(TAG,"接口返回 data="+data.toString());
                        bannerBean = data;
                        freshBanner();
                    }
                });

    }

    private void freshBanner() {
        imageAdapter.setDatas(bannerBean.getData());
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public void onStart() {
        super.onStart();

        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }
}