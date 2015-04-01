public class Card {
  private int quantity;
  private int color;
  private int shading;
  private int shape;
  
  public Card(int cardQuantity, int cardColor, int cardShading, int cardShape) {
    
    if (cardQuantity < 1 || cardQuantity > 3)
      quantity = (((cardQuantity % 3) + 3) % 3) + 1; 
    else
      quantity = cardQuantity;
    
    if (cardColor < 1 || cardColor > 3)
      color = (((cardColor % 3) + 3) % 3) + 1;
    else
      color = cardColor;
    
    if (cardShading < 1 || cardShading > 3)
      shading = (((cardShading % 3) + 3) % 3) + 1;
    else
      shading = cardShading;
    
    if (cardShape < 1 || cardShape > 3)
      shape = (((cardShape % 3) + 3) % 3) + 1;
    else
      shape = cardShape;
  }
  
  public boolean equals (Object obj) {
    Card that = (Card)obj;
    
    return quantity == that.getQuantity () &&
      color == that.getColor() &&
      shading == that.getShading() &&
      shape == that.getShape();
  }
  
  public int getQuantity () {
    return quantity;
  }
  
  public int getColor () {
    return color;
  }
  
  public int getShading () {
    return shading;
  }
  
  public int getShape () {
    return shape;
  }
  
  public boolean isSet(Card c1, Card c2) {
    if ((quantity + c1.getQuantity() + c2.getQuantity()) % 3 != 0)
      return false;
    if ((color + c1.getColor() + c2.getColor()) % 3 != 0)
      return false;
    if ((shading + c1.getShading() + c2.getShading()) % 3 != 0)
      return false;
    if ((shape + c1.getShape() + c2.getShape()) % 3 != 0)
      return false;
    return true;
  }
  
  public String toString() {
    String[] quantityList = {"","1","2","3"};
    String[] colorList = {"","R","G","P"};
    String[] shadingList = {"","O","T","S"};
    String[] shapeList = {"","O","D","S"};
    return quantityList[quantity] + colorList[color] + shadingList[shading] + shapeList[shape];
  }
}
