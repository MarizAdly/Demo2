package com.example.beshoy.demo2.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class AddPostActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageReference;


    Post post;
    User user;

    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    ImageView postImage;
    TextView title;
    EditText postText;
    ImageButton camera, addpost, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        post = new Post();
        user = new User();


        title = findViewById(R.id.titleView);
        postText = findViewById(R.id.postText);
        postImage = findViewById(R.id.postImage);
        camera = findViewById(R.id.addPhoto);
        addpost = findViewById(R.id.addPost);
        cancel = findViewById(R.id.cancel);


        storage = FirebaseStorage.getInstance ();
        storageReference = storage.getReference ();
        mAuth = FirebaseAuth.getInstance();
        Intent  intent = getIntent ();
        user = (User) intent.getSerializableExtra ( "cUser" );


    }

    public void cancelPost(View view) {
        Intent intent = new Intent(AddPostActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void addPostImage(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get ().load ( imageUri ).into ( postImage );
        }
    }

    public void addPost(View view) {

        final String posttext = postText.getText ().toString ();

        if ( TextUtils.isEmpty(posttext)) {
            Toast.makeText(getApplicationContext(), "Fill Post Description!", Toast.LENGTH_SHORT).show();
            return;
        }



        if (imageUri!= null && user!=null || postText != null) {
            final ProgressDialog progressDialog = new ProgressDialog(AddPostActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("uploads/" ).child(imageUri.getLastPathSegment ());
            ref.putFile(imageUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess( final UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss ( );
                            ref.getDownloadUrl ().addOnSuccessListener ( new OnSuccessListener <Uri> ( ) {
                                @Override
                                public void onSuccess ( final Uri uri ) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance ( );
                                    DatabaseReference myRef = database.getReference ( "posts" ).child ( post.getUserID () );

                                    String userId = myRef.push().getKey();

                                    myRef.addValueEventListener ( new ValueEventListener ( ) {
                                        @Override
                                        public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                                            post.setPostText ( posttext );
                                            post.setPostPhoto ( uri.toString ( ) );
                                            post.setUserID ( mAuth.getCurrentUser ( ).getUid ( ) );

                                            user = dataSnapshot.getValue (User.class);
                                            post.setUserName ( dataSnapshot.child ( "displayName" ).toString () );
                                        }

                                        @Override
                                        public void onCancelled ( @NonNull DatabaseError databaseError ) {

                                        }
                                    } );
                                    assert userId != null;
                                    myRef.child ( userId ).setValue ( post );
                                }
                            } );
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
            Toast.makeText(AddPostActivity.this, "Post Added.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent ( AddPostActivity.this, HomeActivity.class );
            intent.putExtra ( "posts", post );
            intent.putExtra ( "currentUser", user );
            startActivity ( intent );

        }
        else
        {
            Toast.makeText ( AddPostActivity.this, "No file is selected",Toast.LENGTH_SHORT).show ();
        }


    }


}