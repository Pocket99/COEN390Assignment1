package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button profileButton;
    private Button gradeButton;
    SharedPreferences sharedPreferences;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
        setupUI();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }
        });

        gradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGradeActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String name = sharedPreferenceHelper.getProfileName();
        if(name == null)
            goToProfileActivity();
        else
            profileButton.setText(name);
    }

    private void goToProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void goToGradeActivity(){
        Intent intent = new Intent(this,GradeActivity.class);
        startActivity(intent);
    }

    private void setupUI(){
        profileButton = findViewById(R.id.profileButton);
        gradeButton = findViewById(R.id.gradeButton);
    }
}
