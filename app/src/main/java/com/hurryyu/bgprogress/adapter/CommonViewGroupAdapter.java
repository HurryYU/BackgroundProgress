package com.hurryyu.bgprogress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hurryyu.bgprogress.DataBean;
import com.hurryyu.bgprogress.R;

import java.util.List;

public class CommonViewGroupAdapter extends RecyclerView.Adapter<CommonViewGroupAdapter.ViewHolder> {

    private List<DataBean> mListData;
    private Context mContext;

    public CommonViewGroupAdapter(Context context, List<DataBean> list) {
        this.mListData = list;
        this.mContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_common_view_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final DataBean dataBean = mListData.get(position);
        holder.tvCount.setText(dataBean.getCount());
        holder.tvMoney.setText(dataBean.getMoney());
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = holder.viewProgressBg.getLayoutParams();
                layoutParams.width = (int) (holder.itemView.getWidth() * dataBean.getPercent());
                holder.viewProgressBg.setLayoutParams(layoutParams);
            }
        });
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

        private TextView tvMoney;
        private TextView tvCount;
        private View viewProgressBg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvCount = itemView.findViewById(R.id.tv_count);
            viewProgressBg = itemView.findViewById(R.id.view_progress_bg);
        }
    }
}
