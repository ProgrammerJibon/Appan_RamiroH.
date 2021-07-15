package com.yapue.appan.adapter;


import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yapue.appan.R;
import com.yapue.appan.activity.food.AddressActivity;
import com.yapue.appan.activity.food.OrdershipedActivity;
import com.yapue.appan.databinding.AdapterAddressBinding;
import com.yapue.appan.models.AddressDTO;
import com.yapue.appan.utils.Consts;

import java.util.ArrayList;

/**
 * Created by hemant on 20/05/19.
 */

public class AdapterAddress extends RecyclerView.Adapter<AdapterAddress.MyViewHolder> {
    private AddressActivity mContext;
    ArrayList<AddressDTO> list;
    private LayoutInflater layoutInflater;
    AdapterAddressBinding binding;

    public AdapterAddress(AddressActivity mContext, ArrayList<AddressDTO> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_address, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder mViewHolder, final int position) {

        mViewHolder.binding.ctvbTitle.setText(list.get(position).getAddress());
        mViewHolder.binding.ctvbCity.setText(list.get(position).getCity());
        mViewHolder.binding.ctvbCountry.setText(list.get(position).getCountry());

        mViewHolder.binding.cvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(mContext, OrdershipedActivity.class);
                inn.putExtra(Consts.ADDRESS, list.get(position));
                inn.putExtra(Consts.PAYMENT_STATUS, mContext.totalPay);
                inn.putExtra(Consts.SHIPPING_COST, mContext.shoppingpay);
                inn.putExtra(Consts.DTO, mContext.getIntent().getSerializableExtra(Consts.DTO));
                inn.putExtra(Consts.FLAG, 1);
                mContext.startActivity(inn);
                mContext.finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AdapterAddressBinding binding;

        public MyViewHolder(AdapterAddressBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

}
