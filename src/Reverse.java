public class Reverse extends Card{
    public Reverse(Colors color,int value) {
        super(color,value);
    }

    @Override
    public String toString() {
        return "Reverse:"+color;
    }
}
