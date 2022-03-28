import java.util.ArrayList;

public class GameBoard {
  private CheckerPiece[][] gameBoard;
  
  public GameBoard(){
    gameBoard = new CheckerPiece[8][8]; //Initialize Gameboard, Sets as 8x8 Board
    for (int i = 0; i < 8; i++){
      if(i%2 == 1){ //Odd Detector
        gameBoard[0][i] = new CheckerPiece("w"); //Every other Piece on Row 0 Will be White
      }
      else
      {
        gameBoard[0][i] = null; //Even Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){
      if(i%2 == 0){ //Even Detector
        gameBoard[1][i] = new CheckerPiece("w"); //Every other Piece on Row 1 Will be White
      }
      else
      {
        gameBoard[1][i] = null; //Odd Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){
      if(i%2 == 1){//Odd Detector
        gameBoard[2][i] = new CheckerPiece("w"); //Every other Piece on Row 2 Will be White
      }
      else
      {
        gameBoard[2][i] = null; //Even pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){
      gameBoard[3][i] = null; //All Pieces in Row are Null
    }
    for (int i = 0; i < 8; i++){
      gameBoard[4][i] = null; //All Pieces in Row are Null
    }
    for (int i = 0; i < 8; i++){
      if(i%2 == 0){ //Even Detector
        gameBoard[5][i] = new CheckerPiece("b"); //Every other Piece on Row 5 Will be Black
      }
      else{
        gameBoard[5][i] = null; //Odd Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){
      if(i%2 == 1){ //Odd Detector
        gameBoard[6][i] = new CheckerPiece("b"); //Every other Piece on Row 6 Will be Black
      }
      else{
        gameBoard[6][i] = null;
      }
    }
    for (int i = 0; i < 8; i++){
      if(i%2 == 0){ //Even Detector
        gameBoard[7][i] = new CheckerPiece("b"); //Every other Piece on Row 7 Will be Black
      }
      else{
        gameBoard[7][i] = null; //Odd Pieces are Null
      }
    }
  }

  public CheckerPiece[][] getGameBoard() {
      return gameBoard;
  }

  private boolean isInArray(int row, int col) { //This method checks if the values are inside the array, stops OOB errors.
      return row >= 0 && row < 8 && col >= 0 && col < 8;
  }


  public ArrayList<String> getAllPossibleJumps(int row, int col) { //Magic method that Checks for every possibke
      ArrayList<String> allPossibleJumps = new ArrayList<String>();
      
      CheckerPiece piece = gameBoard[row][col];
      if (piece == null) return allPossibleJumps;
      String pieceType = piece.toString().substring(0,1);
      String pieceColor = piece.getColor();

      // if spot in bottom right is on the board and a king or white piece is moving there
      if (isInArray(row+1, col+1) && (pieceType.equals("k") || pieceColor.equals("w"))) {
          // if spot in bottom right is empty, add to array
          if (gameBoard[row+1][col+1] == null) allPossibleJumps.add(""+(row+1)+"-"+(col+1));
          // if spot 2 down below and 2 to the right is on board and is empty and the piece in between is of the opposite color, add to array
          else if (isInArray(row+2, col+2) && gameBoard[row+2][col+2] == null && gameBoard[row+1][col+1].getColor() != pieceColor) allPossibleJumps.add(""+(row+2)+"-"+(col+2));
      }
      
      // if spot in top left is on the board and a king or white piece is moving there
      if (isInArray(row-1, col-1) && (pieceType.equals("k") || pieceColor.equals("b"))) {
          // if spot in top left is empty, add to array 
          if (gameBoard[row-1][col-1] == null) allPossibleJumps.add(""+(row-1)+"-"+(col-1));
          // if spot 2 up above and 2 to the left is on board and is empty and the piece in between is of the opposite color, add to array
          else if (isInArray(row-2, col-2) && gameBoard[row-2][col-2] == null && gameBoard[row-1][col-1].getColor() != pieceColor) allPossibleJumps.add(""+(row-2)+"-"+(col-2));
      }
      
      // if spot in bottom left is on the board and a king or white piece is moving there
      if (isInArray(row+1, col-1) && (pieceType.equals("k") || pieceColor.equals("w"))) {
          // if spot in bottom left is empty, add to array
          if (gameBoard[row+1][col-1] == null) allPossibleJumps.add(""+(row+1)+"-"+(col-1));
          // if spot 2 down below and 2 to the left is on board and is empty and the piece in between is of the opposite color, add to array
          else if (isInArray(row+2, col-2) && gameBoard[row+2][col-2] == null && gameBoard[row+1][col-1].getColor() != pieceColor) allPossibleJumps.add(""+(row+2)+"-"+(col-2));
      }
      
      // if spot in top right is on the board and a king or white piece is moving there
      if (isInArray(row-1, col+1) && (pieceType.equals("k") || pieceColor.equals("b"))) {
          // if spot in top right is empty, add to array
          if (gameBoard[row-1][col+1] == null) allPossibleJumps.add(""+(row-1)+"-"+(col+1));
          // if spot 2 up above and 2 to the right is on board and is empty and the piece in between is of the opposite color, add to array
          else if (isInArray(row-2, col+2) && gameBoard[row-2][col+2] == null && gameBoard[row-1][col+1].getColor() != pieceColor) allPossibleJumps.add(""+(row-2)+"-"+(col+2));
      }

      return allPossibleJumps;
  }


  public boolean updateBoard(Player p, int fromRow, int fromCol, int toRow, int toCol) {
      String newSpot = ""+toRow+"-"+toCol;
      if (!getAllPossibleJumps(fromRow, fromCol).contains(newSpot)) return false;
      // normal move
      if (Math.abs(fromRow-toRow) == 1) {
          gameBoard[toRow][toCol] = gameBoard[fromRow][fromCol];
          gameBoard[fromRow][fromCol] = null;
      }
      // taking another piece
      else if (Math.abs(fromRow-toRow) == 2) {
          gameBoard[toRow][toCol] = gameBoard[fromRow][fromCol];
          gameBoard[(fromRow+toRow)/2][(fromCol+toCol)/2] = null;
          gameBoard[fromRow][fromCol] = null;
      }

      String color = p.getColor();
      if (color.equals("w") && toRow == 7) gameBoard[toRow][toCol] = new King("w");
      else if (color.equals("b") && toRow == 0) gameBoard[toRow][toCol] = new King("b");

      return true;
  }

  public ArrayList<String> canForceJump(Player p) {
      // returns ArrayList with two elements: {string containing oldSpot, string containing newSpot}
      // e.g. {"1,2", "3,4"}
      String color = p.getColor();
      ArrayList<String> spots = new ArrayList<String>();

      for (int i = 0; i < gameBoard.length; i++) {
        for (int j = 0; j < gameBoard[i].length; j++) {
          CheckerPiece piece = gameBoard[i][j];
          if (piece == null || !piece.getColor().equals(color)) continue;
          ArrayList<String> jumps = getAllPossibleJumps(i, j);
          for (String jump: jumps) {
            int toRow = Integer.parseInt(jump.substring(0,1));
            if (Math.abs(i-toRow) == 2) {
              spots.add(i+","+j);
              spots.add(jump);
              return spots;
            }
          }
        }
      }

      return spots;
  }


  public String determineWinner() {
      int whiteCount = 0;
      int blackCount = 0;

      for (int i = 0; i < gameBoard.length; i++) {
          for (int j = 0; j < gameBoard[i].length; j++) {
              CheckerPiece piece = gameBoard[i][j];
              if (piece == null) continue;
              String color = piece.getColor();

              if (color.equals("w")) whiteCount++;
              else if (color.equals("b")) blackCount++;
          }
      }

      if (whiteCount == 0) return "b";
      if (blackCount == 0) return "w";
      return "none";
  }

  public String toString() {
      String finalString = "";
      for (int i = 0; i < gameBoard.length; i++) {
        finalString +="|-------------------------------|\n";
          for (int j = 0; j < gameBoard[0].length; j++) {
              if (gameBoard[i][j] == null) {
                  finalString += "| . ";//Adds the Text "Barrier", then adds a . to represent nulls/empty spaces
              } else {
                  finalString += "|" + gameBoard[i][j] + " "; //adds text "Barrier", then the information of the peice at that location on the board.
              }
          }
          finalString += "|\n"; //Adds End barrier & New Line
      }
      finalString +="|-------------------------------|\n";//Adds Bottom "Barrier"
      return finalString;//Return
  }
  
}
