package hu.nye.progtech.battleship.models.fields.impl;


import hu.nye.progtech.battleship.models.Result;
import hu.nye.progtech.battleship.models.fields.GameField;
import hu.nye.progtech.battleship.models.ship.Ship;

/**
 * ShipField, implements GameField. It is a field type.
 *
 * @author Szabics
 */
public class ShipField implements GameField {
    private final Ship ship;

    public ShipField(Ship ship) {
        this.ship = ship;
    }

    @Override
    public char getFieldIcon() {
        char icon;
        Result shipState = ship.getState();
        switch (shipState) {
            case PARTIAL_HIT:
                icon = '+';
                break;
            case DESTROYED:
                icon = '+';
                break;
            case HIT_BEFORE:
                icon = '+';
                break;
            case NO_HIT:
                icon = 'X';
                break;
            default:
                icon = ' ';
                break;
        }
        return icon;
    }

    @Override
    public boolean ableToShot() {
        return ship.getState() != Result.DESTROYED;
    }

    @Override
    public Result shootAt() {
        if (this.ableToShot()) {
            ship.hit();
        }

        return ship.getState();
    }
}