package com.example.smartwaterqualitymonitoringsystem.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwaterqualitymonitoringsystem.Model.User;
import com.example.smartwaterqualitymonitoringsystem.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private ArrayList<User> mlist;
    private Context context;

    public UserAdapter(ArrayList<User> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //  Glide.with(context).load(mlist.get(position).getImageUrl()).into(holder.imageView);
        User user=mlist.get(position);

        holder.tvId.setText("ID: " + user.getId());
        holder.tvATemp_Sensor.setText("Temperature: " + user.getTemperatureSensor() +"C");
        holder.tv_PHSensor.setText("PH Sensor: " + user.getPHSensor());
        holder.tv_tubidity.setText("Turbidity Sensor: " + user.getTurbiditySensor());
        holder.tv_solids.setText("TDS Sensor: " + user.getId());
        holder.tv_date.setText("Date: " + user.getDated());
        holder.tv_time.setText("Time: " + user.getTimed());

        holder.tv_PHSensor.setVisibility(View.GONE);

        if (user.getWaterlavel().equals("0"))
        {
            holder.tv_water.setText("Water Tank is low level" );
        }else
        {
            holder.tv_water.setText("Water Tank is high level" );
        }



    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvId,tvATemp_Sensor,tv_PHSensor,tv_tubidity,tv_solids,tv_time,tv_date,tv_water;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tvId=itemView.findViewById(R.id.tvId);
            tvATemp_Sensor=itemView.findViewById(R.id.tvATemp_Sensor);
            tv_PHSensor=itemView.findViewById(R.id.tv_PHSensor);
            tv_tubidity=itemView.findViewById(R.id.tv_tubidity);
            tv_solids=itemView.findViewById(R.id.tv_solids);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_water=itemView.findViewById(R.id.tv_water);

        }
    }
}
