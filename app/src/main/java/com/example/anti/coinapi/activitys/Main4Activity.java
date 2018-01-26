package com.example.anti.coinapi.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anti.coinapi.Models.Coins;
import com.example.anti.coinapi.Models.Settings;
import com.example.anti.coinapi.R;
import com.example.anti.coinapi.others.PrefererencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {


    @BindView(R.id.grupa)
    RadioGroup grupa1;

    @BindView(R.id.seek)
    SeekBar seekBar;


    @BindView(R.id.kopce)
    Button kopce;

    RadioButton radioButton;

    public Coins cointi;

    Settings settings;

    String vrednost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ButterKnife.bind(this);

        // cointi = PrefererencesManager.addLimit();


        PrefererencesManager.getSettings(this);
        if (settings == null) {

            settings = new Settings();

        }

        grupa1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int radioButtonID = grupa1.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(radioButtonID);
                String vrednost = radioButton.getText().toString();

                settings.convert = vrednost;


            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {


                settings.limit = progress;

                // sekbartekst.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int minValue = seekBar.getMax();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // int maxValue = seekBar.getMin();
            }
        });
    }


    @OnClick(R.id.kopce)
    public void klik(View v) {
        PrefererencesManager.addSetings(settings,this);


    }


}
