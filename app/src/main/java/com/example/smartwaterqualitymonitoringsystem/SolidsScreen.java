package com.example.smartwaterqualitymonitoringsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SolidsScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView img_LoginBack;
    TextView tv_value,tv_date,tv_time,tv_wait;

    EditText ed_temp,ed_humidity;
    Button btn_back,btnSave;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String value,date,time,humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solids_screen);

        initView();
        showDataFormFirebse();


        img_LoginBack.setOnClickListener(this::onClick);
        btn_back.setOnClickListener(this::onClick);

        btnSave.setOnClickListener(this::onClick);
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

                value = snapshot.child("Temp").getValue(String.class);
                humidity = snapshot.child("Humidity").getValue(String.class);
                time = snapshot.child("Timed").getValue(String.class);
                date = snapshot.child("Dated").getValue(String.class);



                tv_value.setText("Temperature is: "+value + "C" + "\n" + "Humidity is " + humidity + "%");
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
        ed_humidity=findViewById(R.id.ed_humidity);
        ed_temp=findViewById(R.id.ed_temp);
        btnSave=findViewById(R.id.btnSave);
    }

    @Override
    public void onClick(View view)
    {
        int id =view.getId();

        if(id == R.id.img_LoginBack || id == R.id.btn_back){
            startActivity(new Intent(SolidsScreen.this,DashboardScreen.class));
            finish();
        }

        if(id == R.id.btnSave){
            setValue();
        }


    }

    void setValue(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Setvalues");

        String temp = ed_temp.getText().toString();
        String humidity = ed_humidity.getText().toString();

        if(temp.isEmpty()){

        }else{
            myRef.child("SetTempValue").setValue(temp);
        }

        if(humidity.isEmpty()){

        }else{
            myRef.child("SetHumidityValue").setValue(humidity);
        }

        Toast.makeText(this, "Value Saved", Toast.LENGTH_SHORT).show();



    }

}