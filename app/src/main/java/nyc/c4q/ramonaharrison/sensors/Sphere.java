package nyc.c4q.ramonaharrison.sensors;

import android.util.Log;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by mona on 5/9/15.
 */
public class Sphere {

    static private FloatBuffer sphereVertex;
    static private FloatBuffer sphereNormal;
    static float sphere_parms[]=new float[3];

    double mRaduis;
    double mStep;
    float mVertices[];
    private static double DEG = Math.PI/180;
    int mPoints;

    /**
     * The value of step will define the size of each facet as well as the number of facets
     *
     * @param radius
     * @param step
     */

    public Sphere( float radius, double step) {
        this.mRaduis = radius;
        this.mStep = step;
        sphereVertex = FloatBuffer.allocate(40000);
        mPoints = build();
        Log.d("ALIS CHECK!!!!!!", " COUNT:" + mPoints);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, sphereVertex);

        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glDrawArrays(GL10.GL_POINTS, 0, mPoints);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private int build() {

        /**
         * x = p * sin(phi) * cos(theta)
         * y = p * sin(phi) * sin(theta)
         * z = p * cos(phi)
         */
        double dTheta = mStep * DEG;
        double dPhi = dTheta;
        int points = 0;

        for(double phi = -(Math.PI); phi <= Math.PI; phi+=dPhi) {
            //for each stage calculating the slices
            for(double theta = 0.0; theta <= (Math.PI * 2); theta+=dTheta) {
                sphereVertex.put((float) (mRaduis * Math.sin(phi) * Math.cos(theta)) );
                sphereVertex.put((float) (mRaduis * Math.sin(phi) * Math.sin(theta)) );
                sphereVertex.put((float) (mRaduis * Math.cos(phi)) );
                points++;

            }
        }
        sphereVertex.position(0);
        return points;
    }
}
