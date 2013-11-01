package com.thoughtworks.beach.android;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements StepListener {

    private static final int DEFAULT_SENSITIVITY = 30;

    private static final int DEFAULT_VOLUME = 50;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    StepDetector stepDetector;
    private int stepCount = 0;
    private ToneGenerator toneGenerator;
    private int sensitivity;
    private boolean counting = false;
    private TextView stepsLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepsLabel = (TextView) findViewById(R.id.steps);

        stepDetector = StepDetector.getInstance();
        setSensitivity(DEFAULT_SENSITIVITY);

        stepDetector.addStepListener(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        toneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, DEFAULT_VOLUME);
    }

    private void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
        stepDetector.setSensitivity(sensitivity);

        TextView sensitivityValue = (TextView) findViewById(R.id.sensitivityValue);
        sensitivityValue.setText(Integer.toString(getSensitivity()));

        Button decreaseButton = (Button) findViewById(R.id.sensitivityDown);
        decreaseButton.setEnabled(sensitivity>0);
    }

    public int getSensitivity() {
        return sensitivity;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    public void increaseSensitivity(View view){
        int sensitivity = getSensitivity();
        setSensitivity(sensitivity + 1);
    }

    public void decreaseSensitivity(View view){
        int sensitivity = getSensitivity();
        if(sensitivity==0)
            return;
        setSensitivity(sensitivity - 1);

    }

    public void toggleCounting(View view){
        Button toggleButton = (Button) findViewById(R.id.toggle_counting_steps_button);
        if (counting) {
            sensorManager.unregisterListener(stepDetector);
            counting = false;
            toggleButton.setText("Start Counting Steps");
            toggleButton.setBackgroundColor(Color.BLUE);
        } else {
            sensorManager.registerListener(stepDetector, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            counting = true;
            toggleButton.setText("Stop Counting Steps");
            toggleButton.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onStep() {
        stepCount++;
        stepsLabel.setText(String.format("You have taken %s steps.", Integer.toString(stepCount)));
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
    }

    public Button getIncreaseButton(){
        return (Button) findViewById(R.id.sensitivityUp);
    }
}
