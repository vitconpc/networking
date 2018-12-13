package vn.com.example.demoparsejson.presenter;

import java.util.List;

import vn.com.example.demoparsejson.model.data.Data;

public interface MainContact {
    interface Presenter{
        void getData();
    }

    interface View{
        void getDataSuccess(List<Data> datas);
        void getDataError(String error);
    }
}
