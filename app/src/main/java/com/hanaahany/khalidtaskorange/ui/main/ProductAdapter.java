package com.hanaahany.khalidtaskorange.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.khalidtaskorange.R;
import com.hanaahany.khalidtaskorange.model.ProductResponse;
import com.hanaahany.khalidtaskorange.ui.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.ProductVH>{

    ArrayList<ProductResponse>arrayListResponse;
    private static final String TAG = "ProductAdapter";

    public ProductAdapter(ArrayList<ProductResponse> productResponses) {
        this.arrayListResponse = productResponses;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);

        return new ProductVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {

        ProductResponse productResponse=arrayListResponse.get(position);
        holder.textViewName.setText(productResponse.getProductDetails().get(position).getTitle());
        holder.textViewPrice.setText( productResponse.getProductDetails().get(position).getPrice()+"");
       // holder.textViewDetails.setText(productResponse.getProductDetails().get(holder.getAdapterPosition()).getDescription());
        Picasso.get().load(productResponse.getProductDetails().get(holder.getAdapterPosition()).getImages().get(0)).into(holder.imageViewProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("ID",productResponse.getProductDetails().get(holder.getAdapterPosition()).getId());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayListResponse.size();
    }


    class ProductVH extends RecyclerView.ViewHolder {
        TextView textViewPrice,textViewName,textViewDetails;
        ImageView imageViewProduct;
        public ProductVH(@NonNull View itemView) {
            super(itemView);
            textViewPrice=itemView.findViewById(R.id.price_product_item);
            textViewDetails=itemView.findViewById(R.id.details_product_item);
            textViewName=itemView.findViewById(R.id.name_product_item);
            imageViewProduct=itemView.findViewById(R.id.image_item);
        }

    }
}
