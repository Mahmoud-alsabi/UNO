public class Draw2Factory {
    public Card makeCard(Colors color){
        return new DrawTwo(color,10);
    }
}
