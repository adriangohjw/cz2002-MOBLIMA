package Model;

public enum PriceType implements PriceChanger{
    STUDENT("Student"),
    SENIOR_CITIZEN("Senior"),
    NORMAL("Normal"),
    HOLIDAY("Holiday"), 
    WEEKEND("Weekend");

    private final String text;

    private PriceType(String text) {
        this.text = text;
    }

    public String toString(){
        return text;
    }
}
