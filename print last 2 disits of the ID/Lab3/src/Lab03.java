import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab03 implements GLEventListener{

    private GLU glu;

    int zone ;

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        // For digit '3'
        drawMPL(gl, 50,50,60,50);
        drawMPL(gl, 59.99F,40,60,50);
        drawMPL(gl, 50,40,60,40);
        drawMPL(gl, 59.99F,30,60,40);
        drawMPL(gl, 50,30,60,30);

        // For digit '2'
        drawMPL(gl, 65,50,75,50);
        drawMPL(gl, 74.99F,40,75,50);
        drawMPL(gl, 65,40,75,40);
        drawMPL(gl, 64.83F,30,65.30F,40);
        drawMPL(gl, 65,30,75,30);



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


    public void drawMPL(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(2.0f);
        gl.glColor3d(1, 0, 0);

        float zone  = findZone(x1,y1,x2,y2);
        float X1 = convertXzero(x1,y1,zone);
        float Y1 = convertYzero(x1,y1,zone);
        float X2 = convertXzero(x2,y2,zone);
        float Y2 = convertYzero(x2,y2,zone);

        float dx=X2-X1;
        float dy=Y2-Y1;
        float dE=dy;
        float dNE=dy-dx;
        float d=dy-(dx/2);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x1, y1);
        float x = X1;
        float y = Y1;
        while (x<X2){
            if(d<0) {
                x=x+1;
                d=d+dE;
                gl.glVertex2f(convertXINIT(x,y,zone),convertYINIT(x,y,zone));
            }
            else {
                x=x+1;
                y=y+1;
                d=d+dNE;
                gl.glVertex2f(convertXINIT(x,y,zone),convertYINIT(x,y,zone));
            }

        }
        gl.glEnd();
    }

    public float findZone(float x1,float y1,float x2,float y2){
        float dx=x2-x1;
        float dy=y2-y1;
        float zone = 0;

        if(Math.abs(dx)>=Math.abs(dy)){
            if(dx>0 && dy >0) zone = 0;
            else if(dx<0 && dy>0) zone = 3;
            else if(dx<0 && dy<0) zone = 4;
            else if(dx>0 && dy<0) zone = 7;
        }
        else {
            if(dx>0 && dy>0) zone = 1;
            else if(dx<0 && dy>0) zone = 2;
            else if(dx<0 && dy<0) zone = 5;
            else if(dx>0 && dy<0) zone = 6;
        }
        return zone;
    }

    float convertXzero(float x, float y, float zone) {
        float a = 0;
        if(zone==0) a = x;
        else if(zone==1) a = y;
        else if(zone==2) a = y;
        else if(zone==3) a = -x;
        else if(zone==4) a = -x;
        else if(zone==5) a = -y;
        else if(zone==6) a = -y;
        else if(zone==7) a = x;
        return a;
    }
    float convertYzero(float x, float y, float zone) {
        float b = 0;
        if(zone==0) b = y;
        else if(zone==1) b = x;
        else if(zone==2) b = -x;
        else if(zone==3) b = y;
        else if(zone==4) b = -y;
        else if(zone==5) b = -x;
        else if(zone==6) b = x;
        else if(zone==7) b = -y;
        return b;

    }
    float convertXINIT(float x, float y, float zone) {
        float a = 0;
        if(zone==0) a = x;
        else if(zone==1) a = y;
        else if(zone==2)  a = -y;
        else if(zone==3) a = -x;
        else if(zone==4) a = -x;
        else if(zone==5) a = -y;
        else if(zone==6) a = y;
        else if(zone==7) a = x;
        return a;
    }
    float convertYINIT(float x, float y, float zone) {
        float b = 0;
        if(zone==0) b = y;
        else if(zone==1) b = x;
        else if(zone==2) b = x;
        else if(zone==3) b = y;
        else if(zone==4) b = -y;
        else if(zone==5) b = -x;
        else if(zone==6) b = -x;
        else if(zone==7) b = -y;
        return b;

    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab03 l = new Lab03();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("Last two digits of my ID (32)");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;