package com.example.beshoy.demo2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.squareup.picasso.Picasso;

public class ProfActivity extends AppCompatActivity {

    User user;
    ImageView profilePhoto;
    TextView nameView, phoneView, bdview, addressView;
    TextView nameEdit, phoneEdit, bdEdit, addEdit;
    ImageButton call, msg;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_prof );

        profilePhoto = findViewById ( R.id.profileView );
        call = findViewById ( R.id.callBtn );
        msg = findViewById ( R.id.msgBtn );


        nameView = findViewById ( R.id.nameView );
        nameEdit = findViewById ( R.id.name );

        phoneView = findViewById ( R.id.phoneView );
        phoneEdit = findViewById ( R.id.phone );

        bdview = findViewById ( R.id.bdView );
        bdEdit = findViewById ( R.id.birthday );

        addressView = findViewById ( R.id. addressView  );
        addEdit = findViewById ( R.id.address );

        Intent intent = getIntent ();
        user = (User ) intent.getSerializableExtra ( "user" ) ;

        nameEdit.setText ( user.getDisplayName () );
        nameEdit.setText ( user.getDisplayName () );
        phoneEdit.setText ( user.getPhoneNo ());
        bdEdit.setText ( user.getBirthDate () );
        addEdit.setText ( user.getAddress () );
        Picasso.get ().load ( user.getUserImage () ).into ( profilePhoto);
    }

    public void call ( View view ) {
        Intent intent = new Intent (Intent.ACTION_DIAL);
        startActivity(intent);
    }

    public void msg ( View view ) {
        try {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData( Uri.parse("sms:"));
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    public void Home ( View view ) {
        Intent intent = new Intent ( ProfActivity.this, HomeActivity.class );
        startActivity ( intent );
    }

    public void friends ( View view ) {
        Intent intent = new Intent ( ProfActivity.this, UsersActivity.class );
        startActivity ( intent );
    }
}

