import laserTurtle.Turtle;
import laserTurtle.World;

public class Claw {
  public static void main(String args[]) {
    
    // Vars
    int turnL = 45;
    int distance = 7;
    // Objects
    World canvas = new World();
    Turtle turtle = new Turtle(canvas);
    //turtle.setVisible(false);
    
    // Logic
    
    // Move to start location
    turtle.penUp();
    turtle.forward(50);
    turtle.turnLeft();
    turtle.forward(100);
    turtle.turnRight();
    
    // Draw Handle
    
    turtle.penDown();
    turtle.backward(200);
    turtle.turnRight();
    turtle.forward(50);
    turtle.turnLeft();
    turtle.forward(200);

    // Draw claw
    for( int i = 0; i < 3; i ++ ) {
      drawClaw(turtle, turnL, distance);
      faceNorth(turtle);
      turtle.turnLeft();
      if( i != 2 )
        turtle.forward(7);
      faceNorth(turtle);
    }
  }
  
  public static void faceNorth( Turtle t ) {
    int x = t.getXPos();
    t.turnToFace(x, 0);
  }
  public static void drawClaw( Turtle turtle, int turnL, int distance) {
    turtle.turn(60);
    for( int i = 0; i < turnL; i++ ) {
      turtle.turn(-3);
      turtle.forward(i%distance);
    }
    turtle.forward(5);
    turtle.turn(180);
    for( int i = 0; i < turnL; i++ ) {
      turtle.turn(3);
      turtle.forward(i%distance);
    }
  }
}


