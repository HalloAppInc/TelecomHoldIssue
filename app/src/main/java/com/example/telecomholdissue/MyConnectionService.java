package com.example.telecomholdissue;

import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = 23)
public class MyConnectionService extends ConnectionService {

    public static final String TAG = "MyConnectionService";

    @Override
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i(TAG, "HaTelecomConnectionService onCreateIncomingConnection request:" + request);

        MyTelecomConnection telecomConnection = new MyTelecomConnection();
        telecomConnection.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED | Connection.PROPERTY_HIGH_DEF_AUDIO);
        telecomConnection.setConnectionCapabilities(Connection.CAPABILITY_MUTE | Connection.CAPABILITY_HOLD | Connection.CAPABILITY_SUPPORT_HOLD);

        telecomConnection.setCallerDisplayName("John Smith", TelecomManager.PRESENTATION_ALLOWED);
        Uri address = Uri.fromParts("tel", "12065550000", null);

        telecomConnection.setAddress(address, TelecomManager.PRESENTATION_ALLOWED);
        telecomConnection.setActive();
        Model.getInstance().setConnection(telecomConnection);
        return telecomConnection;
    }

    @Override
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i(TAG, "onCreateIncomingConnectionFailed request:" + request);
    }

    @Override
    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.e(TAG, "onCreateOutgoingConnectionFailed request:" + request);
    }

    @Override
    public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i(TAG, "onCreateOutgoingConnection(request: " + request + ")");
        Bundle extras = request.getExtras();

        MyTelecomConnection telecomConnection = new MyTelecomConnection();
        telecomConnection.setConnectionProperties(Connection.PROPERTY_SELF_MANAGED | Connection.PROPERTY_HIGH_DEF_AUDIO);
        telecomConnection.setConnectionCapabilities(Connection.CAPABILITY_MUTE | Connection.CAPABILITY_HOLD | Connection.CAPABILITY_SUPPORT_HOLD);

        telecomConnection.setCallerDisplayName("John Smith", TelecomManager.PRESENTATION_ALLOWED);
        Uri address = Uri.fromParts("tel", "12065550000", null);

        telecomConnection.setAddress(address, TelecomManager.PRESENTATION_ALLOWED);
        Model.getInstance().setConnection(telecomConnection);
        return telecomConnection;
    }

}


