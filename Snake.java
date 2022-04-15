import java.util.*;

public class Snake {
  private int[] head;
  private int[] tail;
  private int[][] map;
  private int ticks;
  private boolean alive;
  private int size;
  private ArrayDeque<int[]> body;
  private static int GRASS = 0;
  private static int BODY = 1;
  private static int HEAD = 2;
  private static int TAIL = 3;
  private static int APPLE = 4;

  /**Initializes the map to the size specified
  *and sets the head to the middle row 4 over and the tail three before
  *@param rows is the starting height of the map
  *@param cols is the starting width of the map
  */
  public Snake(int rows, int cols) {
    this();
    body = new ArrayDeque<int[]>();
    if(rows > 5 && cols > 5) {
      map = new int[rows][cols];
      head = new int[2];
      tail = new int[2];
      alive = true;
      head[1] = 4;
      tail[1] = 1;
      head[0] = rows/2;
      tail[0] = rows/2;
      ticks = 0;
      size = 4;
      fillBoard();
      generateApple();
      body.addFirst(head);
      body.addLast(tail);
    }
  }

  /**Initializes the map with a 15 by 15
  *and sets the head to the middle row 4 over and the tail three before
  */
  public Snake(){
    body = new ArrayDeque<int[]>();
    map = new int[15][15];
    head = new int[2];
    tail = new int[2];
    alive = true;
    head[1] = 4;
    head[0] = 15/2;
    tail[1] = 1;
    tail[0] = 15/2;
    ticks = 0;
    size = 4;
    fillBoard();
    generateApple();
    body.addFirst(head);
    body.addLast(tail);
  }

  /**Initializes the map to the size specified
  *and randomizes where the body is, not at the border.
  *@param rows is the starting height of the map
  *@param cols is the starting width of the map
  *@param rand is whether or not it will be randomized, false simply calls another constructor
  *@param orientation determines whether the snake is vertical(0) or horizontal(1);
  *@param yOrientation determines whether the head will be at the top(1) or bottom(-1);
  *invalid values will be set to a default of vertical(0) and top(1) and rows(15) and cols(15);
  */
  public Snake(int rows, int cols, boolean rand, int orientation, int yOrientation) {
    this(rows, cols);
    body = new ArrayDeque<int[]>();
    if (orientation != 0 && orientation != 1) {
      orientation = 0;
    }
    if (yOrientation != 1 && yOrientation != -1) {
      yOrientation = 1;
    }
    if (rand) {
      boolean temp = false;
      while (! temp) {
        head[1] = (int)(Math.random()*(map.length - 2) + 1);
        head[0] = (int)(Math.random()*(map[0].length - 2) + 1);
        int dir = (int)(Math.random()*2);
        if (orientation == 0 && head[1] + yOrientation*3 < map.length && head[1] + yOrientation*3 >= 0) {
          tail[1] = yOrientation*3 + head[1];
          tail[0] = head[0];
          temp = true;
        }
        else if (orientation == 1 && head[0] + yOrientation*3 < map.length && head[0] + yOrientation*3 >= 0) {
          tail[0] = yOrientation*3 + head[0];
          tail[1] = head[1];
          temp = true;
        }
        else {
          temp = false;
        }
      }
    }
    size = 4;
    alive = true;
    ticks = 0;
    fillBoard();
    generateApple();
    body.addFirst(head);
    body.addLast(tail);
  }

  /**Generates a random apple somewhere on the map
  *@return true when apple is placed and false when it is not or when snake is too big
  */
  public boolean generateApple() {
    int maxreps = (int)((double)(map.length * map[0].length)*1.5);
    int counter = 0;
    while (maxreps > counter) {
      counter++;
      int x = (int)(Math.random()*map.length);
      int y = (int)(Math.random()*map[0].length);
      if (map[x][y] == GRASS) {
        map[x][y] = APPLE;
        return true;
      }
    }
    return false;
  }

  /**Takes in a parameter that moves the Snake
  *@param direction should be a letter wasd or up/left/down/right
  */
  public void move(String direction) {
    if (alive) {
      ticks++;
      if (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("w")) {
        if (head[1] - 1 < 0) {
          alive = false;
        }
        else {
          map[head[0]][head[1]] = BODY;
          head[1] -= 1;
          body.addFirst(head);
          map[head[0]][head[1]] = HEAD;
          map[tail[0]][tail[1]] = GRASS;
          int tempT[] = body.removeLast();
          tail = body.getLast();
          if (map[head[0]][head[1]] == BODY) {
            alive = false;
          }
          else if (map[head[0]][head[1]] == APPLE) {
            body.addLast(tempT);
            map[tempT[0]][tempT[1]] = TAIL;
            generateApple();
          }
          else {
            map[tail[0]][tail[1]] = TAIL;
          }
        }
      }
      else if (direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("a")) {
        if (head[0] - 1 < 0) {
          alive = false;
        }
        else {
          map[head[0]][head[1]] = BODY;
          head[0] -= 1;
          body.addFirst(head);
          map[head[0]][head[1]] = HEAD;
          map[tail[0]][tail[1]] = GRASS;
          int tempT[] = body.removeLast();
          tail = body.getLast();
          if (map[head[0]][head[1]] == BODY) {
            alive = false;
          }
          else if (map[head[0]][head[1]] == APPLE) {
            body.addLast(tempT);
            map[tempT[0]][tempT[1]] = TAIL;
            generateApple();
          }
          else {
            map[tail[0]][tail[1]] = TAIL;
          }
        }

      }
      else if (direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("s")) {
        if (head[0] + 1 >= map.length) {
          alive = false;
        }
        else {
          map[head[0]][head[1]] = BODY;
          head[0] += 1;
          body.addFirst(head);
          map[head[0]][head[1]] = HEAD;
          map[tail[0]][tail[1]] = GRASS;
          int tempT[] = body.removeLast();
          tail = body.getLast();
          if (map[head[0]][head[1]] == BODY) {
            alive = false;
          }
          else if (map[head[0]][head[1]] == APPLE) {
            body.addLast(tempT);
            map[tempT[0]][tempT[1]] = TAIL;
            generateApple();
          }
          else {
            map[tail[0]][tail[1]] = TAIL;
          }
        }
      }
      else if (direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("d")) {
        if (head[1] + 1 < map[0].length) {
          alive = false;
        }
        else {
          map[head[0]][head[1]] = BODY;
          head[1] += 1;
          body.addFirst(head);
          map[head[0]][head[1]] = HEAD;
          map[tail[0]][tail[1]] = GRASS;
          int tempT[] = body.removeLast();
          tail = body.getLast();
          if (map[head[0]][head[1]] == BODY) {
            alive = false;
          }
          else if (map[head[0]][head[1]] == APPLE) {
            body.addLast(tempT);
            map[tempT[0]][tempT[1]] = TAIL;
            generateApple();
          }
          else {
            map[tail[0]][tail[1]] = TAIL;
          }
        }
      }
    }
    if (!alive) {
      System.out.println("The snake has died!");
    }
  }

  /**
  *@return number of ticks or times drawn
  */
  public int getTicks() {
    return ticks;
  }

  /**
  *@return size of snake
  */
  public int size() {
    return size;
  }

  private void fillBoard() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        map[i][j] = GRASS;
      }
    }
    fillBody();
    map[head[0]][head[1]] = HEAD;
    map[tail[0]][tail[1]] = TAIL;
  }

  /*
    Method made like this for future proofing when positions can be randomized
  */
  private void fillBody() {
    int xH = head[0];
    int xT = tail[0];
    int yH = head[1];
    int yT = tail[1];
    int bodyPart[] = new int[2];
    if (xH == xT) {
      if (yT > yH) {
        for (int i = yH + 1; i < yT; i++) {
          map[xH][i] = BODY;
          bodyPart[0] = xH; bodyPart[1] = i;
          body.addLast(bodyPart);
        }
      }
      else {
        for (int i = yT + 1; i < yH; i++) {
          map[xH][i] = BODY;
          bodyPart[0] = xH; bodyPart[1] = i;
          body.addFirst(bodyPart);
        }
      }
    }
    else if (yH == yT) {
      if (xT > xH) {
        for (int i = xH + 1; i < xT; i++) {
          map[i][yH] = BODY;
          bodyPart[0] = i; bodyPart[1] = yH;
          body.addLast(bodyPart);
        }
      }
      else {
        for (int i = xT + 1; i < xH; i++) {
          map[i][yH] = BODY;
          bodyPart[0] = i; bodyPart[1] = yH;
          body.addFirst(bodyPart);
        }
      }
    }
  }

  /**Each row is a new line. # is GRASS, H is HEAD, T is TAIL, & is Body, @ is APPLE
  *@return a String seperated by newlines.
  */
  public String toString(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int c = 0; c < map[i].length; c++) {
        if(map[i][c]==GRASS)
          builder.append("#");
        else if(map[i][c]==HEAD)
          builder.append("H");
        else if(map[i][c]==TAIL)
          builder.append("T");
        else if(map[i][c]==BODY)
          builder.append("&");
        else if(map[i][c]==APPLE)
          builder.append("@");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}
