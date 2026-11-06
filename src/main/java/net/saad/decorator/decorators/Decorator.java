package net.saad.decorator.decorators;

import net.saad.decorator.component.Drink;

/**
 * Abstract decorator of the Decorator pattern. It extends {@link Drink} (so a
 * decorated drink can be used anywhere a drink is expected) and holds a
 * {@link Drink} (so it can wrap a base drink or another decorator).
 * <p>
 * {@link #getDescription()} is declared abstract on purpose: a new decorator
 * that forgets to override it will not compile, instead of printing
 * {@code null}.
 */
public abstract class Decorator extends Drink {

    protected final Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public abstract String getDescription();
}
