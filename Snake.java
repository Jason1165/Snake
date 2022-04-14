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
      head[0] = x/2 + 1;
      tail[0] = x/2 + 1;
      fillBoard();
    }
  }

  public Snake(){
    board = new int[15][15];
    head = new int[2];
    tail = new int[2];
    head[1] = 4;
    head[0] = 15/2 + 1;
    tail[1] = 1;
    tail[0] = 15/2 + 1;
    fillBoard();
  }

  private void fillBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = GRASS;
      }
    }
    board[head[0]][head[1]] = HEAD;
    board[tail[0]][tail[1]] = TAIL;
  }

  private void fillBody() {
    int xH = head[0];
    int xT = tail[0];
    int yH = head[1];
    int yT = head[1];
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
    Snake snake = new Snake();
    System.out.println(snake);
  }
}
