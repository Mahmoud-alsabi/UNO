import java.util.*;

public class Game {
    private  Deck deck =new Deck();
    private List<Player> players;
    private Scanner sc = new Scanner(System.in);
    private int numberOfPlayers;
    private List<Card> groundPile = new ArrayList<>();
    private Card groundCard;
    private Colors currentColor;
    private int playerIndex;
    private boolean reverseOrder;
    private static Game singleton =new Game();
    private Game(){
        addPlayers();
    }
    public static Game getInstance(){
        return singleton;
    }
    private void addPlayers(){
        players = new ArrayList<>();
        while (true) {
            System.out.print("choose the number of players (more than one less eleven) : ");
            try {
                numberOfPlayers = sc.nextInt();
                if (!(1 < numberOfPlayers && numberOfPlayers <= 10)) {
                    System.out.println("Invalid input!");
                    continue;
                }
                for (int i = 0; i < numberOfPlayers; i++) {
                    System.out.print("player name: ");
                    String playerName = sc.next();
                    players.add(new Player(playerName));
                }
                break;
            }catch (Exception e){
                System.out.println("Invalid input!");
                sc.next();
            }
        }
        startGame();
    }
    private void startGame(){
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
        groundCard =deck.drawCard();
        while (true){
            if(groundCard instanceof Wild || groundCard instanceof WildDraw4){
                deck.addCard(groundCard);
                groundCard=deck.drawCard();
            }
            else
                break;
        }
        currentColor = groundCard.color;
        groundPile.add(groundCard);
        playerIndex =0;
        reverseOrder=false;
        playGame();
    }
    private void playGame(){
        while (true) {
                if(deck.deckSize()==0 && !(groundPile.isEmpty())) {
                    deck.addCards(groundPile);
                }
                Player currentPlayer = players.get(playerIndex);
                System.out.println("ground card: " + groundCard);
                System.out.println(currentPlayer.getHand());
                while (true) {
                    try {
                        if(canPlay(currentPlayer.getHand())) {
                            System.out.print(currentPlayer.getName()+" choose a card to play: ");
                            int cardNumber = sc.nextInt()-1;
                            Card playedCard = currentPlayer.pickCard(cardNumber);
                            if (!isValidCard(playedCard)) {
                                System.out.println("play a card that is similar in color or type or a wild card!!");
                                continue;
                            }
                            groundCard = currentPlayer.playCard(cardNumber);
                            currentColor=groundCard.color;
                            cardEffect(groundCard);
                            groundPile.add(groundCard);
                            break;
                        }else {
                            System.out.println(currentPlayer.getName()+" draw a card");
                            currentPlayer.drawCard(deck);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                    }
                }
                if(currentPlayer.getHand().isEmpty()){
                    System.out.println(currentPlayer.getName()+" won!!");
                    break;
                }
                nextPlayer();
        }
    }
    private boolean isValidCard(Card card){
        return  card.value== groundCard.value || card.color==currentColor || card instanceof Wild || card instanceof WildDraw4;
    }
    private void nextPlayer(){
        if(reverseOrder)
            playerIndex = (playerIndex-1 + players.size()) % players.size();
        else
            playerIndex = (playerIndex+1) % players.size();
    }
    private boolean canPlay(List<Card> playerHand){
        for (Card card : playerHand)
            if (isValidCard(card))
                return true;
        return false;
    }
    private void cardEffect(Card card){
        if(card instanceof Wild || card instanceof WildDraw4){
            while (true){
                System.out.print("choose a color between(RED,YELLOW,GREEN,BLUE): ");
                try {
                    String color =sc.next();
                    if(color.equalsIgnoreCase(Colors.BLUE.getColor())) {
                        currentColor = Colors.BLUE;
                        System.out.println("The current color is: "+currentColor.getColor());
                        break;
                    }
                    else if(color.equalsIgnoreCase(Colors.RED.getColor())) {
                        currentColor = Colors.RED;
                        System.out.println("The current color is: "+currentColor.getColor());
                        break;
                    }
                    else if (color.equalsIgnoreCase(Colors.YELLOW.getColor())) {
                        currentColor = Colors.YELLOW;
                        System.out.println("The current color is: "+currentColor.getColor());
                        break;
                    }
                    else if (color.equalsIgnoreCase(Colors.GREEN.getColor())) {
                        currentColor=Colors.GREEN;
                        System.out.println("The current color is: "+currentColor.getColor());
                        break;
                    }
                    else
                        System.out.println("Invalid input!");
                }catch (Exception e) {
                    System.out.println("Invalid input!");
                    sc.nextLine();
                }
                }
            }
        if(card instanceof Skip)
            nextPlayer();
        if(card instanceof Reverse)
            reverseOrder=true;
        if(card instanceof DrawTwo) {
            nextPlayer();
            for(int i=0;i<2;i++)
                players.get(playerIndex).drawCard(deck);
        }
        if(card instanceof WildDraw4){
            nextPlayer();
            for(int i=0;i<4;i++)
                players.get(playerIndex).drawCard(deck);
        }
    }
}