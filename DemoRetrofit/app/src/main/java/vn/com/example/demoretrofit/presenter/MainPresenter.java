package vn.com.example.demoretrofit.presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(Context context, MainContact.View mainView) {
        this.context = context;
        this.mainView = mainView;
        retrofitAPI = RetrofitApiUtils.getInstance();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getData() {
        Disposable disposable = retrofitAPI.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            mainView.getDataSuccess(response);
                        }
                        , throwable -> {
                            mainView.getDataError(context.getString(R.string.error_get_data));
                        });
        mCompositeDisposable.add(disposable);
//                enqueue(new Callback<List<Data>>() {
//            @Override
//            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
//                mainView.getDataSuccess(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Data>> call, Throwable t) {
//                mainView.getDataError(context.getString(R.string.error_get_data));
//            }
//        });
    }

    @Override
    public void onDestroy() {
        //tránh trường hợp mất context
        mCompositeDisposable.clear();
    }

}
