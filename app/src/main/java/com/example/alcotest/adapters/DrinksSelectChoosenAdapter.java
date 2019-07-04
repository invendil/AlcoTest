package com.example.alcotest.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.alcotest.DrinksEditor;
import com.example.alcotest.R;
import com.example.alcotest.entities.Drink;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.alcotest.DrinksEditor.COLOR_LIST;
import static com.example.alcotest.DrinksEditor.DRINKS_ITEM_ID;
import static com.example.alcotest.DrinksSelectActivity.drinkORM;

/**
 * Created by Backend Developer on 9/10/2017.
 */

public class DrinksSelectChoosenAdapter extends RecyclerView.Adapter<DrinksSelectChoosenAdapter.ViewHolder> {
    static final String LOG_TAG = "DrinksSelectAdapter";
    private Context contextActivity;
    private ArrayList<Drink> drinksList;
    private OnDrinksSelectChoosenListner mOnDrinksSelectChoosenListner;

    public DrinksSelectChoosenAdapter(ArrayList<Drink> data, OnDrinksSelectChoosenListner onDrinksSelectChoosenListner, Context contextActivity)
    {
        this.contextActivity = contextActivity;
        this.mOnDrinksSelectChoosenListner = onDrinksSelectChoosenListner;
        this.drinksList= data;
        Log.d(LOG_TAG, "constructor created");
    }

    @Override
    public DrinksSelectChoosenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drinks_select_choosen_row, parent, false);

        ViewHolder vh = new ViewHolder(v, mOnDrinksSelectChoosenListner);

        Log.d(LOG_TAG, "ViewHolder created");
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Drawable renderIcon = renderIconColor(drinksList.get(position).getIconId(),drinksList.get(position).getColorFilterId());
        holder.imageView.setImageDrawable(renderIcon);
        holder.textView.setText(drinksList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnDrinksSelectChoosenListner onDrinksSelectListner;
        final ImageView imageView;
        final TextView textView;


        ViewHolder(View v, OnDrinksSelectChoosenListner onDrinksSelectListner){
            super(v);

            this.onDrinksSelectListner = onDrinksSelectListner;
            imageView = (ImageView)v.findViewById(R.id.imageDrinksSelectIcon);
            textView = (TextView)v.findViewById(R.id.textDrinksSelectName);



            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDrinksSelectListner.onDrinksSelectChoosenClick(getAdapterPosition());
        }
    }

    public interface OnDrinksSelectChoosenListner{
        void onDrinksSelectChoosenClick(int position);
    }


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private Drawable renderIconColor(int iconIdId, int iconColorId){
        Drawable renderIcon  = contextActivity.getDrawable(DRINKS_ITEM_ID[iconIdId]).getConstantState().newDrawable().mutate();
        renderIcon.setColorFilter(contextActivity.getColor(COLOR_LIST[iconColorId]), PorterDuff.Mode.SRC_IN );
        return renderIcon;

    }

    public void updateData() {
        drinksList.clear();
        drinksList = drinkORM.getAll("isfavorite = ?", "1");
        notifyDataSetChanged();
    }
}
