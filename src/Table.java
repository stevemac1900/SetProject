public class Table{
  private TableNode head;
  private int length = 0;
  
  public Table() {
    Deck d = new Deck();
    
    if (d.cards.size() >= 12) {
      for (int i = 0; i < 12; i ++){
        add(d.getNext());
      }
    }
    else {
      for (int i = 0; i < d.cards.size(); i++) {
        add(d.getNext());
      }
    }
  }
  
  public void add(Card c1) {
    TableNode newNode = new TableNode(c1);
    
    length ++;
    
    if (head == null) 
      head = newNode;

    else{
      newNode.setNext(head);
      head = newNode;
    }
  }
  
  public void removeSet(Card c1, Card c2, Card c3) {
    if (length < 3)
      return;
    
    if (c1.isSet(c2,c3) == false)
      return;
    
    TableNode prev = head;
    TableNode curr = prev.getNext();
    TableNode next = curr.getNext();
    
    TableNode n1 = null;
    TableNode n2 = null;
    TableNode n3 = null;
    
    TableNode[] node = new TableNode[]{prev,curr,next};
    Card[] card = new Card[] {c1,c2,c3};
    
    int nodeIndex = 0;
    
    while (prev.getNext() != null && nodeIndex < 3) {
      for (int i = 0; i < 3; i ++) {
        if (prev.getCard() == card[i]) {
          node[nodeIndex] = prev;
          nodeIndex ++;
        }
      }
      prev = prev.getNext();
    }
    
    for (int i = 0; i < 3; i ++) {
      if (node[i] == null)
        return;
    }
    
    int headChanges = 0;
    
    while (headChanges < 3 && head != null) {
      for (int i = 0; i < 3; i ++) {
        if (card[i] == head.getCard()) {
          head = head.getNext();
          length -= 1;
          headChanges += 1;
          if (headChanges >= 3)
            return;
        }
      }
    }
    
    if (length < 3)
      return;
    
    prev = head;
    curr = prev.getNext();
    next = curr.getNext();
    
    while (next != null) {
      boolean changed = false;
      for (int i = 0; i < 3; i ++) {
        if (changed == false && node[i] == curr) {
          prev.setNext(next);
          changed = true;
        }
        else
          prev = prev.getNext();
      }
      curr = curr.getNext();
      next = next.getNext();
    }
    
    curr = head;
    for(int i = 0; i < length - 1; i++) {
      curr = curr.getNext();
    }
    curr.setNext(null);
    
    length -= 3;
  }
  
  public Card getCard(int cardIndex) {
    if (cardIndex >= length)
      return null;
    
    TableNode curr = head;
    
    for (int i = 0; i < cardIndex; i++) {
      curr = curr.getNext();
    }
    return curr.getCard();
  }

  public int numCards() {
    return length;
  }
  
  public int numSets() {
    int sets = 0;
    
    if (length < 3)
      return sets;
    
    TableNode n1 = head;
    while (n1 != null && n1.getNext().getNext() != null) {
      TableNode n2 = n1.getNext();
      while (n2 != null && n2.getNext() != null) {
        TableNode n3 = n2.getNext();
        while (n3 != null) {
          if (n1.getCard().isSet(n2.getCard(), n3.getCard()) == true)
            sets ++;
          n3 = n3.getNext();
        }
        n2 = n2.getNext();
      }
      n1 = n1.getNext() ;
    }
    return sets;
  }
}