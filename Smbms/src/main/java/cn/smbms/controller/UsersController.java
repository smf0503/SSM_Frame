package cn.smbms.controller;

import cn.smbms.entity.User;
import cn.smbms.service.IUserService;
import cn.smbms.until.AgeByBirthday;
import cn.smbms.until.PageUntil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/11/13.
 */
@Controller
public class UsersController {
    @Resource(name = "userService")
    IUserService users;

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        User login = users.login(user);
        if (login!=null){   //登录成功
            session.setAttribute("userinfo",login);
            return "/welcome.jsp";
        }else{  //登录失败
            return "/login.jsp";
        }
    }

    //查询单页数据
    @RequestMapping("/userList")
    @ResponseBody
    public Object userList(@RequestParam(defaultValue = "1",required = false,value = "page") int pageIndex, @RequestParam(defaultValue = "2",required = false,value = "rows") int pageSize, User user) throws UnsupportedEncodingException {
        Map<String,Object> map=new HashMap<String,Object>();
        PageUntil<User> page = users.getOnePageData(pageIndex, pageSize,user);
        map.put("total",page.getTotalRecords());
        map.put("rows",page.getList());
        //convertAge(page);  //转换年龄
        List<User> list = page.getList();
        return map;
    }

    //添加用户
    @RequestMapping(value ="/addUser")
    @ResponseBody
    public  Object insertUser(HttpServletRequest request, User user) throws ParseException {
        boolean result=false;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(user.getDate());
        user.setBirthday(parse);
        System.out.println(user);
        int addCount = users.addUser(user);
        if (addCount>0){
            result=true;
        }
        return result;
    }

    //修改用户信息
    @RequestMapping("/updateUser/{id}")
    @ResponseBody
    public Object updateUser(@PathVariable int id,User user,HttpSession session) throws ParseException {
        System.out.println("进入updateUser");
        boolean result=false;
        user.setId(id);
        String date=user.getDate();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(date);
        System.out.println(date+"////////////////////");
        user.setBirthday(parse);
        int updateCount = users.updateUser(user);
        if (updateCount>0){
            result=true;
        }
        return result;
    }

    //根据id查询用户信息
    @RequestMapping("/getInfoById/{id}")
    public String getInfoById(@PathVariable int id,HttpSession session){
        User user = users.getInfoById(id);
        session.setAttribute("info",user);
        return "/userView.jsp";
    }

    //删除用户
    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser(@RequestParam(value = "ids",required = false) String ids){
        boolean result=false;
        String[] split = ids.split(",");
        int delCount=0;
        for (String id:split) {
            delCount = users.delUser(Integer.parseInt(id));
        }
        if (delCount>0){
            result=true;
        }
        return result;
    }

    /**
     //字符串转换成date
     public Date convertDate(String temp){
     Date date=new Date();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     try {
     date=dateFormat.parse(temp);
     } catch (ParseException e) {
     e.printStackTrace();
     }
     return date;
     }
     */
    //转换年龄
    public void convertAge(PageUntil<User> page){
        for (User u:page.getList()) {
            Date date=u.getBirthday();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            try {
                int getagee = AgeByBirthday.getagee(format);
                u.setAge(getagee);
            } catch (Exception e) {
            }
        }
    }
}
