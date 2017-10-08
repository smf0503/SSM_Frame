package cn.smf.dao;

import cn.smf.entity.Provider;

import java.util.List;

/**
 * Created by ASUS on 2017/9/21.
 */
public interface IProviderDao {
    //查询所有供应商信息
    public List<Provider> getAllPro(Provider pro);

    //添加供应商
    public int addPro(Provider pro);

    //修改供应商信息
    public int updateProInfo(Provider pro);

    //删除供应商
    public int delPro(int id);


}
