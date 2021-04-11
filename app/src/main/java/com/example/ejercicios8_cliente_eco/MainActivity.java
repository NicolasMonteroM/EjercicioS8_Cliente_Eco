package com.example.ejercicios8_cliente_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements OnMessageListener, View.OnTouchListener, View.OnClickListener {

    private Button upBtn, downBtn, shotBtn;

    private TCPSingleton tcpSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        shotBtn = findViewById(R.id.shotBtn);

        tcpSingleton = TCPSingleton.getInstance();
        tcpSingleton.setObserver(this);

        downBtn.setOnTouchListener(this);
        upBtn.setOnTouchListener(this);

        shotBtn.setOnClickListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Gson gson = new Gson();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                switch (v.getId()) {
                    case R.id.upBtn:
                        Message upStartMsg = new Message("UP_START");
                        String upStartJson = gson.toJson(upStartMsg);
                        tcpSingleton.sendMessage(upStartJson);

                        break;

                    case R.id.downBtn:
                        Message downStartMsg = new Message("DOWN_START");
                        String downStartJson = gson.toJson(downStartMsg);
                        tcpSingleton.sendMessage(downStartJson);

                        break;
                }
                break;

            case MotionEvent.ACTION_UP:
                switch (v.getId()) {
                    case R.id.upBtn:
                        Message upEndMsg = new Message("UP_END");
                        String upEndJson = gson.toJson(upEndMsg);
                        tcpSingleton.sendMessage(upEndJson);

                        break;

                    case R.id.downBtn:
                        Message downEndMsg = new Message("DOWN_END");
                        String downEndJson = gson.toJson(downEndMsg);
                        tcpSingleton.sendMessage(downEndJson);

                        break;
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        Gson gson = new Gson();

        Message msg = new Message("FIRE");

        String json = gson.toJson(msg);
        tcpSingleton.sendMessage(json);
    }


    @Override
    public void OnMessage(String msg) {

    }
}