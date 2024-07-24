public class ReverseFactory {
    public Card makeCard(Colors color) {
        return new Reverse(color, 11);
    }
}
