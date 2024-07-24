import java.util.ArrayList;
import java.util.List;
public class Player {
    private List<Card> hand;
    private final String name;
    public Player(String name){
        this.name=name;
        hand =new ArrayList<>();
    }
    public void drawCard(Deck deck){
         hand.add(deck.drawCard());
    }
    public Card playCard(int cardIndex) {
        return hand.remove(cardIndex);
    }
    public Card pickCard(int cardIndex){
        return hand.get(cardIndex);
    }
    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
