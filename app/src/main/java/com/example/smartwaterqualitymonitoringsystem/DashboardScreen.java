package com.example.smartwaterqualitymonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DashboardScreen extends AppCompatActivity implements View.OnClickListener {

    CardView cardTemperature,cardSolids,cardTubidity,cardPHSensor,tv_history,cardWater,card_delete;
    ImageView img_logout;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        initView();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("History");

        cardTemperature.setOnClickListener(this::onClick);
        cardSolids.setOnClickListener(this::onClick);
        cardTubidity.setOnClickListener(this::onClick);
        cardPHSensor.setOnClickListener(this::onClick);
        img_logout.setOnClickListener(this::onClick);
        tv_history.setOnClickListener(this::onClick);
        cardWater.setOnClickListener(this::onClick);
        card_delete.setOnClickListener(this::onClick);


    }

    private void initView()
    {
        cardTemperature=findViewById(R.id.cardTemperature);
        cardSolids=findViewById(R.id.cardSolids);
        cardTubidity=findViewById(R.id.cardTubidity);
        cardPHSensor=findViewById(R.id.cardPHSensor);
        img_logout=findViewById(R.id.img_logout);
        tv_history=findViewById(R.id.tv_history);
        cardWater=findViewById(R.id.cardWater);
        card_delete=findViewById(R.id.card_delete);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view)
    {
        int id = view.getId();


        if(id == R.id.cardTemperature){
            startActivity(new Intent(DashboardScreen.this,TemperatureScreen.class));
        }else if(id == R.id.cardSolids){
            startActivity(new Intent(DashboardScreen.this,SolidsScreen.class));
        }else if(id == R.id.cardTubidity){
            startActivity(new Intent(DashboardScreen.this,TurbidityScreen.class));
        }else if(id == R.id.cardPHSensor){
            startActivity(new Intent(DashboardScreen.this,PHSensorScreen.class));
        }else if(id == R.id.cardWater){
            startActivity(new Intent(DashboardScreen.this,WaterLevelScreen.class));
        }else if(id == R.id.tv_history){
            startActivity(new Intent(DashboardScreen.this,ShowAllDataScreen.class));
        }else if(id == R.id.card_delete){
            deleteData();
        }else if(id == R.id.img_logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure to logout");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    startActivity(new Intent(DashboardScreen.this,LoginScreen.class));
                    finish();

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog obj = builder.create();
            obj.show();
        }


    }

    private void deleteData()
    {
        myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
           if (task.isSuccessful())
           {
               Toast.makeText(DashboardScreen.this, "History Delete", Toast.LENGTH_SHORT).show();
           }
            }
        });
    }
}