package com.hjq.demo.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 修改密码
 */
public class BannerApi implements IRequestApi {

    @Override
    public String getApi() {
        return "coin/banner";
    }

}