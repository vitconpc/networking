package vn.com.example.demoparsejson.presenter;

import android.content.Context;

import vn.com.example.demoparsejson.model.DataASynctask;


public class MainPresenter implements MainContact.Presenter {

    private Context context;
    private MainContact.View mainView;

    public MainPresenter(Context context, MainContact.View mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    @Override
    public void getData() {
        new DataASynctask(mainView).execute("https://jsonplaceholder.typicode.com/posts");
    }
}
