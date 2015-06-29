package com.ud.admin.web.permission;

import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.ud.admin.web.util.AccountUtil;
import com.ud.manage.domain.AdminUser;

public class WebPermissionAspect {

	public void checkAccess(JoinPoint jp) {
		Signature sig = jp.getSignature();
		if (sig instanceof MethodSignature) {
			Method method = ((MethodSignature) sig).getMethod();
			Method methodName = method;
			// if (methodName.getDeclaringClass().equals(AdminUserAction.class)
			// TODO recover
			if (methodName.getDeclaringClass().equals(WebPermissionAspect.class)
					&& (methodName.getName().equals("logInex") || methodName.getName().equals("login"))) {
				return;
			}
			WebUserRole role = methodName.getAnnotation(WebUserRole.class);
			AdminUser account = AccountUtil.getAdminAccount();
			if (null != account) {
				String roleType = account.getRoleType();
				if (roleType == "admin") {
					return;
				}
				if (null != role) {
					String[] roles = role.value();
					if (ArrayUtils.contains(roles, roleType)) {
						return;
					} else {
						throw new RuntimeException();
					}
				}
			} else {
				throw new RuntimeException();
			}
		}
	}
}
