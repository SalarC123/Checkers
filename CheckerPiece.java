public class CheckerPiece {

  private String color;
  private String status;
  
  public CheckerPiece(String c, String s){
    color = c;
    status = s;
  }

  public String toString() {
      return color + status;
  }

  public String getStatus() {
      return status;
  }

  public String getColor() {
      return color;
  }
}