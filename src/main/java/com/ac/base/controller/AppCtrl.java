package com.ac.base.controller;

import java.util.List;
import java.util.Map;

import com.qingtengzanya.wanghong.dao.entity.UserEty;
import com.qingtengzanya.wanghong.dao.mapper.base.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.util.SessionUtil;
import com.ac.util.jsonresult.JsonResult;
import com.ac.util.jsonresult.JsonResultFactory;

@Controller
public class AppCtrl {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value="/sys/login")
	public @ResponseBody JsonResult login(@RequestBody Map<String, String> paraMap) {
		String username = paraMap.get("username");
		String password = paraMap.get("password");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
//		String pass = passwordEncoder.encode("wh:20160307@sujf.admin");
		UserEty userEty = new UserEty(username);
		List<UserEty> userEties = userMapper.selectByEntity(userEty);
		if(userEties == null || userEties.size() == 0) {
			return JsonResultFactory.error("用户名不存在！");
		}
		UserEty user = userEties.get(0);

		if(!passwordEncoder.matches(password, user.getPassword())) {
			return JsonResultFactory.error("用户密码输入不正确！");
		}
		SessionUtil.login(userEties.get(0));
		return JsonResultFactory.success();
	}
	
	@RequestMapping(value="/sys/logout")
	public String logout() {
		SessionUtil.logout();
		return "redirect:/login";
	}
}
