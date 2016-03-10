package com.ac.util;

import com.qingtengzanya.wanghong.dao.entity.UserEty;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static void login(UserEty userEty) {
		getSession().setAttribute("SessionUser", userEty);
	}
	
	public static void logout() {
		getSession().removeAttribute("SessionUser");
		getSession().invalidate();
	}
	
	public static UserEty getLoginUser() {
		return (UserEty) getSession().getAttribute("SessionUser");
	}
	
	public static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession();
	}
}
