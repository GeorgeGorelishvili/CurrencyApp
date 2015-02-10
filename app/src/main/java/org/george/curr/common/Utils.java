package org.george.curr.common;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import org.george.curr.model.Currency;
import org.george.curr.model.CurrencyItem;

public class Utils {

	public static Double getEditTextValue(Context context, EditText editText, boolean round) {
		String value = editText.getText().toString();
		Double amount = 0.;
		if (value != null) {
			amount = getNumber(context, value);
		}
		return round ? round(amount, 2) : amount;
	}

	private static Double getNumber(Context context, String value) {
		if (value != null) {
			try {
				return Double.parseDouble(value);
			} catch (NumberFormatException ex) {
				Toast.makeText(context, "araswori tanxis formati", Toast.LENGTH_SHORT).show();
			}
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

	public static Double convert(Double amount, CurrencyItem from, CurrencyItem to) {
		if (from.getCurrency() == to.getCurrency()) {
			return amount;
		}
		if (from.getCurrency() != Currency.GEL && to.getCurrency() != Currency.GEL) {
			return convertFromGEL(convertToGEL(amount, from), to);
		} else {
			if (from.getCurrency() == Currency.GEL) {
				return convertFromGEL(amount, to);
			}
			return convertToGEL(amount, from);
		}
	}

	public static Double convertToGEL(Double amount, CurrencyItem item) {
		return amount * item.getBuy();
	}

	public static Double convertFromGEL(Double amount, CurrencyItem item) {
		return amount / item.getSell();
	}
}
