package com.studydrive.studydrive;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProducerCustomerAdapter extends RecyclerView.Adapter<ProducerCustomerAdapter.ProducerCustomerViewHolder> {

    private ArrayList<ProducerCustomerItem> producerCustomerList;

    public static class ProducerCustomerViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ProducerCustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
        }
    }

    public ProducerCustomerAdapter(ArrayList<ProducerCustomerItem> producerCustomerItems) {
        this.producerCustomerList = producerCustomerItems;

    }

    @NonNull
    @Override
    public ProducerCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.producer_customer_list_item, parent, false);
        ProducerCustomerViewHolder viewHolder = new ProducerCustomerViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerCustomerViewHolder holder, int position) {
        ProducerCustomerItem producerCustomerItem = this.producerCustomerList.get(position);
        holder.nameTextView.setText(producerCustomerItem.getName());

    }

    @Override
    public int getItemCount() {
        return this.producerCustomerList.size();
    }
}
