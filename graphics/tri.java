package Engine.graphics;

public class tri {
    
    public V[] vs = new V[3];
    public V normal;
    public V camToTri;
    public boolean cansee;

    public tri() {
        for (int i = 0;i<3;i++) vs[i] = new V();
        normal = V.cp(vs[0],vs[1]);
    }

    public tri(V va, V vb, V vc) {
        this.vs[0] = va;
        this.vs[1] = vb;
        this.vs[2] = vc;
        normal = V.cp(vs[0],vs[1]);
    }

    public void print() {
        for (V xx : vs) System.out.printf("%.2f  ", xx.x);
        System.out.println();
        for (V yy : vs) System.out.printf("%.2f  ", yy.y);
        System.out.println();
        for (V zz : vs) System.out.printf("%.2f  ", zz.z);
        System.out.println();
        System.out.println();
        

    }

    public tri tcpy() {
        return new tri(this.vs[0].vcpy(),this.vs[1].vcpy(),this.vs[2].vcpy());
    }

    public void updn() {
        V lin1 = V.sub(vs[1], vs[0]);
        V lin2 = V.sub(vs[2], vs[1]);
        this.normal = V.cp(lin1, lin2);
        this.normal.normalize();
    }

    public void updctt(V cam) {
        camToTri = V.sub(vs[0],cam);
    }

    public void updCS(V cam) {
        updn();
        updctt(cam);
        if (V.dp(normal,camToTri) < 0.0) cansee = true;
        else cansee = false;
    }

    /* public void updmid() {
        double xx = vs[0].x + vs[1].x + vs[2].x ;
        double yy = vs[0].y + vs[1].y + vs[2].y ;
        double zz = vs[0].z + vs[1].z + vs[2].z ;
        mid = new V(xx/3.0,yy/3.0,zz/3.0);
    }

    public void updntip() {
        ntip = new V()
    } */

    public static void main(String[] args) {
        tri x = new tri();
        for (V i : x.vs) i.print();
    }
    


}