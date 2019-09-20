package vn.com.example.demoretrofit.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseURL){
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(8000, TimeUnit.MILLISECONDS)  //thời gian đọc là 8s
                    .connectTimeout(8000, TimeUnit.MILLISECONDS) //thời gian kết nối tới server là 8s
                    .writeTimeout(8,TimeUnit.SECONDS)  //thời gian viết trên server là 8s
                    .retryOnConnectionFailure(true)  //kết nối lại khi server lỗi
                    .build();

            Gson gson = new GsonBuilder().setLenient().create(); //giúp cho convert sang gson tốt hơn
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
