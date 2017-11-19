package cn.smbms.dao;

import cn.smbms.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/9/17.
 */
public interface IUserDao {
    //1.登录
    public User login(User user);

    //2.获取单页的数据
    public List<User> getOnePageData(Map<String,Object> map);

    //3.总记录数
    public int getTotalRecords();

    //4.带条件的总记录数
    public int getTotalRecordsByCondition(String userName);

    //5.添加用户
    public int addUser(User user);

    //6.修改用户信息
    public int updateUser(User user);

    //7.根据id查询用户信息
    public User getInfoById(int id);

    //8.删除用户
    public int delUser(int id);

}
