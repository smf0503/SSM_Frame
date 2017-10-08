package cn.smf.test;

import cn.smf.dao.IResultDao;
import cn.smf.entity.Result;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.io.Resources;
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
public class Test20170917 {
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

    //多条件查询索引号方案
    @Test
    public void indexNumberQuery() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.indexNumberQuery("c",71);
        for (Result r:list){
            System.out.println(r.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //智能标签if
    @Test
    public void findByIf() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        Result rs=new Result();
        rs.setSubjectname("c");
        rs.setScore(71);
        List<Result> list = mapper.findByIf(rs);
        for (Result rr:list){
            System.out.println(rr.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //智能标签choose
    @Test
    public void findByChoose() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        Result rs=new Result();
        /*rs.setSubjectname("c");
        rs.setScore(71);*/
        List<Result> list = mapper.findByChoose(rs);
        for (Result rr:list){
            System.out.println(rr.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //智能标签foreach array
    @Test
    public void findByArray() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        int[] s={77,90};
        List<Result> list = mapper.findByArray(s);
        for (Result rr:list){
            System.out.println(rr.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

}
