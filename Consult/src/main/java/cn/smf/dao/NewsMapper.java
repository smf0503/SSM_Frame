package cn.smf.dao;

import cn.smf.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    //查询所有
    List<News> getAllNews(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    //查询所有记录数
    int getTotalCount();
}