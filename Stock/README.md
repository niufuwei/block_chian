   基本的使用说明

   clone代码后，先sync，然后就可以run了`
        //        测试用的host已经设置好了
        //        http://eva7base.com:99
        //                * 首页activity
        //                * 五个标签均为fragment
        //  首页的标签页面，直接添加替换HomeActivity中即可

        //        AssetsBean 为资产的实体类
        //        LogonBean  为登录信息的实体类

  // http post示例
  void getBanner() {

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
                        EasyLog.print("接口返回 data=" + data.toString());
                        //                        Log.i(TAG,"接口返回 data="+data.toString());
                    }
                });

    }
