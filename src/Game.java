public class Game {
  Table t = new Table();
  Deck d;
  
  public Game() {
    d = new Deck();
    
    for (int i = 0; i < 12; i ++){
      t.add(d.getNext());
    }
  }
  
  public Game(String filename) {
    d = new Deck(filename);
    int count = 0;

    while (d.hasNext() && count <12){
      t.add(d.getNext());
      count ++;
    }
  }
  
  public int numSets() {
    return t.numSets();
  }
  
  public int numCards() {
    return t.numCards();
  }
  
  public void playRound() {
    if (numCards() < 3){
      for (int i = 0; i < 3 && d.hasNext() == true; i ++)
        t.add(d.getNext());
      return;
    }
    
    if (numCards() <= 12 && numSets() > 0) {
      for (int i = 0; i < numCards(); i ++) {
        Card c1 = t.getCard(i);
        for (int j = i + 1; j < numCards(); j ++) {
          Card c2 = t.getCard(j);
          for (int k = j + 1; k < numCards(); k ++) {
            Card c3 = t.getCard(k);
            if (c1.isSet(c2,c3) == true) {
              t.removeSet(c1,c2,c3);
              for (int x = 0; x < 3 && d.hasNext() == true; x ++)
                t.add(d.getNext());
              return;
            }
          }
        }
      }
    }
    
    if (numCards() > 12 && numSets() > 0) {
      for (int i = 0; i < numCards(); i ++) {
        Card c1 = t.getCard(i);
        for (int j = i + 1; j < numCards(); j ++) {
          Card c2 = t.getCard(j);
          for (int k = j + 1; k < numCards(); k ++) {
            Card c3 = t.getCard(k);
            if (c1.isSet(c2,c3) == true) {
              t.removeSet(c1,c2,c3);
              return;
            }
          }
        }
      }
    }    
  }
  
  public boolean isGameOver() {
    if (numSets() == 0 && d.hasNext() == false)
      return true;
    
    return false;
  }
}