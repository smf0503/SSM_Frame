package cn.smf.test;

import cn.smf.dao.IResultDao;
import cn.smf.dao.ITeacherDao;
import cn.smf.entity.Result;
import cn.smf.entity.Student;
import cn.smf.entity.Teacher;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2017/9/14.
 * 增删改查
 */
public class Test20170923 {
    //根据老师id查询该老师带的所有学生
    @Test
    public void getTeacherById() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        ITeacherDao mapper = session.getMapper(ITeacherDao.class);
        Teacher teacherById = mapper.getTeacherById(2);
        for (Student stu:teacherById.getStus()){
            System.out.println(teacherById.getTname()+"-------------------"+stu.getSname());
        }
    }

    //查询所有  验证一级缓存
    @Test
    public void findAll() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        System.out.println(list);
        System.out.println("=============分割线=============");
        List<Result> list2 = mapper.findAll();
        System.out.println(list2);
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //增删改对一级缓存的影响
    @Test
    public void findAll2() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        System.out.println(list);

        Result r=new Result();
        r.setStuno("6");
        r.setSubjectname("PHP");
        r.setScore(99);
        r.setAge(55);
        r.setGradeId(22);
        int i = mapper.addResult(r);

        System.out.println("=============分割线=============");
        List<Result> list2 = mapper.findAll();
        System.out.println(list2);
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //查询所有  验证二级缓存
    @Test
    public void cache() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        System.out.println(list);
        session.close();
        System.out.println("=============分割线=============");
        SqlSession session2= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper2 = session2.getMapper(IResultDao.class);
        List<Result> list2 = mapper2.findAll();
        System.out.println(list2);
        //关闭会话,释放资源,提高性能
        session.close();
    }

    //增删改对二级缓存的影响
    @Test
    public void cache2() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        System.out.println(list);

        Result r=new Result();
        r.setStuno("6");
        r.setSubjectname("PHP");
        r.setScore(99);
        r.setAge(55);
        r.setGradeId(22);
        int i = mapper.addResult(r);
        session.close();

        System.out.println("=============分割线=============");
        SqlSession session2= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper2 = session2.getMapper(IResultDao.class);
        List<Result> list2 = mapper2.findAll();
        System.out.println(list2);
        session2.close();

        /*SqlSession session3= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper3 = session3.getMapper(IResultDao.class);
        List<Result> list3 = mapper3.findAll();
        System.out.println(list3);*/

        //关闭会话,释放资源,提高性能
        /*session.close();*/
    }

    @Test
    public void test(){

        String path = System.getProperty("java.io.tmpdir");
        System.out.println(path);
    }


}
