public class Vector2 {

    // Variables
    /**
     * X value of vector2
     */
    private double x;

    /**
     * Y value of vector2
     */
    private double y;

    // Constructors
    /**
     * Default Vector Constructor
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Vector2 Vector2 Constructor
     *
     * @param v default vector2 value
     */
    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Two-Value Vector Constructor
     *
     * @param x default x value
     * @param y default y value
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set Vector2 X Value
     *
     * @param x new x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set Vector2 Y Value
     *
     * @param y new y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Add to Vector2 X Value
     *
     * @param x x value to add
     */
    public void addX(double x) {
        this.x += x;
    }

    /**
     * Add to Vector2 Y Value
     *
     * @param y y value to add
     */
    public void addY(double y) {
        this.y += y;
    }

    /**
     * Add to Vector2 at angle
     *
     * @param m magnitude of the applied force
     * @param d direction of the applied force
     */
    public void addAtAngle(double m, double d) {
        this.x += Math.cos(d)*m;
        this.y += Math.sin(d)*m;
    }

    /**
     * Two Value Set Vector2 Values
     *
     * @param x new x value
     * @param y new y value
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vector2 Set Vector2 Values
     *
     * @param v new vector2 value
     */
    public void set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    // Get Functions
    /**
     * Get Vector2 X Value
     *
     * @return current X value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get Vector2 Y Value
     *
     * @return current Y value
     */
    public double getY() {
        return this.y;
    }

    // Overridden Functions
    @Override
    public String toString() {
        return Double.toString(this.x) + ", " + Double.toString(this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vector2)) {
            return false;
        }
        Vector2 v = (Vector2) obj;
        return v.x == this.x && v.y == this.y;
    }

    @Override
    public int hashCode() {
        double hash = 3;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        return (int)hash;
    }

}
