package com.example.beshoy.demo2.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beshoy.demo2.Models.Post;
import com.example.beshoy.demo2.Models.User;
import com.example.beshoy.demo2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class AddPostActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    String UserId;
    FirebaseStorage firebaseStorage;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Post post;
    User user;

    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    ImageView postImage, userImage;
    TextView title, personName;
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
        userImage = findViewById(R.id.userImage);
        personName = findViewById(R.id.personName);

        Intent addpost = getIntent();

        Picasso.get().load(user.getUserImage()).into(userImage);
        Picasso.get().load(String.valueOf(post.getPostPhoto())).into(postImage);
    }

    public void cancelPost(View view) {
        Intent intent = new Intent(AddPostActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void addPost(View view) {
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("posts");

        if (imageUri!= null) {
            final ProgressDialog progressDialog = new ProgressDialog(AddPostActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();

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
        }
        post.setUserID(mAuth.getCurrentUser().getUid());
        post.setPostText(postText.getText().toString());


        myRef.push().setValue(post);

        Toast.makeText(AddPostActivity.this, "Post Added.",
                Toast.LENGTH_SHORT).show();

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
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                postImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
