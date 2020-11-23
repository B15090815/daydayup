package designPattern.Factory;

public interface Color {
    void fill();
}

class Red implements Color {
    @Override
    public void fill() {
        System.out.println("red");
    }
}

class Green implements Color {
    @Override
    public void fill() {
        System.out.println("green");
    }
}

class Black implements Color {
    @Override
    public void fill() {
        System.out.println("black");
    }
}