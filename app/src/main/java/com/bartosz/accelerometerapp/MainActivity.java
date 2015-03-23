package com.bartosz.accelerometerapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    EditText editX, editY, editZ;
    SensorManager gSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editX=(EditText)findViewById(R.id.edit1);
        editY=(EditText)findViewById(R.id.edit2);
        editZ=(EditText)findViewById(R.id.edit3);
        gSensor=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                editX.setText(String.format("X: %2.3f", event.values[0]));
                editY.setText(String.format("Y: %2.3f", event.values[1]));
                editZ.setText(String.format("Z: %2.3f", event.values[2]));
                TextView text=(TextView)findViewById(R.id.textView);

                if(event.values[0]>8 && event.values[0]<11){
                    text.setText("Urządzenie jest w poziomie!!");
                }
                else if(event.values[1]>8 && event.values[1]<11){
                    text.setText("Urządzenie jest w pionie!!");
                }
                else if(event.values[2]>8 && event.values[2]<11){
                    text.setText("Urządzenie leży!!");
                }
                else{text.setText("");}
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        gSensor.registerListener(sensorEventListener, gSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
