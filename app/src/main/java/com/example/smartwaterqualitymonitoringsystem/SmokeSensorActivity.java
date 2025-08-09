package com.example.smartwaterqualitymonitoringsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SmokeSensorActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView img_LoginBack,img,gifImageView;
    TextView tv_value,tv_date,tv_time,tv_wait;
    Button btn_back;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String value,date,time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smoke_sensor);

        initView();
        showDataFormFirebse();

        img_LoginBack.setOnClickListener(this::onClick);
        btn_back.setOnClickListener(this::onClick);

    }

    private void showDataFormFirebse()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CurrentRoomData");
        DatabaseReference callref=myRef.child("1000");

        myRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                value = snapshot.child("Smoke").getValue(String.class);
                time = snapshot.child("Timed").getValue(String.class);
                date = snapshot.child("Dated").getValue(String.class);


                int checkValue = Integer.parseInt(value);

                if (checkValue > 50)
                {
                    tv_value.setText(value+" More Pollution in Classroom");
                    gifImageView.setVisibility(View.GONE);

                }else {
                    tv_value.setText(value+" Normal Pollution in Classroom");
                    gifImageView.setVisibility(View.GONE);
                }
                img.setVisibility(View.VISIBLE);

                tv_time.setText("Time: "+time);
                tv_date.setText("Date: "+date);

                tv_wait.setVisibility(View.INVISIBLE);


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                value = snapshot.child("Smoke").getValue(String.class);
                time = snapshot.child("Timed").getValue(String.class);
                date = snapshot.child("Dated").getValue(String.class);


                int checkValue = Integer.parseInt(value);

                if (checkValue > 50)
                {
                    tv_value.setText(value+" More Pollution in Classroom");
                    gifImageView.setVisibility(View.GONE);

                }else {
                    tv_value.setText(value+" Normal Pollution in Classroom");
                    gifImageView.setVisibility(View.GONE);
                }
                img.setVisibility(View.VISIBLE);

                tv_time.setText("Time: "+time);
                tv_date.setText("Date: "+date);

                tv_wait.setVisibility(View.INVISIBLE);
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
            startActivity(new Intent(SmokeSensorActivity.this,DashboardScreen.class));
            finish();
        }

    }
}