package com.example.mvvmpractice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    Button btnStart, btnStop;
    TextView txvTimeleft;
    EditText edtTimeinput;
    UserModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        txvTimeleft = findViewById(R.id.textView);
        edtTimeinput = findViewById(R.id.editTextNumber);
        setOnclickListener();
        //UI
        viewModel = ViewModelProviders.of(this).get(UserModel.class);
        setObserver();


    }

    void setObserver() {
        viewModel.seconds().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txvTimeleft.setText(integer.toString());
            }


        });
        viewModel.finished().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(getBaseContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void setOnclickListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTimeinput.getText().toString() == "" || edtTimeinput.length() < 4) {
                    Toast.makeText(getBaseContext(), "invalid number", Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.timerValue.setValue(Long.valueOf(edtTimeinput.getText().toString()));
                    viewModel.startTimer();
                }

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txvTimeleft.setText("0");
                viewModel.stopTimer();
            }
        });


    }
}