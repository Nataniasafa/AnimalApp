package com.aulia.idn.animalapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ArrayList<Animal> list = new ArrayList<>(), spaceFavorite = new ArrayList<>();
    private RecyclerView rvAnimal;
    private AnimalData favorite = new AnimalData();
    //1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("FavoriteID", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();

        rvAnimal = findViewById(R.id.rv_animal);
        rvAnimal.setHasFixedSize(true);
        list.addAll(favorite.getListAnimal());
        showRecyclerList();//imp //2
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);//7
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);//8
    }

    private void setMode(int itemId) {
        switch (itemId){
            case R.id.action_main:
                showRecyclerList();
                break;
            case R.id.action_favorite:
                showFavoriteAnimal();
                break;
        }//9
    }

    private void showRecyclerList() {
        rvAnimal.setLayoutManager(new LinearLayoutManager(this));
        ListAnimalAdapter listAnimalAdapter = new ListAnimalAdapter(list);
        rvAnimal.setAdapter(listAnimalAdapter);
        listAnimalAdapter.setSetOnItemClickCallBack(new ListAnimalAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Animal data) {
                showSelectedAnimal(data);
            }
        });//3
    }
    private void showFavoriteAnimal(){
        rvAnimal.setLayoutManager(new LinearLayoutManager(this));
        FavoriteAnimalAdapter favoriteAnimalAdapter = new FavoriteAnimalAdapter(spaceFavorite);
        rvAnimal.setAdapter(favoriteAnimalAdapter);
        favoriteAnimalAdapter.setOnItemClickCallBack(new FavoriteAnimalAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Animal data) {
                removeFavorite(data);
                Toast.makeText(MainActivity.this, data
                        .getName()+"Berhasil Dihapus",Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onItemClickBack(Animal data) {
                showSelectedAnimal(data);
            }
        });

    }//5

    private void removeFavorite(Animal data) {
        spaceFavorite.remove(data);//awalnya this
        showFavoriteAnimal();//6
    }

    private void showSelectedAnimal(Animal data) {
        Intent moveDetail = new Intent(MainActivity.this, DetailActivity.class);
        moveDetail.putExtra("EXTRA_DATA", data);
        startActivity(moveDetail);//4
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("FavoriteID",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();//10
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("FavoriteID",0);
        String name = preferences.getString("nameAnimFav","null");
        String info = preferences.getString("infoAnimFav","null");
        String img = preferences.getString("imgAnimFav","null");
        String latin = preferences.getString("latinAnimFav","null");
        String habitat = preferences.getString("habitatAnimFav","null");
        String ilmiah = preferences.getString("ilmiahAnimFav","null");
        boolean sameName = false;
        if (name != "null"){
            Animal animFav = new Animal();
            for (int i = 0; i< spaceFavorite.size();i++){
                if (name.equals(spaceFavorite.get(i).getName())){
                    sameName = true;
                }
            }
            if (!sameName) {
                animFav.setName(name);
                animFav.setInfo(info);
                animFav.setPhoto(img);
                animFav.setLatin(latin);
                animFav.setHabitat(habitat);
                animFav.setClassification(ilmiah);
                spaceFavorite.add(animFav);//11
            }


        }
    }
}
