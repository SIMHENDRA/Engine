package Engine.graphics;

public class tri {
    
    public V[] vs = new V[3];

    public tri() {
        for (int i = 0;i<3;i++) vs[i] = new V();
    }

    public tri(V va, V vb, V vc) {
        this.vs[0] = va;
        this.vs[1] = vb;
        this.vs[2] = vc;
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

    public static void main(String[] args) {
        tri x = new tri();
        for (V i : x.vs) i.print();
    }
    


}