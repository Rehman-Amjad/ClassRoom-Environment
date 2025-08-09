package com.example.smartwaterqualitymonitoringsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PHSensorScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView img_LoginBack;
    TextView tv_date,tv_time,tv_wait;
    Button btn_back,btnOpen,btnClose;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private DatabaseReference fanRef;

    String value,date,time;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phsensor_screen);

        fanRef = FirebaseDatabase.getInstance().getReference("Setvalues");
        initView();
        showDataFormFirebse();


        img_LoginBack.setOnClickListener(this::onClick);
        btn_back.setOnClickListener(this::onClick);
        btnOpen.setOnClickListener(this::onClick);
        btnClose.setOnClickListener(this::onClick);


        btnClose.setVisibility(View.GONE);
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

                time = snapshot.child("Timed").getValue(String.class);
                date = snapshot.child("Dated").getValue(String.class);

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
        tv_date=findViewById(R.id.tv_date);
        tv_time=findViewById(R.id.tv_time);
        tv_wait=findViewById(R.id.tv_wait);
        btnOpen = findViewById(R.id.btnSpeedSlow);
        btnClose = findViewById(R.id.btnSpeedVerySlow);
    }

    @Override
    public void onClick(View view)
    {
        int id =view.getId();

        if(id == R.id.img_LoginBack || id == R.id.btn_back){
            startActivity(new Intent(PHSensorScreen.this,DashboardScreen.class));
            finish();
        }

        if(id == R.id.btnSpeedSlow){
            fanRef.child("Door").setValue("1");
            Toast.makeText(this, "Door Open", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    fanRef.child("Door").setValue("0");
                }
            },5000);
            Toast.makeText(this, "Door Close", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.btnSpeedVerySlow){
            fanRef.child("Door").setValue("0");
            Toast.makeText(this, "Door Close", Toast.LENGTH_SHORT).show();
        }
    }
}