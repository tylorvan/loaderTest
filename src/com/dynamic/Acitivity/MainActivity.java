package com.dynamic.Acitivity;

import com.dynamic.impl.DynamicImpl;
import com.dynamic.impl.R;
import com.dynamic.impl.R.id;
import com.dynamic.impl.R.layout;
import com.dynamic.impl.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// DynamicImpl dy = new DynamicImpl(MainActivity.this);
		// dy.showTipe();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		/*
		 * 本代码因为在 Activity 中，所以可以直接调用 getContentResolver()。这个方法实际上是 Context
		 * 中定义的。
		 */

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Log.v("Daynamic", "selected ");

		// if (id == R.id.action_settings) {
		// return true;
		// }
		DynamicImpl dy = new DynamicImpl();
		dy.init(this);
		dy.showTipe();
		return super.onOptionsItemSelected(item);
	}
}
