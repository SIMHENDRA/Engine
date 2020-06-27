package Engine;

import java.awt.Canvas;
import javax.swing.JFrame;

import Engine.graphics.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;


class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int width = 3000;
    public static int height = 1200;
    public static int scale = 1;
    public static int msdel = 1; // delay for pseudo fps control
    public String windowTitle = "3DEngine";

    private double scrW = (double)width;
    private double scrH = (double)height;
    double a = (scrH / scrW); //aspect ratio
    double fov = (1.5708); //radians
    double f = 1.0 / (Math.tan(fov*0.5));
    double zn = 0.1; //nearest z coord
    double zf = 1000; //farthest z coord
    double q = zf/(zf-zn);
    double znq = -zn*q;

    double thet;
    double dy;

    double[][] rotz = new double[][] {{0,0,0},{0,0,0},{0,0,0}};
    double[][] rotx = new double[][] {{0,0,0},{0,0,0},{0,0,0}};
    double[][] proj = new double[][] {{a*f, 0, 0},{0,f,0},{0,0,q}};

    private Thread thread;
    private JFrame frame;
    public boolean running = false;

    private Screen screen;

    private ArrayList<Mesh> meshes;

    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    //private long iters = 0;
    

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width,height);
        frame = new JFrame();
        meshes = new ArrayList<Mesh>();
        thet = 0;
        dy = 0;
        meshes.add(new Mesh());
        //meshers.add(new mesher());
        
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {thread.join();}
        catch (InterruptedException e) {}
    }

    public void initWindow() {
        this.frame.setResizable(false);
        this.frame.setTitle(this.windowTitle);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void waiter() {
        long time = System.currentTimeMillis();
        long timer = time;
        while(timer<time+msdel) timer = System.currentTimeMillis();      
    }

    public void run() {
        while (running) {
            update();
            render();
            waiter();
        }

    }

    public void update() {
        if (thet<12.5664)thet+=.01;
        else thet = 0;
        
        float ct = (float)Math.cos(thet);
        float st = (float)Math.sin(thet);
        float ctt = (float)Math.cos(thet*.5);
        float stt = (float)Math.sin(thet*.5);
        rotz[0][0] = ct;
        rotz[0][1] = st;
        rotz[1][0] = -st;
        rotz[1][1] = ct;
        rotz[2][2] = 1;

        rotx[0][0] = 1;
        rotx[1][1] = ctt;
        rotx[1][2] = stt;
        rotx[2][1] = -stt;
        rotx[2][2] = ctt;
        
        dy = 10*Math.cos(thet) - dy;
        
        

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        //System.out.printf("a: %f f: %f q: %f zn: %f znq: %f height: %d width: %d \n", a, f, q, zn, znq, height, width);
        //screen.prMesher(meshers.get(0), a, f, q, zn, znq, height, width);
        //for (Mesh i : meshes) screen.prMesher(i, proj, znq, width, height, rotz, rotx);
        //meshes.get(0).print();
        //System.out.println("Copy....");
        Mesh toProj = meshes.get(0).mcpy();
        toProj.transm3x3(rotz);
        toProj.transm3x3(rotx);
        //toProj.print();
        toProj.addz(2.0);
        
        toProj.project(proj, znq, scrW, scrH);
        //System.out.println("Project....");

        //toProj.print();

        screen.renderm(toProj);
        /* V a = new V(100,100,1);
        V b = new V(400,400,1);
        screen.renderLn(a,b,-1); */

        for (int i = 0; i < pixels.length; i++) pixels[i] = screen.pixels[i];        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image,0,0, getWidth(),getHeight(), null);
        g.dispose();
        bs.show();
        
    }
    
    public static void main(String args[]) {

        Game game = new Game();
        game.initWindow();
        game.start();

    }

    
    
}