public class SetData {
  public static void main(String[] args) {
    for (int j = 0; j < 5; j++) {
      double iter = 100000;
      double setSum = 0;
      double cardSum = 0;
      
      for (int i = 0; i < iter; i ++) {
        Game g = new Game();
        setSum += g.numSets();
        while (!g.isGameOver()) 
          g.playRound();
        cardSum += g.numCards();
      }
      
      setSum = setSum / iter;
      cardSum = cardSum / iter;
      
      System.out.println("The average number of cards left on the table at the end of a game is " + cardSum);
      System.out.println("The average number of sets found in collection of 12 random cards is " + setSum);
    }
  }
}