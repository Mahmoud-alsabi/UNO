public abstract class Card {
    protected Colors color;
    protected int value;
    public Card(Colors color,int value){
        this.value=value;
        this.color=color;
    }
    public Colors getColor() {
        return color;
    }
    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
