package com.dream.wanandroid.model.bean.main.search;

import com.dream.wanandroid.model.bean.main.collect.FeedArticleData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class SearchData implements Serializable{

    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"JerryChan123","chapterId":314,"chapterName":"RV列表动效","collect":false,"courseId":13,"desc":"RecyclerView 是最佳选择！RecyclerView Item滑动特效 仿探探。\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/ff3f0ed6-f0c4-49fa-a578-16b7e6eceb76.png","fresh":false,"id":2409,"link":"http://www.wanandroid.com/blog/show/2050","niceDate":"2018-02-25","origin":"","projectLink":"https://github.com/JerryChan123/ReSwipeCard/","publishTime":1519571006000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=314"}],"title":"RecyclerView Item滑动特效 仿探探","type":0,"visible":0,"zan":0},{"apkLink":"","author":"yangwen123","chapterId":173,"chapterName":"Choregrapher","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1004,"link":"http://blog.csdn.net/yangwen123/article/details/39518923","niceDate":"2016-09-18","origin":"CSDN","projectLink":"","publishTime":1474191342000,"superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android系统Choreographer机制实现过程","type":0,"visible":1,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 0
     * size : 20
     * total : 0
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<FeedArticleData> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FeedArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<FeedArticleData> datas) {
        this.datas = datas;
    }
}
