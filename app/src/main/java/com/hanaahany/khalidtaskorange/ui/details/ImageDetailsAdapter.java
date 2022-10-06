package com.hanaahany.khalidtaskorange.ui.details;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.hanaahany.khalidtaskorange.R;
import com.hanaahany.khalidtaskorange.model.ProductDetails;
import com.hanaahany.khalidtaskorange.ui.main.ProductAdapter;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ImageDetailsAdapter extends RecyclerView.Adapter<ImageDetailsAdapter.ImageDetailsVH> {


    ProductDetails productDetails;
    private static final String TAG = "ImageDetailsAdapter";
    ArrayList<String>arrayList;

    public ImageDetailsAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImageDetailsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);

        return new ImageDetailsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageDetailsVH holder, int position) {
        for (int i=0;i<(arrayList.size());i++){


            Picasso.get().load(arrayList.get(i)).into(holder.imageView);
//            Log.i(TAG, "onBindViewHolder: "+productDetails.getImages().get(i));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ImageDetailsVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        DotsIndicator dotsIndicator;
        public ImageDetailsVH(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_item_activity_details);
            dotsIndicator=itemView.findViewById(R.id.dots_indicator);
        }
    }
}
