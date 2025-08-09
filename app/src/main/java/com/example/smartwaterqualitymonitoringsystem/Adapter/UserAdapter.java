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

        byte[] decodedBytes =
                Base64.decode(user.getImg(), Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        holder.img.setImageBitmap(decodedBitmap);

        holder.tvId.setText("ID: " + user.getId());
        holder.tvATemp_Sensor.setText("Temperature: " + user.getTemp() +"C");
        holder.tv_solids.setText("TDS Sensor: " + user.getFan());
        holder.tv_solids.setText("Humidity" + user.getHumidity() + "%");
        if(user.getLDR().equals("1")){
            holder.tv_solids.setText("LDR: " + "Need Lights in classroom");
        }else{
            holder.tv_solids.setText("LDR: " + "No Need Lights in classroom");
        }

        if(Integer.parseInt(user.getSmoke()) > 50){
            holder.tv_solids.setText("Smoke: " + "More pollution");
        }else{
            holder.tv_solids.setText("Smoke: " + "Normal pollution");
        }

        holder.tv_date.setText("Date: " + user.getDated());
        holder.tv_time.setText("Time: " + user.getTimed());

        holder.tv_PHSensor.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvId,tvATemp_Sensor,tv_PHSensor,tv_tubidity,tv_solids,tv_time,tv_date,tv_water;

        ImageView img;
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
            img=itemView.findViewById(R.id.img);

        }
    }
}
