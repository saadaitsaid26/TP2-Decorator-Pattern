package net.saad.decorator.component;

/**
 * Concrete component: the decaf base drink at 12 DH.
 */
public class Deca extends Drink {

    public Deca() {
        description = "Deca";
    }

    @Override
    public double cost() {
        return 12;
    }
}
