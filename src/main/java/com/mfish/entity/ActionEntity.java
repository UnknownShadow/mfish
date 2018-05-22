package com.mfish.entity;

/**
 * Created by juunew on 2017/6/20.
 */
public class ActionEntity {

    private int cmd;    //0. 关闭窗口 1. 跳转到网页 2. 跳转到页面  3. 领取奖励  4. 退出应用
    private int page;    // page定义:    1：消息；2：任务；3：比赛列表 4：宝箱；5：充值界面；6：好友界面
    private String url;//跳转到网页的url


    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
