public enum Colors {
    RED("RED"), YELLOW("YELLOW"), GREEN("GREEN"), BLUE("BLUE"),WILD("WILD")
    ;
    private String color;
    private Colors(String color){
        this.color=color;
    }
    public String getColor() {
        return color;
    }
}
