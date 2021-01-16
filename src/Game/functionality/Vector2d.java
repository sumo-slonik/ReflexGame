package Game.functionality;

//klasa reprezentująca wektor 2d
public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //wypisywanie wektora jako napis
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    // sprawdzanie porpzedzania  innego wektor
    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    // sprawdzanie bycia następcą innego wektora
    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    //zwraca prawy górny róg prostokąta opisanego przez dwa wektory
    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    // zwraca lewy dolny róg opisanego przez dwa wektory prostokąta
    public Vector2d lowerRight(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    // dodawanie dwóch wektorów
    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    // odejmowanie wektorów
    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    //porównywanie wektoreów
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2d)) return false;
        Vector2d that = (Vector2d) other;
        return this.x == that.x && this.y == that.y;
    }

    ////wektor przeciwny do danego
    public Vector2d opposite() {
        return new Vector2d(this.x * (-1), this.y * (-1));
    }
    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
