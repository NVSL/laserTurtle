import laserTurtle.ModelDisplay;
import laserTurtle.Turtle;
import laserTurtle.World;

public class CSE91Turtle extends Turtle{

    /**
     * The constructor
     * @param startx    Starting x position
     * @param starty    Starting y position
     * @param modelDisplay  The "world"
     */
    public CSE91Turtle(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    public static void main(String[] args){
        CSE91Turtle turtle = new CSE91Turtle(50,50,new World(600,400));

        /* YOUR MARVELOUS LASER TURTLE CREATION GOES HERE */
    }
}


