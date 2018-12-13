package vn.com.example.demoparsejson.custom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.example.demoparsejson.R;
import vn.com.example.demoparsejson.model.data.Data;
import vn.com.example.demoparsejson.model.callback.DataCallback;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<Data> datas;
    private Context context;
    private DataCallback callback;

    public DataAdapter(List<Data> datas, Context context, DataCallback callback) {
        this.datas = datas;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DataViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.custom_item_data, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder dataViewHolder, int i) {
        dataViewHolder.bindData(datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserId;
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvBody;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            setEvent();
        }

        private void initView(View itemView) {
            tvId = itemView.findViewById(R.id.tv_id);
            tvBody = itemView.findViewById(R.id.tv_body);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvUserId = itemView.findViewById(R.id.tv_user_id);
        }

        private void setEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickItem(datas.get(getAdapterPosition()));
                }
            });
        }

        public void bindData(Data data) {
            tvTitle.setText(data.getTitle());
            tvBody.setText(data.getBody());
            tvId.setText(data.getId()+"");
            tvUserId.setText(data.getUserId()+"");
        }
    }
}
