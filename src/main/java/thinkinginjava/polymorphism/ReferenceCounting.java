package thinkinginjava.polymorphism;

/**
 * Created by DreamYao on 2016/5/19.
 */
//P161
public class ReferenceCounting {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Shared shared = new Shared();
        Composing[] composings = { new Composing(shared), new Composing(shared), new Composing(shared),
                new Composing(shared), new Composing(shared), };

        for (Composing c : composings) {
            c.dispose();
        }
    }

}

class Shared {
    private int refcount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shared() {
        // TODO Auto-generated constructor stub
        System.out.println("Creating " + this);
    }

    public void addRef() {
        refcount++;
    }

    protected void dispose() {
        if (--refcount == 0) {
            System.out.println("Disposing " + this);
        }
    }

    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared shared;
    private static long counter = 0;
    private final long id = counter++;

    public Composing(Shared shared) {
        System.out.println("Creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        System.out.println("disposing " + this);
        shared.dispose();
    }

    public String toString() {
        return "Composing " + id;
    }
}
