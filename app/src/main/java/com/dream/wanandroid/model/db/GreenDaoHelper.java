package com.dream.wanandroid.model.db;

import com.dream.wanandroid.WanAndroidApp;
import com.dream.wanandroid.model.dao.DaoSession;
import com.dream.wanandroid.model.dao.HistoryData;
import com.dream.wanandroid.model.dao.HistoryDataDao;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/8.
 */

public class GreenDaoHelper implements DbHelper{

    private DaoSession daoSession;

    @Inject
    GreenDaoHelper(){
        daoSession = WanAndroidApp.getInstance().getDaoSession();
    }

    @Override
    public void addHistoryData(String data) {
        HistoryDataDao historyDataDao = daoSession.getHistoryDataDao();
        List<HistoryData> historyDataList = historyDataDao.loadAll();
        HistoryData historyData = new HistoryData();
        historyData.setDate(System.currentTimeMillis());
        historyData.setData(data);

        //重复搜索
        Iterator<HistoryData> iterator = historyDataList.iterator();
        while (iterator.hasNext()){
            HistoryData historyData1 = iterator.next();
            if(historyData1.getData().equals(data)){
                historyDataList.remove(historyData1);
                historyDataList.add(historyData);
                historyDataDao.deleteAll();
                historyDataDao.insertInTx(historyDataList);
                return;
            }
        }

        //只能存储10条 多余的移除最后一条留下最新的
        if(historyDataList.size() < 10){
            historyDataDao.insert(historyData);
        }else {
            historyDataList.remove(0);
            historyDataList.add(historyData);
            historyDataDao.deleteAll();
            historyDataDao.insertInTx(historyDataList);
        }
    }

    @Override
    public void clearHistoryData() {
        HistoryDataDao historyDataDao = daoSession.getHistoryDataDao();
        historyDataDao.deleteAll();
    }

    @Override
    public List<HistoryData> getHistoryData() {
        HistoryDataDao historyDataDao = daoSession.getHistoryDataDao();
        return historyDataDao.loadAll();
    }
}
