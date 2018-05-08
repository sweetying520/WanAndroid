package com.dream.wanandroid.model.db;

import com.dream.wanandroid.model.dao.HistoryData;

import java.util.List;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface DbHelper {

    /**
     * 增加历史记录
     * @param data 历史记录
     */
    void addHistoryData(String data);

    /**
     * 清楚历史记录
     */
    void clearHistoryData();

    /**
     * 获取历史记录
     * @return 获取历史记录
     */
    List<HistoryData> getHistoryData();



}
