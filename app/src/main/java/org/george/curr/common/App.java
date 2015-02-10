package org.george.curr.common;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    public static App self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        initApp();
    }

    private void initApp() {
	    getDefaultCurrency();
    }

    private SharedPreferences getPreferences() {
        return getSharedPreferences("APP", MODE_PRIVATE);
    }

	private String getCurrencyData(String data) {
		String currencyData = getPreferences().getString(C.PREFS.CURRENCY_DATA, null);
		return currencyData;
	}

	private String getDefaultCurrency() {
		String defaultCurrency = getPreferences().getString(C.PREFS.DEFAULT_CURRENCY, null);
		if (defaultCurrency == null) {
			getPreferences().edit().putString(C.PREFS.DEFAULT_CURRENCY, C.DEF.GEL).apply();
		}
		return defaultCurrency;
	}
}
