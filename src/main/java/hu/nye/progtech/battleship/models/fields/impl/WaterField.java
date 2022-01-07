package hu.nye.progtech.battleship.models.fields.impl;

import hu.nye.progtech.battleship.models.Result;
import hu.nye.progtech.battleship.models.fields.GameField;

/**
 * WaterField, implements GameField. It is a field type.
 *
 * @author Szabics
 */
public class WaterField implements GameField {
    private boolean isThisFieldHit = false;

    @Override
    public char getFieldIcon() {
        return isThisFieldHit ? 'M' : '~';
    }

    @Override
    public boolean ableToShot() {
        return !isThisFieldHit;
    }

    @Override
    public Result shootAt() {
        if (this.ableToShot()) {
            isThisFieldHit = true;
        } else {
            System.out.println("Ide lőttél korábban, Vízbe ért!");
        }

        return Result.NO_HIT;
    }
}