package com.hurryyu.bgprogress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hurryyu.bgprogress.DataBean;
import com.hurryyu.bgprogress.MoneyItemView;
import com.hurryyu.bgprogress.R;

import java.util.List;

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.ViewHolder> {

    private List<DataBean> mListData;
    private Context mContext;

    public CustomViewAdapter(Context context, List<DataBean> list) {
        this.mListData = list;
        this.mContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final DataBean dataBean = mListData.get(position);
        holder.moneyItemView.setLeftText(dataBean.getMoney());
        holder.moneyItemView.setRightText(dataBean.getCount());
        holder.moneyItemView.setProgressPercent((float) dataBean.getPercent());
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private MoneyItemView moneyItemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            moneyItemView = itemView.findViewById(R.id.money_item_view);
        }
    }
}
