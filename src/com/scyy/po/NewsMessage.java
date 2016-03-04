package com.scyy.po;

import  java.util.List;
/**
 * Created by LYH on 2016/2/26.
 * 说明:图文消息实体类
 */
public class NewsMessage extends  BaseMessage{

    private int ArticleCount;
    private List<NewsItem> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<NewsItem> getArticles() {
        return Articles;
    }

    public void setArticles(List<NewsItem> articles) {
        Articles = articles;
    }
}
