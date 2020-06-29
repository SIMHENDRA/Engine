package Engine.graphics;

public class V {
    
    public double x, y, z;
    

    public V() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public V(V a) {
        this(a.x,a.y,a.z);
    }

    public V(double xx, double yy, double zz) {
        this.x = xx;
        this.y = yy;
        this.z = zz;
    }

    public V vcpy() {
        return new V(this);
    }

    public static double dp(V a, V b) {
        return a.x*b.x + a.y*b.y + a.z*b.z;
    }

    public void trans3x3(double[][] A) {
        double xx = this.x;
        double yy = this.y;
        double zz = this.z;
        V old = new V(xx,yy,zz);

        V x1 = new V(A[0][0], A[1][0], A[2][0]);
        V y1 = new V(A[0][1], A[1][1], A[2][1]);
        V z1 = new V(A[0][2], A[1][2], A[2][2]);
        //x1.print();
        //y1.print();
        //z1.print();

        this.x = dp(old, x1);
        this.y = dp(old, y1);
        this.z = dp(old, z1);
    
    }

    public void print() {
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println();
    }

    public static V cp(V a, V b) {
        double a1 = a.x;
        double a2 = a.y;
        double a3 = a.z;
        double b1 = b.x;
        double b2 = b.y;
        double b3 = b.z;

        return new V((a2*b3 - a3*b2),(a3*b1 - a1*b3),(a1*b2 - a2*b1));
    }

    public static V add(V a, V b) {
        V ret = new V();
        ret.x = a.x + b.x;
        ret.y = a.y + b.y;
        ret.z = a.z + b.z;
        return ret;
    }
    public static V sub(V a, V b) {
        V ret = new V();
        ret.x = a.x - b.x;
        ret.y = a.y - b.y;
        ret.z = a.z - b.z;
        return ret;
    }

    public double norm() {
        return Math.sqrt((this.x*this.x + this.y*this.y + this.z*this.z));
    }

    public static double norm(V a) {
        return a.norm();
    }

    public void normalize() {
        double n = this.norm();
        if (n==0) return;
        else {
        this.x = this.x/n;
        this.y = this.y/n;
        this.z = this.z/n;
        }
    }


    public static void main(String[] args) {
        //double a = 0;
        double[][] A = new double[][] {{1,0,0},{1,2,3},{3,3,3}};
        /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = a++;//(i==j) ? 1 : 0;
                //A[1][2] = 5;
                System.out.printf("%.2f  ", A[i][j]);

            }
            System.out.println();
        }
        System.out.println(); */
        
        
        V b = new V(1,2,3);
        //b.print();

        b.trans3x3(A);

        b.print();


    }

    




}