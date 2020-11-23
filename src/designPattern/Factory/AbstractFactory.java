package designPattern.Factory;

public abstract  class AbstractFactory {
    public abstract Shape getShape(String shape);
    public abstract Color getColor(String color);
}


class ShapeFactory2 extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        if (shape == null)
            return null;
        if(shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {

        return null;
    }

}

class ColorFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        return null;
    }
    @Override
    public Color getColor(String color) {
        if (color == null)
                return null;
        if (color.equalsIgnoreCase("red"))
            return new Red();
        else if (color.equalsIgnoreCase("green"))
            return new Green();
        else if (color.equalsIgnoreCase("black"))
            return new Black();
        return null;
    }
}

