package cn.smf.dao;

import cn.smf.entity.Bill;
import java.util.List;

/**
 * Created by ASUS on 2017/9/18.
 */
public interface IBillDao {
    //查询所有订单
    public List<Bill> getAllBill();

}
