package Engine;
import Engine.graphics.*;

public class Screen {
    
    private int width, height;
    public int[] pixels;

    public Screen (int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void renderLn(V a, V b, int coll) {
        int x1 = (int)a.x;
        int y1 = (int)a.y;
        int x2 = (int)b.x;
        int y2 = (int)b.y;
        if (coll == -1) coll = 0xff00ff;
        
        int d = 0;
 
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
 
        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        int x = x1;
        int y = y1;
 
        if (dx >= dy) {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                if (withinBounds(x, y)) pixels[x + y*width] = coll;
                else return;
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        //System.out.println("Line Drawn.");
    }

    public void rendert(tri a) {
        if (!a.cansee) return;
        else { 
        renderLn(a.vs[0],a.vs[1],-1);
        renderLn(a.vs[1],a.vs[2],-1);
        renderLn(a.vs[2],a.vs[0],-1);
        }
    }

    public void renderm(Mesh a) {
        int sz = a.ts.length;
        for (int i = 0; i<sz; i++) {
            rendert(a.ts[i]);
        }
    }

    public void clear() { for (int i = 0;i<pixels.length;i++) pixels[i] = 0; }


    public boolean withinBounds(int x, int y) {
        return (y>=0 && y<height)&&(x>=0 && x<width);
    }

    public static void main(String[] args) {
        double a = 1.6; //aspect ratio
        double fov = (1.5708); //radians
        double f = 1.0 / (Math.tan(fov*0.5));
        double zn = 0.1; //nearest z coord
        double zf = 1000; //farthest z coord
        double q = zf/(zf-zn);
        double znq = -zn*q;

        double[][] proj = new double[][] {{a*f, 0, 0},{0,f,0},{0,0,q}};
        
        
        Mesh A = new Mesh();
        A.print();
        System.out.println("transforming....");
        A.addz(5.0);
        A.project(proj, znq, 1600, 1000);
        A.print();
    }



} 