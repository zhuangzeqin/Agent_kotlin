package com.eeepay.zzq.listener;

import android.view.View;

/**
 * 描述：防止短时间内重复点击监听
 * 作者：ChinzLee
 * 时间：2020/3/31 17:12
 * 邮箱：ljq@eeepay.cn
 * 备注:
 */
public abstract class NoRepeatClickListener implements View.OnClickListener {
    private long lastClickTime;
    private long timeInterval = 1000L;

    public NoRepeatClickListener() {
    }

    public NoRepeatClickListener(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > timeInterval) {//两次点击间隔大于1s
            lastClickTime = currentTime;
            onSingleClick(view);
        }
    }

    protected abstract void onSingleClick(View view);
}
