package cn.smf.test;

import cn.smf.dao.IResultDao;
import cn.smf.entity.Result;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2017/9/14.
 * 增删改查
 */
public class Test20170921 {
    //查询所有
    @Test
    public void findAll() throws IOException{
        SqlSession session= MyBatisUntil.getSession();
        //调度slectList方法执行sql,并且获取结果
        IResultDao mapper = session.getMapper(IResultDao.class);
        List<Result> list = mapper.findAll();
        for (Result r:list){
            System.out.println(r.getStuno()+"\t"+r.getSubjectname()+"\t"+r.getScore()+"\t"+r.getGradeId());
        }
        //关闭会话,释放资源,提高性能
        session.close();
    }


}
