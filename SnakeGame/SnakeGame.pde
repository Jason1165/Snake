Snake snake;
int ROWS;
int COLS;
float SQUARESIZE;

void setup() {
  size(500, 500);
  ROWS = 10;
  COLS = 10;
  snake = new Snake(10, 10);
  SQUARESIZE = width/COLS;
}

void draw() {
  background(0, 255, 0);
  if (frameCount % 100 == 0) {
    snake.move(snake.getDirection());
  }
  String []lines = snake.toString().split("\n");
  stringToSquare(lines);
}

void stringToSquare(String[] lines) {
  noStroke();
  for (int i = 0; i < ROWS; i++) {
    for (int j = 0; j < lines[i].length(); j++) {
      char c = lines[i].charAt(j);
      if (c == '#') {
        fill(0, 255, 0);
        rect(j*SQUARESIZE, i*SQUARESIZE, SQUARESIZE, SQUARESIZE);
      }
      if (c == 'H') {
        fill(126, 0, 0);
        rect(j*SQUARESIZE, i*SQUARESIZE, SQUARESIZE, SQUARESIZE, SQUARESIZE/4);
      }
      if (c == 'T') {
        fill(0, 0, 126);
        rect(j*SQUARESIZE, i*SQUARESIZE, SQUARESIZE, SQUARESIZE, SQUARESIZE/4);
      }
      if (c == '&') {
        fill(0, 0, 256);
        rect(j*SQUARESIZE, i*SQUARESIZE, SQUARESIZE, SQUARESIZE, SQUARESIZE/4);
      }
      if (c == '@') {
        fill(255, 0, 0);
        ellipse(j*SQUARESIZE+SQUARESIZE/2, i*SQUARESIZE+SQUARESIZE/2, SQUARESIZE, SQUARESIZE);
      }
    }
  }
}

void keyPressed() {
  if (key == 'w' || key == 'W' || key == UP) {
    snake.move("w"); 
  }
  if (key == 'a' || key == 'A' || key == LEFT) {
    snake.move("a"); 
  }
  if (key == 's' || key == 'S' || key == DOWN) {
    snake.move("s"); 
  }
  if (key == 'd' || key == 'D' || key == RIGHT) {
    snake.move("d"); 
  }
  if (key == ' ') {
    background(155);
  }
}
