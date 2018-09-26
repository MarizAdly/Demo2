package com.example.beshoy.demo2.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.beshoy.demo2.Adapters.PostAdapter;
import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mRecView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    PostAdapter postAdapter;
     private List<Post> postList;
    FirebaseUser mUser;
User user;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        mRecView = findViewById ( R.id.postView );

        postList = new ArrayList <> ( );
         Intent intent = getIntent ();
         user = (User) intent.getSerializableExtra ( "currentUser" );

        mAuth = FirebaseAuth.getInstance ();
        mUser = mAuth.getCurrentUser ();
        firebaseDatabase = FirebaseDatabase.getInstance ( );

        databaseReference = firebaseDatabase.getReference ( "posts" );
        databaseReference.push ( ).setValue ( new Post ( ) );

       databaseReference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( DataSnapshot dataSnapshot ) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren ( )) {
                    Post posts = postSnapshot.getValue ( Post.class );
                    postList.add ( posts );
                    postAdapter.notifyDataSetChanged ( );
                }
            }
            @Override
            public void onCancelled ( DatabaseError databaseError ) {
                Toast.makeText ( HomeActivity.this,databaseError.getMessage (),Toast.LENGTH_LONG ).show ();
            }

        } );

        postAdapter = new PostAdapter ( postList, this );
        mRecView.setAdapter ( postAdapter );
        mRecView.setLayoutManager ( new LinearLayoutManager( this ) );

    }


    public void addPost(View view) {
        Intent addIntent = new Intent ( HomeActivity.this, AddPostActivity.class);
        startActivity ( addIntent );

    }

    public void friends ( View view ) {
        Intent addIntent = new Intent ( HomeActivity.this,UsersListActivity.class);
        startActivity ( addIntent );
    }

    public void signOut ( View view ) {

        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
    }
}
