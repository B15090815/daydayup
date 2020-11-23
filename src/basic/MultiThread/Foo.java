package basic.MultiThread;

class Demo {

    public void f1() {
        synchronized (Demo.class) {
            System.out.println("Hello World.");
        }
    }

    public synchronized void f2() {
        System.out.println("Hello World.");
    }

}

class Increment implements Runnable{
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            i++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Foo {
    public static void main(String[] args) throws InterruptedException {
        Increment inc = new Increment();
        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(inc);
        Thread t3 = new Thread(inc);
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        System.out.println(inc.i);
    }



}
