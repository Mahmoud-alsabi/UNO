public class NumberFactory {
    public Card[] makeCards(Colors color){
        Card[] cards =new Card[10];
        for(int i =0;i<cards.length;i++){
            cards[i]=new Number(color,i);
        }
        return cards;
    }
}
