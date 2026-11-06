package net.saad.decorator.component;

/**
 * The component contract of the Decorator pattern. Both base drinks and every
 * extra wrapping them are {@link Drink}s, so extras can be stacked freely.
 */
public abstract class Drink {

    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
