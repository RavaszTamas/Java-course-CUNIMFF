package Java11.src.cz.cuni.mff.java.patterns;

public class Complex {
    private double real;
    private double imaginary;

    public static Complex fromCartesian(double real, double imaginary) {
        return new Complex(real, imaginary);
    }

    public static Complex fromPolar(double modulus, double angle) {
        return new Complex(modulus * Math.cos(angle), modulus * Math.sin(angle));
    }

    private Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
}
