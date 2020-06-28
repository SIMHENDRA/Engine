package Engine.graphics;



public class Mesh {
    
    public tri[] ts;

    public Mesh(int sz) {
        ts = new tri[sz];
        for (int i = 0; i<sz;i++) {
            ts[i] = new tri();
        }
    }

    public Mesh() {
        V a = new V(-.5, -.5, 0);
        V b = new V(-.5, .5, 0);
        V c = new V(.5, -.5, 0);
        V d = new V(.5, .5, 0);
        V e = new V(-.5, -.5, 1);
        V f = new V(-.5, .5, 1);
        V g = new V(.5, -.5, 1);
        V h = new V(.5, .5, 1); 

        tri ta = new tri(a.vcpy(),b.vcpy(),d.vcpy());
        tri tb = new tri(a.vcpy(),d.vcpy(),c.vcpy());
        tri tc = new tri(c.vcpy(),d.vcpy(),h.vcpy());
        tri td = new tri(c.vcpy(),h.vcpy(),g.vcpy());
        tri te = new tri(g.vcpy(),h.vcpy(),f.vcpy());
        tri tf = new tri(g.vcpy(),f.vcpy(),e.vcpy());
        tri tg = new tri(e.vcpy(),f.vcpy(),b.vcpy());
        tri th = new tri(e.vcpy(),b.vcpy(),a.vcpy());
        tri ti = new tri(e.vcpy(),a.vcpy(),c.vcpy());
        tri tj = new tri(e.vcpy(),c.vcpy(),g.vcpy());
        tri tk = new tri(h.vcpy(),d.vcpy(),b.vcpy());
        tri tl = new tri(h.vcpy(),b.vcpy(),f.vcpy());

        ts = new tri[] {ta,tb,tc,td,te,tf,tg,th,ti,tj,tk,tl};
    }

    public void project(double[][] mat, double znq, double scrW, double scrH) { //arbitrary z component added in Game.render()
        this.transm3x3(mat);
        this.addz(znq);
        for (tri i : ts) {
            for (V j : i.vs) {
                j.x /= j.z;
                j.y /= j.z;
                j.x += 1.0;                
                j.y += 1.0;
                j.x *= .5*scrW;
                j.y *= .5*scrH;
            }
        }

    }

    public void transm3x3(double[][] A) {
        //int sz = ts.length;
        for (tri i : ts) {
            for (V j : i.vs) {
                //j.print();
                j.trans3x3(A);
                //j.print();
            }
            i.updn();
        }
    }

    public void addz(double dz) {
        int sz = ts.length;
        for (int i = 0;i<sz;i++) {
            for (int j = 0;j<3;j++) {
                ts[i].vs[j].z += dz;
            }
        }
    }

    public void addy(double dy) {
        int sz = ts.length;
        for (int i = 0;i<sz;i++) {
            for (int j = 0;j<3;j++) {
                ts[i].vs[j].y += dy;
            }
        }
    }

    public void addx(double dx) {
        int sz = ts.length;
        for (int i = 0;i<sz;i++) {
            for (int j = 0;j<3;j++) {
                ts[i].vs[j].x += dx;
            }
        }
    }

    public Mesh mcpy() {
        int sz = this.ts.length;
        Mesh ret = new Mesh(sz);
        for (int i = 0; i<sz; i++) ret.ts[i] = this.ts[i].tcpy();
        return ret;
    }

    public void updn() {
        for (tri i : ts) i.updn();
    }

    public void updCS(V cam) {
        for (tri i : ts) i.updCS(cam);
    }

    public void print() {
        for (tri i : ts) i.print();
    }

    public static void main(String[] args) {
        
        /* double[][] A = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = (i==j) ? 2 : 0;
                //A[1][2] = 5;
                System.out.printf("%.2f  ", A[i][j]);

            }
            System.out.println();
        }
        System.out.println();
        
        Mesh X = new Mesh();
        //X.print();
        X.transm3x3(A);
        X.print();
        System.out.println();
        System.out.println();
        System.out.println();
        //X.print(); */


        /* Mesh X = new Mesh();
        X.print();
        System.out.println("Copy:");
        Mesh Y = X.mcpy();
        Y.print();  */


        /* Mesh X = new Mesh();

        X.addz(50.52);
        X.print(); */



    }


}