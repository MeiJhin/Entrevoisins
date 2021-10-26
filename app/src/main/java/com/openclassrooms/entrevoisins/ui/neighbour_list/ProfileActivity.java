package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {


    private Neighbour neighbour;
    public static final String NEIGHBOUR_ID = "NEIGHBOUR_ID";




    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent profileActivityIntent = getIntent();

        long id = profileActivityIntent.getLongExtra(NEIGHBOUR_ID,0);

        NeighbourApiService apiService = DI.getNeighbourApiService();
        neighbour = apiService.getNeighbourById(id);
        initView();
        initFavoriteButton();


    }

    private void initFavoriteButton() {
        FloatingActionButton favoriteActionButton = findViewById(R.id.floatingbutton);
        updateFavoriteButton(favoriteActionButton);


        favoriteActionButton.setOnClickListener(v -> {
            neighbour.setFavorite(!neighbour.isFavorite());
            updateFavoriteButton(favoriteActionButton);

        });
    }

    private void updateFavoriteButton( FloatingActionButton favoriteActionButton ) {
        if (neighbour.isFavorite()){
            favoriteActionButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        else {
            favoriteActionButton.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }

    private void initView() {
        ImageView mProfileAvatar = findViewById(R.id.profileavatar);
        TextView mProfileName = findViewById(R.id.profilename);
        TextView mProfileAdress = findViewById(R.id.profileadress);
        TextView mProfilePhoneNumber = findViewById(R.id.profilephonenumber);
        TextView mSocialNetworks = findViewById(R.id.socialnetworks);
        TextView mProfileAboutMe = findViewById(R.id.profileaboutme);
        CollapsingToolbarLayout mProfileNameBar = findViewById(R.id.collapsingToolbar);


        Glide.with(mProfileAvatar)
                .load(neighbour.getAvatarUrl())
                .into(mProfileAvatar);
        mProfileName.setText(neighbour.getName());
        mProfileNameBar.setTitle(neighbour.getName());
        mProfileAdress.setText(neighbour.getAddress());
        mProfilePhoneNumber.setText(neighbour.getPhoneNumber());
        mSocialNetworks.setText(String.format(Locale.getDefault(),"%d/%s", R.string.facebook_url, neighbour.getName()));
        mProfileAboutMe.setText(neighbour.getAboutMe());
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}
