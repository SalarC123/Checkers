import java.util.ArrayList;

public class GameBoard {
  private CheckerPiece[][] gameBoard;//Declares Gameboard
  
  public GameBoard(){
    gameBoard = new CheckerPiece[8][8];//Initialize Gameboard, Sets as 8x8 Board
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 1){//Odd Detector
        gameBoard[0][i] = new CheckerPiece("w");//Every other Piece on Row 0 Will be White
      }
      else
      {
        gameBoard[0][i] = null;//Even Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 0){//Even Detector
        gameBoard[1][i] = new CheckerPiece("w");//Every other Piece on Row 1 Will be White
      }
      else
      {
        gameBoard[1][i] = null;//Odd Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 1){//Odd Detector
        gameBoard[2][i] = new CheckerPiece("w");//Every other Piece on Row 2 Will be White
      }
      else
      {
        gameBoard[2][i] = null;//Even pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){//Loop <8
      gameBoard[3][i] = null;//All Pieces in Row are Null
    }
    for (int i = 0; i < 8; i++){//Loop <8
      gameBoard[4][i] = null;//All Pieces in Row are Null
    }
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 0){//Even Detector
        gameBoard[5][i] = new CheckerPiece("b");//Every other Piece on Row 5 Will be Black
      }
      else{
        gameBoard[5][i] = null;//Odd Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 1){//Odd Detector
        gameBoard[6][i] = new CheckerPiece("b");//Every other Piece on Row 6 Will be Black
      }
      else{
        gameBoard[6][i] = null;//Even Pieces are Null
      }
    }
    for (int i = 0; i < 8; i++){//Loop <8
      if(i%2 == 0){//Even Detector
        gameBoard[7][i] = new CheckerPiece("b");//Every other Piece on Row 7 Will be Black
      }
      else{
        gameBoard[7][i] = null;//Odd Pieces are Null
      }
    }
  }

  public CheckerPiece[][] getGameBoard() {//Get
      return gameBoard;//Self Explanitory
  }

  private boolean isInArray(int row, int col) {//This Method Checks if the Values are inside the Array, Stops OOB Errors.
      return row >= 0 && row < 8 && col >= 0 && col < 8;
  }


  public ArrayList<String> getAllPossibleJumps(int row, int col) {//Magic Method that Checks for every Jump
      ArrayList<String> allPossibleJumps = new ArrayList<String>();
      
      CheckerPiece piece = gameBoard[row][col];
      if (piece == null) return allPossibleJumps;
      String pieceType = piece.getStatus();
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
/*
// * Comment here Later *
   public String checkJump(CheckerPiece piece, String jump, int row, int column){
     if(piece.getColor().equals("b"))
     {
       if(piece.getStatus().equals("n"))
       {
         if (jump.equals("left"))
         {
           if (gameBoard[row + 1][column - 1] == null)
           {
             return "left";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column - 1);
             if (temp.equals("left"))
             {
               return "double left";
             }
           }
         }
         else if (jump.equals("right"))
         {
           if (gameBoard[row + 1][column + 1] == null)
           {
             return "right";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column + 1);
             if (temp.equals("right"))
             {
               return "double right";
             }
           }
         }
       }
       else
       {
         if (jump.equals("left"))
         {
           if (gameBoard[row + 1][column - 1] == null)
           {
             return "left";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column - 1);
             if (temp.equals("left"))
             {
               return "double left";
             }
           }
         }
         else if (jump.equals("right"))
         {
           if (gameBoard[row + 1][column + 1] == null)
           {
             return "right";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column + 1);
             if (temp.equals("right"))
             {
               return "double right";
             }
           }
         }
         else if (jump.equals("reverse left"))
         {
           if (gameBoard[row - 1][column - 1] == null)
           {
             return "reverse left";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column - 1);
             if (temp.equals("reverse left"))
             {
               return "double reverse left";
             }
           }
         }
         else if (jump.equals("reverse right"))
         {
           if (gameBoard[row - 1][column + 1] == null)
           {
             return "reverse right";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column + 1);
             if (temp.equals("reverse right"))
             {
               return "double reverse right";
             }
           }
         }
       }
     }
     else if(piece.getColor().equals("w"))
     {
       if(piece.getStatus().equals("n"))
       {
         if (jump.equals("left"))
         {
           if (gameBoard[row - 1][column - 1] == null)
           {
             return "left";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column - 1);
             if (temp.equals("left"))
             {
               return "double left";
             }
           }
         }
         else if (jump.equals("right")){
           if (gameBoard[row - 1][column + 1] == null)
           {
             return "right";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column + 1);
             if (temp.equals("right"))
             {
               return "double right";
             }
           }
         }
       }
       else{
         if (jump.equals("left")){
           if (gameBoard[row - 1][column - 1] == null)
           {
             return "left";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column - 1);
             if (temp.equals("left"))
             {
               return "double left";
             }
           }
         }
         else if (jump.equals("right")){
           if (gameBoard[row - 1][column + 1] == null)
           {
             return "right";
           }
           else
           {
             String temp = checkJump(piece, jump, row - 1, column + 1);
             if (temp.equals("right"))
             {
               return "double right";
             }
           }
         }
         else if (jump.equals("reverse left")){
           if (gameBoard[row + 1][column - 1] == null)
           {
             return "reverse left";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column - 1);
             if (temp.equals("reverse left"))
             {
               return "double reverse left";
             }
           }
         }
         else if (jump.equals("reverse right")){
           if (gameBoard[row + 1][column + 1] == null)
           {
             return "reverse right";
           }
           else
           {
             String temp = checkJump(piece, jump, row + 1, column + 1);
             if (temp.equals("reverse right"))
             {
               return "double reverse right";
             }
           }
         }
       }
     }
     return "can't";
   }
  */

  public void updateBoard(int fromRow, int fromCol, int toRow, int toCol) {
      String newSpot = ""+toRow+"-"+toCol;
      if (!getAllPossibleJumps(fromRow, fromCol).contains(newSpot)) return;
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
  }
    
  /*
  // * Comment Here *
   public void updateBoard(String jump, int row, int column){
     String result = checkJump(gameBoard[row][column],jump,row,column);
     boolean run = true;
    
     if(result.equals("can't"))
     {run = false;}

     if(run)
     {
       if(gameBoard[row][column].getColor().equals("b"))
       {
         if(result.equals("left"))
         {
           gameBoard[row+1][column-1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("right"))
         {
           gameBoard[row+1][column+1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("reverse left"))
         {
           gameBoard[row-1][column-1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("reverse right"))
         {
           gameBoard[row-1][column+1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
       }
        
       else if(gameBoard[row][column].getColor().equals("w"))
       {
         if(result.equals("left"))
         {
           gameBoard[row-1][column-1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("right"))
         {
           gameBoard[row-1][column+1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("reverse left"))
         {
           gameBoard[row+1][column-1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("reverse right"))
         {
           gameBoard[row+1][column+1] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
       }
      
       if(gameBoard[row][column].getColor().equals("b"))
       {
         if(result.equals("double left"))
         {
           gameBoard[row+2][column-2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double right"))
         {
           gameBoard[row+2][column+2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double reverse left"))
         {
           gameBoard[row-2][column-2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double reverse right"))
         {
           gameBoard[row-2][column+2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
       }
       else if(gameBoard[row][column].getColor().equals("w"))
       {
         if(result.equals("double left"))
         {
           gameBoard[row-2][column-2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double right"))
         {
           gameBoard[row-2][column+2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double reverse left"))
         {
           gameBoard[row+2][column-2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
        
         else if(result.equals("double reverse right"))
         {
           gameBoard[row+2][column+2] = gameBoard[row][column];
           gameBoard[row][column] = null;
         }
       }
     }
   }
  */
  
  public String toString() {//Overrides toString() Method
      String finalString = "";//The String being Continuously added and then returned
      for (int i = 0; i < gameBoard.length; i++) {//Outer Loop, 0 to Vertical Length
        finalString +="|-------------------------------|\n";//Adds A "Barrier" after every line
          for (int j = 0; j < gameBoard[0].length; j++) {
              if (gameBoard[i][j] == null) {
                  finalString += "| . ";//Adds the Text "Barrier", then adds a . to represent nulls/empty spaces
              } else {
                  finalString += "|" + gameBoard[i][j] + " ";//adds text "Barrier", then the information of the peice at that location on the board.
              }
          }
          finalString += "|\n";// Adds End barrier & New Line
      }
      finalString +="|-------------------------------|\n";// Adds Bottom "Barrier"
      return finalString;//Return
  }
  
}