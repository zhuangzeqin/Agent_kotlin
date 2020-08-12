package com.eeepay.zzq.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * 描述：防止短时间内重复点击监听
 * 作者：zhuangzeqin
 * 时间：2020/3/31 17:12
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public abstract class NoRepeatItemListener implements AdapterView.OnItemClickListener {
    private long lastClickTime;
    private long timeInterval = 1000L;

    public NoRepeatItemListener() {
    }

    public NoRepeatItemListener(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > timeInterval) {//两次点击间隔大于1s
            lastClickTime = currentTime;
            onSingleItemClickClick(parent, view, position, id);
        }
    }

    protected abstract void onSingleItemClickClick(AdapterView<?> parent, View view, int position, long id);
}
