package com.example.intentassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostingActivity extends AppCompatActivity {

    ImageView profiles, posting;
    TextView name, user, capt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        profiles = findViewById(R.id.iv_profile);
        posting = findViewById(R.id.iv_post_image);
        name = findViewById(R.id.tv_fullname);
        user = findViewById(R.id.tv_username);
        capt = findViewById(R.id.tv_caption);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String username = intent.getStringExtra("username");
        String caption = intent.getStringExtra("caption");
        ImageAccess profile = intent.getParcelableExtra("profile");
        ImageAccess post = intent.getParcelableExtra("picture");

        name.setText(fullname);
        user.setText(username);
        capt.setText(caption);
        profiles.setImageURI(profile.getImageUri());
        posting.setImageURI(post.getImageUri());

    }
}