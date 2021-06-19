import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Assignment04 implements GLEventListener{

    private GLU glu;


    //@Override
    public void init(GLAutoDrawable gld)
    {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -250.0, 250.0);
    }

    public void display(GLAutoDrawable drawable)
    {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
          DrawMPLCircle(gl, 160,0,0);
          DrawMPLCircle(gl, 80,57,57);
          DrawMPLCircle(gl, 80,-57,-57);
          DrawMPLCircle(gl, 80,57,-57);
          DrawMPLCircle(gl, 80,-57,57);
          DrawMPLCircle(gl, 80,-57,57);
          DrawMPLCircle(gl, 80,0,79);
          DrawMPLCircle(gl, 80,79,0);
          DrawMPLCircle(gl, 80,0,-79);
          DrawMPLCircle(gl, 80,-79,0);
    }

    //@Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
        //do nothing
    }



    private void DrawMPLCircle(GL2 gl, int r,int Xc, int Yc)
    {
        //write your own code

        gl.glPointSize(1.0f);
        gl.glColor3d(1, 0, 0);
        gl.glBegin(GL2.GL_POINTS);

        int d = 1 - r;
        int x=0;
        int y=r;
        while(x<y)
        {
            if(d<0)
            {
                d = d + 2 * x + 3;
                x++;
            }
            else
            {
                d = d + 2 * x - 2 * y + 5;
                x++;
                y--;
            }
            gl.glVertex2d(Xc+x, Yc+y);
            gl.glVertex2d(Xc-x, Yc+y);
            gl.glVertex2d(Xc-x, Yc-y);
            gl.glVertex2d(Xc+x, Yc-y);
            gl.glVertex2d(Xc+y, Yc+x);
            gl.glVertex2d(Xc-y, Yc+x);
            gl.glVertex2d(Xc-y, Yc-x);
            gl.glVertex2d(Xc+y, Yc-x);
        }
        gl.glEnd();
    }




    public void dispose(GLAutoDrawable arg0)
    {
        //do nothing
    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Assignment04 l = new Assignment04();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("circle");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;
