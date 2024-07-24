public class Skip extends Card{
    public Skip(Colors color, int value) {
        super(color,value);
    }

    @Override
    public String toString() {
        return "Skip:"+color;
    }
}