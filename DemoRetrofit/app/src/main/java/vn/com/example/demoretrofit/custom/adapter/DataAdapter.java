package vn.com.example.demoretrofit.custom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.com.example.demoretrofit.R;
import vn.com.example.demoretrofit.model.callback.DataCallback;
import vn.com.example.demoretrofit.model.data.Data;

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

        private TextView tvTilte;
        private ImageView ivAvatar;
        private ImageView ivThumb;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            setEvent();
        }

        private void initView(View itemView) {
            tvTilte = itemView.findViewById(R.id.tv_title);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            ivThumb = itemView.findViewById(R.id.iv_thumb);
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
            tvTilte.setText(data.getTitle());
            Glide.with(context).load(data.getUrl()).into(ivAvatar);
            Glide.with(context).load(data.getThumbnailUrl()).into(ivThumb);
        }
    }
}
