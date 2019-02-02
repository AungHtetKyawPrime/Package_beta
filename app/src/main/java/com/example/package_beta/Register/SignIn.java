package com.example.package_beta.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.package_beta.R;

public class SignIn extends AppCompatActivity {
    TextView sign_up;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_sign_in);
        sign_up=(TextView)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });
    }
}
