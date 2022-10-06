package com.hanaahany.khalidtaskorange.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.hanaahany.khalidtaskorange.databinding.ActivityMainBinding;
import com.hanaahany.khalidtaskorange.model.ProductResponse;
import com.hanaahany.khalidtaskorange.model.ProductAPI;
import com.hanaahany.khalidtaskorange.model.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.recycler.setLayoutManager(layoutManager);
        ArrayList<ProductResponse>arrayList=new ArrayList<>();
        ProductAPI productAPI= RetrofitClient.getInstance().create(ProductAPI.class);
        productAPI.getProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    ProductResponse productResponse=response.body();
                    for (int i=0;i<productResponse.getProductDetails().size();i++){
                        productAdapter=new ProductAdapter(arrayList);
                        Log.i(TAG, "onResponse: "+productResponse);
                        arrayList.add(productResponse);

                    }




                binding.recycler.setAdapter(productAdapter);
                Log.i(TAG, "onCreate: "+arrayList.toString());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });


    }
}