package com.dynamictheme;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;

import static com.facebook.drawee.backends.pipeline.Fresco.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layout_green, layout_purple;
    private SwitchCompat switch_dark;
    private boolean isDarkTheme;
    private static final String Theme_Current = "ThemeCurrent";
    private static final String Dark_Theme = "DarkTheme";
    private SimpleDraweeView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initialize(this);



        super.onCreate(savedInstanceState);

        setAppTheme();
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskStackBuilder.create(MainActivity.this)
                        .addNextIntent(new Intent(MainActivity.this, MainActivity.class))
                        .addNextIntent(getIntent())
                        .startActivities();

            }
        });


        layout_green = (LinearLayout) findViewById(R.id.Layout_green);
        layout_purple = (LinearLayout) findViewById(R.id.Layout_purple);
        imageView = (SimpleDraweeView) findViewById(R.id.imageView);


        layout_green.setOnClickListener(this);
        layout_purple.setOnClickListener(this);

        chanageBackgroundImage();


    }


    private void chanageBackgroundImage() {


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        if (!MainController.preferenceGetString(Theme_Current, "").equals("")) {
            if (MainController.preferenceGetString(Theme_Current, "").equals("Green")) {

                Picasso.with(MainActivity.this)                   //Activity context
                        .load(R.drawable.bg_green)
                        .resize(width, height)
                        .onlyScaleDown() // the image will only be resized if it's bigger than 6000x2000 pixels.
                        .into(imageView);

            } else if (MainController.preferenceGetString(Theme_Current, "").equals("Purple")) {

                Picasso.with(MainActivity.this)                   //Activity context
                        .load(R.drawable.bg_purple)
                        .resize(width, height)
                        .onlyScaleDown() // the image will only be resized if it's bigger than 6000x2000 pixels.
                        .into(imageView);
            }
        } else {
            Picasso.with(MainActivity.this)                   //Activity context
                    .load(R.drawable.bg_green)
                    .resize(width, height)
                    .onlyScaleDown() // the image will only be resized if it's bigger than 6000x2000 pixels.
                    .into(imageView);
        }


    }


    private void setAppTheme() {

        if (!MainController.preferenceGetString(Theme_Current, "").equals("")) {
            if (MainController.preferenceGetString(Theme_Current, "").equals("Green")) {
                setTheme(R.style.ThemeApp_Green);
            } else if (MainController.preferenceGetString(Theme_Current, "").equals("Purple")) {


                setTheme(R.style.ThemeApp_Purple);


            }
        } else {


            setTheme(R.style.ThemeApp_Green);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Layout_green:


                MainController.preferencePutString(Theme_Current, "Green");


                break;

            case R.id.Layout_purple:


                MainController.preferencePutString(Theme_Current, "Purple");


                /*TaskStackBuilder.create(this)
                        .addNextIntent(new Intent(this, Listactivity.class))
                        .addNextIntent(getIntent())
                        .startActivities();*/
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
