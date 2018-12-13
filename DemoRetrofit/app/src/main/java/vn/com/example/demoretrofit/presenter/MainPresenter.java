package vn.com.example.demoretrofit.presenter;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.example.demoretrofit.R;
import vn.com.example.demoretrofit.model.data.Data;
import vn.com.example.demoretrofit.retrofit.RetrofitAPI;
import vn.com.example.demoretrofit.retrofit.RetrofitApiUtils;


public class MainPresenter implements MainContact.Presenter {

    private Context context;
    private MainContact.View mainView;
    private RetrofitAPI retrofitAPI;

    public MainPresenter(Context context, MainContact.View mainView) {
        this.context = context;
        this.mainView = mainView;
        retrofitAPI = RetrofitApiUtils.getInstance();
    }

    @Override
    public void getData() {
        retrofitAPI.getData().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                mainView.getDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                mainView.getDataError(context.getString(R.string.error_get_data));
            }
        });
    }
}
