package com.dream.wanandroid.model.event;

/**
 * Created by Administrator on 2018/5/2.
 */

public class CollectEvent {
    private boolean isCancelCollectSuccess;

    public CollectEvent(boolean isCancelCollectSuccess){
        this.isCancelCollectSuccess = isCancelCollectSuccess;
    }

    public boolean isCancelCollectSuccess() {
        return isCancelCollectSuccess;
    }

    public void setCancelCollectSuccess(boolean cancelCollectSuccess) {
        isCancelCollectSuccess = cancelCollectSuccess;
    }
}
