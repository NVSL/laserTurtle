import turtleClasses.*;

/**
 * Created by sciencectn on 9/18/15.
 */
public class ExampleLaserTurtle extends Turtle{
    /**
     * Makes a new instance of your custom Turtle.
     * This is the recommended constructor
     *
     * @param startx The x position of where the turtle should start
     * @param starty Starting y position. y=0 means top, and increasing y means go down
     * @param modelDisplay For now, this is how you tell it the width and height of your design
     */
    public ExampleLaserTurtle(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    public void triangle(int base, int height, int depth){
        double angle = Math.toDegrees(Math.atan2(2 * height, base));
        turn(-angle);
        int hypot = (int)Math.round(Math.sqrt( Math.pow(base / 2.0, 2) + Math.pow(height, 2.0) ));
        if(depth==0) {
            forward(hypot);
        }else{
            forward(hypot/3);
            triangle(hypot / 3, height / 3, depth - 1);
            forward(hypot/3);
        }
        turn(2 * angle);
        if (depth == 0) {
            forward(hypot);
        }else{
            forward(hypot/3);
            triangle(hypot / 3, height/3, depth-1);
            forward(hypot/3);
        }
        turn(-angle);
    }

    /**
     * Example LaserTurtle program which makes a Koch snowflake
     * @param sides The number of sides of the polygon inside the Koch snowflake.
     *              The original Koch snowflake has 6 sides
     */
    public static void snowflake(int sides){
        ExampleLaserTurtle t = new ExampleLaserTurtle(960/2,150,new World(960,720));
        t.setDelay(5);
        t.setHeading(90);
        for(int i=0;i<sides;i++){
            t.triangle(120,120,2);
            t.turn(360.0/sides);
        }
        t.writeDXF("snowflake.dxf");
    }

    /**
     * Just an example LaserTurtle program which cuts out a triangle
     */
    public static void triangle(){
        World earth = new World();
        ExampleLaserTurtle t1 = new ExampleLaserTurtle(50,50,earth);
        t1.moveTo(100,100);
        t1.moveTo(200,0);
        t1.moveTo(50,50);
        t1.writeDXF("test.dxf");
    }


    public static void main(String[] args) {
        snowflake(8);
    }
}


