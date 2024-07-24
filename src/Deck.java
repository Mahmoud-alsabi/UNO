import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Deck {
    private List<Card> cards;
    public Deck(){
        cards =new ArrayList<>();
        WildFactory wildFactory =new WildFactory();
        Draw2Factory draw2Factory=new Draw2Factory();
        ReverseFactory reverseFactory =new ReverseFactory();
        SkipFactory skipFactory = new SkipFactory();
        Wild4Factory wild4Factory =new Wild4Factory();
        NumberFactory numberFactory=new NumberFactory();
        Card card;
        Card[] numbers=new Card[10];

        for(Colors color:Colors.values()) {
            if(color != Colors.WILD) {
                for(int i=0;i<2;i++) {
                    cards.add(card = skipFactory.makeCard(color));
                    cards.add(card = draw2Factory.makeCard(color));
                    cards.add(card = reverseFactory.makeCard(color));

                    cards.addAll(List.of(numbers = numberFactory.makeCards(color)));
                }
                cards.add(card = wildFactory.makeCard());
                cards.add(card = wild4Factory.makeCard());
            }
            shuffle();
        }
    }
    public void shuffle(){
        Collections.shuffle(cards);
    }
    public Card drawCard(){
        return cards.remove(cards.size()-1);
    }
    public void addCards(List<Card> Cards) {
        cards.addAll(Cards);
        shuffle();
    }
    public void addCard(Card card){
        cards.add(card);
        shuffle();
    }
    public int deckSize(){
        return cards.size();
    }
}