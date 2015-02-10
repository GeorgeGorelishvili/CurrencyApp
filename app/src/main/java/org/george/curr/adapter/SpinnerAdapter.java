package org.george.curr.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.george.curr.R;
import org.george.curr.model.CurrencyItem;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<CurrencyItem> {

	private Context context;
	private List<CurrencyItem> items;
	private TypedArray flags;


	public SpinnerAdapter(Context context, List<CurrencyItem> items, TypedArray flags) {
		super(context, R.layout.spiner_view_item, items);
		this.context = context;
		this.items = items;
		this.flags = flags;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.spiner_view_item, parent, false);
		}
		CurrencyItem item = items.get(position);
		TextView textView = (TextView)convertView.findViewById(R.id.currency_text);
		textView.setText(item.getCurrency().name());

//		ImageView image = (ImageView)convertView.findViewById(R.id.list_item_icon);
//		image.setImageResource(flags.getResourceId(position, -1));

		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public CurrencyItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
