package com.example.facebookv2java.ui.main;

import android.util.Log;

import com.example.facebookv2java.data.PostClient;
import com.example.facebookv2java.pojo.PostModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.*;

public class PostViewModel extends ViewModel {
    private static final String TAG ="PostViewModel";
    MutableLiveData<List<PostModel>> postsMutableLiveData=new MutableLiveData<>();
    public void getPosts(){
        Observable<List<PostModel>> observable=PostClient.getInstance().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());

        observable.subscribe(o->postsMutableLiveData.setValue(o),e->Log.d(TAG,"getPosts : "+e.toString()));
    }
}
