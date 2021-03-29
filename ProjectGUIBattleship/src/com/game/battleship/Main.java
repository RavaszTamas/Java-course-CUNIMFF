package com.game.battleship;

import com.game.battleship.GUI.ClickableGridGUI;
import com.game.battleship.controller.Controller.AppController;
import com.game.battleship.controller.aicontroller.AIController;
import com.game.battleship.domain.HitRepository.HitRepository;
import com.game.battleship.controller.Validators.HitValidator;
import com.game.battleship.domain.ShipRepository.ShipRepository;
import com.game.battleship.Tests.ShipRepoTest;
import com.game.battleship.Tests.ShipTest;
import com.game.battleship.controller.Validators.ShipValidator;
import com.game.battleship.Tests.ShipValidatorTest;

/**
 * Battleship program
 *
 * @author Ravasz Tamás
 * @version 12.0.2
 */
public class Main {

    private static final int STANDARDSIZEX = 10;
    private static final int STANDARDSIZEY = 10;

    public static void runTests() {
        //ShipTest.testEquals();
        //ShipRepoTest.testAddShip();
        //ShipValidatorTest.validateShipTest();
        ShipRepository playerShipRepository = new ShipRepository();
        ShipRepository AIShipRepository = new ShipRepository();
        HitRepository playerHitRepository = new HitRepository();
        HitRepository AIHitRepository = new HitRepository();
        ShipValidator shipValidator = new ShipValidator(STANDARDSIZEX, STANDARDSIZEY);
        HitValidator hitValidator = new HitValidator(STANDARDSIZEX, STANDARDSIZEY);
        AIController aiController = new AIController(STANDARDSIZEX, STANDARDSIZEY, playerHitRepository, AIHitRepository, playerShipRepository, AIShipRepository, shipValidator);
        AppController controller = new AppController(playerHitRepository, AIHitRepository, playerShipRepository, AIShipRepository, shipValidator, hitValidator);
        ClickableGridGUI gui = new ClickableGridGUI(STANDARDSIZEX, STANDARDSIZEY, controller, aiController);
    }

    public static void main(String[] args) {
        runTests();
    }
}
