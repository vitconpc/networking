package vn.com.example.demoretrofit.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import vn.com.example.demoretrofit.model.data.Data;

public interface RetrofitAPI {
    @GET("photos")
    Observable<List<Data>> getData();
}
