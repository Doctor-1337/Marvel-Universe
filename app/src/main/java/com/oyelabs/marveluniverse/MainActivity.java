package com.oyelabs.marveluniverse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oyelabs.marveluniverse.adapter.CharListAdapter;
import com.oyelabs.marveluniverse.model.Result;
import com.oyelabs.marveluniverse.model.RootObj;
import com.oyelabs.marveluniverse.network.APIService;
import com.oyelabs.marveluniverse.network.RetroInstance;
import com.oyelabs.marveluniverse.viewModel.CharListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CharListAdapter.ItemClickListener {

    EditText heroName;
    TextView text;
    RecyclerView mRecyclerView;
    private List<Result> heroList;
    private CharListAdapter mAdapter;
    private CharListViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.search_button);
         heroName = findViewById(R.id.searchField);

         mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager =  new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new CharListAdapter(this,heroList,this);
        mRecyclerView.setAdapter(mAdapter);

        View.OnClickListener mOnClickListener = v -> searchButtonClicked();


        button.setOnClickListener(mOnClickListener);

        mViewModel = new ViewModelProvider(this).get(CharListViewModel.class);
        mViewModel.getCharListObserver().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                if(mViewModel != null){
                    heroList = results;
                    mAdapter.setCharList(results);
                }
            }
        });

    }

    private void searchButtonClicked() {
        String BASE_URL = "https://gateway.marvel.com/";
        String input = heroName.getText().toString().trim();
        mViewModel.makeApiCall(input);

        if(input.equals("")){
            Toast.makeText(this,"Error No Input", Toast.LENGTH_SHORT).show();
            System.out.println("we in if condition");
            return;
        }




    }

    @Override
    public void onHeroClick(Result hero) {
        Log.i("Button CLicked",hero.getName());
    }
}