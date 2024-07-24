public class DrawTwo extends Card{
    public DrawTwo(Colors color, int value) {
        super(color,value);
    }

    @Override
    public String toString() {
        return "DrawTwo:"+color;
    }
}