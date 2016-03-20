package com.example.zhenmin.justplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hasee on 2016/1/24.
 */
public class AdapterForContacts extends RecyclerView.Adapter {
    private List<Contact> contacts;
    private Context context;

    AdapterForContacts(Context context,List<Contact> contacts){
        this.contacts = contacts;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvPhone;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.ceil_contact_name);
            tvPhone = (TextView) itemView.findViewById(R.id.ceil_contact_phone);
        }

        public TextView getTvName(){return tvName;}
        public TextView getTvPhone(){return tvPhone;}
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_ceil,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(contacts.get(position).getName());
        viewHolder.tvPhone.setText(contacts.get(position).getPhone());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


}
