package com.hovah_inc.beamraisingoperationchecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import retrofit2.http.Url;

public class Developer extends AppCompatActivity {

    ImageButton messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        messageButton = findViewById(R.id.messageBtn);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://AnodeJackingApp.wasap.my"));
                startActivity(browserIntent);
            }
        });

    }
}
