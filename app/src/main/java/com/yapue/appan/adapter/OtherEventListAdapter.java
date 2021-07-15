package com.yapue.appan.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yapue.appan.R;
import com.yapue.appan.activity.event.OtherEventDescriptionActivity;
import com.yapue.appan.models.OtherEventDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomTextView;
import com.yapue.appan.utils.CustomTextViewBold;
import com.yapue.appan.utils.ProjectUtils;

import java.util.ArrayList;

/**
 * Created by hemant on 16/2/18.
 */

public class OtherEventListAdapter extends RecyclerView.Adapter<OtherEventListAdapter.MyViewHolder> {


    private ArrayList<OtherEventDTO> otherEventDTOList;
    private SharedPrefrence prefrence;
    private Context context;

    public OtherEventListAdapter(Context context, ArrayList<OtherEventDTO> otherEventDTOList) {
        this.otherEventDTOList = otherEventDTOList;
        this.context = context;
        prefrence = SharedPrefrence.getInstance(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.ctvbEvntTitle.setText(ProjectUtils.getFirstLetterCapital(otherEventDTOList.get(position).getEvent_name()));
        holder.ctvDate.setText(otherEventDTOList.get(position).getEvent_date());
        holder.ctvDiscription.setText(otherEventDTOList.get(position).getAddress());

        Glide.with(context)
                .load(otherEventDTOList.get(position).getImage())
                .placeholder(R.drawable.default_error)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IVevent);

        holder.rlEventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, OtherEventDescriptionActivity.class);
                in.putExtra(Consts.EVENT_DTO, otherEventDTOList.get(position));
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherEventDTOList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlEventList;
        CustomTextViewBold ctvbEvntTitle;
        CustomTextView ctvDate, ctvDiscription;
        ImageView IVevent;

        public MyViewHolder(View item) {
            super(item);
            rlEventList = item.findViewById(R.id.rlEventList);
            ctvbEvntTitle = item.findViewById(R.id.ctvbEvntTitle);
            ctvDate = item.findViewById(R.id.ctvDate);
            ctvDiscription = item.findViewById(R.id.ctvDiscription);
            IVevent = item.findViewById(R.id.IVevent);
        }
    }

}