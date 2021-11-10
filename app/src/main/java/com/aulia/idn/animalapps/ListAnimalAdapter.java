package com.aulia.idn.animalapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.BreakIterator;
import java.text.CollationElementIterator;
import java.util.ArrayList;

public class ListAnimalAdapter extends RecyclerView.Adapter<ListAnimalAdapter.ViewHolder> { //1
    private ArrayList<Animal> ListAnimal;
    private onItemClickCallBack onItemClickCallBack;//2

    public void setSetOnItemClickCallBack(ListAnimalAdapter.onItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;//8
    }

    public ListAnimalAdapter(ListAnimalAdapter.onItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;//4
    }

    public ListAnimalAdapter(ArrayList<Animal> listAnimal) { this.ListAnimal = listAnimal; } //kurang this tadi

    @NonNull
    @Override
    public ListAnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_animal, parent, false);
        return new ViewHolder(view);//5
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAnimalAdapter.ViewHolder holder, int position) {
        Animal animal = ListAnimal.get(position);
        Glide.with(holder.itemView.getContext())
                .load(animal.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(ListAnimal
                        .get(holder.getAdapterPosition()));
            }
        });
        holder.tvName.setText(animal.getName());
        holder.tvInfo.setText(animal.getInfo());//6

    }

    @Override
    public int getItemCount() {
        return ListAnimal.size();//7
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         ImageView imgPhoto;//bakal nongol klo di imp yg di atas tuh
         TextView tvName;//sama jg
         TextView tvInfo;//sama jg

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvInfo = itemView.findViewById(R.id.tv_item_info);//8
        }
    }
    public interface onItemClickCallBack {
        void onItemClicked(Animal data);
    }//3
}

