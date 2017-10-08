package cn.smf.service.impl;

import cn.smf.dao.NewsMapper;
import cn.smf.entity.News;
import cn.smf.service.INewsService;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ASUS on 2017/9/26.
 */
public class NewsServiceImpl implements INewsService {
    NewsMapper ind=null;
    public NewsServiceImpl(){
        SqlSession session = MyBatisUntil.getSession();
        ind=session.getMapper(NewsMapper.class);
    }

    //查询所有
    public List<News> getAllNews(int pageIndex,int pageSize) {
        return ind.getAllNews(pageIndex,pageSize);
    }

    //查询所有记录数
    public int getTotalCount() {
        return ind.getTotalCount();
    }


}
