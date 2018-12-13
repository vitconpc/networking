package vn.com.example.demoretrofit.retrofit;

public class RetrofitApiUtils {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static RetrofitAPI instance;
    public static RetrofitAPI getInstance() {
        if (instance == null) {
            instance = RetrofitClient.getClient(BASE_URL).create(RetrofitAPI.class);
        }
        return instance;
    }
}
