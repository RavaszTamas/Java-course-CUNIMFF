package com.game.battleship.controller.aicontroller;

import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.MyExceptions.ShipException;
import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.domain.Models.Hit;
import com.game.battleship.domain.HitRepository.HitRepository;
import com.game.battleship.domain.Models.Ship;
import com.game.battleship.domain.ShipRepository.ShipRepository;
import com.game.battleship.controller.Validators.ShipValidator;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The controller for the AI, it has the necessary functionalities for the AI to perform the attack
 * and finding an optimal target
 *
 * @author Ravasz Tamás
 */
public class AIController {

    final private int[] DirectionX = {0, 1};
    final private int[] DirectionY = {1, 0};
    private final int CORRECTHIT = 2;
    private final int MISSEDHIT = 1;
    private final int NOHIT = 0;
    private HitRepository playerHitRepository;
    private HitRepository computerHitRepository;
    private ShipRepository playerShipRepository;
    private ShipRepository computerShipRepository;
    private ShipValidator shipValidator;
    private int maxBattleship;
    private int maxCruiser;
    private int maxDestroyer;
    private HashMap<Integer, Integer> dictionaryOfSizes = new HashMap<>();
    private int maxGridSizeX = 10;
    private int maxGridSizeY = 10;
    private ArrayList<Point> goodHits;
    private List<Point> listOfVisited;
    private List<Point> listofPossibleTargets;
    private List<Point> hitQueue;

    /**
     * Constructor for the controller
     *
     * @param maxXParam              int - Width of the grid
     * @param maxYParam              int - Height of the grid
     * @param playerHitRepository    HitRepository - repository for the player hits
     * @param computerHitRepository  HitRepository - repository for the computer hits
     * @param playerShipRepository   ShipRepository - repository for the player ships
     * @param computerShipRepository ShipRepository - repository for the computer ships
     * @param shipValidator          ShipValidator - validator for the ships
     */
    public AIController(int maxXParam, int maxYParam, HitRepository playerHitRepository, HitRepository computerHitRepository, ShipRepository playerShipRepository, ShipRepository computerShipRepository, ShipValidator shipValidator) {
        this.playerHitRepository = playerHitRepository;
        this.computerHitRepository = computerHitRepository;
        this.playerShipRepository = playerShipRepository;
        this.computerShipRepository = computerShipRepository;
        this.shipValidator = shipValidator;
        maxGridSizeX = maxXParam;
        maxGridSizeY = maxYParam;


        this.goodHits = new ArrayList<>();
        this.listOfVisited = new ArrayList<>();
        this.listofPossibleTargets = new ArrayList<>();
        this.hitQueue = new LinkedList<>();


        this.AISetUp();
    }

    /**
     * This where the AI places all the ships on it's grid
     */
    public void AISetUp() {
        goodHits.clear();
        listOfVisited.clear();
        listofPossibleTargets.clear();
        hitQueue.clear();
        for (int i = 0; i < maxGridSizeX; i++)
            for (int j = 0; j < maxGridSizeX; j++)
                listofPossibleTargets.add(new Point(i, j));

        this.maxBattleship = 1;
        this.maxCruiser = 1;
        this.maxDestroyer = 2;
        dictionaryOfSizes.put(2, this.maxDestroyer);
        dictionaryOfSizes.put(3, this.maxCruiser);
        dictionaryOfSizes.put(4, this.maxBattleship);

        int numberofships = 0;
        Random random = new Random(System.currentTimeMillis());
        while (numberofships != this.maxBattleship) {
            int XCoord = random.nextInt(maxGridSizeX);
            int YCoord = random.nextInt(maxGridSizeY);
            int Direction = random.nextInt(2);
            try {
                this.shipValidator.validateShip(XCoord, YCoord, 4, Direction);
                this.computerShipRepository.addShip(new Ship(new Point(XCoord, YCoord), 4, Direction));
                numberofships += 1;
            } catch (RepositoryException | ValidatorException ignored) {
            }
        }
        numberofships = 0;
        while (numberofships != this.maxCruiser) {
            int XCoord = random.nextInt(maxGridSizeX);
            int YCoord = random.nextInt(maxGridSizeY);
            int Direction = random.nextInt(2);
            try {
                this.shipValidator.validateShip(XCoord, YCoord, 3, Direction);
                this.computerShipRepository.addShip(new Ship(new Point(XCoord, YCoord), 3, Direction));
                numberofships += 1;
            } catch (RepositoryException | ValidatorException ignored) {
            }
        }
        numberofships = 0;
        while (numberofships != this.maxDestroyer) {
            int XCoord = random.nextInt(maxGridSizeX);
            int YCoord = random.nextInt(maxGridSizeY);
            int Direction = random.nextInt(2);
            try {
                this.shipValidator.validateShip(XCoord, YCoord, 2, Direction);
                this.computerShipRepository.addShip(new Ship(new Point(XCoord, YCoord), 2, Direction));
                numberofships += 1;
            } catch (RepositoryException | ValidatorException ignored) {
            }
        }
    }

    /**
     * This where the AI makes it's turn, the function returns whether it has hit a target or not
     *
     * @return boolean - true if the AI hit something, false otherwise
     */
    public boolean aiTurn() {
        return this.TakeTurn();
    }

    /**
     * Based on if the AI has already hit something or it has not target to find, the function returns whether it has hit a target or not
     *
     * @return boolean - true if the AI hit something, false otherwise
     */
    private boolean TakeTurn() {
        if (goodHits.size() == 0)
            return this.shootRandom();
        else
            return this.shootHuntProbability();
    }


    /**
     * The function produces a positive integer value representing the the number of possible ships for each position on the grid, based on previous correct hit(s)
     *
     * @param playerBoardMatrix The matrix where for each position of the grid it is marked whether there was no hit or a missed hit or a correct hit
     * @return int[][] - the matrix representing the densities
     */
    private int[][] getProbabilitiesForHit(int[][] playerBoardMatrix) {
        int[][] densityMatrix = new int[maxGridSizeX][maxGridSizeY];
        for (int[] row : densityMatrix)
            Arrays.fill(row, 0);
        Set<Map.Entry<Integer, Integer>> entrySet = dictionaryOfSizes.entrySet();
        int[] localDirectionX = {0, 1, 0, -1};
        int[] localDirectionY = {-1, 0, 1, 0};

        for (Point hit : goodHits) {
            int i = hit.x;
            int j = hit.y;
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                int auxiliaryI = i;
                int auxiliaryJ = j;
                if (j - entry.getKey() + 1 >= 0) {
                    boolean good = true;
                    auxiliaryI += localDirectionX[0];
                    auxiliaryJ += localDirectionY[0];
                    for (int length = 0; length < entry.getKey() - 1; length++) {
                        if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == MISSEDHIT)
                            good = false;
                        auxiliaryI += localDirectionX[0];
                        auxiliaryJ += localDirectionY[0];

                    }
                    if (good) {
                        auxiliaryI = i;
                        auxiliaryJ = j;
                        for (int length = 0; length < entry.getKey() - 1; length++) {
                            auxiliaryI += localDirectionX[0];
                            auxiliaryJ += localDirectionY[0];
                            if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == NOHIT) {
                                densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                            }
                        }
                    }
                }


                auxiliaryI = i;
                auxiliaryJ = j;
                if (i + entry.getKey() - 1 < maxGridSizeX) {
                    boolean good = true;
                    auxiliaryI += localDirectionX[1];
                    auxiliaryJ += localDirectionY[1];
                    for (int length = 0; length < entry.getKey() - 1; length++) {
                        if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == MISSEDHIT)
                            good = false;
                        auxiliaryI += localDirectionX[1];
                        auxiliaryJ += localDirectionY[1];

                    }
                    if (good) {
                        auxiliaryI = i;
                        auxiliaryJ = j;
                        for (int length = 0; length < entry.getKey() - 1; length++) {
                            auxiliaryI += localDirectionX[1];
                            auxiliaryJ += localDirectionY[1];
                            if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == NOHIT) {
                                densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                            }
                        }
                    }
                }


                auxiliaryI = i;
                auxiliaryJ = j;
                if (j + entry.getKey() - 1 < maxGridSizeY) {
                    boolean good = true;
                    auxiliaryI += localDirectionX[2];
                    auxiliaryJ += localDirectionY[2];
                    for (int length = 0; length < entry.getKey() - 1; length++) {
                        if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == MISSEDHIT)
                            good = false;
                        auxiliaryI += localDirectionX[2];
                        auxiliaryJ += localDirectionY[2];

                    }
                    if (good) {
                        auxiliaryI = i;
                        auxiliaryJ = j;
                        for (int length = 0; length < entry.getKey() - 1; length++) {
                            auxiliaryI += localDirectionX[2];
                            auxiliaryJ += localDirectionY[2];
                            if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == NOHIT) {
                                densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                            }
                        }
                    }
                }


                auxiliaryI = i;
                auxiliaryJ = j;
                if (i - entry.getKey() + 1 >= 0) {
                    boolean good = true;
                    auxiliaryI += localDirectionX[3];
                    auxiliaryJ += localDirectionY[3];
                    for (int length = 0; length < entry.getKey() - 1; length++) {
                        if (auxiliaryI < 0 || auxiliaryJ < 0)
                            System.out.println(entry.getKey() + " " + entry.getValue() + " " + i + " " + j + " " + auxiliaryI + " " + auxiliaryJ);
                        if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == MISSEDHIT)
                            good = false;
                        auxiliaryI += localDirectionX[3];
                        auxiliaryJ += localDirectionY[3];

                    }
                    if (good) {
                        auxiliaryI = i;
                        auxiliaryJ = j;
                        for (int length = 0; length < entry.getKey() - 1; length++) {
                            auxiliaryI += localDirectionX[3];
                            auxiliaryJ += localDirectionY[3];
                            if (playerBoardMatrix[auxiliaryI][auxiliaryJ] == NOHIT) {
                                densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                            }
                        }
                    }
                }
            }

        }
        /*
        System.out.println("Shoot hunt");
        for(var rowa : densityMatrix)
            System.out.println(Arrays.toString(rowa));
        System.out.println();
        for(var rowa : playerBoardMatrix)
            System.out.println(Arrays.toString(rowa));
        System.out.println();*/
        return densityMatrix;
    }

    /**
     * The function produces a positive integer value representing the the number of possible ships for each position on the grid, based on overall data
     *
     * @param playerBoardMatrix The matrix where for each position of the grid it is marked whether there was no hit or a missed hit or a correct hit
     * @return int[][] - the matrix representing the densities
     */
    private int[][] getProbabilities(int[][] playerBoardMatrix) {
        int[][] densityMatrix = new int[maxGridSizeX][maxGridSizeY];
        for (int[] row : densityMatrix)
            Arrays.fill(row, 0);
        Set<Map.Entry<Integer, Integer>> entrySet = dictionaryOfSizes.entrySet();
        for (int i = 0; i < maxGridSizeY; i++) {
            for (int j = 0; j < maxGridSizeY; j++) {
                for (Map.Entry<Integer, Integer> entry : entrySet) {
                    boolean goodOne = false;
                    boolean goodTwo = false;
                    for (int appearanceOfShipType = 0; appearanceOfShipType < entry.getValue(); appearanceOfShipType++) {
                        int auxiliaryI = i;
                        int auxiliaryJ = j;
                        if (j + entry.getKey() - 1 < maxGridSizeX) {
                            goodOne = true;
                            for (int length = 0; length < entry.getKey(); ++length) {
                                if (playerBoardMatrix[auxiliaryI][auxiliaryJ] != 0) {
                                    goodOne = false;
                                }
                                auxiliaryI += DirectionX[0];
                                auxiliaryJ += DirectionY[0];
                            }
                            if (goodOne) {
                                auxiliaryI = i;
                                auxiliaryJ = j;
                                for (int length = 0; length < entry.getKey() - 1; ++length) {
                                    auxiliaryI += DirectionX[0];
                                    auxiliaryJ += DirectionY[0];
                                    densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                                }
                            }
                        } else
                            goodOne = false;
                        if (goodOne)
                            densityMatrix[i][j] += 1;
                        auxiliaryI = i;
                        auxiliaryJ = j;

                        if (i + entry.getKey() - 1 < maxGridSizeY) {
                            goodTwo = true;
                            for (int length = 0; length < entry.getKey(); ++length) {
                                if (playerBoardMatrix[auxiliaryI][auxiliaryJ] != 0) {
                                    goodTwo = false;
                                }
                                auxiliaryI += DirectionX[1];
                                auxiliaryJ += DirectionY[1];
                            }
                            if (goodTwo) {
                                auxiliaryI = i;
                                auxiliaryJ = j;
                                for (int length = 0; length < entry.getKey() - 1; ++length) {
                                    auxiliaryI += DirectionX[1];
                                    auxiliaryJ += DirectionY[1];
                                    densityMatrix[auxiliaryI][auxiliaryJ] += 1;
                                }
                            }

                        } else
                            goodTwo = false;
                        if (goodTwo)
                            densityMatrix[i][j] += 1;
                    }
                }
            }
        }
        //System.out.println("Random");
        /*
        for(var rowa : densityMatrix)
            System.out.println(Arrays.toString(rowa));
        System.out.println();
        for(var rowa : playerBoardMatrix)
            System.out.println(Arrays.toString(rowa));
        System.out.println();*/
        return densityMatrix;
    }

    /**
     * This function handles the case when the AI made a correct hit so it tries to find the ship based on probability values
     *
     * @return boolean - true if the AI hit something, false if not
     */
    private boolean shootHuntProbability() {
        boolean succes = false;
        int[][] playerBoardMatrix = new int[maxGridSizeX][maxGridSizeY];
        for (int[] row : playerBoardMatrix)
            Arrays.fill(row, NOHIT);
        Random random = new Random(System.currentTimeMillis());

        this.setShootBoard(playerBoardMatrix);
        int[][] densityProbabilityMatrix = this.getProbabilitiesForHit(playerBoardMatrix);
        ArrayList<Point> listOfHighestProbabilities = this.chooseHighestPossibleList(densityProbabilityMatrix);
        int chosenPoint = random.nextInt(listOfHighestProbabilities.size());
        Point target = listOfHighestProbabilities.get(chosenPoint);
        if (this.playerShipRepository.checkIfHit(target.x, target.y)) {
            succes = true;
            try {
                playerShipRepository.decreaseShipHP(target.x, target.y);
            } catch (ShipException | RepositoryException ignored) {
            }
            if (this.playerShipRepository.checkIfKill(target.x, target.y)) {
                Ship lastDestroyedShip = playerShipRepository.LastDestroyedShip();
                this.dictionaryOfSizes.put(lastDestroyedShip.getLength(), this.dictionaryOfSizes.get(lastDestroyedShip.getLength()) - 1);
                lastDestroyedShip.setDestroyed(true);
                for (Point coord : lastDestroyedShip.generatePoints()) {
                    if (goodHits.contains(coord))
                        goodHits.removeIf(x -> x.equals(coord));
                }
            } else {
                goodHits.add(target);
            }
            try {
                computerHitRepository.add(new Hit(target.x, target.y, true));
            } catch (RepositoryException ignored) {
            }
        } else {
            try {
                computerHitRepository.add(new Hit(target.x, target.y, false));
            } catch (RepositoryException ignored) {
            }
        }
        return succes;
    }

    /**
     * The AI shoots randomly based on the probability values
     *
     * @return boolean - true if the AI hit something, false if not
     */
    private boolean shootRandom() {
        goodHits.clear();
        boolean success = false;
        int[][] playerBoardMatrix = new int[maxGridSizeX][maxGridSizeY];
        for (int[] row : playerBoardMatrix)
            Arrays.fill(row, NOHIT);

        this.setShootBoard(playerBoardMatrix);
        Random random = new Random(System.currentTimeMillis());
        int[][] densityProbabilityMatrix = this.getProbabilities(playerBoardMatrix);
        ArrayList<Point> listOfHighestProbabilities = this.chooseHighestPossibleList(densityProbabilityMatrix);

        int chosenPoint = random.nextInt(listOfHighestProbabilities.size());
        Point target = listOfHighestProbabilities.get(chosenPoint);

        if (this.playerShipRepository.checkIfHit(target.x, target.y)) {
            try {
                this.computerHitRepository.add(new Hit(target.x, target.y, true));
            } catch (RepositoryException ignored) {
                System.out.println("This shouldn't happen");
                ignored.printStackTrace();
            }
            try {
                playerShipRepository.decreaseShipHP(target.x, target.y);
            } catch (ShipException | RepositoryException ignored) {
            }
            this.acquireNewTargets(new Point(target));
            success = true;
        } else {
            try {
                this.computerHitRepository.add(new Hit(target.x, target.y, false));
            } catch (RepositoryException ignored) {
                System.out.println("This shouldn't happen");
                ignored.printStackTrace();
            }

        }
        listOfVisited.add(target);
        listofPossibleTargets.removeIf(x -> x.equals(target));
        return success;
    }

    /**
     * Finds new targets for the AI to hunt down, based on a correct hit
     *
     * @param centerTarget Point - the point where the AI made a correct hit
     */
    private void acquireNewTargets(Point centerTarget) {
        goodHits.add(centerTarget);
        int[] localDirectionX = {0, 1, 0, -1};
        int[] localDirectionY = {-1, 0, 1, 0};
        for (int i = 0; i < localDirectionX.length; i++) {
            if (centerTarget.x + localDirectionX[i] >= 0 &&
                    centerTarget.x + localDirectionX[i] < maxGridSizeX &&
                    centerTarget.y + localDirectionY[i] >= 0 &&
                    centerTarget.y + localDirectionY[i] < maxGridSizeY) {
                Point newTarget = new Point(centerTarget.x + localDirectionX[i], centerTarget.y + localDirectionY[i]);
                if (!listOfVisited.contains(newTarget)) {
                    listOfVisited.add(newTarget);
                    hitQueue.add(newTarget);
                }

            }
        }

    }

    /**
     * Based on the density matrix, the function chooses the points with the highest probability
     *
     * @param denstyProbablityMatrix int[][] - the matrix with the probabilities
     * @return ArrayList - of Points where each element is a possible target
     */
    private ArrayList<Point> chooseHighestPossibleList(int[][] denstyProbablityMatrix) {

        int highestChance = 0;
        ArrayList<Point> listofHighestPossibilities = new ArrayList<>();


        for (int[] row : denstyProbablityMatrix)
            for (int element : row)
                if (element > highestChance)
                    highestChance = element;

        for (int i = 0; i < denstyProbablityMatrix.length; i++)
            for (int j = 0; j < denstyProbablityMatrix[i].length; j++)
                if (denstyProbablityMatrix[i][j] == highestChance)
                    listofHighestPossibilities.add(new Point(i, j));

        return listofHighestPossibilities;

    }


    /**
     * Creates the shoot board for a matrix given as parameter, based on hit data, correct hits, missed hits, or no hits
     *
     * @param playerBoardMatrix int[][] - a matrix where for each element a value show the grid cell's state
     */
    private void setShootBoard(int[][] playerBoardMatrix) {
        for (Hit hit : computerHitRepository.getAllHits()) {
            if (hit.shipWasHit)
                playerBoardMatrix[hit.x][hit.y] = CORRECTHIT;
            else
                playerBoardMatrix[hit.x][hit.y] = MISSEDHIT;

        }

    }
}
