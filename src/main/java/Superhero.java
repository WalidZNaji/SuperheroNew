public class Superhero {

    private String name;
    private String realName;
    private String superpower;
    private int age;
    private int strength;
    private boolean isHuman;

    public Superhero(String name, String realName, String superpower, int age, int strength, boolean isHuman) {
        this.name = name;
        this.realName = realName;
        this.superpower = superpower;
        this.age = age;
        this.strength = strength;
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean IsHuman) {
        isHuman = IsHuman;
    }

    @Override
    public String toString() {
        String isHumanString = isHuman ? "Superhero is human" : "Superhero is not human";
        return
                "Name: " + name + "\n" +
                        "RealName: " + realName + "\n" +
                        "Superpower: " + superpower + "\n" +
                        "Age: " + age + "\n" +
                        "Strength: " + strength + "\n" +
                        "Human?: " + isHumanString + "\n";
    }
}
