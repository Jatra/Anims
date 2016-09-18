package uk.co.jatra.animbutton;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(R.drawable.ble_scan);
                AnimationDrawable animation = (AnimationDrawable)fab.getDrawable();
                animation.start();
            }
        });
    }

}
