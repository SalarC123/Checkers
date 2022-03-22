public class CheckerPiece {

  protected String color; //Protected Because King Class Inheritence
  public CheckerPiece(String c){
    color = c;
  }
  public String getColor() {
      return color;
  }
  public String toString(){ //overriding the toString() method  
  return ("n" + color);
 }
}
