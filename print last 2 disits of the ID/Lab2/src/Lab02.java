import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab02 implements GLEventListener{

    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        // For digit '3'
        DDA(gl, 50,50,60,50);
        DDA(gl, 60,40,60,50);
        DDA(gl, 50,40,60,40);
        DDA(gl, 60,30,60,40);
        DDA(gl, 50,30,60,30);

        // For digit '2'
        DDA(gl, 65,50,75,50);
        DDA(gl, 75,40,75,50);
        DDA(gl, 65,40,75,40);
        DDA(gl, 66,30,66,40);
        DDA(gl, 65,30,75,30);

    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }



    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }


    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(1.3f);
        gl.glColor3d(1, 0, 0);

        float dx = x2 - x1;
        float dy = y2 - y1;
        float m = dy / dx;
        float x = x1, y = y1, steps;

        if (dy > dx) {
            steps = Math.abs(dy);
        } else {
            steps = Math.abs(dx);
        }

        while (steps != 0) {
            steps--;
            gl.glBegin (GL2.GL_POINTS);
            if (m < 1) {
                x = x + 1;
                y = y + m;
                gl.glVertex3f(x,y,0);
            } else if(m>1) {
                x = x + (1 / m);
                y = y + 1;
                gl.glVertex3f(x,y,0);
            }
            gl.glEnd();
        }
    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab02 l = new Lab02();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("Last two digit of my id");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;
