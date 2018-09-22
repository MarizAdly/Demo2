package com.example.beshoy.demo2.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private List<Post> postsList;
    User user;
    Activity activity;


    public PostAdapter(List <Post> postsList, Activity activity ) {


        this.activity = activity;
        this.postsList = postsList;
    }


    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_item, parent, false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.userName.setText(postsList.get(position).getDisplayName());
        holder.userImage.setImageResource(Integer.parseInt(String.valueOf(postsList.get(position).getUserImage())));
holder.postText.setText(postsList.get(position).getPostText());


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage, postImage;
        TextView userName, postText;

        FirebaseStorage storage;
        StorageReference storageReference;

        public ViewHolder ( View itemView) {
            super ( itemView );

           userImage = itemView.findViewById(R.id.userImage);
           postImage = itemView.findViewById(R.id.postPhoto);
           userName = itemView.findViewById(R.id.personName);
           postText  = itemView.findViewById(R.id.postText);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();


        }

    }

}
