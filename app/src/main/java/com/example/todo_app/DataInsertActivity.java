package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todo_app.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
ActivityDataInsertBinding binding;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type=getIntent().getStringExtra("type");
        setTitle("Add Mode");
        if(type.equals("update")){
            setTitle("update");

            binding.title.setText(getIntent().getStringExtra("title"));
            binding.disc.setText(getIntent().getStringExtra("disc"));
            int id=getIntent().getIntExtra("id",0);
            binding.add.setText("Update note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("title",binding.title.getText().toString());
                    intent.putExtra("disc",binding.disc.getText().toString());
                    setResult(RESULT_OK,intent);
                    intent.putExtra("id",id);
                    finish();
                }
            });

        }else{

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("title",binding.title.getText().toString());
                intent.putExtra("disc",binding.disc.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });}
//        setContentView(R.layout.activity_data_insert);
    }
    @Override
    public  void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}