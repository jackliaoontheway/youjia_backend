package com.ryit.service.admin;

import javax.naming.directory.DirContext;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.support.LdapUtils;

import com.ryit.service.BaseService;

public class LdapAuthenticationService extends BaseService{
	private ContextSource contextSource;

	private String prefix = "sf\\";

	private String checkPassword;

	public void setContextSource(ContextSource contextSource) {
		this.contextSource = contextSource;
	}

	public void setPrefix(String prefix) {this.prefix = prefix;}

	public void setCheckPassword(String checkPassword) {this.checkPassword = checkPassword;}

	/**
	 * 根据用户名和密码进行域用户验证
	 */
	public boolean authentication(String name, String password) {
		DirContext ctx = null;
		try {
			if("1".equals(checkPassword)) {
				ctx = contextSource.getContext(prefix + name, password);
				LOGGER.info("user[" + name + "], domain verify success!");
			}
			return true;
		} catch (Exception e) {
			LOGGER.info("user[" + name + "] or password invalid, domain verify failed!");
			return false;
		} finally {
			LdapUtils.closeContext(ctx);
		}
	}
}