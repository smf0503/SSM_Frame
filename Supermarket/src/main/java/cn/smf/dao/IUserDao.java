package cn.smf.dao;

import cn.smf.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by ASUS on 2017/9/17.
 */
public interface IUserDao {
    //登录
    public User login(User user);

    //查询所有用户分页
    public List<User> getAllUser(@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    //模糊查询 分页
    public List<User> fuzzyQuery(@Param("name") String name,@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    //添加用户
    public int addUser(User user);

    //修改用户信息
    public int updateUser(User user);

    //删除用户
    public int delUser(int id);

    //获取所有记录数(查询所有||模糊查询)
    public int getTotalCount(@Param("name") String name);

    //根据id查询用户信息
    public User getInfoById(@Param("id") int id);

}
