package laserTurtle;

import java.awt.*;
import java.awt.geom.*;
import java.io.FileNotFoundException;

/**
 * Class that represents a Logo-style turtle.  The turtle
 * starts off facing north.
 * A turtle can have a name, has a starting x and y position,
 * has a heading, has a width, has a height, has a visible
 * flag, has a body color, can have a shell color, and has a pen.
 * The turtle will not go beyond the model display or picture
 * boundaries.
 * <p/>
 * You can display this turtle in either a picture or in
 * a class that implements ModelDisplay.
 * <p/>
 * Copyright Georgia Institute of Technology 2004
 *
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class SimpleTurtle {
    ///////////////// fields ////////////////////////

    /**
     * count of the number of turtles created
     */
    private static int numTurtles = 0;

    /**
     * array of colors to use for the turtles
     */
    private static Color[] colorArray = {Color.green, Color.cyan, new Color(204, 0, 204), Color.gray};

    /**
     * who to notify about changes to this turtle
     */
    private ModelDisplay modelDisplay = null;

    /**
     * picture to draw this turtle on
     */
    private Picture picture = null;

    /**
     * width of turtle in pixels
     */
    private int width = 15;

    /**
     * height of turtle in pixels
     */
    private int height = 18;

    /**
     * current location in x (center)
     */
    private int xPos = 0;

    /**
     * current location in y (center)
     */
    private int yPos = 0;

    /**
     * heading angle
     */
    private double heading = 0;  // default is facing north

    /**
     * pen to use for this turtle
     */
    private Pen pen = new Pen();

    /**
     * color to draw the body in
     */
    private Color bodyColor = null;

    /**
     * color to draw the shell in
     */
    private Color shellColor = null;

    /**
     * color of information string
     */
    private Color infoColor = Color.black;

    /**
     * flag to say if this turtle is visible
     */
    private boolean visible = true;

    /**
     * flag to say if should show turtle info
     */
    private boolean showInfo = false;

    /**
     * the name of this turtle
     */
    private String name = "No name";

    /**
     * default delay
     **/
    private int delay = 0;

    ////////////////// constructors ///////////////////

    /**
     * Constructor that takes the x and y position for the
     * turtle
     *
     * @param x the x pos
     * @param y the y pos
     */
    public SimpleTurtle(int x, int y) {
        xPos = x;
        yPos = y;
        bodyColor = colorArray[numTurtles % colorArray.length];
        setPenColor(bodyColor);
        numTurtles++;
    }

    /**
     * Constructor that takes the x and y position and the
     * model displayer
     *
     * @param x       the x pos
     * @param y       the y pos
     * @param display the model display
     */
    public SimpleTurtle(int x, int y, ModelDisplay display) {
        this(x, y); // invoke constructor that takes x and y
        modelDisplay = display;
        display.addModel(this);
    }

    /**
     * Constructor that takes a model display and adds
     * a turtle in the middle of it
     *
     * @param display the model display
     */
    public SimpleTurtle(ModelDisplay display) {
        // invoke constructor that takes x and y
        this((int) (display.getWidth() / 2),
                (int) (display.getHeight() / 2));
        modelDisplay = display;
        display.addModel(this);
    }

    public SimpleTurtle(ModelDisplay display, int delay) {
        // invoke constructor that takes x and y
        this((int) (display.getWidth() / 2),
                (int) (display.getHeight() / 2));
        modelDisplay = display;
        this.delay = delay;
        display.addModel(this);
    }

    /**
     * Constructor that takes the x and y position and the
     * picture to draw on
     *
     * @param x       the x pos
     * @param y       the y pos
     * @param picture the picture to draw on
     */
    public SimpleTurtle(int x, int y, Picture picture) {
        this(x, y); // invoke constructor that takes x and y
        this.picture = picture;
        this.visible = false; // default is not to see the turtle
    }

    /**
     * Constructor that takes the
     * picture to draw on and will appear in the middle
     *
     * @param picture the picture to draw on
     */
    public SimpleTurtle(Picture picture) {
        // invoke constructor that takes x and y
        this((int) (picture.getWidth() / 2),
                (int) (picture.getHeight() / 2));
        this.picture = picture;
        this.visible = false; // default is not to see the turtle
    }

    //////////////////// methods /////////////////////////

    /**
     * Get the distance from the passed x and y location
     *
     * @param x the x location
     * @param y the y location
     */
    public double getDistance(int x, int y) {
        int xDiff = x - xPos;
        int yDiff = y - yPos;
        return (Math.sqrt((xDiff * xDiff) + (yDiff * yDiff)));
    }

    /**
     * Method to turn to face another simple turtle
     */
    public void turnToFace(SimpleTurtle turtle) {
        turnToFace(turtle.xPos, turtle.yPos);
    }

    /**
     * Method to turn towards the given x and y
     *
     * @param x the x to turn towards
     * @param y the y to turn towards
     */
    public void turnToFace(int x, int y) {
        double dx = x - this.xPos;
        double dy = y - this.yPos;
        double arcTan = 0.0;
        double angle = 0.0;

        // avoid a divide by 0
        if (dx == 0) {
            // if below the current turtle
            if (dy > 0)
                heading = 180;

                // if above the current turtle
            else if (dy < 0)
                heading = 0;
        }
        // dx isn't 0 so can divide by it
        else {
            arcTan = Math.toDegrees(Math.atan(dy / dx));
            if (dx < 0)
                heading = arcTan - 90;
            else
                heading = arcTan + 90;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }

        // notify the display that we need to repaint
        updateDisplay();
    }

    /**
     * Method to get the picture for this simple turtle
     *
     * @return the picture for this turtle (may be null)
     */
    public Picture getPicture() {
        return this.picture;
    }

    /**
     * Method to set the picture for this simple turtle
     *
     * @param pict the picture to use
     */
    public void setPicture(Picture pict) {
        this.picture = pict;
    }

    /**
     * Method to get the model display for this simple turtle
     *
     * @return the model display if there is one else null
     */
    public ModelDisplay getModelDisplay() {
        return this.modelDisplay;
    }

    /**
     * Method to set the model display for this simple turtle
     *
     * @param theModelDisplay the model display to use
     */
    public void setModelDisplay(ModelDisplay theModelDisplay) {
        this.modelDisplay = theModelDisplay;
    }

    /**
     * Method to get value of show info
     *
     * @return true if should show info, else false
     */
    public boolean getShowInfo() {
        return this.showInfo;
    }

    /**
     * Method to show the turtle information string
     *
     * @param value the value to set showInfo to
     */
    public void setShowInfo(boolean value) {
        this.showInfo = value;
    }

    /**
     * Method to get the shell color
     *
     * @return the shell color
     */
    public Color getShellColor() {
        Color color = null;
        if (this.shellColor == null && this.bodyColor != null)
            color = bodyColor.darker();
        else color = this.shellColor;
        return color;
    }

    /**
     * Method to set the shell color
     *
     * @param color the color to use
     */
    public void setShellColor(Color color) {
        this.shellColor = color;
    }

    /**
     * Method to get the body color
     *
     * @return the body color
     */
    public Color getBodyColor() {
        return this.bodyColor;
    }

    /**
     * Method to set the body color which
     * will also set the pen color
     *
     * @param color the color to use
     */
    public void setBodyColor(Color color) {
        this.bodyColor = color;
        setPenColor(this.bodyColor);
    }

    /**
     * Method to set the color of the turtle.
     * This will set the body color
     *
     * @param color the color to use
     */
    public void setColor(Color color) {
        this.setBodyColor(color);
    }

    /**
     * Method to get the information color
     *
     * @return the color of the information string
     */
    public Color getInfoColor() {
        return this.infoColor;
    }

    /**
     * Method to set the information color
     *
     * @param color the new color to use
     */
    public void setInfoColor(Color color) {
        this.infoColor = color;
    }

    /**
     * Method to return the width of this object
     *
     * @return the width in pixels
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Method to return the height of this object
     *
     * @return the height in pixels
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Method to set the width of this object
     *
     * @param theWidth in width in pixels
     */
    public void setWidth(int theWidth) {
        this.width = theWidth;
    }

    /**
     * Method to set the height of this object
     *
     * @param theHeight the height in pixels
     */
    public void setHeight(int theHeight) {
        this.height = theHeight;
    }

    /**
     * Method to get the current x position
     *
     * @return the x position (in pixels)
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Method to get the current y position
     *
     * @return the y position (in pixels)
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * Method to get the pen
     *
     * @return the pen
     */
    public Pen getPen() {
        return this.pen;
    }

    /**
     * Method to set the pen
     *
     * @param thePen the new pen to use
     */
    public void setPen(Pen thePen) {
        this.pen = thePen;
    }

    public void setDelay(int delay){
        this.delay = delay;
    }

    /**
     * Method to check if the pen is down
     *
     * @return true if down else false
     */
    public boolean isPenDown() {
        return this.pen.isPenDown();
    }

    /**
     * Method to set the pen down boolean variable
     *
     * @param value the value to set it to
     */
    public void setPenDown(boolean value) {
        this.pen.setPenDown(value);
    }

    /**
     * Method to lift the pen up
     */
    public void penUp() {
        this.pen.setPenDown(false);
    }

    /**
     * Method to set the pen down
     */
    public void penDown() {
        this.pen.setPenDown(true);
    }

    /**
     * Method to get the pen color
     *
     * @return the pen color
     */
    public Color getPenColor() {
        return this.pen.getColor();
    }

    /**
     * Method to set the pen color
     *
     * @param color the color for the pen ink
     */
    public void setPenColor(Color color) {
        this.pen.setColor(color);
    }

    /**
     * Method to set the pen width
     *
     * @param width the width to use in pixels
     */
    public void setPenWidth(int width) {
        this.pen.setWidth(width);
    }

    /**
     * Method to get the pen width
     *
     * @return the width of the pen in pixels
     */
    public int getPenWidth() {
        return this.pen.getWidth();
    }

    /**
     * Method to clear the path (history of
     * where the turtle has been)
     */
    public void clearPath() {
        this.pen.clearPath();
    }

    /**
     * Method to get the current heading
     *
     * @return the heading in degrees
     */
    public double getHeading() {
        return this.heading;
    }

    /**
     * Method to set the heading
     *
     * @param heading the new heading to use
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * Method to get the name of the turtle
     *
     * @return the name of this turtle
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set the name of the turtle
     *
     * @param theName the new name to use
     */
    public void setName(String theName) {
        this.name = theName;
    }

    /**
     * Method to get the value of the visible flag
     *
     * @return true if visible else false
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Method to hide the turtle (stop showing it)
     * This doesn't affect the pen status
     */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * Method to show the turtle (doesn't affect
     * the pen status
     */
    public void show() {
        this.setVisible(true);
    }

    /**
     * Method to set the visible flag
     *
     * @param value the value to set it to
     */
    public void setVisible(boolean value) {
        // if the turtle wasn't visible and now is
        if (visible == false && value == true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }

            // update the display
            this.updateDisplay();
        }

        // set the visibile flag to the passed value
        this.visible = value;
    }

    /**
     * Method to update the display of this turtle and
     * also check that the turtle is in the bounds
     */
    public synchronized void updateDisplay() {
        // check that x and y are at least 0
        if (xPos < 0)
            xPos = 0;
        if (yPos < 0)
            yPos = 0;

        // if picture
        if (picture != null) {
            if (xPos >= picture.getWidth())
                xPos = picture.getWidth() - 1;
            if (yPos >= picture.getHeight())
                yPos = picture.getHeight() - 1;
            Graphics g = picture.getGraphics();
            paintComponent(g);
        } else if (modelDisplay != null) {
            if (xPos >= modelDisplay.getWidth())
                xPos = modelDisplay.getWidth() - 1;
            if (yPos >= modelDisplay.getHeight())
                yPos = modelDisplay.getHeight() - 1;
            modelDisplay.modelChanged();
        }
    }

    /**
     * Method to move the turtle forward 100 pixels
     */
    public void forward() {
        forward(100);
    }

    /**
     * Method to move the turtle forward the given number of pixels
     *
     * @param pixels the number of pixels to walk forward in the heading direction
     */
    public void forward(int pixels) {
        int oldX = xPos;
        int oldY = yPos;

        // change the current position
        xPos = oldX + (int) (pixels * Math.sin(Math.toRadians(heading)));
        yPos = oldY + (int) (pixels * -Math.cos(Math.toRadians(heading)));

        // add a move from the old position to the new position to the pen
        pen.addMove(oldX, oldY, xPos, yPos);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }

        // update the display to show the new line
        updateDisplay();
    }

    /**
     * Method to go backward by 100 pixels
     */
    public void backward() {
        backward(100);
    }

    /**
     * Method to go backward a given number of pixels
     *
     * @param pixels the number of pixels to walk backward
     */
    public void backward(int pixels) {
        forward(-pixels);
    }

    /**
     * Method to move to turtle to the given x and y location
     *
     * @param x the x value to move to
     * @param y the y value to move to
     */
    public void moveTo(int x, int y) {
        this.pen.addMove(xPos, yPos, x, y);
        this.xPos = x;
        this.yPos = y;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }

        this.updateDisplay();
    }

    /**
     * Method to turn left
     */
    public void turnLeft() {
        this.turn(-90);
    }

    /**
     * Method to turn right
     */
    public void turnRight() {
        this.turn(90);
    }

    /**
     * Method to turn the turtle the passed degrees
     * use negative to turn left and pos to turn right
     *
     * @param degrees the amount to turn in degrees
     */
    public void turn(double degrees) {
        this.heading = (heading + degrees) % 360;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }

        this.updateDisplay();
    }

    /**
     * Method to draw a passed picture at the current turtle
     * location and rotation in a picture or model display
     *
     * @param dropPicture the picture to drop
     */
    public synchronized void drop(Picture dropPicture) {
        Graphics2D g2 = null;

        // only do this if drawing on a picture
        if (picture != null)
            g2 = (Graphics2D) picture.getGraphics();
        else if (modelDisplay != null)
            g2 = (Graphics2D) modelDisplay.getGraphics();

        // if g2 isn't null
        if (g2 != null) {

            // save the current tranform
            AffineTransform oldTransform = g2.getTransform();

            // rotate to turtle heading and translate to xPos and yPos
            g2.rotate(Math.toRadians(heading), xPos, yPos);

            // draw the passed picture
            g2.drawImage(dropPicture.getImage(), xPos, yPos, null);

            // reset the tranformation matrix
            g2.setTransform(oldTransform);

            //  draw the pen
            pen.paintComponent(g2);
        }
    }

    /**
     * Method to paint the turtle
     *
     * @param g the graphics context to paint on
     */
    public synchronized void paintComponent(Graphics g) {
        // cast to 2d object
        Graphics2D g2 = (Graphics2D) g;

        // if the turtle is visible
        if (visible) {
            // save the current tranform
            AffineTransform oldTransform = g2.getTransform();

            // rotate the turtle and translate to xPos and yPos
            g2.rotate(Math.toRadians(heading), xPos, yPos);

            // determine the half width and height of the shell
            int halfWidth = (int) (width / 2); // of shell
            int halfHeight = (int) (height / 2); // of shell
            int quarterWidth = (int) (width / 4); // of shell
            int thirdHeight = (int) (height / 3); // of shell
            int thirdWidth = (int) (width / 3); // of shell

            // draw the body parts (head)
            g2.setColor(bodyColor);
            g2.fillOval(xPos - quarterWidth,
                    yPos - halfHeight - (int) (height / 3),
                    halfWidth, thirdHeight);
            g2.fillOval(xPos - (2 * thirdWidth),
                    yPos - thirdHeight,
                    thirdWidth, thirdHeight);
            g2.fillOval(xPos - (int) (1.6 * thirdWidth),
                    yPos + thirdHeight,
                    thirdWidth, thirdHeight);
            g2.fillOval(xPos + (int) (1.3 * thirdWidth),
                    yPos - thirdHeight,
                    thirdWidth, thirdHeight);
            g2.fillOval(xPos + (int) (0.9 * thirdWidth),
                    yPos + thirdHeight,
                    thirdWidth, thirdHeight);


            // draw the shell
            g2.setColor(getShellColor());
            g2.fillOval(xPos - halfWidth,
                    yPos - halfHeight, width, height);

            // draw the info string if the flag is true
            if (showInfo)
                drawInfoString(g2);

            // reset the tranformation matrix
            g2.setTransform(oldTransform);
        }

        //  draw the pen
        pen.paintComponent(g);
    }

    /**
     * Method to draw the information string
     *
     * @param g the graphics context
     */
    public synchronized void drawInfoString(Graphics g) {
        g.setColor(infoColor);
        g.drawString(this.toString(), xPos + (int) (width / 2), yPos);
    }

    /**
     * Method to return a string with informaiton
     * about this turtle
     *
     * @return a string with information about this object
     */
    public String toString() {
        return this.name + " turtle at " + this.xPos + ", " +
                this.yPos + " heading " + this.heading + ".";
    }

    /**
     *
     * @param filename Filename of DXF to write
     * @param VIPmode I know what I am doing and I can break the rules
     */
    public void writeDXF(String filename, boolean VIPmode){
        //Set the scaling such that 1 pixel is 0.25mm
        writeDXF(filename, 0.25, VIPmode);
    }

    public void writeDXF(String filename){
        //Set the scaling such that 1 pixel is 0.25mm
        writeDXF(filename, 0.25, false);
    }

    /**
     * Write out a DXF file containing all the line segments we've drawn
     *
     * @param filename dxf file to write out
     * @param scaling How much to scale the coordinates
     */
    public void writeDXF(String filename, double scaling, boolean VIPmode){

        //Find the bounding box of the artwork
        double xmin, xmax, ymin, ymax;
        xmin = 1.0e99;
        ymin = 1.0e99;
        xmax = -1.0e99;
        ymax = -1.0e99;
        for(PathSegment p : pen.getSegments()){
            //Man this was so much easier with numpy
            xmin = Math.min(xmin, p.getLine().getX1() * scaling);
            xmin = Math.min(xmin, p.getLine().getX2() * scaling);
            xmax = Math.max(xmax, p.getLine().getX1() * scaling);
            xmax = Math.max(xmax, p.getLine().getX2() * scaling);
            ymin = Math.min(ymin, p.getLine().getY1() * scaling);
            ymin = Math.min(ymin, p.getLine().getY2() * scaling);
            ymax = Math.max(ymax, p.getLine().getY1() * scaling);
            ymax = Math.max(ymax, p.getLine().getY2() * scaling);
        }

        double max_width = 150;
        double max_height = 100;
        double width = xmax - xmin;
        double height = ymax - ymin;
        boolean bad = false;
        if(width > max_width) {
            System.out.println(String.format("Your drawing has width %.2fmm, but the maximum allowed is %.0fmm", width, max_width));
            bad = true;
        }
        if(height > max_height) {
            System.out.println(String.format("Your drawing has height %.2fmm, but the maximum allowed is %.0fmm", height, max_height));
            bad=true;
        }

        if(bad && !VIPmode)
            return;
        else
            System.out.println(String.format("Your drawing is %.2fx%.2fmm",width,height));

        try {
            DXFfile file = new DXFfile(filename);
            double mheight = getModelDisplay().getHeight();
            for (PathSegment p : pen.getSegments()) {
                //Invert y coordinates
//                p.getLine().y1 = getModelDisplay().getHeight() - p.getLine().y1;
//                p.getLine().y2 = getModelDisplay().getHeight() - p.getLine().y2;
                file.addLineSegment(
                        p.getLine().getX1() * scaling,
                        (mheight - p.getLine().getY1()) * scaling,
                        p.getLine().getX2() * scaling,
                        (mheight - p.getLine().getY2()) * scaling);
            }
            file.close();
        }catch(FileNotFoundException e){
            //not sure why this would get thrown...we're writing new files
        }
    }

} // end of class

