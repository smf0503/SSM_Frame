package cn.smf.service;
import cn.smf.entity.User;

import java.util.List;

/**
 * Created by ASUS on 2017/9/17.
 */
public interface IUserService {
    //登录
    public User login(User user);

    //查询所有用户+分页
    public List<User> getAllUser(int pageIndex, int pageSize);

    //模糊查询+分页
    public List<User> fuzzyQuery(String name,int pageIndex,int pageSize);

    //添加用户
    public int addUser(User user);

    //修改用户信息
    public int updateUser(User user);

    //删除用户
    public int delUser(int id);

    //获取所有记录数(查询所有||模糊查询)
    public int getTotalCount(String name);

    //根据id查询用户信息
    public User getInfoById(int id);

}
