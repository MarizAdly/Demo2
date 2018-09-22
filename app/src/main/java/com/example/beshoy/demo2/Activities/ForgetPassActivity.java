package com.example.beshoy.demo2.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beshoy.demo2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ImageButton back;
    TextView title, paragraph;
    Button send;
    EditText emailAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        back = findViewById(R.id.back);
        title = findViewById(R.id.titleView);
        paragraph = findViewById(R.id.textView2);
        //send = findViewById(R.id.button);
        emailAd = findViewById(R.id.emailAddEditText);


        mAuth = FirebaseAuth.getInstance();
    }

    public void back(View view) {
        Intent intent = new Intent (ForgetPassActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void sendEmail(View view) {


        final String emailAdd = emailAd.getText().toString();
        if (TextUtils.isEmpty(emailAdd)){
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
        }
        else
            mAuth.sendPasswordResetEmail(emailAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetPassActivity.this, "Check Your MailBox", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgetPassActivity.this, MainActivity.class));
                    } else {
                        String msg = task.getException().getMessage();
                        Toast.makeText(ForgetPassActivity.this, "Error Occured" + msg, Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}
