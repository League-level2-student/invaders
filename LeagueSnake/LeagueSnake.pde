//*

// ***** SEGMENT CLASS *****

// This class will be used to represent each part of the moving snake.

//*



class Segment {



//Add x and y member variables. They will hold the corner location of each segment of the snake.
int x;
int y;

public Segment (int x, int y){
  this.x = x;
  this.y = y;
}




// Add a constructor with parameters to initialize each variable.







}





//*

// ***** GAME VARIABLES *****

// All the game variables that will be shared by the game methods are here

//*

Segment head;
ArrayList<Segment> tail = new ArrayList<Segment>();
int foodX;
int foodY;
int direction = UP;
int foodEaten = 0;








//*

// ***** SETUP METHODS *****

// These methods are called at the start of the game.

//*



void setup() {
size(500, 500);
head = new Segment(250, 250);
frameRate(20);
dropFood();
}



void dropFood() {

  //Set the food in a new random location
foodX = ((int)random(100)*5);
foodY = ((int)random(100)*5);
    

}







//*

// ***** DRAW METHODS *****

// These methods are used to draw the snake and its food 

//*



void draw() {
background(0);
drawFood();
eat();
move();
drawSnake();
}



void drawFood() {

  //Draw the food
fill(#FF0000);
rect(foodX, foodY,10, 10);
  

}



void drawSnake() {

  //Draw the head of the snake followed by its tail
  fill(#10FF00);
  rect(head.x, head.y, 10, 10);
  manageTail();
}





//*

// ***** TAIL MANAGEMENT METHODS *****

// These methods make sure the tail is the correct length.

//*



void drawTail() {

  //Draw each segment of the tail 
  for(int i  = 0; i < tail.size(); i++){
    rect(tail.get(i).x, tail.get(i).y, 10, 10);
  }


}



void manageTail() {

  //After drawing the tail, add a new segment at the "start" of the tail and remove the one at the "end" 

  //This produces the illusion of the snake tail moving.
  checkTailCollision();
  drawTail();
  tail.add(new Segment (head.x, head.y));
  tail.remove(0);
  

}



void checkTailCollision() {

  //If the snake crosses its own tail, shrink the tail back to one segment
  for(int i = 0; i<tail.size(); i++){
    if(tail.get(i).x == head.x && tail.get(i).y == head.y){
      foodEaten = 1;
      tail.clear();
      //tail.add(new Segment (head.x, head.y));
    }
  }

}







//*

// ***** CONTROL METHODS *****

// These methods are used to change what is happening to the snake

//*



void keyPressed() {

  //Set the direction of the snake according to the arrow keys pressed
  if(keyCode == UP){
    direction = UP;
  } else if (keyCode == DOWN) {
    direction = DOWN;
  } else if (keyCode == LEFT) {
    direction = LEFT;
  } else if (keyCode == RIGHT) {
    direction = RIGHT;
  }
  

}



void move() {

  //Change the location of the Snake head based on the direction it is moving.

  

    

  switch(direction) {

  case UP:
  
    head.y -= 5;
    // move head up here 

    break;

  case DOWN:
    head.y += 5;
    // move head down here 

    break;

  case LEFT:

   // figure it out 
    head.x -= 5;
    break;

  case RIGHT:

    // mystery code goes here 
    head.x += 5;
    break;

  }

  checkBoundaries();

}



void checkBoundaries() {

 //If the snake leaves the frame, make it reappear on the other side
if(head.x >= 500 || head.x <= 0){
      if(head.x >= 500){
        head.x -= 500;
      } else if(head.x <= 500){
        head.x += 500; 
     }
 }
 
 if(head.y >= 500 || head.y <= 0){
      if(head.y >= 500){
        head.y -= 500;
      } else if(head.y <= 500){
        head.y += 500; 
     }
 }
}







void eat() {

  //When the snake eats the food, its tail should grow and more food appear
  if(dist(head.x, head.y, foodX, foodY) <= 10){
    tail.add(new Segment (head.x, head.y));
    foodX = ((int)random(100)*5);
    foodY = ((int)random(100)*5);
  }


}
