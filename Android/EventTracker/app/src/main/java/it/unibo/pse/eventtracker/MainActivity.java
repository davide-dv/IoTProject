package it.unibo.pse.eventtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.UUID;

import it.unibo.pse.eventtracker.bt.BluetoothConnectionManager;
import it.unibo.pse.eventtracker.bt.BluetoothConnectionTask;
import it.unibo.pse.eventtracker.bt.BluetoothUtils;
import it.unibo.pse.eventtracker.bt.MsgTooBigException;

public class MainActivity extends Activity {

    private BluetoothAdapter btAdapter;
    private BluetoothDevice targetDevice;
    private static MainActivityHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        uiHandler = new MainActivityHandler(this);
        /*Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!BluetoothConnectionManager.getInstance().isConnected()) {
            if (btAdapter != null) {
                if (btAdapter.isEnabled()) {
                    targetDevice = BluetoothUtils.findPairedDevice(C.TARGET_BT_DEVICE_NAME, btAdapter);
                    if (targetDevice != null) {
                        ((TextView) findViewById(R.id.btFoundFlagLabel)).setText("Target BT Device: Found " + targetDevice.getName());
                        connectToTargetBtDevice();
                    }
                } else {
                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), C.ENABLE_BT_REQUEST);
                }
            } else {
                showBluetoothUnavailableAlert();
            }
        }
    }


    @Override
    public void onActivityResult(int reqID, int res, Intent data) {

        if (reqID == C.ENABLE_BT_REQUEST && res == Activity.RESULT_OK) {
            targetDevice = BluetoothUtils.findPairedDevice(C.TARGET_BT_DEVICE_NAME, btAdapter);

            if (targetDevice != null) {
                ((TextView) findViewById(R.id.btFoundFlagLabel)).setText("Target BT Device: Found " + targetDevice.getName());
                connectToTargetBtDevice();
            }
        }

        if (reqID == C.ENABLE_BT_REQUEST && res == Activity.RESULT_CANCELED) {
            // BT enabling process aborted
        }
    }

    private void alarmRequest() {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }
/*
    public void sendResponse(boolean value) {
        try {
            if (value) {
                BluetoothConnectionManager.getInstance().sendMsg(C.SET_ALARM);
            } else {
                BluetoothConnectionManager.getInstance().sendMsg(C.NO_SET_ALARM);
            }
        } catch (MsgTooBigException e) {
            e.printStackTrace();
        }
    }
*/
    public static MainActivityHandler getHandler() {
        return uiHandler;
    }

    private void showBluetoothUnavailableAlert() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.btUnavailableAlertTitle))
                .setMessage(getString(R.string.btUnavailableAlertMessage))
                .setCancelable(false)
                .setNeutralButton(getString(R.string.btUnavailableAlertBtnText), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                })
                .create();

        dialog.show();
    }

    private void connectToTargetBtDevice() {
        UUID uuid = UUID.fromString(C.TARGET_BT_DEVICE_UUID);

        BluetoothConnectionTask task = new BluetoothConnectionTask(this, targetDevice, uuid);
        task.execute();
    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();
        BluetoothConnectionManager.getInstance().cancel();
    }


    /**
     * The Handler Associated to the MainActivity Class
     */
    public static class MainActivityHandler extends Handler {
        private final WeakReference<MainActivity> context;

        MainActivityHandler(MainActivity context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {

            Object obj = msg.obj;

            if (obj instanceof String) {
                String message = obj.toString();

                switch (message) {
                    case C.ALARM_REQUEST:
                        //context.get().alarmRequest();
                        Log.d("DEB","RICEVUTO ALLARME");
                        break;
                }
            }

        }
    }
}
