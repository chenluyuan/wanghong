package com.qingtengzanya.wanghong.service;

import com.qingtengzanya.wanghong.dao.entity.WangHongInfoEty;
import com.qingtengzanya.wanghong.dao.mapper.base.WangHongInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenluyuan (chenluyuanit@gmail.com)
 */
@Service
@Transactional
public class WhInfoService {

    @Autowired
    private WangHongInfoMapper wangHongInfoMapper;

    public int selectLimitCount(WangHongInfoEty wangHongInfoEty) {
        return wangHongInfoMapper.selectLimitCount(wangHongInfoEty);
    }

    public List<WangHongInfoEty> selectByLimit(WangHongInfoEty wangHongInfoEty) {
        return wangHongInfoMapper.selectByLimit(wangHongInfoEty);
    }

    public WangHongInfoEty selectById(Long id) {
        return wangHongInfoMapper.selectById(id);
    }

    public void deleteById(Long id) {
        wangHongInfoMapper.deleteById(id);
    }

    public void insert(WangHongInfoEty wangHongInfoEty) {
        wangHongInfoMapper.insert(wangHongInfoEty);
    }

    public void updateById(WangHongInfoEty wangHongInfoEty) {
        wangHongInfoMapper.insert(wangHongInfoEty);
    }

    public void insertEntityList(List<WangHongInfoEty> wangHongInfoEties) {
        wangHongInfoMapper.insertEntityList(wangHongInfoEties);
    }

    public List<WangHongInfoEty> selectByIds(List<Long> ids) {
        return wangHongInfoMapper.selectByIds(ids);
    }
}
