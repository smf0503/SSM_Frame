package cn.smf.test;

import cn.smf.dao.IUserDao;
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
public class Test20170918 {
    @Test
    public void login(){
        SqlSession session = MyBatisUntil.getSession();
        IUserDao mapper = session.getMapper(IUserDao.class);
        User user=new User();
        user.setUserName("阿苏");
        user.setUserPassword("0503");
        mapper.login(user);
    }

    @Test
    public void getTime() throws Exception {
        Date date=new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*String format = dateFormat.format(date);
        System.out.println("***********"+format);*/
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int o = c.get(Calendar.MONTH);
        int cc = c.get(Calendar.DATE);
        System.out.println("年------"+year);
        System.out.println("月------"+o);
        System.out.println("日------"+cc);

        IUserService ius=new UserServiceImpl();
        User user=null;
        /*List<User> allUser = ius.getAllUser(user,0,);
        for(User u:allUser){
            date = u.getBirthday();
            //int format = Integer.parseInt(dateFormat.format(date));
            //u.setAge(year-format);
            //System.out.println("数据库年---->"+format);
            String format = dateFormat.format(date);
            int i = AgeByBirthday.getagee(format);
           // System.out.println("当前年---->"+year);
            System.out.println("年龄---->"+i);
        }*/

    }

}
