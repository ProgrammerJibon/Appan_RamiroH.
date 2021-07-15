package com.yapue.appan.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yapue.appan.R;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.activity.food.SubCategoryList;
import com.yapue.appan.models.PetCatList;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomTextViewBold;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by hemant on 16/2/18.
 */

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.MyViewHolder> {

    private ArrayList<PetCatList> petCatList;
    private ArrayList<PetCatList> newPetCatList;
    private SharedPrefrence prefrence;
    public BaseActivity baseActivity;
    public FragmentManager fm;
    private Context context;

    public CatagoryAdapter(Context context, ArrayList<PetCatList> petCatList) {
        this.petCatList = petCatList;
        this.context = context;
        prefrence = SharedPrefrence.getInstance(context);
        newPetCatList = new ArrayList<>();
        newPetCatList.addAll(petCatList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_adapter, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        baseActivity = (BaseActivity) context;
        fm = baseActivity.getSupportFragmentManager();

        holder.ctvbType.setText(petCatList.get(position).getPet_name());
        Glide.with(context).load(petCatList.get(position).getPet_image()).placeholder(R.drawable.app_icon).into(holder.ivType);
        /*for (int i = 0; i <= petCatList.size() - 1; i++) {
            holder.ivType.setImageResource(R.drawable.hero_bulldog);
        }*/

        holder.cvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_event));
                Intent in = new Intent(context, SubCategoryList.class);
                in.putExtra(Consts.P_PET_TYPE, petCatList.get(position).getId());
                in.putExtra(Consts.P_PET_TYPE_NAME, petCatList.get(position).getPet_name());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petCatList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextViewBold ctvbType;
        ImageView ivType;
        CardView cvType;

        public MyViewHolder(View item) {
            super(item);
            ctvbType = (CustomTextViewBold) item.findViewById(R.id.ctvbType);
            ivType = (ImageView) item.findViewById(R.id.ivType);
            cvType = (CardView) item.findViewById(R.id.cvType);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        petCatList.clear();
        if (charText.length() == 0) {
            petCatList.addAll(newPetCatList);
        } else {
            for (PetCatList petCate : newPetCatList) {
                if (petCate.getPet_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    petCatList.add(petCate);
                }
            }
        }
        notifyDataSetChanged();
    }
}