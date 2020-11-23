package designPattern.Factory;

public class Demo {
    public static void main(String[] args) {
        // 工厂模式
//        ShapeFactory shapeFactory = new ShapeFactory();
//        Shape a;
//        a = shapeFactory.getShape("CIRCLE");
//        a.draw();
//
//        a = shapeFactory.getShape("RECTANGLE");
//        a.draw();
//
//        a = shapeFactory.getShape("SQUARE");
//        a.draw();

//        抽象工厂模式
        FactoryProducer factoryProducer = new FactoryProducer();

        AbstractFactory shapeFactory = factoryProducer.getFactory("shape");
        AbstractFactory colorFactory = factoryProducer.getFactory("color");
        Shape f;
        f = shapeFactory.getShape("CIRCLE");
        f.draw();

        f = shapeFactory.getShape("RECTANGLE");
        f.draw();

        f = shapeFactory.getShape("SQUARE");
        f.draw();

        Color c;
        c = colorFactory.getColor("red");
        c.fill();

        c = colorFactory.getColor("black");
        c.fill();

        c = colorFactory.getColor("green");
        c.fill();
    }
}
