package hu.nye.progtech.battleship.models;

import java.awt.Point;
import java.util.Scanner;

import hu.nye.progtech.battleship.models.fields.GameField;
import hu.nye.progtech.battleship.models.fields.impl.ShipField;
import hu.nye.progtech.battleship.models.fields.impl.WaterField;
import hu.nye.progtech.battleship.models.ship.Ship;
import hu.nye.progtech.battleship.ui.PrintWrapper;

/**
 * Map class to implement a game board.
 *
 * @author Szabics
 */
public class Map {
    private static final char WATER = '~';
    private static final int BOARD_SIZE = 10;
    private static final int SHIP_COUNT = 3;
    private static final char[] BOARD_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";

    private final PrintWrapper printWrapper;

    private Scanner scanner;
    private GameField[][] board;
    private Ship[] ships;

    public Map() {
        this.scanner = new Scanner(System.in);
        this.board = new GameField[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new WaterField();
            }
        }
        this.printWrapper = new PrintWrapper();
        printBoard();
    }

    /**
     * Places ships on the game board from user input points.
     */
    public void placeShipsOnBoard() {
        for (int i = 0; i < SHIP_COUNT; i++) {
            String shipName = (i + 1) + ". Csatahajó";
            Ship ship = new Ship(shipName);
            //boolean horizontal = askValidShipDirection();
            boolean horizontal = true;
            Point startingPoint = askValidStartingPoint(ship, horizontal);
            placeValidShip(ship, startingPoint, horizontal);
            printBoard();
        }
    }

    /**
     * Returns a field from a specific point.
     *
     * @param rowCoordinate    for row point.
     * @param columnCoordinate for column.
     * @return GameField
     */
    public GameField getField(int rowCoordinate, int columnCoordinate) {
        if (!isInsideBoard(rowCoordinate, columnCoordinate)) {
            throw new IllegalArgumentException("Játéktáblán kívül esik a lövés, kérlek próbáld újra!: ");
        }
        return board[columnCoordinate][rowCoordinate];
    }

    /**
     * Printing the game board on console.
     */
    public void printBoard() {
        printWrapper.print("\t");

        for (int i = 0; i < BOARD_SIZE; i++) {
            printWrapper.print(BOARD_LETTERS[i] + "\t");
        }

        printWrapper.printLine("");

        for (int i = 0; i < BOARD_SIZE; i++) {
            printWrapper.print((i + 1) + "\t");
            for (int j = 0; j < BOARD_SIZE; j++) {
                printWrapper.print(board[i][j].getFieldIcon() + "\t");
            }
            printWrapper.printLine("");
        }
    }

    private boolean askValidShipDirection() {
        printWrapper.print("== Hajó elhelyezése (H) Horizontális, (V) Vertikális: ");
        String direction;
        do {
            direction = scanner.nextLine().trim();
        } while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

        return HORIZONTAL.equals(direction);
    }

    private Point askValidStartingPoint(Ship ship, boolean horizontal) {
        Point from;
        do {
            printWrapper.print("Kérem a " + ship.getName() + " pozícióit (1-9 1-9): (hajóhossz  " + ship.getSize() + "): ");
            from = new Point(scanner.nextInt(), scanner.nextInt());
        } while (!isValidStartingPoint(from, ship.getSize(), horizontal));

        return from;
    }

    private boolean isValidStartingPoint(Point from, int length, boolean horizontal) {
        int rowDifference = 0;
        int columnDifference = 0;
        if (horizontal) {
            rowDifference = 1;
        } else {
            columnDifference = 1;
        }

        int rowPoint = (int) from.getX() - 1;
        int columnPoint = (int) from.getY() - 1;

        if (!isInsideBoard(rowPoint, columnPoint) ||
                (!isInsideBoard(rowPoint + length, columnPoint) && horizontal) ||
                (!isInsideBoard(rowPoint, columnPoint + length) && !horizontal)
        ) {
            printWrapper.printLine("Érvénytelen mező érték!");
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (board[(int) from.getY() + i * columnDifference - 1]
                    [(int) from.getX() + i * rowDifference - 1].getFieldIcon() != WATER) {
                printWrapper.printLine("Ez a pozíció már foglalt!");
                return false;
            }
        }
        return true;
    }

    private void placeValidShip(Ship ship, Point startingPoint, boolean horizontal) {
        int differenceRow = 0;
        int differenceColumn = 0;
        if (horizontal) {
            differenceRow = 1;
        } else {
            differenceColumn = 1;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            board[(int) startingPoint.getY() + i * differenceColumn - 1]
                    [(int) startingPoint.getX() + i * differenceRow - 1] = new ShipField(ship);
        }
    }

    private boolean isInsideBoard(int rowCoodrinate, int columnCoordinate) {
        return rowCoodrinate <= BOARD_SIZE && rowCoodrinate >= 0
                && columnCoordinate <= BOARD_SIZE && columnCoordinate >= 0;
    }
}