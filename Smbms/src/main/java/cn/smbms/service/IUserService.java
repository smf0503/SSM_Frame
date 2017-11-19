package cn.smbms.service;
import cn.smbms.entity.User;
import cn.smbms.until.PageUntil;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by ASUS on 2017/9/17.
 */
public interface IUserService {
    //1.登录
    public User login(User user);

    //2.获取单页的数据
    //public PageUntil<User> getOnePageData(int pageIndex, int pageSize);
    public PageUntil<User> getOnePageData(int pageIndex, int pageSize,User user);

    //3.总记录数
    public int getTotalRecords();

    //4.添加用户
    public int addUser(User user);

    //5.修改用户信息
    public int updateUser(User user);

    //6.根据id查询用户信息
    public User getInfoById(int id);

    //7.删除用户
    public int delUser(int id);

}
