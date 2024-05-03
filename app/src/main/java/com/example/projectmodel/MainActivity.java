package com.example.projectmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    taskDAO dao;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // addTask();
    }

    public void getTask(View view)
    {
        System.out.print("Enter getTask");
        textView=findViewById(R.id.textView);
        dao=new taskDAO();
        dao.getAllTasks(taskList -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                String str = taskList.toString();
                textView.setText(str);
            });
        });
    }

    public void go_to_dashboard(View view)
    {
        startActivity(new Intent(MainActivity.this,DashboardActivity.class));
    }

    public void addTask()
    {
        dao=new taskDAO();


        dao.insertTask(new Task(), success -> {
            if (success) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Task inserted successfully", Toast.LENGTH_SHORT).show();
                    // Refresh task list or update UI as needed

                });
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Failed to insert task", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }



}