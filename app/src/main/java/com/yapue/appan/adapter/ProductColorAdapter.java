package com.yapue.appan.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yapue.appan.R;

import java.util.ArrayList;

/**
 * Created by hemant on 16/2/18.
 */

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter.MyViewHolder> {

    private ArrayList<String> colorList;

    private Context context;
    public static ArrayList<String> manulist;
    int selectedPosition = -1;

    public ProductColorAdapter(Context context, ArrayList<String> colorList) {
        this.colorList = colorList;
        this.context = context;
        manulist = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productvaluxml, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.chValue.setText(colorList.get(position));

        if (selectedPosition == position) {
            holder.chValue.setChecked(true);
        } else {
            holder.chValue.setChecked(false);

        }

        holder.chValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    manulist.add(colorList.get(position));
                    selectedPosition = holder.getAdapterPosition();
                    notifyDataSetChanged();
                } else {
                    manulist.remove(colorList.get(position));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox chValue;


        public MyViewHolder(View item) {
            super(item);
            chValue = (CheckBox) item.findViewById(R.id.chValue);

        }
    }

}