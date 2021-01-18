package com.devender.videoapp;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;


public class PictureInPictureActivity extends AppCompatActivity
{

    private VideoView videoView;
    private ImageButton pip;
    private  PictureInPictureParams.Builder pictureInPictureParamsBuilder;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_in_picture);

        actionBar = getSupportActionBar();
        pip = findViewById(R.id.pip);
        videoView = findViewById(R.id.videoView);
        setVideoView(getIntent());
        pip.setOnClickListener(view -> pictureInPictureMode());
    }

    public void setVideoView(Intent intent) {
        String videoUrl = intent.getStringExtra("videoUrl");

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(videoUrl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mp -> videoView.start());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParamsBuilder = new PictureInPictureParams.Builder();
        }
    }
    private void pictureInPictureMode()
    {
        Rational aspectRatio = new Rational(videoView.getWidth(), videoView.getHeight());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
            enterPictureInPictureMode(pictureInPictureParamsBuilder.build());
            Log.d("PIP", "Version supported");
        } else {
            Log.d("PIP", "Version doesn't support");
        }

    }

    @Override
    public void onUserLeaveHint()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(!isInPictureInPictureMode()){
                pictureInPictureMode();
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged (boolean isInPictureInPictureMode, Configuration newConfig)
    {
        if (isInPictureInPictureMode) {
            Log.d("PIP ", "Enter To PIP");
            pip.setVisibility(View.GONE);
//            actionBar.hide();
        } else {
            Log.d("PIP  ", "EXIT To PIP");

            pip.setVisibility(View.VISIBLE);
//            actionBar.show();
        }
    }

    @Override
    public void onStop()
    {
        if( videoView.isPlaying()){
            videoView.stopPlayback();
        }
        super.onStop();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("onNewIntent", "Play new Video : ");
        setVideoView(intent);

    }
}