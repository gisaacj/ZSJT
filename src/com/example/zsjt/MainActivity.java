package com.example.zsjt;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button Login;
	
	private Button Register;
	
	public void onClick(View v){
		if (v.getId()==R.id.login){
			sendRequestWithHttpClient();
		}
	}

	private void sendRequestWithHttpClient() {
		// TODO Auto-generated method stub
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("http://");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode()==200){
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity,"utf-8");
						parseJSONWithJSONObject(response);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void parseJSONWithJSONObject(String jsonData) {
		// TODO Auto-generated method stub
		try{
			//JSONArray jsonArray = new JSONArray(jsonData);
			JSONObject jsonObject = new JSONObject(jsonData);
			//for (int i=0;i<jsonArray.length();i++){
				//JSONObject jsonObject = jsonArray.getJSONObject(i);
				int login_status = jsonObject.getInt("login_status");
			//}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
