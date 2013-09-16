package turtle;

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        int numberOfSides = 4;
        int interiorAngle = 90;
        for (int x = 0; x < numberOfSides; x++) {
            turtle.forward(sideLength);
            turtle.turn(interiorAngle);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, must be >2
     * @return angle, in degrees between 0 and 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return ((sides - 2) * 180.0)/sides;              
    }

    /**
     * Determine number of sides given the interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle interior angles, in degrees
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        //inverse of formula for calculating interior angles from sides
        return (int)Math.round(360/(180 - angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double insideAngle = calculateRegularPolygonAngle(sides);
        for (int x = 0; x < sides; x++) {
            turtle.forward(sideLength);
            turtle.turn(180 - insideAngle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing the angle currentHeading. The return angle must be expressed in
     * degrees, and 0 <= angle < 360.
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX currentY current location
     * @param targetX targetY target point
     * @return adjustment to heading (right turn amount) to get to target point.
     *         Must be positive, and less than 360.
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double heading = Math.toDegrees(Math.atan2(targetX - currentX, targetY - currentY));
        double newHeading = heading - currentHeading;
        //make sure newHeading is between 0 and 360
        return ((newHeading % 360) + 360) % 360;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point to the next.
     * 
     * You should use your calculateHeadingToPoint() function in this one to simplify things. Assume
     * that the turtle is at the first point, facing up (i.e. 0 degrees), and for further points,
     * assume that the turtle is facing the direction last specified.  
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size #points-1.
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> headings = new ArrayList<Double>();
        double currentHeading = 0;
        for (int x = 0; x < xCoords.size() - 1; x++) {
            double newHeading = calculateHeadingToPoint(currentHeading, xCoords.get(x), yCoords.get(x), xCoords.get(x + 1), yCoords.get(x + 1));
            //must add all newHeadings up to calculate the currentHeading
            currentHeading = (currentHeading + newHeading) % 360;
            headings.add(newHeading);
        }
        return headings;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * We'll be peer-voting on the different images, and the highest-rated one will win a prize:
     * free dinner with select staff members.
     * 
     * @param turtle the turtle context
     */
    
    //draws random lines at random angles
    public static void drawPersonalArt(Turtle turtle) {
        int length = 100;
        double angle = 90;
        while (angle < 180) {
            turtle.forward(length);
            turtle.turn(angle);
            length++;
            angle++;
        }
    }

    /**
     * Main method
     * 
     * This is the method that runs when you run "java TurtleSoup".  Right now, it
     * will run your drawSquare() method.
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
