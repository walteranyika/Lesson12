package com.walter.lesson12;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

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

	public void fetch(View x) {
		// emobilis-server.com
		String url = "http://emobilis-server.com/lesson10/fetch.php";
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] data) {
				String response = new String(data);
				Log.d("JSON", response);
				process_json(response);
			}
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				Toast.makeText(getApplicationContext(), "Failed",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void process_json(String response) {
		try {
			JSONArray array = new JSONArray(response);
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String id = obj.getString("id");
				String names = obj.getString("names");
				String email = obj.getString("email");
				String age = obj.getString("age");
				Log.d("DATA", id + " " + names);

			}
		} catch (JSONException e) {
			Toast.makeText(getApplicationContext(), "Error while Fetching",
					Toast.LENGTH_SHORT).show();
		}
	}
    
}
