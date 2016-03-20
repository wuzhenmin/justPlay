package com.example.zhenmin.justplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhenmin on 2015/12/4.
 */
class MyAdapter extends RecyclerView.Adapter {

    class ViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private TextView tvTitle,tvContent;
        public ViewHolder(View root) {
            super(root);
            tvContent = (TextView) root.findViewById(R.id.tvContent);
            tvTitle = (TextView) root.findViewById(R.id.tvTitle);
        }

        public TextView getTvContent() {
            return tvContent;
        }

        public TextView getTvTitle() {
            return tvTitle;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cell,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;
        CellData cellData = data[i];
        vh.getTvTitle().setText(cellData.title);
        vh.getTvContent().setText(cellData.content);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    private CellData [] data = new CellData[]{new CellData("hello","world"),new CellData("good","morning")};
}
