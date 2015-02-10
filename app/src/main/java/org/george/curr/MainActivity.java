package org.george.curr;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.george.curr.adapter.SpinnerAdapter;
import org.george.curr.common.C;
import org.george.curr.common.Convert;
import org.george.curr.common.DefaultData;
import org.george.curr.common.Logger;
import org.george.curr.model.Currency;
import org.george.curr.model.CurrencyItem;


public class MainActivity extends Activity {

	private TypedArray flags;
	private Spinner spinnerFrom;
	private Spinner spinnerTo;
	private EditText topField;
	private EditText bottomField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.MAIN_ACTIVITY, "onCreate");
        setContentView(R.layout.activity_main);

	    initFlags();
	    initSpinnerItemFrom();
	    initSpinnerItemTo();
	    initSwitchListener();
	    topField = (EditText)findViewById(R.id.amount_from);
	    topField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    boolean handled = false;
			    if (actionId == EditorInfo.IME_ACTION_DONE) {
				    Double result = convertValue(v.getText().toString(), getTopCurrencyItem(), getBottomCurrencyItem());
				    if (result != null) {
					    bottomField.setText(result.toString());
				    }
				    handled = true;
			    }
			    return handled;
		    }
	    });
	    bottomField = (EditText)findViewById(R.id.amount_to);
	    bottomField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    boolean handled = false;
			    if (actionId == EditorInfo.IME_ACTION_DONE) {
				    Double result = convertValue(v.getText().toString(), getBottomCurrencyItem(), getTopCurrencyItem());
				    if (result != null) {
					    topField.setText(result.toString());
				    }
				    handled = true;
			    }
			    return handled;
		    }
	    });
    }

	private Double convertValue(String value, CurrencyItem from, CurrencyItem to) {
		Double amount = getNumber(value);
		if (amount != null) {
			Double result = Convert.convert(amount, from, to);
			return round(result, 2);
		}
		return null;
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	private CurrencyItem getTopCurrencyItem() {
		return (CurrencyItem)spinnerFrom.getSelectedItem();
	}

	private CurrencyItem getBottomCurrencyItem() {
		return (CurrencyItem)spinnerTo.getSelectedItem();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Logger.log(C.TAG.MAIN_ACTIVITY, "onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.log(C.TAG.MAIN_ACTIVITY, "onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_config) {
	        startActivity(new Intent(this, ConfigurationActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private void initFlags() {
		flags = getResources().obtainTypedArray(R.array.flags);
		flags.recycle();
	}

	private void initSpinnerItemFrom() {
		SpinnerAdapter adapter = new SpinnerAdapter(this, DefaultData.getItems(), flags);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerFrom = (Spinner)findViewById(R.id.currency_from);
		spinnerFrom.setAdapter(adapter);
		spinnerFrom.setSelection(Currency.USD.ordinal());
	}

	private void initSpinnerItemTo() {
		SpinnerAdapter adapter = new SpinnerAdapter(this, DefaultData.getItems(), flags);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTo = (Spinner)findViewById(R.id.currency_to);
		spinnerTo.setAdapter(adapter);
		spinnerTo.setSelection(Currency.GEL.ordinal());
	}

	private void initSwitchListener() {
		ImageButton imageButton = (ImageButton)findViewById(R.id.switch_button);
		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int itemFrom = spinnerFrom.getSelectedItemPosition();
				int itemTo = spinnerTo.getSelectedItemPosition();
				spinnerFrom.setSelection(itemTo);
				spinnerTo.setSelection(itemFrom);
			}
		});
	}

	private Double getNumber(String value) {
		if (value != null) {
			try {
				return Double.parseDouble(value);
			} catch (NumberFormatException ex) {
				Toast.makeText(this, "araswori tanxis formati", Toast.LENGTH_SHORT).show();
			}
		}
		return null;
	}
}
