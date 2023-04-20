import java.util.Scanner;
import java.lang.Math;

abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
}

class Triangle extends Shape {
    double a, b, c;
    public Triangle(double s1, double s2, double s3) {
        a = s1;
        b = s2;
        c = s3;
    }
    public double getArea() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    public double getPerimeter() {
        return a + b + c;
    }
}

class Rectangle extends Shape {
    double width, height;
    public Rectangle(double w, double h) {
        width = w;
        height = h;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Circle extends Shape {
    double radius;
    public Circle(double r) {
        radius = r;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Square extends Rectangle {
    public Square(double a) {
        super(a, a);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
        	System.out.print("Enter number of shapes: ");
        	n = sc.nextInt();
        }
        while(n <= 0);
               
        Shape[] shapes = new Shape[n];
        for (int i = 0; i < n; i++) {        	
        	System.out.println("1. Triangle");
        	System.out.println("2. Rectangle");
        	System.out.println("3. Circle");
        	System.out.println("4. Square");
        	System.out.print("Enter shape type: ");
            int type = sc.nextInt();
            switch (type) {
                case 1:
                    System.out.print("Enter sides of triangle: ");
                    double s1 = sc.nextDouble();
                    double s2 = sc.nextDouble();
                    double s3 = sc.nextDouble();
                    shapes[i] = new Triangle(s1, s2, s3);
                    System.out.println("Area: " + shapes[i].getArea());
                    System.out.println("Perimeter: " + shapes[i].getPerimeter());
                    break;
                case 2:
                    System.out.print("Enter width and height of rectangle: ");
                    double w = sc.nextDouble();
                    double h = sc.nextDouble();
                    shapes[i] = new Rectangle(w, h);
                    System.out.println("Area: " + shapes[i].getArea());
                    System.out.println("Perimeter: " + shapes[i].getPerimeter());
                    break;
                case 3:
                    System.out.print("Enter radius of circle: ");
                    double r = sc.nextDouble();
                    shapes[i] = new Circle(r);
                    System.out.println("Area: " + shapes[i].getArea());
                    System.out.println("Perimeter: " + shapes[i].getPerimeter());
                    break;
                case 4:
                    System.out.print("Enter side of square: ");
                    double side = sc.nextDouble();
                    shapes[i] = new Square(side);
                    System.out.println("Area: " + shapes[i].getArea());
                    System.out.println("Perimeter: " + shapes[i].getPerimeter());
                    break;
                default:
                    System.out.println("Invalid shape type");
                    i--;
                    break;
            }
        }
    }
}
