package com.xs.pojo;
/**
 * 百度AccessToken
 * @author 小帅丶
 * @date  2017-5-26上午10:38:18
 */
public class BDAccessToken {
	/**
	 * AccessToken 是用户身份验证和授权的凭证
	 */
	public String access_token;
	/**
	 * SessionKey
	 */
	public String session_key;
	/**
	 * Scope
	 */
	public String scope;
	/**
	 * RefreshToken
	 */
	public String refresh_token;
	/**
	 * SessionSecret
	 */
	public String session_secret;
	/**
	 * ExpiresIn 有效时长 
	 */
	public Integer expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String sessionKey) {
		session_key = sessionKey;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refreshToken) {
		refresh_token = refreshToken;
	}
	public String getSession_secret() {
		return session_secret;
	}
	public void setSession_secret(String sessionSecret) {
		session_secret = sessionSecret;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expiresIn) {
		expires_in = expiresIn;
	}
	
}
