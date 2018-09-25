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

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.ViewHolder> {
 Activity activity;
private List<Post> postList;



public PostAdapter( List <Post> postList,Activity activity ) {
       this.activity = activity;
      this.postList = postList;

        }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int i ) {
        LayoutInflater inflater = LayoutInflater.from ( parent.getContext ( ) );
        View view = inflater.inflate ( R.layout.post_item, parent, false );
        ViewHolder viewholder = new ViewHolder ( view );
        return viewholder;
    }

    @Override
    public void onBindViewHolder ( @NonNull PostAdapter.ViewHolder holder, int position ) {
holder.postText.setText ( postList.get ( position ).getPostText () );
Picasso.get ().load ( postList.get ( position ).getPostPhoto () ).into ( holder.postImage );



    }

    @Override
    public int getItemCount ( ) {
        return postList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    EditText postText;
    ImageView  postImage;
    TextView userName;


        public ViewHolder ( @NonNull View itemView ) {
            super ( itemView );

            postImage = itemView.findViewById ( R.id.postPhoto );
            postText = itemView.findViewById ( R.id.postText );
            userName = itemView.findViewById ( R.id.cUserName );

        }

    }
}
