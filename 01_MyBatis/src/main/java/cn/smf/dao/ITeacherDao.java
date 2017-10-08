package cn.smf.dao;

import cn.smf.entity.Teacher;

/**
 * Created by ASUS on 2017/9/23.
 */
public interface ITeacherDao {
    //根据老师id查询该老师带的所有学生
    public Teacher getTeacherById(int id);
}