package Engine.graphics;

public class mat {
    public V a, b, c; //DOWNARDS

    public mat() {
        this.a = new V();
        this.b = new V();
        this.c = new V();
    }

    public mat(V x, V y, V z) {
        this.a = x;
        this.b = y;
        this.c = z;
    }

    public void set(int x, int y, double val)  {
        return;
    }

    public void print() {
        System.out.printf("%.2f  %.2f  %.2f \n", a.x, b.x, c.x);
        System.out.printf("%.2f  %.2f  %.2f \n", a.y, b.y, c.y);
        System.out.printf("%.2f  %.2f  %.2f \n", a.z, b.z, c.z);
    }

    public static void main(String[] args) {
        V x = new V(1,2,3);
        V y = new V(4,5,6);
        V z = new V(7,8,9);
        mat A = new mat(x,y,z);
        A.print();
    }

}