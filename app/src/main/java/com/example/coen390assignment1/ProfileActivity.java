package com.example.coen390assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameEditTextView;
    private TextView ageEditTextView;
    private TextView studentIDEditTextView;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView studentIDTextView;
    private Button saveButton;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUI();
        //up navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        sharedPreferenceHelper = new SharedPreferenceHelper(ProfileActivity.this);

        String name = sharedPreferenceHelper.getProfileName();
        nameEditTextView.setText(name);
        String age= sharedPreferenceHelper.getProfileAge();
        ageEditTextView.setText(age);
        String id= sharedPreferenceHelper.getProfileID();
        studentIDEditTextView.setText(id);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditTextView.getText().toString();
                int age = Integer.parseInt(ageEditTextView.getText().toString());
                int id = Integer.parseInt(studentIDEditTextView.getText().toString());

                sharedPreferenceHelper.saveProfileName(name);
                sharedPreferenceHelper.saveProfileAge(age);
                sharedPreferenceHelper.saveProfileID(id);
            }
        });
    }
    //disable input
    private void disable(){
        saveButton.setVisibility(View.GONE);
        nameEditTextView.setEnabled(false);
        ageEditTextView.setEnabled(false);
        studentIDEditTextView.setEnabled(false);
    }

    private void setupUI(){


        saveButton = findViewById(R.id.saveButton);

        nameEditTextView = findViewById(R.id.nameEditText);
        ageEditTextView = findViewById(R.id.ageEditText);
        studentIDEditTextView = findViewById(R.id.studentIDEditText);

        nameTextView = findViewById(R.id.nameTextView);
        ageTextView = findViewById(R.id.ageTextView);
        studentIDTextView = findViewById(R.id.studenIDTextView);
        disable();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit:
                saveButton.setVisibility(View.VISIBLE);
                nameEditTextView.setEnabled(true);
                ageEditTextView.setEnabled(true);
                studentIDEditTextView.setEnabled(true);
                break;
            case R.id.display:
                disable();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

