package cn.smbms.service.impl;
import cn.smbms.dao.IUserDao;
import cn.smbms.entity.User;
import cn.smbms.service.IUserService;
import cn.smbms.until.PageUntil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/9/17.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource(name = "IUserDao")
    private IUserDao userDao;

    //登录
    public User login(User user) {
        return userDao.login(user);
    }

    public PageUntil<User> getOnePageData(int pageIndex, int pageSize, User user) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("pageIndex",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        if (user!=null&&!user.getUserName().equals(null)){  //用户填写了查询条件
            map.put("userName",user.getUserName());
        }
        PageUntil<User> pageUntil=new PageUntil<User>();
        pageUntil.setPageIndex(pageIndex);
        pageUntil.setPageSize(pageSize);
        int totalRecords;
        if (user!=null&&!user.getUserName().equals(null)){  //用户填写了查询条件
            totalRecords=userDao.getTotalRecordsByCondition(user.getUserName());  //带条件的总记录数
        }else {
            totalRecords = userDao.getTotalRecords();   //不带条件的总记录数
        }
        pageUntil.setTotalRecords(totalRecords);
        //总页数=totalRecords/pageSize
        pageUntil.setTotalPages(pageUntil.getTotalRecords()%pageUntil.getPageSize()==0?pageUntil.getTotalRecords()/pageUntil.getPageSize():pageUntil.getTotalRecords()/pageUntil.getPageSize()+1);
        //集合数据
        pageUntil.setList(userDao.getOnePageData(map));
        return pageUntil;
    }

    //获取单页的数据
    /*public PageUntil<User> getOnePageData(int pageIndex, int pageSize) {
        Map<String,Integer> map=new HashMap<String, Integer>();
        map.put("pageIndex",pageIndex*pageSize);
        map.put("pageSize",pageSize);

        PageUntil<User> pageUntil=new PageUntil<User>();
        pageUntil.setPageIndex(pageIndex);
        pageUntil.setPageSize(pageSize);
        int totalRecords=userDao.getTotalRecords();
        pageUntil.setTotalRecords(totalRecords);
        //总页数=totalRecords/pageSize
        pageUntil.setTotalPages(pageUntil.getTotalRecords()%pageUntil.getPageSize()==0?pageUntil.getTotalRecords()/pageUntil.getPageSize():pageUntil.getTotalRecords()/pageUntil.getPageSize()+1);
        //集合数据
        pageUntil.setList(userDao.getOnePageData(map));
        return pageUntil;
    }*/

    //获取总记录数
    public int getTotalRecords() {
        return userDao.getTotalRecords();
    }

    //添加用户
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    //修改用户信息
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    //根据id查询用户信息
    public User getInfoById(int id) {
        return userDao.getInfoById(id);
    }

    //删除用户
    public int delUser(int id) {
        return userDao.delUser(id);
    }


}
