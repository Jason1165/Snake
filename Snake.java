public class Snake {
  private int[] head;
  private int[] tail;
  private int[][] board;
  private int ticks;
  private static int GRASS = 0;
  private static int BODY = 1;
  private static int HEAD = 2;
  private static int TAIL = 3;
  private static int APPLE = 4;

  /**Initializes the SnakeField to the size specified
  *and sets the head to the middle row 4 over and the tail three before
  *@param rows is the starting height of the SnakeField
  *@param cols is the starting width of the SnakeField
  */
  public Snake(int rows, int cols) {
    this();
    if(rows > 5 && cols > 5) {
      board = new int[rows][cols];
      head = new int[2];
      tail = new int[2];
      head[1] = 4;
      tail[1] = 1;
      head[0] = rows/2;
      tail[0] = rows/2;
      ticks = 0;
      fillBoard();
    }
  }
  /**Initializes the SnakeField a 15 by 15
  *and sets the head to the middle row 4 over and the tail three before
  */
  public Snake(){
    board = new int[15][15];
    head = new int[2];
    tail = new int[2];
    head[1] = 4;
    head[0] = 15/2;
    tail[1] = 1;
    tail[0] = 15/2;
    ticks = 0;
    fillBoard();
  }

  /**Initializes the SnakeField to the size specified
  *and randomizes where the body is, not at the border.
  *@param rows is the starting height of the SnakeField
  *@param cols is the starting width of the SnakeField
  *@param rand is whether or not it will be randomized, false simply calls another constructor
  *@param orientation determines whether the snake is vertical(0) or horizontal(1);
  *@param yOrientation determines whether the head will be at the top(1) or bottom(-1);
  *invalid values will be set to a default of vertical(0) and top(1) and rows(15) and cols(15);
  */
  public Snake(int rows, int cols, boolean rand, int orientation, int yOrientation) {
    this(rows, cols);
    if (orientation != 0 && orientation != 1) {
      orientation = 0;
    }
    if (yOrientation != 1 && yOrientation != -1) {
      yOrientation = 1;
    }
    if (rand) {
      boolean temp = false;
      while (! temp) {
        head[1] = (int)(Math.random()*(board.length - 2) + 1);
        head[0] = (int)(Math.random()*(board[0].length - 2) + 1);
        int dir = (int)(Math.random()*2);
        if (orientation == 0 && head[1] + yOrientation*3 < board.length && head[1] + yOrientation*3 >= 0) {
          tail[1] = yOrientation*3 + head[1];
          tail[0] = head[0];
          temp = true;
        }
        else if (orientation == 1 && head[0] + yOrientation*3 < board.length && head[0] + yOrientation*3 >= 0) {
          tail[0] = yOrientation*3 + head[0];
          tail[1] = head[1];
          temp = true;
        }
        else {
          temp = false;
        }
      }
    }
    ticks = 0;
    fillBoard();
  }

  /**Takes in a parameter that moves the Snake
  *@param direction should be a letter wasd or up/left/down/right
  */
  public void move(String direction) {

  }

  private void fillBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = GRASS;
      }
    }
    fillBody();
    board[head[0]][head[1]] = HEAD;
    board[tail[0]][tail[1]] = TAIL;
  }

  /*
    Method made like this for future proofing when positions can be randomized
  */
  private void fillBody() {
    int xH = head[0];
    int xT = tail[0];
    int yH = head[1];
    int yT = tail[1];
    if (xH == xT) {
      if (yT > yH) {
        for (int i = yH + 1; i < yT; i++) {
          board[xH][i] = BODY;
        }
      }
      else {
        for (int i = yT; i < yH; i++) {
          board[xH][i] = BODY;
        }
      }
    }
    else if (yH == yT) {
      if (xT > xH) {
        for (int i = xH + 1; i < xT; i++) {
          board[i][yH] = BODY;
        }
      }
      else {
        for (int i = xT + 1; i < xH; i++) {
          board[i][yH] = BODY;
        }
      }
    }
  }

  /**Each row is a new line. # is GRASS, H is HEAD, T is TAIL, & is Body, @ is APPLE
  *@return a String seperated by newlines.
  */
  public String toString(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int c = 0; c < board[i].length; c++) {
        if(board[i][c]==GRASS)
          builder.append("#");
        else if(board[i][c]==HEAD)
          builder.append("H");
        else if(board[i][c]==TAIL)
          builder.append("T");
        else if(board[i][c]==BODY)
          builder.append("&");
        else if(board[i][c]==APPLE)
          builder.append("@");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
