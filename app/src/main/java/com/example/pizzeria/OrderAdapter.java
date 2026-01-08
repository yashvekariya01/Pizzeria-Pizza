package com.example.pizzeria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orderList;

    // Constructor: Jo list hum pass karenge use yahan set karega
    public OrderAdapter(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Yahan aapka banaya hua row_order design load hoga
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Ek-ek karke data set karega
        Order currentOrder = orderList.get(position);
        holder.name.setText(currentOrder.getName());
        holder.table.setText("Table No: " + currentOrder.getTable());
        holder.time.setText(currentOrder.getTime());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // Design ke elements ko identify karne ke liye
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView name, table, time;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvOrderName);
            table = itemView.findViewById(R.id.tvOrderTable);
            time = itemView.findViewById(R.id.tvOrderTime);
        }
    }
}