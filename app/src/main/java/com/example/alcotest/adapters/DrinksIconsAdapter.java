package com.example.alcotest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alcotest.R;

import java.util.ArrayList;

/**
 * Created by Backend Developer on 9/10/2017.
 */

public class DrinksIconsAdapter extends RecyclerView.Adapter<DrinksIconsAdapter.ViewHolder> {

    private ArrayList<Integer> drinksIconsId;
    private OnDrinkIconListner mOnDrinkIconListner;

    public DrinksIconsAdapter(ArrayList<Integer> data, OnDrinkIconListner onDrinkIconListner)
    {
        this.mOnDrinkIconListner = onDrinkIconListner;
        this.drinksIconsId = data;
    }

    @Override
    public DrinksIconsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drinks_icons_row, parent, false);

        ViewHolder vh = new ViewHolder(v, mOnDrinkIconListner);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(drinksIconsId.get(position));
    }

    @Override
    public int getItemCount() {
        return drinksIconsId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnDrinkIconListner onDrinkIconListner;
        final ImageView imageView;

        ViewHolder(View v, OnDrinkIconListner onDrinkIconListner){
            super(v);

            this.onDrinkIconListner = onDrinkIconListner;
            imageView = (ImageView)v.findViewById(R.id.drink_icon_inrow);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDrinkIconListner.onDrinkIconClick(getAdapterPosition());
        }
    }

    public interface OnDrinkIconListner{
        void onDrinkIconClick(int position);
    }
}
