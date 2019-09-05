package com.hovah_inc.beamraisingoperationchecklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import com.hovah_inc.beamraisingoperationchecklist.document.Document;
import com.hovah_inc.beamraisingoperationchecklist.notify.Emergency;
import com.hovah_inc.beamraisingoperationchecklist.record.Record;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView record, emergency, checklist, camera, document, developer;

    //PopUp
    @Override
    public void onBackPressed() {
        //To Quit Application on Button click use this code :
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***

        startActivity(a);

        //To kill the complete app and remove it from Runningapp list kill the app through its pid(its nasty)... use this lines before above code.
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //defining cards
        record = findViewById(R.id.record);
        emergency = findViewById(R.id.emergency);
        checklist = findViewById(R.id.check);
        document = findViewById(R.id.document);
        developer = findViewById(R.id.developer);
        camera = findViewById(R.id.camera);

        record.setOnClickListener(this);
        emergency.setOnClickListener(this);
        checklist.setOnClickListener(this);
        developer.setOnClickListener(this);
        document.setOnClickListener(this);
        camera.setOnClickListener(this);

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(this);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.record:
                i = new Intent(this, Record.class); startActivity(i); break;
            case R.id.emergency:
                i = new Intent(this, Emergency.class); startActivity(i); break;
            case R.id.check:
                i = new Intent(this, Checklist.class); startActivity(i); break;
            case R.id.camera:
                i = new Intent(this, Camera.class); startActivity(i); break;
            case R.id.document:
                i = new Intent(this, Document.class); startActivity(i); break;
            case R.id.developer:
                i = new Intent(this, Developer.class); startActivity(i); break;
            default:
                break;
        }
    }



}



