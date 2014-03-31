package ch.ptoj.monitorenergie.android;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class FormParam {

	private List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	public void addParameter(String key,String value){
		nameValuePairs.add(new BasicNameValuePair(key, value));
	}
	
	public void setParameterAsEntity(HttpPost httppost){
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
