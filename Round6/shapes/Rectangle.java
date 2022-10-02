
public class Rectangle implements IShapeMetrics{
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    
    public String toString(){
        return String.format("Rectangle with height %.2f and widht %.2f", height, width);
    }

    @Override
    public String name(){
        return "rectangle";
    }
    
    @Override
    public double area(){
        return height * width;
    }
    
    @Override
    public double circumference(){
        return 2* height + 2 * width;
    }
}
