package org.george.curr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.george.curr.R;
import org.george.curr.common.Logger;
import org.george.curr.model.CurrencyItem;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<CurrencyItem> {

	private Context context;
	private List<CurrencyItem> items;
	private int listViewItemResource;

	public ListViewAdapter(Context context, int listViewItemResource, List<CurrencyItem> items) {
		super(context, listViewItemResource, items);
		this.context = context;
		this.items = items;
		this.listViewItemResource = listViewItemResource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(listViewItemResource, parent, false);
		}
		CurrencyItem item = items.get(position);
		TextView textView = (TextView)convertView.findViewById(R.id.currency_text);
		textView.setText(item.getCurrency().name());

		final EditText buyEditText = (EditText)convertView.findViewById(R.id.amount_buy);
		buyEditText.setText(item.getBuy() != null ? item.getBuy().toString() : "");

		final EditText sellEditText = (EditText)convertView.findViewById(R.id.amount_sell);
		sellEditText.setText(item.getSell() != null ? item.getSell().toString() : "");

		final ImageButton button = (ImageButton)convertView.findViewById(R.id.button_edit);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean visible = buyEditText.isEnabled();
				buyEditText.setEnabled(!visible);
				sellEditText.setEnabled(!visible);
				button.setImageResource(visible ? R.drawable.ic_action_edit : R.drawable.ic_action_done);
				if (visible) {

				}
			}
		});
		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public CurrencyItem getItem(int position) {
		return items.get(position);
	}
}
