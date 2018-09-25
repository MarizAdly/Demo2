package com.example.beshoy.demo2.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beshoy.demo2.Activities.ProfileActivity;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter <UsersAdapter.ViewHolder>{
    List<User> userList;
    Activity activity;


    public UsersAdapter ( List <User>userList, Activity activity ) {
        this.activity = activity;
        this.userList = userList;
    }
    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int i ) {
        LayoutInflater inflater = LayoutInflater.from ( parent.getContext ( ) );
        View view = inflater.inflate ( R.layout.user_item, parent, false );
        ViewHolder viewholder = new ViewHolder ( view );
        return viewholder;
    }

    @Override
    public void onBindViewHolder ( @NonNull UsersAdapter.ViewHolder viewHolder, int  position) {
        viewHolder.userName.setText ( userList.get ( position ).getDisplayName () );
        Picasso.get ( ).load ( userList.get ( position ).getUserImage () ).into ( viewHolder.userImage );


    }

    @Override
    public int getItemCount ( ) {
        return userList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName;

        public ViewHolder ( @NonNull View itemView ) {
            super ( itemView );
             userImage = itemView.findViewById ( R.id.userProfImg );
             userName = itemView.findViewById ( R.id.userNameList );

            userName.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {

                    Intent intent = new Intent ( activity, ProfileActivity.class );
                    intent.putExtra ( "users", userList.get ( getAdapterPosition ( ) ) );
                    activity.startActivity ( intent);
                }
            } );
        }
    }
}
