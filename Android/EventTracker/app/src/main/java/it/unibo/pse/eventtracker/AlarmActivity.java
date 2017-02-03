package it.unibo.pse.eventtracker;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import it.unibo.pse.eventtracker.bt.BluetoothConnectionManager;
import it.unibo.pse.eventtracker.bt.MsgTooBigException;

public class AlarmActivity extends Activity {

    private final long time = 10000;
    private final long timeInterval = 1000;
    private boolean cancelTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        final Button btnSet = (Button)findViewById(R.id.buttonSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(C.SET_ALARM);
            }
        });

        final Button btnIgnore = (Button)findViewById(R.id.buttonIgnore);
        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(C.NO_SET_ALARM);
            }
        });

        final TextView mTextField = (TextView)findViewById(R.id.textViewTimer);
        new CountDownTimer(this.time, this.timeInterval) {

            public void onTick(long millisUntilFinished) {
                if (cancelTimer) {
                    cancel();
                } else {
                    mTextField.setText("secondi rimanenti: " + millisUntilFinished / 1000);
                }
            }

            public void onFinish() {
                Toast.makeText(getBaseContext(), "Tempo scaduto, allarme attivato", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();
    }

    private void sendMsg(String msg) {
        try {
            BluetoothConnectionManager.getInstance().sendMsg(msg);
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            this.cancelTimer = true;
            finish();
        } catch (MsgTooBigException e) {
            e.printStackTrace();
        }
    }
}
