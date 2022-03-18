package com.example.telecomholdissue;

import android.os.Build;
import android.os.Bundle;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.util.Log;

public class MyTelecomConnection extends Connection {
    private static final String TAG = "MyTelecomConnection";

    public MyTelecomConnection() {
    }

    @Override
    public void onCallAudioStateChanged(CallAudioState state) {
        super.onCallAudioStateChanged(state);
        Log.i(TAG, "onCallAudioStateChanged: " + state);
    }

    @Override
    public void onStateChanged(int state) {
        Log.i(TAG, "onStateChanged: " + Connection.stateToString(getState()) + " -> " + Connection.stateToString(state));
        super.onStateChanged(state);
    }

    @Override
    public void onDisconnect() {
        Log.i(TAG, "onDisconnect()");
        super.onDisconnect();
    }

    @Override
    public void onAbort() {
        Log.i(TAG, "onAbort()");
        super.onAbort();
    }

    @Override
    public void onHold() {
        Log.i(TAG, "onHold()");
        super.onHold();
        setOnHold();
        Model.getInstance().setHold(true);
    }

    @Override
    public void onUnhold() {
        Log.i(TAG, "onUnhold()");
        super.onUnhold();
        setActive();
        Model.getInstance().setHold(false);
    }

    @Override
    public void onAnswer(int videoState) {
        Log.i(TAG, "onAnswer(videoState: " + videoState + ")");
        super.onAnswer(videoState);
    }

    @Override
    public void onAnswer() {
        Log.i(TAG, "onAnswer()");
        super.onAnswer();
    }

    @Override
    public void onReject() {
        Log.i(TAG, "onReject()");
        super.onReject();
    }

    @Override
    public void onReject(int rejectReason) {
        Log.i(TAG, "onReject(rejectReason: " + rejectReason + ")");
        super.onReject(rejectReason);
    }

    @Override
    public void onReject(String replyMessage) {
        Log.i(TAG, "onReject(replyMessage: " + replyMessage + ")");
        super.onReject(replyMessage);
    }

    @Override
    public void onSilence() {
        Log.i(TAG, "onSilence()");
        super.onSilence();
    }

    @Override
    public void onCallEvent(String event, Bundle extras) {
        Log.i(TAG, "onCallEvent(event: " + event + ")");
        super.onCallEvent(event, extras);
    }

    @Override
    public void onShowIncomingCallUi() {
        Log.i(TAG, "onShowIncomingCallUi");
        super.onShowIncomingCallUi();
    }

}
