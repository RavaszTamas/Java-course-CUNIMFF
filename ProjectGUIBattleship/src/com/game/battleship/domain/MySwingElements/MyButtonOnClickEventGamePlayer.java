package com.game.battleship.domain.MySwingElements;

import com.game.battleship.GUI.ClickableGridGUI;
import com.game.battleship.MyExceptions.ControllerException;
import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.controller.Controller.AppController;
import com.game.battleship.controller.aicontroller.AIController;

import java.awt.event.MouseEvent;

/**
 * The event handler for the grid of the ai during the active game state, it extends MyButtonOnClick
 *
 * @author Ravasz Tamás
 * @see MyButtonOnClick
 */
public class MyButtonOnClickEventGamePlayer extends MyButtonOnClick {
    private AppController controller;
    private AIController aiController;
    private ClickableGridGUI gui;
    int wasAHit = 0;
    boolean nextTurnAI = false;

    /**
     * Constructor for the event handler
     *
     * @param gui          ClickableGridGUI - The handler belongs to this gui
     * @param controller   AppController - the controller of the application
     * @param aiController AIController - the controller of the AI actions
     */
    public MyButtonOnClickEventGamePlayer(ClickableGridGUI gui, AppController controller, AIController aiController) {
        super();
        this.controller = controller;
        this.aiController = aiController;
        this.gui = gui;
    }

    /**
     * The click on a GridButton, if LEFT click then attack, otherwise nothing happens
     * After the attack if it is a correct hit the player takes one more turn, otherwise the ai also attacks in that turn
     * If the AI hits a ship then repeated clicks are needed to make it progress, until it doesn't hit a target, after that
     * the player's turn comes.
     * After each turn it is checked whether the AI or the Player had won.
     * In case of victory a case appropriate message is printed out and the start button is changed to the restart button
     *
     * @param e MouseEvent - the click event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!nextTurnAI)
                this.UIAttack(e);
            gui.AiMessageLabel.setText("");
            boolean checkIfWinPlayer = controller.checkIfPlayerWin();
            if (checkIfWinPlayer) {
                gui.setMessageLabel("Player has won!");
                gui.disableAllGridButtons();
                gui.switchStartRestart();
            } else if (wasAHit == 0) {
                if (aiController.aiTurn()) {
                    gui.UpdateAI("AI has hit a target");
                    this.setNextTurnAI();
                    boolean checkIfAIWin = controller.checkIfAIWin();
                    if (checkIfAIWin) {
                        gui.setMessageLabel("Computer has won!");
                        gui.disableAllGridButtons();
                        gui.switchStartRestart();
                    }
                } else {
                    gui.UpdateAI("AI has not hit a target");
                    this.setNextTurnNotAI();

                }

            }
        }
    }

    /**
     * Sets that the on next click the AI doesn't attacks
     */
    private void setNextTurnNotAI() {
        nextTurnAI = false;
    }

    /**
     * Sets that the on next click the AI attacks
     */
    private void setNextTurnAI() {
        nextTurnAI = true;
    }

    /**
     * The UI function which handles the player clicking on the grid
     *
     * @param e MouseEvent - the click event
     */
    private void UIAttack(MouseEvent e) {
        GridButton clicked = (GridButton) e.getSource();
        wasAHit = 0;
        String message = null;
        try {
            wasAHit = controller.playerAttack(clicked.getMyX(), clicked.getMyY());
        } catch (ValidatorException | RepositoryException | ControllerException ex) {
            message = ex.getMessage();
            wasAHit = -1;
        }
        if (wasAHit == -1) {
            gui.Update(message);
            return;
        }
        if (wasAHit == 2)
            gui.Update("A ship was sunk!");
        else if (wasAHit == 1)
            gui.Update("A ship was hit!");
        else if (wasAHit == 0)
            gui.Update("No hit!");
    }

    /**
     * Reset the event handler state, so that on first click the player attacks
     */
    @Override
    public void reset() {
        setNextTurnNotAI();
    }

}
