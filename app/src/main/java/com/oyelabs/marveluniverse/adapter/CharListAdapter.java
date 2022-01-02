package com.oyelabs.marveluniverse.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.oyelabs.marveluniverse.R;
import com.oyelabs.marveluniverse.model.Result;

import java.net.URL;
import java.util.List;

public class CharListAdapter extends RecyclerView.Adapter<CharListAdapter.CharViewHolder> {

   private Context context;
   private List<Result> charList;
   private ItemClickListener clickListener;

    public CharListAdapter(Context context, List<Result> charList, ItemClickListener clickListener) {
        this.context = context;
        this.charList = charList;
        this.clickListener = clickListener;
    }

    public void setCharList(List<Result> charList){
        this.charList = charList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharListAdapter.CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new CharViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharListAdapter.CharViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onHeroClick(charList.get(holder.getAdapterPosition()));
            }
        });
        holder.heroName.setText(this.charList.get(holder.getAdapterPosition()).getName());

       // holder.heroDesc.setText(this.charList.get(position).getDescription());

        String extension = charList.get(position).getThumbnail().getExtension();
        String path = charList.get(position).getThumbnail().getPath();
        String size = "/standard_medium.";
        String imagePath = path+size+extension;
        Uri myUri = Uri.parse(imagePath);


        Glide.with(context)
                .load(myUri)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(this.charList != null){
            return this.charList.size();
        }
            return 0;
    }

    public class CharViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView heroName;
       // TextView heroDesc;

        public CharViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.heroImage);
            heroName = itemView.findViewById(R.id.hero_name);
        //    heroDesc = itemView.findViewById(R.id.textViewdesc);
        }
    }
    public interface ItemClickListener{
        public void onHeroClick(Result hero);
    }
}
