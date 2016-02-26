package p3ns.shake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeEventManager mShakeDetector;
    ImageView iv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.imageView);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeEventManager();
        mShakeDetector.setOnShakeListener(new ShakeEventManager.OnShakeListener() {

            @Override
            public void onShake(int count) {

                RotateAnimation ro=new RotateAnimation(0.0f, (float) (-10.10*360.0f),40,0);
                ro.setDuration((long) 2*1500);
                ro.setRepeatCount(0);
                iv.startAnimation(ro);

				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */

                Random r = new Random();
                int i1 = r.nextInt(7-1) + 1;

                switch(i1)
                {
                    case 1:
                        iv.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        iv.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        iv.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        iv.setImageResource(R.drawable.dice6);
                        break;

                }



            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
