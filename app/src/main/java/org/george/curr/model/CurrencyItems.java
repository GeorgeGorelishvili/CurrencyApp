package org.george.curr.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyItems {

    private List<CurrencyItem> currencyItems = new ArrayList<CurrencyItem>();

    public List<CurrencyItem> getCurrencyItems() {
        return currencyItems;
    }

    public void setCurrencyItems(List<CurrencyItem> currencyItems) {
        this.currencyItems = currencyItems;
    }

	public CurrencyItem getItem(Currency currency) {
		for (CurrencyItem c : currencyItems) {
			if (c.getCurrency() == currency) {
				return c;
			}
		}
		return null;
	}
}
