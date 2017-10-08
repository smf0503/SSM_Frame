package cn.smf.test;

import cn.smf.dao.IProviderDao;
import cn.smf.dao.IUserDao;
import cn.smf.entity.Provider;
import cn.smf.entity.User;
import cn.smf.service.IUserService;
import cn.smf.service.impl.UserServiceImpl;
import cn.smf.until.AgeByBirthday;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/9/18.
 */
public class Test20170922 {
    @Test
    public void getAllPro(){
        SqlSession session = MyBatisUntil.getSession();
        IProviderDao dao=session.getMapper(IProviderDao.class);
        Provider p=new Provider();
        List<Provider> allPro = dao.getAllPro(p);
        for(Provider pp:allPro){
            System.out.println(pp.getProName());
        }
    }

    @Test
    public void getTime(){
        Date date=new Date();

        String s = date.toString();
        long time = date.getTime();
        System.out.println("date--------->"+date);
        System.out.println("date.toString()--------->"+date.toString());
        System.out.println("time--------->"+time);
    }


}
