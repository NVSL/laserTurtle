package turtleClasses;

import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;

/**
 * Class that represents a turtle which is similar to a Logo turtle.
 * This class inherits from SimpleTurtle and is for students
 * to add methods to.
 * <p/>
 * Copyright Georgia Institute of Technology 2004
 *
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class Turtle extends SimpleTurtle {
    ////////////////// constructors ///////////////////////

    /**
     * Constructor that takes the x and y and a picture to
     * draw on
     *
     * @param x       the starting x position
     * @param y       the starting y position
     * @param picture the picture to draw on
     */
    public Turtle(int x, int y, Picture picture) {
        // let the parent constructor handle it
        super(x, y, picture);
    }

    /**
     * Constructor that takes the x and y and a model
     * display to draw it on
     *
     * @param x              the starting x position
     * @param y              the starting y position
     * @param modelDisplayer the thing that displays the model
     */
    public Turtle(int x, int y,
                  ModelDisplay modelDisplayer) {
        // let the parent constructor handle it
        super(x, y, modelDisplayer);
    }

    /**
     * Constructor that takes the model display
     *
     * @param modelDisplay the thing that displays the model
     */
    public Turtle(ModelDisplay modelDisplay) {
        // let the parent constructor handle it
        super(modelDisplay);
    }

    public Turtle(ModelDisplay modelDisplay, int delay) {
        // let the parent constructor handle it
        super(modelDisplay, delay);
    }

    /**
     * Constructor that takes a picture to draw on
     *
     * @param p the picture to draw on
     */
    public Turtle(Picture p) {
        // let the parent constructor handle it
        super(p);
    }

    /////////////////// methods ///////////////////////
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


    public static void spikey(int sides){
        Turtle t = new Turtle(960/2,150,new World(960,720));
        t.setDelay(5);
        t.setHeading(90);
        for(int i=0;i<sides;i++){
            //t.forward(100);
            t.triangle(120,120,2);
            t.turn(360.0/sides);
        }
        t.writeDXF("snowflake.dxf");
    }


    public static void main(String[] args) {
        //TODO: 96dpi (0.25mm), 4x6 inch max, example code
//        World earth = new World();
//        Turtle t1 = new Turtle(50,50,earth);
//        t1.moveTo(100,100);
//        t1.moveTo(200,0);
//        t1.moveTo(50,50);
//        t1.writeDXF("test.dxf");

        spikey(8);
    }

} // this } is the end of class Turtle, put all new methods before this
