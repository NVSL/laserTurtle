import laserTurtle.ModelDisplay;
import laserTurtle.Turtle;
import laserTurtle.World;


public class ExamplesTurtle extends Turtle {
    public ExamplesTurtle(int startx, int starty, ModelDisplay modelDisplay){
        super(startx, starty, modelDisplay);
    }

    /**
     * Draw an n-sided polygon around wherever the turtle is
     * @param sides Number of sides of the polygon
     * @param radius Circumradius, distance from the center to any vertex
     */
    public void drawPolygon(int sides, int radius){
        int startx = getXPos();
        int starty = getYPos();


        int apothem = (int)Math.round(radius * Math.cos(Math.PI / sides));
        int side_length = (int)Math.round(2*Math.sin(Math.PI / sides)*radius);

        //Move up and left, start heading east
        penUp();
        moveTo(startx - side_length/2, starty - apothem);
        setHeading(90);
        penDown();

        for(int i=0;i<sides;i++){
            forward(side_length);
            turn(360.0 / sides);
        }

        penUp();
        moveTo(startx, starty);
        penDown();
    }

    public void drawRectangle(int length, int height){
        setHeading(90);
        for(int i=0;i<2;i++){
            forward(length);
            turn(90);
            forward(height);
            turn(90);
        }
    }

    public static void cat(){
        ExamplesTurtle t = new ExamplesTurtle(1000/2, 1000/2, new World(1000,1000));

// Path 0
        t.penUp();
        t.moveTo(704, 601);
        t.penDown();
        t.moveTo(740, 601);
        t.moveTo(740, 343);
        t.moveTo(848, 343);
        t.moveTo(848, 313);
        t.moveTo(650, 313);
        t.moveTo(650, 343);
        t.moveTo(704, 343);
        t.moveTo(704, 601);

// Path 1
        t.penUp();
        t.moveTo(362, 607);
        t.penDown();
        t.moveTo(350, 595);
        t.moveTo(338, 607);
        t.moveTo(350, 619);
        t.moveTo(362, 607);

// Path 2
        t.penUp();
        t.moveTo(338, 319);
        t.penDown();
        t.moveTo(206, 343);
        t.moveTo(164, 427);
        t.moveTo(176, 505);
        t.moveTo(224, 577);
        t.moveTo(314, 613);
        t.moveTo(332, 589);
        t.moveTo(260, 565);
        t.moveTo(212, 511);
        t.moveTo(200, 445);
        t.moveTo(212, 397);
        t.moveTo(248, 361);
        t.moveTo(350, 343);
        t.moveTo(338, 319);

// Path 3
        t.penUp();
        t.moveTo(662, 613);
        t.penDown();
        t.moveTo(674, 601);
        t.moveTo(662, 589);
        t.moveTo(650, 601);
        t.moveTo(662, 613);

// Path 4
        t.penUp();
        t.moveTo(770, 589);
        t.penDown();
        t.moveTo(758, 601);
        t.moveTo(770, 613);
        t.moveTo(782, 601);
        t.moveTo(770, 589);

// Path 5
        t.penUp();
        t.moveTo(374, 607);
        t.penDown();
        t.moveTo(506, 295);
        t.moveTo(620, 601);
        t.moveTo(578, 601);
        t.moveTo(536, 469);
        t.moveTo(482, 469);
        t.moveTo(410, 607);
        t.moveTo(374, 607);

// Path 6
        t.penUp();
        t.moveTo(482, 439);
        t.penDown();
        t.moveTo(506, 361);
        t.moveTo(542, 445);
        t.moveTo(482, 445);
        t.moveTo(482, 439);

        t.penUp();
        t.moveTo(120,250);
        t.penDown();
        t.drawRectangle(800,400);

    }

    public static void lambda(){
        ExamplesTurtle t = new ExamplesTurtle(1000/2, 1000/2, new World(1000,1000));

        // Draw a lambda
        t.penUp();
        t.moveTo(493, 325);
        t.penDown();
        t.moveTo(423, 325);
        t.moveTo(423, 375);
        t.moveTo(463, 375);
        t.moveTo(463, 415);
        t.moveTo(343, 685);
        t.moveTo(413, 685);
        t.moveTo(493, 485);
        t.moveTo(573, 685);
        t.moveTo(673, 635);
        t.moveTo(643, 585);
        t.moveTo(603, 605);
        t.moveTo(493, 325);
        t.penUp();

        // Draw a polygon to go around it
        t.moveTo(500, 500);     //Move to center
        t.penDown();
        t.drawPolygon(12, 320);     //Draw the polygon

        t.writeDXF("lambda.dxf", true);
    }

    public static void main(String[] args){
//        lambda();
        cat();

    }
}
