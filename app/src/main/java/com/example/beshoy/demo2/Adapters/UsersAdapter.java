package com.example.beshoy.demo2.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beshoy.demo2.Activities.ProfileActivity;
import com.example.beshoy.demo2.Activities.UsersListActivity;
import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private List <User> userList;
    Activity activity;


    public UsersAdapter ( List <User> userList, Activity activity ) {
        this.activity = activity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup, int i ) {
        LayoutInflater inflater = LayoutInflater.from ( viewGroup.getContext ( ) );
        View view = inflater.inflate ( R.layout.user_item, viewGroup, false );
        UsersHolder usersHolder = new UsersHolder ( view );
        return usersHolder;
    }

    @Override
    public void onBindViewHolder ( @NonNull UsersHolder usersHolder, int position ) {
usersHolder.useridplayName.setText ( userList.get ( position ).getDisplayName () );
Picasso.get ().load ( userList.get ( position ).getUserImage () ).into ( usersHolder.userPhoto );
    }

    @Override
    public int getItemCount ( ) {
        return userList.size ( );
    }

    public class UsersHolder extends ViewHolder {

        ImageView userPhoto;
        TextView useridplayName;

        public UsersHolder ( @NonNull View itemView ) {
            super ( itemView );

            useridplayName = itemView.findViewById ( R.id.userNameList );
            userPhoto = itemView.findViewById ( R.id.userProfImg );
            useridplayName.setOnClickListener ( new View.OnClickListener ( ) {

                @Override
                public void onClick ( View view ) {

                    Intent intent = new Intent ( activity, ProfileActivity.class );
                    intent.putExtra ( "users", userList.get ( getAdapterPosition ( ) ) );
                    activity.startActivity ( intent );
                }
            } );
        }
    }
}

