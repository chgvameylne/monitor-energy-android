package ch.ptoj.monitorenergie.android;

import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class ToDoHttp {

	public enum MethodType {GET,POST};
	private String url;
	private Account account;
	private FormParam form;
	private MethodType method;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public FormParam getForm() {
		return form;
	}
	public void setForm(FormParam form) {
		this.form = form;
	}
	
	public MethodType getMethod(){
		if(method==null){
			return MethodType.GET;
		}
		return method;		
	}
	
	public void setMethod(MethodType method){
		this.method=method;
	}
	
	public HttpUriRequest getRequest(){
		if(getMethod().equals(MethodType.POST)){
		   HttpPost post=new HttpPost(url);
		   form.setParameterAsEntity(post);
		   return post;
		}
		return new HttpGet(url);
	}
	
	public void setCredential(DefaultHttpClient httpclient){
		if (account.getCredential()!= null) {
			CookieStore cookieStore = new BasicCookieStore();
			cookieStore.addCookie(account.getCredential());
			httpclient.setCookieStore(cookieStore);
		}
	}
	
	
	public void extractCredential(DefaultHttpClient httpclient){
		List<Cookie> cookiejar = httpclient.getCookieStore().getCookies();
		for (Cookie c : cookiejar) {
			if (c.getName().equals("JSESSIONID")) {
				account.setCredential(c);
			}
		}				
	}
}
