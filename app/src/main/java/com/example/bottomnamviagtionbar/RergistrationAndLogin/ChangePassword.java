package com.example.bottomnamviagtionbar.RergistrationAndLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnamviagtionbar.Helpers.HelperFunctions;
import com.example.bottomnamviagtionbar.Helpers.SharedPrefsUtil;
import com.example.bottomnamviagtionbar.Interfaces.User;
import com.example.bottomnamviagtionbar.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {

    EditText newPassword, confirmPassword;
    Button Save;
    SharedPrefsUtil sharedPrefsUtil;
    HelperFunctions helperFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword =(EditText)findViewById(R.id.etNewPassword);
        confirmPassword =(EditText)findViewById(R.id.etConfirmNPassword);
        Save =(Button)findViewById(R.id.btnSave);

        sharedPrefsUtil = new SharedPrefsUtil(this);
        helperFunctions = new HelperFunctions();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassVal = newPassword.getText().toString().trim();
                String confirmVal = confirmPassword.getText().toString().trim();
                if (helperFunctions.ValidateFields(newPassword, confirmPassword)) {
                    if (helperFunctions.StringEquality(newPassVal, confirmVal)) {
                        String email = sharedPrefsUtil.get("user_email", "");
                        User user = sharedPrefsUtil.get(email, User.class, new User());
                        user.setPassword(newPassVal);
                        sharedPrefsUtil.put(email, User.class, user);
                        sharedPrefsUtil.deleteSavedData("tempPassword");
                        Intent intent = new Intent(ChangePassword.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ChangePassword.this, "Passwords do not match or too short.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
