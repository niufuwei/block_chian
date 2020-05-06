package com.hjq.demo.http.response;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2019/12/07
 * desc   : 登录返回
 */
public class LoginBean {
    /**
     * id : 35
     * phone : 18210672821
     * email :
     * updateDate : 0001-01-01T00:00:00Z
     * createDate : 0001-01-01T00:00:00Z
     * loginDate : 0001-01-01T00:00:00Z
     * wallet : {"omniAddr":"1PoprcanofjwtYFX4cYe7Jk1u1E6kA52fh","ercAddr":"0x34Aacf4047B7B1254527928dab430F3Ce34e715d"}
     * token : 03e76bc864e036abe6c0b5fde3ea857579594c580c3f76db97786c05f03ccb7fa7765ad85eadf3a4dedef1840bce52031a7e9d71
     * veried : false
     * fingerPrint : 0x8867f1f4346233e159dce7df8a9154e868528475776d16ad29ebc13fda8ee5bc5ae1d350a3e5c085d4edb8d4a09936cef85642d96bd3d74ecb03eebfecb67fbff4fc6099ee70aa25f860a0146a67d35f114330f4234e3723778607933f72d214bf9ba40561e7d6c2aac0f96a285fc4f801a28e1232def369d77b43201f3f58652e
     */
    private int id;
    private String phone;
    private String email;
    private String updateDate;
    private String createDate;
    private String loginDate;
    private WalletBean wallet;
    private String token;
    private boolean veried;
    private String fingerPrint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public WalletBean getWallet() {
        return wallet;
    }

    public void setWallet(WalletBean wallet) {
        this.wallet = wallet;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVeried() {
        return veried;
    }

    public void setVeried(boolean veried) {
        this.veried = veried;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public static class WalletBean {
        /**
         * omniAddr : 1PoprcanofjwtYFX4cYe7Jk1u1E6kA52fh
         * ercAddr : 0x34Aacf4047B7B1254527928dab430F3Ce34e715d
         */

        private String omniAddr;
        private String ercAddr;

        public String getOmniAddr() {
            return omniAddr;
        }

        public void setOmniAddr(String omniAddr) {
            this.omniAddr = omniAddr;
        }

        public String getErcAddr() {
            return ercAddr;
        }

        public void setErcAddr(String ercAddr) {
            this.ercAddr = ercAddr;
        }
    }
}