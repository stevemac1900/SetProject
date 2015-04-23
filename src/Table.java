public class Table{
  private TableNode head;
  
  public Table() {
    head = null;
  }
  
  public void add(Card c1) {
    TableNode newNode = new TableNode(c1);
    
    if (head == null) 
      head = newNode;
    
    else{
      newNode.setNext(head);
      head = newNode;
    }
  }
  
  public void removeSet(Card c1, Card c2, Card c3) {
    if (head == null)
      return;
    
    if (numCards() < 3)
      return;
    
    if (c1.isSet(c2,c3) == false)
      return;
    
    TableNode temp = head;
    TableNode[] placeHolder = {null,null,null};
    Card[] card = {c1,c2,c3};
    
    int placeHolderIndex = 0;
    
    while (temp != null && placeHolderIndex < 3) {
      for (int i = 0; i < 3; i ++) {
        if (temp.getCard().equals(card[i])) {
          placeHolder[placeHolderIndex] = temp;
          placeHolderIndex ++;
        }
      }
      temp = temp.getNext();
    }
    
     for (int i = 0; i < 3; i ++) {
       if (placeHolder[i] == null)
         return;
     }
     
     int removals = 0;
     
     for (int j = 0; j < 3 && removals < 3 && head != null; j ++) {
       for (int i = 0; i < 3 && head != null; i ++) {
         if(card[i].equals(head.getCard())) {
           head = head.getNext();
           removals ++;
         }
       }
     }
     
     if (head == null || removals >= 3)
      return;
     
     TableNode prev = head;
     TableNode curr = prev.getNext();
     
     while (curr != null) {
       for (int i = 0; i < 3 && curr != null; i ++) {
         if (removals < 3 && placeHolder[i] == curr) {
           prev.setNext(curr.getNext());
           curr = prev.getNext();
           removals ++;
         }
       }
       
       if (curr != null) {
         prev = prev.getNext();
         curr = curr.getNext();
       }
     }
  }

  public Card getCard(int cardIndex) {
    if (cardIndex >= numCards())
      return null;
    
    TableNode curr = head;
    
    for (int i = 0; i < cardIndex; i++) {
      curr = curr.getNext();
    }
    return curr.getCard();
  }

  public int numCards() {
    int cards = 0;
    TableNode curr = head;
    while (curr != null) {
        cards += 1;
        curr = curr.getNext();
    }
    return cards;
  }
  
  public int numSets() {
    int sets = 0;
    
    if (numCards() < 3)
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