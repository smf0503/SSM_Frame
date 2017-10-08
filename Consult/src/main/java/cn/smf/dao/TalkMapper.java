package cn.smf.dao;

import cn.smf.entity.Talk;

public interface TalkMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(Talk record);

    int insertSelective(Talk record);

    Talk selectByPrimaryKey(Long tid);

    int updateByPrimaryKeySelective(Talk record);

    int updateByPrimaryKey(Talk record);
}