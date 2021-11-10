package com.aulia.idn.animalapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.text.BreakIterator;
import java.util.ArrayList;

public class FavoriteAnimalAdapter extends RecyclerView.Adapter<FavoriteAnimalAdapter.ViewHolder> implements Serializable {
    private ArrayList<Animal> listAnimal;

    public FavoriteAnimalAdapter(ArrayList<Animal> listAnimal) {
        this.listAnimal = listAnimal;
    }

    @NonNull
    @Override
    public FavoriteAnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fav_animal, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAnimalAdapter.ViewHolder holder, int position) {
        final Animal animal = listAnimal.get(position);
        Glide.with(holder.itemView.getContext())
                .load(animal.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);
        holder.tvName.setText(animal.getName());
        holder.tvInfo.setText(animal.getInfo());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listAnimal
                        .get(holder.getAdapterPosition()));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClickBack(listAnimal.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;
        try{
            size = listAnimal.size();
        }catch (NullPointerException e){
            size = 0;
        }
        return size;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         ImageView imgPhoto;
         TextView tvName;
         TextView tvInfo;
         Button btnRemove;

         ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_fav_item);
            tvName = itemView.findViewById(R.id.tv_fav_name);
            tvInfo = itemView.findViewById(R.id.tv_fav_info);
            btnRemove = itemView.findViewById(R.id.btn_set_favorite);
        }
    }
    private onItemClickCallBack onItemClickCallBack;//a

    public void setOnItemClickCallBack(FavoriteAnimalAdapter.onItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;//a
    }

    public interface onItemClickCallBack{
        void onItemClicked (Animal data);
        void onItemClickBack(Animal data);//a
    }


}//1
