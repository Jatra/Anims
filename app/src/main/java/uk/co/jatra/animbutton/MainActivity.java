package uk.co.jatra.animbutton;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_LEVEL = 10000;

    private static final String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton fab;
    ReadyableButton rbutton;
    CheckBox readying;
    CheckBox operating;
    CheckBox completing;
    CheckBox ready;
    ProgressBar progressBar;
    ImageView imageView;
    private boolean inDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbutton = (ReadyableButton)findViewById(R.id.button);
        readying = (CheckBox) findViewById(R.id.readying);
        readying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbutton.setReadying(isChecked);
            }
        });
        operating = (CheckBox) findViewById(R.id.operating);
        operating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbutton.setOperating(isChecked);
            }
        });
        completing = (CheckBox) findViewById(R.id.completing);
        completing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbutton.setCompleting(isChecked);
            }
        });
        ready = (CheckBox) findViewById(R.id.ready);
        ready.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbutton.setReady(isChecked);
            }
        });

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Drawable d = progressBar.getIndeterminateDrawable();
        Log.d(TAG, d.toString());

        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        fab.setRippleColor(Color.CYAN);

        final Drawable fabDrawable = getResources().getDrawable(R.drawable.ble_scan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageDrawable(fabDrawable);
//                fab.setImageResource(R.drawable.ble_scan);
                AnimationDrawable animation = (AnimationDrawable)fab.getDrawable();
                animation.start();
            }
        });


        ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        Picasso.with(this)
                .load("http://example.com")
//                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.progress_animation)
                .into(imageView4);


//        AnimatedVectorDrawable avd = (AnimatedVectorDrawable)findViewById(R.id.bleanim);

        ImageView bleanim = (ImageView)findViewById(R.id.bleanim);
//        AnimatedVectorDrawable avd = (AnimatedVectorDrawable)bleanim.getDrawable();
//        avd.start();

        AnimatedVectorDrawableCompat avdc = AnimatedVectorDrawableCompat.create(this, R.drawable.bleanim);
        bleanim.setImageDrawable(avdc);
        avdc.start();


        ImageView grid = (ImageView)findViewById(R.id.grid);
        AnimatedVectorDrawableCompat gridv = AnimatedVectorDrawableCompat.create(this, R.drawable.gridav);
        grid.setImageDrawable(gridv);
        gridv.start();

    }

}
