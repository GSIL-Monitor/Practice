package thinkinginjavapractice.innerclasses;

/**
 * Created by DreamYao on 2016/6/21.
 */
class Sequence3 {
    private Object[] objects;
    private int next;

    public Sequence3(int size) {
        objects = new Object[size];
    }

    public void add(Object x) {
        if (next < objects.length)
            objects[next++] = x;
    }

    private class ReverseSelector implements Selector {
        int i = objects.length - 1;

        public boolean end() {
            return i < 0;
        }

        public Object current() {
            return objects[i];
        }

        public void next() {
            if (i >= 0) i--;
        }
    }

    private class SequenceSelector implements Selector {
        private int i;

        public boolean end() {
            return i == objects.length;
        }

        public Object current() {
            return objects[i];
        }

        public void next() {
            if (i < objects.length) i++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public Selector reverseSelector() {
        return new ReverseSelector();
    }
}

public class Test2022 {
    public static void main(String[] args) {
        Sequence3 sequence = new Sequence3(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.reverseSelector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}