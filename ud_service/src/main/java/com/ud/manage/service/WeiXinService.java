package com.ud.manage.service;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.ud.util.ConfigHelper;
import com.ud.util.HttpUtil;

@Service
public class WeiXinService {

	private static final String APP_ID = ConfigHelper.getInstance().getValue("wx_app_id");
	private static final String APP_SECRET = ConfigHelper.getInstance().getValue("wx_app_secret");

	/**
	 * 根据授权码获取openid
	 * 
	 * @param code
	 * @return
	 */
	public String getOpenidByAuthCode(String code) {
		String getAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		getAccessTokenUrl = String.format(getAccessTokenUrl, APP_ID, APP_SECRET, code);
		String response = HttpUtil.postRequest(getAccessTokenUrl, null);
		JSONObject jo = JSONObject.fromObject(response);
		String openid = null;
		if (jo.get("openid") != null) {
			openid = jo.get("openid").toString();
		}
		return openid;
	}
}
