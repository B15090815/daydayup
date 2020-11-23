package designPattern.Factory;

public class FactoryProducer {
    public AbstractFactory getFactory(String factory) {
        if (factory == null)
            return null;
        if (factory.equalsIgnoreCase("shape"))
            return new ShapeFactory2();
        else if(factory.equalsIgnoreCase("color"))
            return new ColorFactory();
        return null;
    }
}
