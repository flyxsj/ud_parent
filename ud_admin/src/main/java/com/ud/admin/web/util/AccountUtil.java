package com.ud.admin.web.util;

import com.ud.manage.domain.AdminUser;

public class AccountUtil {
	private static ThreadLocal<AdminUser> threadLocal = new ThreadLocal<AdminUser>();

	public static void setAdminAccount(AdminUser account) {
		threadLocal.set(account);
	}

	public static AdminUser getAdminAccount() {
		return threadLocal.get();
	}
}
