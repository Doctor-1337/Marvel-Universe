package com.oyelabs.marveluniverse.viewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oyelabs.marveluniverse.model.Result;
import com.oyelabs.marveluniverse.model.RootObj;
import com.oyelabs.marveluniverse.network.APIService;
import com.oyelabs.marveluniverse.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharListViewModel extends ViewModel {


    private MutableLiveData<List<Result>> charList;

    public CharListViewModel(){
        charList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getCharListObserver(){
        return charList;
    }

    public void makeApiCall(String query){
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<RootObj> call = apiService.getCharList(query,1,"da74d4f33040f360e6892fa5db8293ae","c1fa3ddcf55c35dc96b526ddcfd499af");
        call.enqueue(new Callback<RootObj>() {
            @Override
            public void onResponse(Call<RootObj> call, Response<RootObj> response) {
                Log.i("FAiled Attempt URL",response.raw().request().url().toString());
                if(response.body().getData().getCount() != 0){
                Log.i("Success","are we here");
                charList.postValue(response.body().getData().getResults());
                }else{
                    Log.i("No Char Found","Search Result 0");

                }
            }

            @Override
            public void onFailure(Call<RootObj> call, Throwable t) {
                Log.i("FAiled Attempt URL","No way");
            }
        });
    }

}
