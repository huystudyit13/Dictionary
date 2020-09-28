import java.util.Scanner;

public class Fraction {
    private int numerator;
    private int denominator = 1;

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int n) {
        this.numerator = n;
    }

    public int getDenominator() {
        return denominator;
    }

    /**
     * ham set tu so.
     */
    public void setDenominator(int n) {
        if (n != 0) {
            this.denominator = n;
        }
    }

    /**
     * ham khoi tao khong co tham so.
     */
    public Fraction() {
    }

    /**
      * ham khoi tao co tham so.
      */
    public Fraction(int n, int d) {
        if (d != 0) {
            this.numerator = n;
            this.denominator = d;
        }
        if (n == 0) {
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    /**
     * ham tim uoc chung lon nhat.
     */
    public int gcd(int a, int b) {
        if (a * b == 0) {
            return a + b;
        } else if (a > b) {
            return gcd(a % b, b);
        } else {
            return gcd(a, b % a);
        }
    }

    /**
     * ham rut gon.
     */
    public Fraction reduce() {
        Fraction a = new Fraction();
        int n = gcd(this.numerator, this.denominator);
        a.numerator = this.numerator / n;
        a.denominator = this.denominator / n;
        return a;
    }

    /**
     * ham cong.
     */
    public Fraction add(Fraction b) {
        Fraction a = new Fraction();
        a.numerator = this.numerator * b.denominator + this.denominator * b.numerator;
        a.denominator = this.denominator * b.denominator;
        return a;
    }

    /**
     * ham tru.
     */
    public Fraction subtract(Fraction b) {
        Fraction a = new Fraction();
        a.numerator = this.numerator * b.denominator - this.denominator * b.numerator;
        a.denominator = this.denominator * b.denominator;
        return a;
    }

    /**
     * ham nhan.
     */
    public Fraction multiply(Fraction b) {
        Fraction a = new Fraction();
        a.numerator = this.numerator * b.numerator;
        a.denominator = this.denominator * b.denominator;
        return a;
    }

    /**
     * ham chia.
     */
    public Fraction divide(Fraction b) {
        Fraction a = new Fraction();
        if (b.numerator != 0) {
            a.numerator = this.numerator * b.denominator;
            a.denominator = this.denominator * b.numerator;
        }
        return a;
    }

    /**
     * ham so sanh.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Fraction) {
            Fraction other = (Fraction) obj;
            other.reduce();
            double n = this.numerator / this. denominator;
            double m = other.numerator / other.denominator;
            return n == m;
        } else {
            return false;
        }
    }
}
