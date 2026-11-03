package lab4;

public abstract class Passenger {
    private final String name;

    protected Passenger(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я пасажира не може бути порожнім");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + name + "}";
    }
}
