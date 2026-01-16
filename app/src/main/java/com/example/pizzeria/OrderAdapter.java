package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Order> orderList;

    public OrderAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Row layout ko inflate karna
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_order, parent, false);
        }

        // Data nikalna list se
        Order currentOrder = orderList.get(position);

        // Views ko find karna
        TextView name = convertView.findViewById(R.id.tvOrderName);
        TextView table = convertView.findViewById(R.id.tvOrderTable);
        TextView time = convertView.findViewById(R.id.tvOrderTime);

        // Data set karna
        name.setText(currentOrder.getName());
        table.setText("Table No: " + currentOrder.getTable());
        time.setText(currentOrder.getTime());

        return convertView;
    }
}