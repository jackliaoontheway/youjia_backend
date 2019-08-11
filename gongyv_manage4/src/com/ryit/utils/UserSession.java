package com.ryit.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ryit.entity.admin.AdminUser;

public class UserSession {

    /**
     * 获取session中的user
     **/
    public static AdminUser getAdminUser() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
        return (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER);
    }

    /**
     * 将user设到session中
     **/
    public static void setAdminUser(AdminUser adminUser) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(Constants.ADMIN_USER, adminUser);
    }

    /**
     * remove session中 user
     **/
    public static void removeUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(Constants.ADMIN_USER, null);
    }

   /* public static void setWebUser(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(Constants.USER, null);
    }

    public static User getWebUser() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
        return (User) request.getSession().getAttribute(Constants.USER);
    }*/


}