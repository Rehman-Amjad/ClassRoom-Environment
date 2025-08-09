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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TurbidityScreen extends AppCompatActivity
        implements View.OnClickListener{

    ImageView img_LoginBack;
    TextView tv_date,tv_time,tv_wait;
    Button btn_back,btnSpeedSlow,btnSpeedVerySlow,btnAutoSpeed;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private DatabaseReference fanRef;

    String value,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turbidity_screen);

        fanRef = FirebaseDatabase.getInstance().getReference("Setvalues");
        initView();
        showDataFormFirebse();


        img_LoginBack.setOnClickListener(this::onClick);
        btn_back.setOnClickListener(this::onClick);
        btnSpeedSlow.setOnClickListener(this::onClick);
        btnSpeedVerySlow.setOnClickListener(this::onClick);
        btnAutoSpeed.setOnClickListener(this::onClick);
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
        btnSpeedSlow = findViewById(R.id.btnSpeedSlow);
        btnSpeedVerySlow = findViewById(R.id.btnSpeedVerySlow);
        btnAutoSpeed = findViewById(R.id.btnAutoSpeed);
    }

    @Override
    public void onClick(View view)
    {
        int id =view.getId();

        if(id == R.id.img_LoginBack || id == R.id.btn_back){
            startActivity(new Intent(TurbidityScreen.this,DashboardScreen.class));
            finish();
        }

        if(id == R.id.btnSpeedSlow){
            fanRef.child("FanSpeed").setValue("7");
            fanRef.child("AutoSpeed").setValue("1");
            Toast.makeText(this, "Fan Speed Slow", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.btnSpeedVerySlow){
            fanRef.child("FanSpeed").setValue("8");
            fanRef.child("AutoSpeed").setValue("1");
            Toast.makeText(this, "Fan Speed very Slow", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.btnAutoSpeed){
            fanRef.child("FanSpeed").setValue("0");
            fanRef.child("AutoSpeed").setValue("0");
            Toast.makeText(this, "Fan Auto Speed", Toast.LENGTH_SHORT).show();
        }


    }
}