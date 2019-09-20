package vn.com.example.demoretrofit.presenter;

import java.util.List;

import vn.com.example.demoretrofit.model.data.Data;

public interface MainContact {
    interface Presenter{
        void getData();

        void onDestroy();
    }

    interface View{
        void getDataSuccess(List<Data> datas);
        void getDataError(String error);
    }
}
