package cn.smf.service.impl;

import cn.smf.dao.IProviderDao;
import cn.smf.entity.Provider;
import cn.smf.service.IProviderService;
import cn.smf.until.MyBatisUntil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ASUS on 2017/9/21.
 */
public class ProviderServiceImpl implements IProviderService {
    IProviderDao ip;
    public ProviderServiceImpl(){
        SqlSession session= MyBatisUntil.getSession();
        ip=session.getMapper(IProviderDao.class);
    }
    //查询所有供应商信息
    public List<Provider> getAllPro(Provider pro) {
        return ip.getAllPro(pro);
    }

    //添加供应商
    public int addPro(Provider pro) {
        return ip.addPro(pro);
    }

    //修改供应商信息
    public int updateProInfo(Provider pro) {
        return ip.updateProInfo(pro);
    }

    //删除供应商
    public int delPro(int id) {
        return ip.delPro(id);
    }


}
