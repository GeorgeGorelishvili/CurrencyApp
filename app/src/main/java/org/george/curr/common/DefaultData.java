package org.george.curr.common;

import org.george.curr.model.Currency;
import org.george.curr.model.CurrencyItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultData {

	private static String DEFAULT_CURRENCY = "GEL";
	private static Map<String, CurrencyItem> currencyMap = new HashMap<String, CurrencyItem>();

	static {
		for (Currency currency : Currency.values()) {
			CurrencyItem item = new CurrencyItem(currency);
			if (currency == Currency.GEL) {
				item.setBuy(1.);
				item.setSell(1.);
			}
			if (currency == Currency.USD) {
				item.setBuy(1.742);
				item.setSell(1.782);
			}
			if (currency == Currency.EUR) {
				item.setBuy(2.141);
				item.setSell(2.229);
			}
			if (currency == Currency.GBP) {
				item.setBuy(2.698);
				item.setSell(2.802);
			}
			if (currency == Currency.RUB) {
				item.setBuy(0.0349);
				item.setSell(0.0397);
			}
			currencyMap.put(currency.name(), item);
		}
	}

	public static CurrencyItem getItem(Currency c) {
		return currencyMap.get(c);
	}

	public static List<CurrencyItem> getItems() {
		List<CurrencyItem> items = new ArrayList<CurrencyItem>();
		for (Currency currency : Currency.values()) {
			CurrencyItem item = currencyMap.get(currency.name());
			if (item != null) {
				items.add(item);
			}
		}
		return items;
	}

	public static List<CurrencyItem> getItemsExcept(String firstItem) {
		List<CurrencyItem> items = new ArrayList<CurrencyItem>();
		for (Currency currency : Currency.values()) {
			if (!firstItem.equals(currency.name())) {
				CurrencyItem item = currencyMap.get(currency.name());
				items.add(item);
			}
		}
		return items;
	}
}
