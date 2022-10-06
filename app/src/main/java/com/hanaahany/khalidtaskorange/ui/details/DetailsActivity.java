package com.hanaahany.khalidtaskorange.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hanaahany.khalidtaskorange.databinding.ActivityDetailsBinding;
import com.hanaahany.khalidtaskorange.model.ProductAPI;
import com.hanaahany.khalidtaskorange.model.ProductDetails;
import com.hanaahany.khalidtaskorange.model.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    ActivityDetailsBinding binding;
    ArrayList<String> arrayList=new ArrayList<String>();
    ImageDetailsAdapter imageDetailsAdapter;
    int id;
    double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
         id=intent.getIntExtra("ID",0);
        Log.i(TAG, "onCreate: "+intent.getIntExtra("ID",0));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.recyclerImage.setLayoutManager(layoutManager);
        setViews();

        onClicks();

    }

    private void onClicks() {
        binding.tvAmountActivityDetails.setText("1");
        binding.increaseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount= Integer.parseInt((binding.tvAmountActivityDetails.getText()+""));
                binding.tvAmountActivityDetails.setText(amount+1+"");
                binding.tvPriceActivityDetails.setText(price*(amount+1)+"");
            }
        });

        binding.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount= Integer.parseInt((binding.tvAmountActivityDetails.getText()+""));
                if (amount==1){
                    Toast.makeText(DetailsActivity.this, "Cant decrease", Toast.LENGTH_SHORT).show();
                }else{
                    binding.tvAmountActivityDetails.setText(amount-1+"");
                    double price1= Double.parseDouble(binding.tvPriceActivityDetails.getText()+"");
                    binding.tvPriceActivityDetails.setText(price1-price+"");

                }
            }
        });
    }

    private void setViews() {

        ProductAPI productAPI= RetrofitClient.getInstance().create(ProductAPI.class);
       productAPI.getProductDetails(id).enqueue(new Callback<ProductDetails>() {
           @Override
           public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
               Log.i(TAG, "onResponse: "+response.body());

               binding.tvPriceActivityDetails.setText(response.body().getPrice()+"");
               price=response.body().getPrice();
               binding.tvTitleActivityDetails.setText(response.body().getTitle());
               binding.tvDescriptionActivityDetails.setText(response.body().getDescription());
               binding.tvRateActivityDetails.setText(response.body().getRating()+"");
               for (int i = 0; i <response.body().getImages().size() ; i++) {

                   arrayList.add(response.body().getImages().get(i));

                   imageDetailsAdapter=new ImageDetailsAdapter(arrayList);


               }
               binding.recyclerImage.setAdapter(imageDetailsAdapter);

              // Picasso.get().load(response.body().getImages().get(i)).into(binding.imageItemActivityDetails);

           }

           @Override
           public void onFailure(Call<ProductDetails> call, Throwable t) {

           }
       });
    }
}