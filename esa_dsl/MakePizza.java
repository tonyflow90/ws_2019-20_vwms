public class MakePizza {
    String ingredients;
    String name;
    Integer duration;

    public MakePizza(){};

    public MakePizza(String name){
        this.name = name;
    }

    public MakePizza ingredients(String inggredients) {
        this.ingredients = inggredients;
        return this;
    }

    public MakePizza duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String toString() {
        return "Name: " + this.name + "\n" +
               "Ingredients: " + this.ingredients + "\n" +
               "Duration: " + this.duration;
    }
}