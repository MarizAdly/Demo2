package com.example.beshoy.demo2.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    ImageView logo;
    EditText emailEdit , passwordEdit;
    CheckBox saveLoginCheckBox;
    Button signIn ;
    TextView forget, signUpp;


    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mAuth.getCurrentUser() != null) {
           Toast.makeText ( MainActivity.this, "Hi NewComer, Please Register First! ",Toast.LENGTH_LONG ).show ();

        }

        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        emailEdit = findViewById(R.id.emailAddEditText);
        passwordEdit = findViewById(R.id.passWord);
        saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);
        signIn = findViewById(R.id.signIn);
        signUpp = findViewById(R.id.signUpp);
        forget = findViewById(R.id.forget);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        mAuth = FirebaseAuth.getInstance ( );


        FirebaseDatabase database = FirebaseDatabase.getInstance ( );
        DatabaseReference myRef = database.getReference ( "LogingIn_Users" );
        myRef.child ( mAuth.getCurrentUser ().getUid () ).push().setValue("logging_in_user");

    }

    public void savePass(View view) {
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            emailEdit.setText(loginPreferences.getString("email", ""));
            passwordEdit.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailEdit.getWindowToken(), 0);

        String Email = emailEdit.getText().toString();
        String Password = passwordEdit.getText().toString();


        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("email", Email);
            loginPrefsEditor.putString("password", Password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
    }
    public void logIn(View view) {

        String email = emailEdit.getText ().toString ();
        String password = passwordEdit.getText ().toString ();

        if ( TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter Your Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter Your Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Intent docIntent = new Intent(MainActivity.this, HomeActivity.class);
                            docIntent.putExtra("currentUser", mAuth.getCurrentUser().getUid());
                            startActivity(docIntent);
                            Toast.makeText(MainActivity.this, "Authentication Succeed.", Toast.LENGTH_SHORT).show();

                        } else

                        {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).

                                    show();
                        }
                    }
                });
    }


    public void signUp(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forget(View view) {
Intent passIntent = new Intent(MainActivity.this, ForgetPassActivity.class);
startActivity(passIntent);
    }
}
