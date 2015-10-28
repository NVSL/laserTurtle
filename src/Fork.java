import laserTurtle.ModelDisplay;
import laserTurtle.Turtle;
import laserTurtle.World;

public class Fork extends Turtle{

    /**
     * The constructor
     * @param startx    Starting x position
     * @param starty    Starting y position
     * @param modelDisplay  The "world"
     */
    public Fork(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    public static void main(String[] args){
        Fork turtle = new Fork(50,50,new World(600,400));

        // Path 0
        turtle.penUp();
        turtle.moveTo(250, 109);
        turtle.penDown();
        turtle.moveTo(250, 181);
        turtle.moveTo(236, 181);
        turtle.moveTo(236, 109);
        turtle.moveTo(221, 123);
        turtle.moveTo(221, 196);
        turtle.moveTo(236, 210);
        turtle.moveTo(236, 370);
        turtle.moveTo(250, 384);
        turtle.moveTo(294, 384);
        turtle.moveTo(308, 370);
        turtle.moveTo(308, 210);
        turtle.moveTo(323, 196);
        turtle.moveTo(323, 123);
        turtle.moveTo(308, 109);
        turtle.moveTo(308, 181);
        turtle.moveTo(294, 181);
        turtle.moveTo(294, 109);
        turtle.moveTo(279, 123);
        turtle.moveTo(279, 181);
        turtle.moveTo(265, 181);
        turtle.moveTo(265, 123);
        turtle.moveTo(250, 109);

// Path 1
        turtle.penUp();
        turtle.moveTo(366, 384);
        turtle.penDown();
        turtle.moveTo(395, 384);
        turtle.moveTo(395, 196);
        turtle.moveTo(468, 123);
        turtle.moveTo(395, 51);
        turtle.moveTo(366, 51);
        turtle.moveTo(366, 384);

        turtle.writeDXF("fork_and_knife.dxf", 0.33, true);

    }
}


