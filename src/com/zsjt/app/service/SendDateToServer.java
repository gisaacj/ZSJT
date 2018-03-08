package com.zsjt.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
import org.json.JSONObject;


public class SendDateToServer {
	public void Checklogin(final String name,final String pwd){
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					HttpClient hc = new DefaultHttpClient();
					HttpPost request = new HttpPost("服务器要填的接口");
					List<NameValuePair> params = new ArrayList<NameValuePair>();
				    params.add(new BasicNameValuePair("username", name));
				    params.add(new BasicNameValuePair("password", pwd));
				    try {
						request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    try {
						HttpResponse response = hc.execute(request);
						if (response.getStatusLine().getStatusCode() == 200) {
					    	HttpEntity entity = response.getEntity();
					    	String reply = EntityUtils.toString(entity,"utf-8");
					    	CheckloginReplyWithJSONObject(reply);
					    }
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	private Integer CheckloginReplyWithJSONObject(String jsonData) {
		// TODO Auto-generated method stub
		try{
			/*JSONArray jsonArray = new JSONArray(jsonData);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Integer id = jsonObject.getInt("id");
				return id;
			}
			*/
			JSONObject jsonObject = new JSONObject(jsonData);
			Integer id = jsonObject.getInt("id");
			return id;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
