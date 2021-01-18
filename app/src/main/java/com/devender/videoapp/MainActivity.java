package com.devender.videoapp;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.devender.videoapp.Registration.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String songOne = "android.resource://com.devender.videoapp/"+R.raw.movie;
    String songTwo = "android.resource://com.devender.videoapp/"+R.raw.metaxaskellerbell;
    String songThree = "android.resource://com.devender.videoapp/"+R.raw.metaxaskellerbell;

    private ImageButton signOut;
    private Button firstVideo;
    private Button secondVideo;
    private Button thirdVideo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        firstVideo = findViewById(R.id.firstVideo);
        secondVideo = findViewById(R.id.secondVideo);
        thirdVideo = findViewById(R.id.thirdVideo);

        firstVideo.setOnClickListener(v -> {
            playVideo(songOne);

        });
        secondVideo.setOnClickListener(v -> {
            playVideo(songTwo);

        });
        thirdVideo.setOnClickListener(v -> {
            playVideo(songThree);

        });
    }

    private void playVideo(String videoUrl) {
        Intent intent = new Intent(this, PictureInPictureActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        startActivity(intent);
    }
}