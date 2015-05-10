package nyc.c4q.ramonaharrison.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class SensorActivity extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mAccelerometer;
    Context mContext;

    private float[] RM = new float[9];
    private float[] I = new float[9];
    private float[] gravity = new float[3];
    private float[] geomagnetic = new float[3];
    private TextView grid1;
    private TextView grid2;
    private TextView grid3;
    private TextView grid4;
    private TextView grid5;
    private TextView grid6;
    private TextView grid7;
    private TextView grid8;
    private TextView grid9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid1 = (TextView) findViewById(R.id.grid1);
        grid2 = (TextView) findViewById(R.id.grid2);
        grid3 = (TextView) findViewById(R.id.grid3);
        grid4 = (TextView) findViewById(R.id.grid4);
        grid5 = (TextView) findViewById(R.id.grid5);
        grid6 = (TextView) findViewById(R.id.grid6);
        grid7 = (TextView) findViewById(R.id.grid7);
        grid8 = (TextView) findViewById(R.id.grid8);
        grid9 = (TextView) findViewById(R.id.grid9);

        final SensorActivity sensor = new SensorActivity(this);

        Button button = (Button) findViewById(R.id.get_matrix);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                SensorManager mSensor = sensor.getSensorManager();
//                boolean rmChanged = mSensor.getRotationMatrix(RM, I, gravity, geomagnetic);
//                Log.d("rm", "changed:" + rmChanged);
//                grid1.setText("" + RM[0]);
//                grid2.setText("" + RM[1]);
//                grid3.setText("" + RM[2]);
//
//                grid4.setText("" + RM[3]);
//                grid5.setText("" + RM[4]);
//                grid6.setText("" + RM[5]);
//
//                grid7.setText("" + RM[6]);
//                grid8.setText("" + RM[7]);
//                grid9.setText("" + RM[8]);

               // Intent intent = new Intent(ReadSensorActivity);
            }
        });

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
    public SensorActivity(Context mContext) {
        this.mContext = mContext;
        mSensorManager = (SensorManager)this.mContext.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public SensorManager getSensorManager() {
        return this.mSensorManager;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        // displays current rotation matrix to grid

    }
}

