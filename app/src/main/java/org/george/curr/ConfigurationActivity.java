package org.george.curr;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.george.curr.adapter.ListViewAdapter;
import org.george.curr.common.C;
import org.george.curr.common.DefaultData;
import org.george.curr.common.Logger;
import org.george.curr.model.Currency;


public class ConfigurationActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.log(C.TAG.CONFIGURATION_ACTIVITY, "onCreate");

		setContentView(R.layout.activity_configuration);

		String main_currency = Currency.GEL.name();

		TextView textView = (TextView)findViewById(R.id.currency_main);
		textView.setText(main_currency);

		listView = (ListView)findViewById(R.id.list_view);
		ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_view_item, DefaultData.getItemsExcept(main_currency));
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Logger.log(C.TAG.CONFIGURATION_ACTIVITY, "onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_configuration, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Logger.log(C.TAG.CONFIGURATION_ACTIVITY, "onOptionsItemSelected");
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_done) {
			actionDone();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void actionDone() {
		listView.getSelectedItem();
	}
}
