package com.yapue.appan.adapter;

/**
 * Created by hemant on 31/10/17.
 */

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yapue.appan.R;
import com.yapue.appan.activity.chat.OneTwoOneChatDemo;
import com.yapue.appan.models.GetMyMessageDTO;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomTextView;
import com.yapue.appan.utils.CustomTextViewBold;
import com.yapue.appan.utils.ProjectUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<GetMyMessageDTO> getMyMessageDTOlist = new ArrayList<>();


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CustomTextView CTVdescription, CTVdate;
        CustomTextViewBold CTVtitel;
        CircleImageView ivImage;
        RelativeLayout rlMessage;

        public MyViewHolder(View view) {
            super(view);

            CTVdescription = (CustomTextView) view.findViewById(R.id.CTVdescription);
            CTVdate = (CustomTextView) view.findViewById(R.id.CTVdate);
            CTVtitel = (CustomTextViewBold) view.findViewById(R.id.CTVtitel);
            ivImage = view.findViewById(R.id.ivImage);
            rlMessage = view.findViewById(R.id.rlMessage);


        }
    }

    public MyMessageAdapter(Context mContext, ArrayList<GetMyMessageDTO> getMyMessageDTOlist) {
        this.mContext = mContext;
        this.getMyMessageDTOlist = getMyMessageDTOlist;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptercontactmessagexml, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.CTVdescription.setText(getMyMessageDTOlist.get(position).getDescription());
            holder.CTVtitel.setText(getMyMessageDTOlist.get(position).getUser_name());

            Glide.with(mContext)
                    .load(getMyMessageDTOlist.get(position).getUser_img())
                    .placeholder(R.drawable.dummy_user)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivImage);

            holder.rlMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(mContext, OneTwoOneChatDemo.class);
                    in.putExtra(Consts.USER_ID, getMyMessageDTOlist.get(position).getUser_id());
                    in.putExtra(Consts.NAME, getMyMessageDTOlist.get(position).getUser_name());
                    mContext.startActivity(in);
                }
            });

            //String strdate = ProjectUtils.parseDateToddMMyyyy(notificationDTOS.get(position).getCreated_at());

            //holder.CTVdate.setText(notificationDTOS.get(position).getCreated_at());
            holder.CTVdate.setText(ProjectUtils.convertTimestampToDate(ProjectUtils.correctTimestamp(Long.parseLong(getMyMessageDTOlist.get(position).getCreated()))));

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {

        return getMyMessageDTOlist.size();
    }
}