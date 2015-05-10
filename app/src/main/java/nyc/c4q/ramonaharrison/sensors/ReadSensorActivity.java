package nyc.c4q.ramonaharrison.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;


public class ReadSensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mASensor;
    private Sensor mGSensor;
    private GLSurfaceView mGLView;
    private static float[] RM = new float[16];
    private float[] I = new float[16];
    private float[] gravity = new float[3];
    private float[] geomagnetic = new float[3];

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mASensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    public static float[] getRM() {
        return RM;
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.

        // If the sensor data is unreliable return
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
            return;

        // Gets the value of the sensor that has been changed
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                //Log.d("RM", "gravity changed");
                gravity = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                //Log.d("RM", "geomagnetic changed");
                geomagnetic = event.values.clone();
                break;
        }

        // If gravity and geomag have values then find rotation matrix
        if (gravity != null && geomagnetic != null) {

            SensorManager.getRotationMatrix(RM, I, gravity, geomagnetic);
            Log.d("RM", "" + RM[0]);
            Log.d("RM", "" + RM[1]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mASensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    private class MyGLSurfaceView extends GLSurfaceView {
        private final MyGLRenderer mRenderer;

        public MyGLSurfaceView(Context context){
            super(context);

            // Create an OpenGL ES 2.0 context
            setEGLContextClientVersion(2);

            mRenderer = new MyGLRenderer();

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(mRenderer);
        }
    }
}
