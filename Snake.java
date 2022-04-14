public class Snake {
  int[] head;
  int[] tail;
  char[][] board;

  public Snake(int x, int y) {
    this();
    if(x > 5 && y > 5) {
      board = new char[x][y];
      head = new int[2];
      tail = new int[2];
      head[1] = 4;
      tail[1] = 1;
      head[0] = x/2 + 1;
      tail[0] = x/2 + 1;
    }
  }

  public Snake(){
    board = new char[15][15];
    head = new int[2];
    tail = new int[2];
    head[1] = 4;
    head[0] = 15/2 + 1;
    tail[1] = 1;
    tail[0] = 15/2 + 1;
  }
}
