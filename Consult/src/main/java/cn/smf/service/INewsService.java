package cn.smf.service;

import cn.smf.entity.News;

import java.util.List;

/**
 * Created by ASUS on 2017/9/26.
 */
public interface INewsService {
    //查询所有
    List<News> getAllNews(int pageIndex,int pageSize);

    //查询所有记录数
    int getTotalCount();

}
