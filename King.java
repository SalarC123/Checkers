public class King extends CheckerPiece{
  public King(String c){
    super(c);
  }
  public String toString(){//overriding the toString() method  
  return ("k" + color);
 }
}