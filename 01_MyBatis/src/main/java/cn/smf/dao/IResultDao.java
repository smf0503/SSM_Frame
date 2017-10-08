package cn.smf.dao;

import cn.smf.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/9/14.
 * 成绩接口
 */
public interface IResultDao {
    //查询所有
    public List<Result> findAll();

    //添加Result
    public int addResult(Result r);

    //添加Result
    public int addResultReturnStuno(Result r);

    //修改Result
    public int editResult(Result r);

    //删除Result
    public int delResult(int age);

    //模糊查询
    public List<Result> queryLike(Result rs);

    //多条件查询
    public List<Result> termQuery(Map<String,Object> map);

    //多条件查询索引号方案
    public List<Result> indexNumberQuery(String name,int score);

    //智能标签If
    public List<Result> findByIf(Result rs);

    //智能标签choose
    public List<Result> findByChoose(Result rs);

    //智能标签foreach array
    public List<Result> findByArray(int[] score);

}
