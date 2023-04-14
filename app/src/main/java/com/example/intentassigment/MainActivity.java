package com.example.intentassigment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    ShapeableImageView imageProfile;
    EditText etFullname, etUsername;
    Button submit;
    ImageAccess profileImage;

    private ActivityResultLauncher<Intent> ProfileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                        Uri ChooseImage = result.getData().getData();
                        imageProfile.setImageURI(ChooseImage);
                        profileImage.setImageUri(ChooseImage);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFullname = findViewById(R.id.et_fullname);
        etUsername = findViewById(R.id.et_username);
        submit = findViewById(R.id.btn_submit);
        imageProfile = findViewById(R.id.iv_profile);

        profileImage = new ImageAccess();

        if (imageProfile != null) {
            imageProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentImage =new Intent(Intent.ACTION_PICK);
                    intentImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    ProfileLauncher.launch(Intent.createChooser(intentImage, "Choose your photo"));
                }
            });
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etFullname.getText().toString();
                String username = etUsername.getText().toString();

                Boolean isEmpty = false;

                if (profileImage.getImageUri() == null) {
                    Toast.makeText(MainActivity.this, "Choose your profile picture first", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fullname.isEmpty()) {
                    etFullname.setError("Field can't be empty");
                    Toast.makeText(MainActivity.this, "Fill in your name first", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if (username.isEmpty()) {
                    etFullname.setError("Field can't be empty");
                    Toast.makeText(MainActivity.this, "Fill in your username first", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if (!isEmpty){
                    Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                    intent.putExtra("fullname", fullname);
                    intent.putExtra("username", username);
                    intent.putExtra("profile", profileImage);
                    startActivity(intent);
                }
            }
        });
    }
}