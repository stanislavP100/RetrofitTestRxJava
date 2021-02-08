package com.example.retrofittestrxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.NumberViewHolder>{

    private int mNumberItems;

    private Response<List<Product>> products;


    public Adapter(Response<List<Product>> product) {
        mNumberItems = product.body().size();
        this.products=product;
        //  bb=image;
    }

    public Adapter( ArrayList<Product> products) {

    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_main2;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new NumberViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        holder.setDetails(products, position);

    }


    @Override
    public int getItemCount() {

        return mNumberItems;
    }


    static class NumberViewHolder extends RecyclerView.ViewHolder {


        TextView viewHolderIndex;
        ImageView imageView;


        NumberViewHolder(View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.my_image);

            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance);
        }


        void setDetails(Response<List<Product>> products, int p) {
            viewHolderIndex.setText(products.body().get(p).getName());
            imageView.setImageBitmap(products.body().get(p).getImageBitmap());

        }



    }
}