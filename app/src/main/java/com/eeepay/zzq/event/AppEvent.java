package com.eeepay.zzq.event;

/**
 * 描述：AppEvent 事件
 * 作者：zhuangzeqin
 * 时间: 2020/4/17-18:45
 * 邮箱：zzq@eeepay.cn
 * 备注:
 */
public class AppEvent {
    public AppEvent(int index, String mark) {
        this.index = index;
        this.mark = mark;
    }

    private int index ;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    private String mark;

    @Override
    public String toString() {
        return "AppEvent{" +
                "index=" + index +
                ", mark='" + mark + '\'' +
                '}';
    }
}
