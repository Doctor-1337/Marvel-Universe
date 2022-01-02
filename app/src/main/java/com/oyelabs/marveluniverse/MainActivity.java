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




    }
}