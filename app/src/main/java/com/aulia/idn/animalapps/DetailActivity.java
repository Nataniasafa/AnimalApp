package com.aulia.idn.animalapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    //deklarasi
    private TextView detailName, detailInfo, detailLatin, detailHabitat, detailIlmiah;
    private ImageView detailPhoto;
    private Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //inisialisasi
        detailName = findViewById(R.id.tv_detail_name);
        detailInfo = findViewById(R.id.tv_detail_info);
        detailLatin = findViewById(R.id.tv_detail_latin);
        detailHabitat = findViewById(R.id.tv_detail_habitat);
        detailIlmiah = findViewById(R.id.tv_detail_klasifikasi);
        detailPhoto = findViewById(R.id.img_detail_photo);

        //membuat uniq tag
        final Animal data = (Animal)getIntent().getSerializableExtra("EXTRA_DATA");
        //Get dan Set data
        Glide.with(this).load(data.getPhoto()).apply(new RequestOptions()
                .override(550,350))
                .into(detailPhoto);
        detailName.setText(data.getName());
        detailInfo.setText(data.getInfo());
        detailHabitat.setText(data.getHabitat());
        detailLatin.setText(data.getLatin());
        detailIlmiah.setText(data.getClassification());
        //pemanggil dan pengaktifan button serta penyimpanan data dengan SP
        btnFavorite = findViewById(R.id.btn_fav);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("FavoriteID",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nameAnimFav", data.getName());
                editor.putString("infoAnimFav", data.getInfo());
                editor.putString("habitatAnimFav", data.getHabitat());
                editor.putString("imgAnimFav", data.getPhoto());
                editor.putString("latinAnimFav", data.getLatin());
                editor.putString("ilmiahAnimFav", data.getClassification());
                editor.commit();
                Toast.makeText(DetailActivity.this, data
                        .getName()+"ditambahkan ke Favorite", Toast.LENGTH_SHORT).show();
            }
        });

    }//1
}
