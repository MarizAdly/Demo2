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

import com.example.beshoy.demo2.Adapters.PostAdapter;
import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.firebase.auth.FirebaseAuth;
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
     Post post;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        mAuth = FirebaseAuth.getInstance ( );
        mRecView = findViewById ( R.id.postView );

        postList = new ArrayList <> ( );
        Intent intent = getIntent ();
        post = (Post )intent.getSerializableExtra ( "posts" );

        firebaseDatabase = FirebaseDatabase.getInstance ( );
        databaseReference = firebaseDatabase.getReference ( "posts" ).child ( "userID" );
        databaseReference.push ( ).setValue ( new Post ( ) );

       databaseReference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( DataSnapshot dataSnapshot ) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren ( )) {
                    Post posts = dataSnapshot.getValue ( Post.class );
                    postList.add ( posts );
                    postAdapter.notifyDataSetChanged ( );
                }

            }



            @Override
            public void onCancelled ( DatabaseError databaseError ) {

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
}
