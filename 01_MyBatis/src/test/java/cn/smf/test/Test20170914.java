package cn.smf.test;
import cn.smf.dao.IResultDao;
import cn.smf.entity.Result;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ASUS on 2017/9/14.
 * 增删改查
 */
public class Test20170914 {
    //查询所有
    @Test
    public void findAll() throws IOException{
        String resouce="mybatis-config.xml";
        //将硬盘上的一个xml文件变成一个输入流
        InputStream is=Resources.getResourceAsStream(resouce);
        //使用流对象作为参数创建一个会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过会话工厂创建会话对象 session就是程序员和数据库交互的入口
        SqlSession session=factory.openSession();
        //调度slectList方法执行sql,并且获取结果
        List<Result> list = session.selectList("findAll");
        //IResultDao mapper = session.getMapper(IResultDao.class);
       // List<Result> list = mapper.findAll();
        for (Result r:list){
            System.out.println(r.getSubjectname());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //添加
    @Test
    public void addResult() throws IOException{
        String resouce="mybatis-config.xml";
        //将硬盘上的一个xml文件变成一个输入流
        InputStream is=Resources.getResourceAsStream(resouce);
        //使用流对象作为参数创建一个会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过会话工厂创建会话对象 session就是程序员和数据库交互的入口
        SqlSession session=factory.openSession();
        IResultDao mapper = session.getMapper(IResultDao.class);
        Result r=new Result();
        r.setStuno("6");
        r.setSubjectname("PHP");
        r.setScore(88);
        r.setAge(8);
        int i = mapper.addResult(r);
        session.commit();
        System.out.println("i------------"+i);
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //修改
    @Test
    public void editResult() throws IOException{
        String resouce="mybatis-config.xml";
        //将硬盘上的一个xml文件变成一个输入流
        InputStream is=Resources.getResourceAsStream(resouce);
        //使用流对象作为参数创建一个会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过会话工厂创建会话对象 session就是程序员和数据库交互的入口
        SqlSession session=factory.openSession();
        IResultDao mapper = session.getMapper(IResultDao.class);
        Result r=new Result();
        r.setSubjectname("大数据");
        r.setAge(8);
        mapper.editResult(r);
        session.commit();
        System.out.println("修改成功");
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
        session.commit();
        System.out.println("delete成功");
        //关闭会话,释放资源,提高性能
        session.close();
    }

}
