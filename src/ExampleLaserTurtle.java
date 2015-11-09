import laserTurtle.ModelDisplay;
import laserTurtle.Turtle;
import laserTurtle.World;


/**
 * ExampleLaserTurtle class
 *
 * Just draws a triangle
 */
public class ExampleLaserTurtle extends Turtle {
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

    public void drawTree(int levels, int size){
        if(levels == 0)
            return;
        forward(size);
        turn(30);
        drawTree(levels - 1, size / 2);
        turn(-60);
        drawTree(levels - 1, size / 2);
        turn(30);
        penUp();
        backward(size);
        penDown();
    }

    public static void main(String[] args) {

        //The "world" the turtle moves around in.
        //The default is 640x480 pixels (160mmx120mm) but you can make a bigger world (see the snowflake example)
        World earth = new World(2000,1000);

        //Make a new instance of your laser turtle class and start it at (50,50)
        ExampleLaserTurtle turtle = new ExampleLaserTurtle(100,500,earth);

        turtle.turnToFace(2000, 500);
        turtle.drawTree(4, 320);

        //Draw a triangle
//        turtle.moveTo(100, 100);
//        turtle.moveTo(200, 0);
//        turtle.moveTo(50, 50);   //Move it back to where we started

        //Now, convert the steps the turtle just took into something a laser cutter can understand
        //Laser cutters speak the strange language of "DXF files"
//        turtle.writeDXF("triangle.dxf");
    }
}

