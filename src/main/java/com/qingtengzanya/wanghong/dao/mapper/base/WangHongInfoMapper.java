package com.qingtengzanya.wanghong.dao.mapper.base;

import com.qingtengzanya.wanghong.dao.entity.WangHongInfoEty;

import java.util.List;

public interface WangHongInfoMapper extends com.ac.base.dao.BaseMapper<WangHongInfoEty> {

    void insertEntityList(List<WangHongInfoEty> wangHongInfoEties);

    List<WangHongInfoEty> selectByIds(List<Long> ids);
}