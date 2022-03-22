public class King extends CheckerPiece{
  public King(String c){
    super(c);//Calls Super Constructor
  }
  public String toString(){//overriding the toString() method  
  return ("k" + color);
 }
}