package com.qingtengzanya.wanghong.controller.basicinfo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.ac.util.jsonresult.JsonResult;
import com.ac.util.jsonresult.JsonResultFactory;

import com.qingtengzanya.wanghong.dao.entity.DictionaryEty;
import com.qingtengzanya.wanghong.dao.mapper.base.DictionaryMapper;

/**
 * 字典信息
 */
@Controller
@RequestMapping("/basicinfo/DictionaryCtrl/")
public class DictionaryCtrl {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	/**
	 * 查询
	 */
	@RequestMapping(value="search")
	public @ResponseBody JsonResult search(@RequestBody DictionaryEty dictionaryEty) throws Exception {
		int count = dictionaryMapper.selectLimitCount(dictionaryEty);
		List<DictionaryEty> list = dictionaryMapper.selectByLimit(dictionaryEty);
		return JsonResultFactory.extgrid(list, count);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value="save")
	public @ResponseBody JsonResult save(@RequestBody DictionaryEty dictionaryEty) throws Exception {
		if(dictionaryEty.getId() == null) {
			dictionaryMapper.insert(dictionaryEty);
		}
		else {
			dictionaryMapper.updateById(dictionaryEty);
		}
		return JsonResultFactory.success();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete")
	public @ResponseBody JsonResult delete(@RequestParam("id") Long id) {
		dictionaryMapper.deleteById(id);
		return JsonResultFactory.success();
	}
	
	/**
	 * 得到详细信息
	 */
	@RequestMapping(value="getDetailInfo")
	public @ResponseBody JsonResult getDetailInfo(@RequestParam("id") Long id) throws Exception {
		DictionaryEty dictionaryEty = dictionaryMapper.selectById(id);
		return JsonResultFactory.success(dictionaryEty);
	}
	
}