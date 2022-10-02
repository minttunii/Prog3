
public class Circle implements IShapeMetrics{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    
    public String toString(){
        return "Circle with radius: " + radius;
    }
    
    @Override
    public String name(){
        return "cirle";
    }
    
    @Override
    public double area(){
        return Math.pow(IShapeMetrics.PI, 2) * radius;
    }
    
    @Override
    public double circumference(){
        return 2* IShapeMetrics.PI * radius;
    }
    
   
}
