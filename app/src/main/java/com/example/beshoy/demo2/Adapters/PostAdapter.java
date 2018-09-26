package com.example.beshoy.demo2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beshoy.demo2.Activities.HomeActivity;
import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends  RecyclerView.Adapter< PostAdapter.PostHolder> {
 List<Post> postList;
Activity activity;

public PostAdapter (List<Post> postList , Activity activity){
    this.postList = postList;
    this.activity = activity;
}

    @NonNull
    @Override
    public PostHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup, int i ) {

        LayoutInflater inflater = LayoutInflater.from ( viewGroup.getContext ( ) );
        View view = inflater.inflate ( R.layout.post_item, viewGroup, false );
        PostHolder postHolder = new PostHolder ( view );
        return postHolder;
    }

    @Override
    public void onBindViewHolder ( @NonNull PostHolder postHolder, int i ) {
postHolder.userdiplayname.setText ( postList.get ( i ).getUserName () );
Picasso.get ().load ( postList.get ( i ).getPostPhoto () ).into ( postHolder.postUrl );
postHolder.postdesc.setText ( postList.get ( i ).getPostText () );
    }

    @Override
    public int getItemCount ( ) {
        return postList.size ();

    }

    public class PostHolder extends ViewHolder {

    ImageView postUrl;
    TextView userdiplayname, postdesc;

        public PostHolder ( @NonNull View itemView ) {
            super ( itemView );
            postdesc = itemView.findViewById ( R.id.postText );
            postUrl = itemView.findViewById ( R.id.postPhoto );
            userdiplayname = itemView.findViewById ( R.id.cUserName );

        }
    }
}
