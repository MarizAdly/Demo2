package com.example.beshoy.demo2.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beshoy.demo2.Activities.ProfActivity;
import com.example.beshoy.demo2.Activities.ProfileActivity;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class User2Adapter extends  RecyclerView.Adapter<User2Adapter.UsersHolder> {

    private List<User> userList;
    Activity activity;

    public User2Adapter ( List <User> userList, Activity activity ) {
        this.activity = activity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public User2Adapter.UsersHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( parent.getContext ( ) );
        View view = inflater.inflate ( R.layout.user_item, parent, false );
        User2Adapter.UsersHolder users2Holder= new User2Adapter.UsersHolder ( view );
        return users2Holder;
    }

    @Override
    public void onBindViewHolder ( @NonNull User2Adapter.UsersHolder usersHolder, int position ) {
        usersHolder.userDisplayName.setText ( userList.get ( position ).getDisplayName () );
        Picasso.get ().load ( userList.get ( position ).getUserImage () ).into ( usersHolder.userPhoto );
    }

    @Override
    public int getItemCount ( ) {
        return userList.size ( );
    }

    public class UsersHolder extends RecyclerView.ViewHolder {

        ImageView userPhoto;
        public TextView userDisplayName;

        public UsersHolder ( @NonNull View itemView ) {
            super ( itemView );

            userDisplayName = itemView.findViewById ( R.id.userNameList );
            userPhoto = itemView.findViewById ( R.id.userProfImg );
            userDisplayName.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {
                    Intent detintent = new Intent ( activity, ProfActivity.class );
                    detintent.putExtra ( "user", userList.get ( getAdapterPosition ( ) ) );
                    activity.startActivity ( detintent );
                    activity.finish ( );
                }
            } );

        }
    }
}


