package com.example.mray.server;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class GlassListener extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public GlassListener(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity

                byte[] fin = new byte[bytes];
                for (int i = 0; i < bytes; i++) {
                    fin[i] = buffer[i];
                }
                accept(fin);
            } catch (IOException e) {
                break;
            }
        }
    }

    public void accept(byte[] bytes){
        String str = new String(bytes, "UTF-8");
        Log.d("glassInput", str);
    }
}
