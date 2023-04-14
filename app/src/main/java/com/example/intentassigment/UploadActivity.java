package com.example.intentassigment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadActivity extends AppCompatActivity {

    ImageView imgPost;
    EditText caption;
    Button btnPost;
    ImageAccess profileImage, postImage;

    private ActivityResultLauncher<Intent> PictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                        Uri ChooseImage = result.getData().getData();
                        imgPost.setImageURI(ChooseImage);
                        postImage.setImageUri(ChooseImage);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imgPost = findViewById(R.id.iv_post_image);
        caption = findViewById(R.id.et_caption);
        btnPost = findViewById(R.id.btn_post);

        Intent intent = getIntent();
        String fulllname = intent.getStringExtra("fullname");
        String username = intent.getStringExtra("username");

        profileImage = intent.getParcelableExtra("profile");
        postImage = new ImageAccess();

        imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImg =new Intent(Intent.ACTION_PICK);
                intentImg.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                PictureLauncher.launch(Intent.createChooser(intentImg, "Choose your photo"));
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isEmpty = false;
                String captFinal = caption.getText().toString();

                if (postImage.getImageUri() == null) {
                    Toast.makeText(UploadActivity.this, "Choose your photo first", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty) {
                    Intent upload = new Intent(UploadActivity.this, PostingActivity.class);
                    upload.putExtra("fullname", fulllname);
                    upload.putExtra("username", username);
                    upload.putExtra("caption", captFinal);
                    upload.putExtra("profile", profileImage);
                    upload.putExtra("picture", postImage);
                    startActivity(upload);
                }
            }
        });

    }
}