import java.util.Scanner;

public class TicTacToe {
  
  private static char[][] board; //stores characters representing markings on the board\
  private static Scanner s;

  private static void initializeBoard() {
    s = new Scanner(System.in);
    board = new char[3][3];
    for (int x = 0; x < board.length; x++)
      for (int y = 0; y < board[x].length; y++)
        board[x][y] = ' ';
  }
  
  private static String getBoard() {
    String str = "";
    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[y].length; x++) {
        str += " " + board[x][y] + " ";
        if (x != 2) str += "|";
      }
      str += "\n";
      if (y < 2) str += "-----------\n";
    }
    return str;
  }
  
  private static int markBoard(int x, int y, char c) {
    if (x < 3 && x >= 0 && y < 3 && y >= 0 && board[x][y] == ' ') {
      board[x][y] = c;
      return 0;
    }
    return -1;
  }
  
  private static void userMove() {
    System.out.println("Enter the coordinates of your next move");
    while (markBoard(s.nextInt(),s.nextInt(),'X') == -1) {
      System.out.println("That coordinated is invalid because it is either out of bounds or it is already marked");
    }
  }
  
  private static int[] almostThree() {
   for(int x = 0; x < 3; x++) {
     int tx = 0; int lx = 0;
     int ty = 0; int ly = 0;
     for(int y = 0; y < 3; y++) {
       if (board[x][y] == 'X') tx += 2;
       if (board[x][y] == 'O') tx = -5;
       if (board[x][y] == ' ') { lx = y; tx++; }
       
       if (board[y][x] == 'X') ty += 2;
       if (board[y][x] == 'O') ty = -5;
       if (board[y][x] == ' ') { ly = y; ty++; }
     }
     if (tx == 5) {
       int[] c = {x,lx};
       return c;
     }
     if (ty == 5) {
       int[] c = {ly,x};
       return c;
     }
   }
   
   int[] c = {0};
   return c;
  }
  
  private static void computerMove() {
    int[] block = almostThree();
    if (block.length == 2) {
      if (markBoard(block[0],block[1],'O') == 0) return;
      System.out.println(block[0] + " " + block[1]);
    }

    for(int x = 0; x < 3; x++)
      for(int y = 0; y < 3; y++)
        if (markBoard(x,y,'O') == 0) return;
     
    return;
}
  
  private static char checkGamestate() {
    for (int x = 0; x < board.length; x++) { //check columns
      if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][0] != ' ')
        return board[x][0];
    }
    for (int y = 0; y < board.length; y++) { //check rows
      if (board[0][y] == board[1][y] && board[1][y] == board[2][y] && board[0][y] != ' ')
        return board[0][y];
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') //check diagonals
      return board[0][0];
    if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != ' ')
      return board[2][0];    
    
    for(int x = 0; x < 3; x++)
      for(int y = 0; y < 3; y++)
         if(board[x][y] == ' ') return ' ';

    return '-';        
}
  
  public static void main(String[] args) {

    initializeBoard();    
    
    System.out.println("Welcome to TicTacToe by Darius Barbano");
    System.out.println(" - press any character and hit enter to begin");
    s.next();
    
    System.out.println("**Coordinates begin from the top left corner");
    while (checkGamestate() == ' ') {
      System.out.print(getBoard());
      userMove();
      computerMove();
    }
    System.out.print(getBoard());
    
    if (checkGamestate() == 'X')
      System.out.println("Congratulations, you won!");
    else if (checkGamestate() == 'O')
      System.out.println("You lost, better luck next time ):");
    else
      System.out.println("The game has ended in a draw");
    
  }
}