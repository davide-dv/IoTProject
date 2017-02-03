package it.unibo.pse.eventtracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import it.unibo.pse.eventtracker.bt.BluetoothConnectionManager;
import it.unibo.pse.eventtracker.bt.MsgTooBigException;

public class AlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        final Button btnSet = (Button)findViewById(R.id.buttonSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothConnectionManager.getInstance().sendMsg(C.SET_ALARM);
                    Log.d("DEB","SET");
                } catch (MsgTooBigException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnIgnore = (Button)findViewById(R.id.buttonIgnore);
        btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothConnectionManager.getInstance().sendMsg(C.NO_SET_ALARM);
                    Log.d("DEB","UNSET");
                } catch (MsgTooBigException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
