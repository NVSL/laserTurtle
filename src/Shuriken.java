import laserTurtle.ModelDisplay;
import laserTurtle.Turtle;
import laserTurtle.World;

public class Shuriken extends Turtle{

    /**
     * The constructor
     * @param startx    Starting x position
     * @param starty    Starting y position
     * @param modelDisplay  The "world"
     */
    public Shuriken(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    /**
     *
     * Draw the semicircle start at point (x1,y1), ending at (x2,y2),
     * and subtending the given angle
     *
     * @param x1 start x
     * @param y1 start y
     * @param x2 end x
     * @param y2 end y
     * @param degrees angle from x to y
     */
    public void drawArc(int x1, int y1, int x2, int y2, int degrees){
        double theta = Math.toRadians(degrees);

        //Center of the circle
        double centerx = (Math.cos(theta) * (y1 - y2) + Math.sin(theta) * x1 + Math.sin(theta) * x2 + y1 - y2) / Math.sin(theta) / 0.2e1;
        double centery = ((y1 + y2) * Math.sin(theta) - (Math.cos(theta) + 0.1e1) * (x1 - x2)) / Math.sin(theta) / 0.2e1;

        //Radius of circle
        double radius = Math.hypot(x1 - centerx, y1 - centery);

        //Starting angle of semicircle
        double theta0 = Math.atan2(y1 - centery, x1 - centerx);

        int segments = 15;
        //Draw the arc
        penUp();
        moveTo(x1, y1);
        penDown();
        for(int i=1; i<=segments; i++){
            double current = theta0 + theta * ((double)i / segments);
            int x = (int)Math.round(radius * Math.cos(current) + centerx);
            int y = (int)Math.round(radius * Math.sin(current) + centery);
            moveTo(x, y);
        }

    }

    /**
     * Same as drawArc but using a vector displacement
     * @param vx
     * @param vy
     * @param degrees
     */
    public void drawArc(int vx, int vy, int degrees){
        drawArc(getXPos(), getYPos(), getXPos() + vx, getYPos() + vy, degrees);
    }

    /**
     * Draw the sharp part of our shuriken
     * @param size Size of the base
     */
    public void drawHook(int size){
        int x3,y3,x2,y2;
        int diagonal = (int)Math.round(size*2.2);
        int tilt = 20;
        penUp();
        turn(-tilt);
        forward(diagonal);
        x2 = getXPos();
        y2 = getYPos();
        backward(diagonal);
        turn(tilt);

        forward(size);
        x3 = getXPos();
        y3 = getYPos();
        backward(size);

        penDown();
        drawArc(getXPos(), getYPos(), x2, y2, 80);
        drawArc(x2,y2,x3,y3,-80);
    }

    /**
     * Draw an arc in the given direction
     * @param pixels Amount to go forward
     * @param angle Angle to subtend
     */
    public void forwardArc(int pixels, int angle){
        int x1,y1,x2,y2;
        x1 = getXPos();
        y1 = getYPos();
        penUp();
        forward(pixels);
        x2 = getXPos();
        y2 = getYPos();
        backward(pixels);
        penDown();
        drawArc(x1,y1,x2,y2, angle);
    }


    public static void main(String[] args){
        Shuriken turtle = new Shuriken(50,50,new World(600,400));

        int sides = 4;
        turtle.penUp();
        turtle.moveTo(240, 230);

        for(int i=0; i<sides; i++){
            turtle.drawHook(85);
            turtle.turn(360 / sides);
        }

    }
}


