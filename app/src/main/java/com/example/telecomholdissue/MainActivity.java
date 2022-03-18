package com.example.telecomholdissue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    PhoneAccountHandle phoneAccountHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerPhoneAccount();

        findViewById(R.id.button_place_call).setOnClickListener(v -> {
            Log.i(TAG, "Place Call clicked");
            telecomPlaceCall();
        });

        findViewById(R.id.button_end_call).setOnClickListener(v -> {
            Log.i(TAG, "End Call clicked");
            MyTelecomConnection c = Model.getInstance().getConnection();
            c.setDisconnected(new DisconnectCause(DisconnectCause.REJECTED));
            c.destroy();
        });

        findViewById(R.id.button_answer_call).setOnClickListener(v -> {
            Log.i(TAG, "Answer Call clicked");
            Model.getInstance().getConnection().setActive();
        });

        Model.getInstance().getHold().observe(this, hold -> {
            Log.i(TAG, "Call hold is " + hold);
            ((TextView)findViewById(R.id.text_field)).setText(hold ? " on Hold" : "Active");
        });
    }

    private void registerPhoneAccount() {
        TelecomManager tm = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        ComponentName cName = new ComponentName(this, MyConnectionService.class);
        this.phoneAccountHandle = new PhoneAccountHandle(cName, "HoldTest");
        Log.i(TAG, "TelecomRegisterAccount: phoneAccountHandle: " + phoneAccountHandle);

        final Icon icon = Icon.createWithResource(this, R.drawable.ic_launcher_foreground);
        PhoneAccount phoneAccount = PhoneAccount.builder(phoneAccountHandle, "HoldTest")
                .setCapabilities(PhoneAccount.CAPABILITY_SELF_MANAGED)
                .setIcon(icon)
                .build();

        tm.registerPhoneAccount(phoneAccount);
        Log.i(TAG, "phone account registered with telecom manager: " + phoneAccount);
    }

    private void telecomPlaceCall() {
        TelecomManager tm = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        Bundle extras = new Bundle();
        extras.putParcelable(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, this.phoneAccountHandle);
        Uri uri = Uri.fromParts("tel", "12065550000", null);
        try {
            tm.placeCall(uri, extras);
        } catch (SecurityException e) {
            Log.e(TAG, "TelecomManager.placeCall raised SecurityException " + e);
        }
    }
}