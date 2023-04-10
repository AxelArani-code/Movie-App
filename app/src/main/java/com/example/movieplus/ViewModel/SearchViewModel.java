package com.example.movieplus.ViewModel;

import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieplus.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

public class SearchViewModel extends RecyclerView.ViewHolder {

    private KenBurnsView posterImagenView;
    public AppCompatTextView posterTitle;



    public SearchViewModel(@NonNull View itemView) {
        super(itemView);

        posterImagenView = itemView.findViewById(R.id.poster_image_view);
        posterTitle = itemView.findViewById(R.id.poster_title);

        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000,new DecelerateInterpolator());
        posterImagenView.setTransitionGenerator(generator);
    }

    public void setPosterImagenView(Context context, String posterUrl) {
        Picasso.get().load(posterUrl).into(posterImagenView);
        //Picasso.get(context).load(posterUrl).into(posterImagenView);
    }
}
