package com.example.lkwid.leveler;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    @BindView(R.id.level_dot)
    ImageView mDot;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Log.d("aaa", "aaa");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        FrameLayout frame = (FrameLayout) mDot.getParent();
        int maxX = (frame.getWidth() / 2) - mDot.getWidth();
        int maxY = (frame.getHeight() / 2) - mDot.getHeight();

        int x = (int) (event.values[0] / 9.81 * maxX);
        int y = (int) (event.values[1] / 9.81 * maxY);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mDot.getLayoutParams();
        params.setMargins(x, 0, 0, y);

        mDot.setLayoutParams(params);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
