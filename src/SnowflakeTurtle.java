import turtleClasses.ModelDisplay;
import turtleClasses.Turtle;
import turtleClasses.World;

public class SnowflakeTurtle extends Turtle {
    /**
     * Makes a new instance of your custom Turtle.
     * This is the recommended constructor
     *
     * @param startx The x position of where the turtle should start
     * @param starty Starting y position. y=0 means top, and increasing y means go down
     * @param modelDisplay For now, this is how you tell it the width and height of your design
     */
    public SnowflakeTurtle(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    /**
     * Makes an isosceles triangle. Can recursively add more triangles to each side
     * If depth=0, it makes an upside-down 'V' shape
     * Otherwise, it adds on more triangles
     *
     * @param base  Size of the base of the triangle. Does not actually get drawn
     * @param height Height of the triangle
     * @param depth Number of times to recurse. Higher depth means more detail
     *
     */
    public void triangle(int base, int height, int depth){
        double angle = Math.toDegrees(Math.atan2(2 * height, base));
        turn(-angle);   //Turn to the direction of the first leg

        //Length of the leg; hypotenuse of the right triangle with side lengths (base/2) and (height)
        int hypot = (int)Math.round(Math.sqrt( Math.pow(base / 2.0, 2) + Math.pow(height, 2.0) ));
        if(depth==0) {
            //Just draw the leg
            forward(hypot);
        }else{
            //Draw one third of the leg, a triangle, then the other third
            forward(hypot/3);
            triangle(hypot / 3, height / 3, depth - 1);
            forward(hypot/3);
        }

        //Turn and do the same for the other leg
        turn(2 * angle);
        if (depth == 0) {
            forward(hypot);
        }else{
            forward(hypot/3);
            triangle(hypot / 3, height/3, depth-1);
            forward(hypot/3);
        }
        turn(-angle);   //Turn back to where we started
    }

    /**
     * Example LaserTurtle program which makes a Koch snowflake
     * @param sides The number of sides of the polygon inside the Koch snowflake.
     *              The original Koch snowflake has 6 sides
     */
    public static void snowflake(int sides){

        //Make a new snowflake turtle, starting it at the middle (x=960/2) and toward the top (y=150)
        //The world is 960x720 (widthxheight)
        SnowflakeTurtle snowflakeTurtle = new SnowflakeTurtle(960/2,150,new World(960,720));
        snowflakeTurtle.setDelay(5);        //Set a delay of 5ms between actions to make it animate
        snowflakeTurtle.setHeading(90);     //Start facing east

        //Make each "side" of the snowflake
        for(int i=0;i<sides;i++){
            snowflakeTurtle.triangle(93, 93, 2);    //Recursively put triangles inside triangles
            snowflakeTurtle.turn(360.0 / sides);    //Turn to the next "side"
        }
        snowflakeTurtle.writeDXF("snowflake.dxf");
    }

    public static void main(String[] args) {
        snowflake(8);
    }
}


