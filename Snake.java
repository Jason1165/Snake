public class Snake {
  private int[] head;
  private int[] tail;
  private int[][] board;
  private static int GRASS = 0;
  private static int BODY = 1;
  private static int HEAD = 2;
  private static int TAIL = 3;

  public Snake(int x, int y) {
    this();
    if(x > 5 && y > 5) {
      board = new int[x][y];
      head = new int[2];
      tail = new int[2];
      head[1] = 4;
      tail[1] = 1;
      head[0] = x/2;
      tail[0] = x/2;
      fillBoard();
    }
  }

  public Snake(){
    board = new int[15][15];
    head = new int[2];
    tail = new int[2];
    head[1] = 4;
    head[0] = 15/2;
    tail[1] = 1;
    tail[0] = 15/2;
    fillBoard();
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

  public String toString(){
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int c = 0; c < board[i].length; c++) {
        if(board[i][c]==GRASS)
          builder.append("@");
        else if(board[i][c]==HEAD)
          builder.append("H");
        else if(board[i][c]==TAIL)
          builder.append("T");
        else if(board[i][c]==BODY)
          builder.append("#");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    Snake snake = new Snake(6, 6);
    System.out.println(snake);
  }
}
