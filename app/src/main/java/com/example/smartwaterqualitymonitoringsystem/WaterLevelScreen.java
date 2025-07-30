package com.example.smartwaterqualitymonitoringsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WaterLevelScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView img_LoginBack,img,gifImageView;
    TextView tv_value,tv_date,tv_time,tv_wait;
    Button btn_back;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String value,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level_screen);

        initView();
        showDataFormFirebse();

        img_LoginBack.setOnClickListener(this::onClick);
        btn_back.setOnClickListener(this::onClick);
    }

    private void showDataFormFirebse()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CurrentData");
        DatabaseReference callref=myRef.child("1000");

        myRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                value = snapshot.child("Waterlavel").getValue(String.class);
                time = snapshot.child("Timed").getValue(String.class);
                date = snapshot.child("Dated").getValue(String.class);


                if (value.equals("0"))
                {
                    img.setImageResource(R.drawable.low);
                    tv_value.setText("Water Tank is low level");
                    gifImageView.setVisibility(View.VISIBLE);
                    Glide.with(WaterLevelScreen.this)
                            .asGif()  // Indicate that we are loading a GIF
                            .load(R.drawable.warning)  // Replace with your actual drawable resource name
                            .into(gifImageView);

                }else if (value.equals("1"))
                {
                    img.setImageResource(R.drawable.normal);
                    tv_value.setText("Water Tank is high level");
                    gifImageView.setVisibility(View.GONE);
                }
                img.setVisibility(View.VISIBLE);

                tv_time.setText("Time: "+time);
                tv_date.setText("Date: "+date);

                tv_wait.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView()
    {
        btn_back=findViewById(R.id.btn_back);
        img_LoginBack=findViewById(R.id.img_LoginBack);
        tv_value=findViewById(R.id.tv_value);
        tv_date=findViewById(R.id.tv_date);
        tv_time=findViewById(R.id.tv_time);
        tv_wait=findViewById(R.id.tv_wait);
        img=findViewById(R.id.img);
        gifImageView=findViewById(R.id.gifImageView);
    }

    @Override
    public void onClick(View view)
    {
        int id =view.getId();

        if(id == R.id.img_LoginBack || id == R.id.btn_back){
            startActivity(new Intent(WaterLevelScreen.this,DashboardScreen.class));
            finish();
        }

    }
}