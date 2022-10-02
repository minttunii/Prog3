
public class Circle implements IShapeMetrics{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    
    public String toString(){
        return String.format("Circle with radius: %.2f", radius);
    }
    
    @Override
    public String name(){
        return "circle";
    }
    
    @Override
    public double area(){
        return Math.pow(radius, 2) * IShapeMetrics.PI;
    }
    
    @Override
    public double circumference(){
        return 2 * IShapeMetrics.PI * radius;
    }
    
   
}
