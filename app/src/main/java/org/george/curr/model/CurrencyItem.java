package org.george.curr.model;

public class CurrencyItem {

    private Currency currency;
    private Double buy;
    private Double sell;

	public CurrencyItem(Currency currency) {
		this.currency = currency;
	}

	public CurrencyItem(Currency currency, Double buy, Double sell) {
		this.currency = currency;
		this.buy = buy;
		this.sell = sell;
	}

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

	public boolean isBuyConfigured() {
		return buy != null && buy > 0;
	}

	public boolean isCellConfigured() {
		return sell != null && sell > 0;
	}

	public boolean isConfigured() {
		return isBuyConfigured() && isCellConfigured();
	}

	@Override
	public String toString() {
		return this.currency.name();
	}
}

