package com.example.smartwaterqualitymonitoringsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

    Button btn_back;
    FirebaseDatabase database;
    DatabaseReference myRef;

    CardView pumpOn_card,pumpOff_card,foodOff_card,foodOn_card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phsensor_screen);

        initView();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Automatic");



        img_LoginBack.setOnClickListener(this::onClick);
//        btn_back.setOnClickListener(this::onClick);

        pumpOn_card.setOnClickListener(v -> {
            saveDataResponse("PumpInfo","1","Pump is On");
        });

        pumpOff_card.setOnClickListener(v -> {
            saveDataResponse("PumpInfo","0","Pump is Off");
        });

        foodOn_card.setOnClickListener(v -> {
            saveDataResponse("TrayInfo","1","Food Tray is On");
        });

        foodOff_card.setOnClickListener(v -> {
            saveDataResponse("TrayInfo","0","Food Tray is Off");
        });
    }


    private void saveDataResponse(String path,String value,String message)
    {
        myRef.child(path)
                .setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(PHSensorScreen.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PHSensorScreen.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private void initView()
    {
//        btn_back=findViewById(R.id.btn_back);
        img_LoginBack=findViewById(R.id.img_LoginBack);
        pumpOn_card = findViewById(R.id.pumpOn_card);
        pumpOff_card = findViewById(R.id.pumpOff_card);
        foodOff_card = findViewById(R.id.foodOff_card);
        foodOn_card = findViewById(R.id.foodOn_card);
    }

    @Override
    public void onClick(View view)
    {
        int id =view.getId();

        switch (id)
        {
            case R.id.img_LoginBack:
            case R.id.btn_back:
                startActivity(new Intent(PHSensorScreen.this,DashboardScreen.class));
                finish();
                break;
        }


    }
}