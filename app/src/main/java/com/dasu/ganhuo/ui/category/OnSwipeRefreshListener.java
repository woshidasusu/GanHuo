package com.dasu.ganhuo.ui.category;

/**
 * Created by dasu on 2017/4/21.
 *
 * activity 和 fragment通信接口，即fragment通过该接口通知activity干活
 */

interface OnSwipeRefreshListener {

    void onRefreshing();

    void onRefreshFinish();

}
