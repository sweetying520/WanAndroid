package com.dream.wanandroid.model.bean.navigation;

import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class NavigationData implements Serializable{

    /**
     * articles : [{"apkLink":"","author":"小编","chapterId":359,"chapterName":"应用发布","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2873,"link":"http://dev.360.cn/","niceDate":"2018-04-25","origin":"","projectLink":"","publishTime":1524666305000,"superChapterId":0,"superChapterName":"","tags":[],"title":"360开发者","type":0,"visible":0,"zan":0}]
     * cid : 359
     * name : 应用发布
     */

    private int cid;
    private String name;
    private List<FeedArticleData> articles;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FeedArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<FeedArticleData> articles) {
        this.articles = articles;
    }
}
