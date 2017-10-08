package cn.smf.service.impl;
import cn.smf.dao.IUserDao;
import cn.smf.entity.User;
import cn.smf.service.IUserService;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ASUS on 2017/9/17.
 */
public class UserServiceImpl implements IUserService {
    IUserDao mapper;
    public UserServiceImpl(){
        SqlSession session = MyBatisUntil.getSession();
        mapper = session.getMapper(IUserDao.class);
    }
    //登录
    public User login(User user) {
        return mapper.login(user);
    }

    //查询所有用户+分页
    public List<User> getAllUser(int pageIndex, int pageSize) {
        return mapper.getAllUser(pageIndex,pageSize);
    }

    //模糊查询+分页
    public List<User> fuzzyQuery(String name,int pageIndex,int pageSize) {
        return mapper.fuzzyQuery(name,pageIndex,pageSize);
    }

    //添加用户
    public int addUser(User user) {
        return mapper.addUser(user);
    }

    //修改用户信息
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    //删除用户
    public int delUser(int id) {
        return mapper.delUser(id);
    }

    //获取所有记录数(查询所有||模糊查询)
    public int getTotalCount(String name) {
        return mapper.getTotalCount(name);
    }

    //根据id查询用户信息
    public User getInfoById(int id) {
        return mapper.getInfoById(id);
    }


}
