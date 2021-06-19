import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class lab4 implements GLEventListener{

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
        DrawMPLCircle(gl, 10,20,20);
        DrawMPLCircle(gl, 10,30,20);
        DrawMPLCircle(gl, 10,40,20);
        DrawMPLCircle(gl, 10,50,20);
        DrawMPLCircle(gl, 10,60,20);
        DrawMPLCircle(gl, 10,70,20);
        DrawMPLCircle(gl, 10,80,8);
        DrawMPLCircle(gl, 10,80,-9);
        DrawMPLCircle(gl, 10,80,-20);
        DrawMPLCircle(gl, 10,80,-30);
        DrawMPLCircle(gl, 10,70,-40);
        DrawMPLCircle(gl, 10,60,-40);
        DrawMPLCircle(gl, 10,50,-40);
        DrawMPLCircle(gl, 10,40,-40);
        DrawMPLCircle(gl, 10,30,-40);
        DrawMPLCircle(gl, 10,20,-50);
        DrawMPLCircle(gl, 10,20,-60);
        DrawMPLCircle(gl, 10,20,-70);
        DrawMPLCircle(gl, 10,20,-80);
        DrawMPLCircle(gl, 10,30,-95);
        DrawMPLCircle(gl, 10,40,-95);
        DrawMPLCircle(gl, 10,50,-95);
        DrawMPLCircle(gl, 10,60,-95);
        DrawMPLCircle(gl, 10,70,-95);
        DrawMPLCircle(gl, 10,80,-95);
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
        lab4 l = new lab4();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("Last one digit of my id");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;
