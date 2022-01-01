package com.oyelabs.marveluniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oyelabs.marveluniverse.model.RootObj;
import com.oyelabs.marveluniverse.network.APIService;
import com.oyelabs.marveluniverse.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText heroName;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.search_button);
         heroName = findViewById(R.id.searchField);
         text = findViewById(R.id.heroDesc);

        View.OnClickListener mOnClickListener = v -> searchButtonClicked();


        button.setOnClickListener(mOnClickListener);
    }

    private void searchButtonClicked() {
        String BASE_URL = "https://gateway.marvel.com/";
        String input = heroName.getText().toString().trim();

        if(input.equals("")){
            Toast.makeText(this,"Error No Input", Toast.LENGTH_SHORT).show();
            System.out.println("we in if condition");
            return;
        }



        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<RootObj> call = apiService.getCharList(input,1,"da74d4f33040f360e6892fa5db8293ae","c1fa3ddcf55c35dc96b526ddcfd499af");
        call.enqueue(new Callback<RootObj>() {
            @Override
            public void onResponse(Call<RootObj> call, Response<RootObj> response) {
                Log.i("FAiled Attempt URL",response.raw().request().url().toString());
                text.setText(response.body().getCode().toString()+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<RootObj> call, Throwable t) {
                Log.i("FAiled Attempt URL","No way");
            }
        });
    }
}