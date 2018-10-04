
        package com.example.beshoy.demo2.Activities;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v4.view.MenuCompat;
        import android.support.v4.view.MenuItemCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.SearchView;
        import android.support.v7.widget.SearchView.OnQueryTextListener;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Toast;

        import com.example.beshoy.demo2.Adapters.UsersAdapter;
        import com.example.beshoy.demo2.Models.User;
        import com.example.beshoy.demo2.R;
        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class UsersListActivity extends AppCompatActivity {
    RecyclerView recView;
    UsersAdapter usersAdapter;
    List <User> userList;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_users_list );

        userList = new ArrayList <> ( );

        recView = findViewById ( R.id.recUsersView );

        FirebaseDatabase database = FirebaseDatabase.getInstance ( );
        final DatabaseReference myRef = database.getReference ( "users" );
        myRef.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren ( )) {
                    User user = userSnapShot.getValue ( User.class );
                    userList.add ( user );
                    usersAdapter.notifyDataSetChanged ( );
                }

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Toast.makeText ( UsersListActivity.this, databaseError.getMessage ( ), Toast.LENGTH_LONG ).show ( );
            }

        } );

        usersAdapter = new UsersAdapter ( userList, this );
        recView.setAdapter ( usersAdapter );
        recView.setLayoutManager ( new LinearLayoutManager ( this ) );

    }



    public void Home ( View view ) {
        Intent intent = new Intent ( UsersListActivity.this, HomeActivity.class );
        startActivity ( intent );
    }
}
