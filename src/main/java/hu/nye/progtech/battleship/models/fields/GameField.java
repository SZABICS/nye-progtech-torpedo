package hu.nye.progtech.battleship.models.fields;

import hu.nye.progtech.battleship.models.Result;

/**
 * Field class to implement a Field at a specific point on map.
 *
 * @author Szabics
 */
public interface GameField {
    char getFieldIcon();

    boolean ableToShot();

    Result shootAt();
}