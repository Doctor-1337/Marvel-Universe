package com.oyelabs.marveluniverse;

import static com.oyelabs.marveluniverse.MainActivity.flag;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oyelabs.marveluniverse.model.Item;
import com.oyelabs.marveluniverse.model.Result;
import com.oyelabs.marveluniverse.util.ObjectWrapperBinder;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CharFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CharFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CharFragment.
     */
    public static CharFragment newInstance(Result selectedHero) {
        CharFragment fragment = new CharFragment();
        Bundle args = new Bundle();
        args.putBinder("my_bundle", new ObjectWrapperBinder(selectedHero));
        //args.putBundle("my_bundle",args);
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_char, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Result selectedHero = null;
        if (getArguments() != null) {
            selectedHero = ((ObjectWrapperBinder) getArguments().getBinder("my_bundle")).getData();
            //getArguments().getBundle("my_bundle");
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            System.out.println(selectedHero.getName() + "we in fragment");
        } else {
            return;
        }
        ImageView imageView = view.findViewById(R.id.heroImageLandscape);
        TextView mTextView = view.findViewById(R.id.heroNameFragment);

        mTextView.setText(selectedHero.getName());

        String extension = selectedHero.getThumbnail().getExtension();
        String path = selectedHero.getThumbnail().getPath();
        String size = "/landscape_large.";
        String imagePath = path+size+extension;
        Uri myUri = Uri.parse(imagePath);


        Glide.with(getContext())
                .load(myUri)
                .apply(RequestOptions.centerCropTransform())
                .fitCenter()
                .into(imageView);
        TextView descTextView = view.findViewById(R.id.descTextFragment);
        descTextView.append(selectedHero.getDescription());

        int availableComics = selectedHero.getComics().getAvailable();
        if(availableComics != 0){
            List<Item> comicsList = selectedHero.getComics().getItems();
            for(int i=0;i<comicsList.size();i++) {
                TextView textView = view.findViewById(R.id.comicsFragmentText);
                textView.append(comicsList.get(i).getName()+"\n");
            }
        }






    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FragmentOnDestroy","I was called");
        flag = true;
    }
}