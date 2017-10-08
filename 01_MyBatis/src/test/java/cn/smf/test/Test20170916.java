package cn.smf.test;

import cn.smf.dao.IResultDao;
import cn.smf.entity.Result;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.ognl.IntHashMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/9/14.
 * 增删改查
 */
public class Test20170916 {
    //查询所有
    @Test
    public void findAll() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        for (Result r:list){
            System.out.println(r.getStuno()+"\t"+r.getSubjectname()+"\t"+r.getScore());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //模糊查询
    @Test
    public void queryLike() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        Result rs=new Result();
        rs.setSubjectname("C");
        List<Result> list = mapper.queryLike(rs);
        for (Result r:list){
            System.out.println(r.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //多条件查询
    @Test
    public void termQuery() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("subjectname","C");
        map.put("score",71);
        List<Result> list = mapper.termQuery(map);
        for (Result r:list){
            System.out.println(r.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //删除
    @Test
    public void delResult() throws IOException{
        String resouce="mybatis-config.xml";
        //将硬盘上的一个xml文件变成一个输入流
        InputStream is=Resources.getResourceAsStream(resouce);
        //使用流对象作为参数创建一个会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过会话工厂创建会话对象 session就是程序员和数据库交互的入口
        SqlSession session=factory.openSession();
        IResultDao mapper = session.getMapper(IResultDao.class);
        mapper.delResult(8);
        session.delete("");
        session.commit();
        System.out.println("delete成功");
        //关闭会话,释放资源,提高性能
        session.close();
    }

}
