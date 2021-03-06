package vn.com.example.demoparsejson.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.com.example.demoparsejson.R;
import vn.com.example.demoparsejson.custom.adapter.DataAdapter;
import vn.com.example.demoparsejson.model.data.Data;
import vn.com.example.demoparsejson.model.callback.DataCallback;
import vn.com.example.demoparsejson.presenter.MainContact;
import vn.com.example.demoparsejson.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContact.View,DataCallback {

    private RecyclerView rvData;
    private DataAdapter dataAdapter;
    private List<Data> datas;
    private MainContact.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        datas = new ArrayList<>();
        dataAdapter = new DataAdapter(datas,this,this);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setHasFixedSize(true);
        rvData.setAdapter(dataAdapter);
        mainPresenter = new MainPresenter(this, this);
    }

    private void initView() {
        rvData = findViewById(R.id.rv_data);
    }

    @Override
    public void getDataSuccess(List<Data> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void getData(View view) {
        mainPresenter.getData();
    }

    @Override
    public void onClickItem(Data data) {
        Toast.makeText(this, data.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
