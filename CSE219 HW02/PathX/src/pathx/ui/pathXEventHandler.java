/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import mini_game.Sprite;
import mini_game.Viewport;
import pathx.ThePathX.pathXPropertyType;
import pathx.data.pathXDataModel;
import pathx.file.pathXFileManager;
import pathx.file.pathXlevelloadingmanager;
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Wei
 */
public class pathXEventHandler {

    private pathXMiniGame game;

    public pathXEventHandler(pathXMiniGame initGame) {
        game = initGame;
    }

    public void respondToExitRequest() {
        // IF THE GAME IS STILL GOING ON, END IT AS A LOSS
        if (game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }
        // AND CLOSE THE ALL
        System.exit(0);
    }

    public void respondToPlayRequest() {

        game.switchToLevelSelectScreen();

    }

    public void respondToResetRequest() {

    }

    public void respondToSelectLevelRequest(String levelFile) {
        if (game.isCurrentScreenState(LEVEL_SELECT_STATE)) {

            pathXDataModel data = (pathXDataModel) game.getDataModel();
            data.beginGame();
            data.newLevel();
            pathXFileManager fileManager = game.getFileManager();
            data.setlevel(fileManager.loadLevel(levelFile));
            data.initCar();
            data.reset(game);
            game.switchToGameScreen();
        }
    }
    public void respondToStopRequest(){
         pathXDataModel data = (pathXDataModel) game.getDataModel();
         Sprite s = game.getGUIButtons().get(STOP_BUTTON_TYPE);
         if(s.getState().equals(pathXSpriteState.VISIBLE_STATE.toString())){
             s.setState(pathXSpriteState.SELECTED_STATE.toString());
         }
         else{
             s.setState(pathXSpriteState.VISIBLE_STATE.toString());
         }
         data.Stop();
    }

    public void respondToSettingRequest() {
        game.switchToSettingScreen();
        if (game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }
    }

    public void respondToHomeRequest() {
        game.switchToHomeScreen();
        if (game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }

    }

    public void respondToGameRequest() {
        game.switchToGameScreen();
        if (game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }
    }

    public void respondToCloseRequest() {
        if (game.getDataModel().inProgress()) {
            game.getDataModel().endGameAsLoss();
        }
        // AND CLOSE THE ALL
        System.exit(0);
    }

    public void respondToHelpRequest() {
        game.switchToHelpScreen();
    }

    public void respondToUpRequest() {
        pathXDataModel data = (pathXDataModel) game.getDataModel();
        if (data.getViewport().getViewportY() > 0 && ((pathXMiniGame) game).isCurrentScreenState(LEVEL_SELECT_STATE)) {
            Collection<Sprite> buttonSprites = game.getGUIButtons().values();
            for (Sprite s : buttonSprites) {
                if (s.getSpriteType().getSpriteTypeID() == NODE_TYPE) {
                    if (s.getX() < 9 || s.getX() > 595 || s.getY() > 437 || s.getY() < 98) {
                        s.setState(pathXSpriteState.INVISIBLE_STATE.toString());
                        s.setEnabled(false);
                    } else if (s.getX() >= 9 || s.getX() <= 595 || s.getY() <= 437 || s.getY() >= 98) {
                        s.setState(pathXSpriteState.VISIBLE_STATE.toString());
                        s.setEnabled(true);
                    }
                    s.setY(s.getY() + VIEWPORT_INC);

                }
            }
            data.getViewport().scroll(0, -VIEWPORT_INC);
        }
        if (data.getLevelviewport().getViewportY() > 0 && ((pathXMiniGame) game).isCurrentScreenState(GAME_STATE)) {
            data.getLevelviewport().scroll(0, -VIEWPORT_INC);
            for (int i = 0; i < data.getcarstack().size(); i++) {
                data.getcarstack().get(i).setY(((int) data.getcarstack().get(i).getY()) + VIEWPORT_INC);
            }
        }

    }

    public void respondToDownRequest() {
        pathXDataModel data = (pathXDataModel) game.getDataModel();
        if (data.getViewport().getViewportY() < 362 && ((pathXMiniGame) game).isCurrentScreenState(LEVEL_SELECT_STATE)) {
            Collection<Sprite> buttonSprites = game.getGUIButtons().values();

            for (Sprite s : buttonSprites) {
                if (s.getSpriteType().getSpriteTypeID() == NODE_TYPE) {
                    if (s.getX() < 9 || s.getX() > 595 || s.getY() > 437 || s.getY() < 98) {
                        s.setState(pathXSpriteState.INVISIBLE_STATE.toString());
                        s.setEnabled(false);
                    } else if (s.getX() >= 9 || s.getX() <= 595 || s.getY() <= 437 || s.getY() >= 98) {
                        s.setState(pathXSpriteState.VISIBLE_STATE.toString());
                        s.setEnabled(true);
                    }
                    s.setY(s.getY() - VIEWPORT_INC);
                }
            }
            data.getViewport().scroll(0, VIEWPORT_INC);
        }
        if (data.getLevelviewport().getViewportY() < 186 && ((pathXMiniGame) game).isCurrentScreenState(GAME_STATE)) {
            data.getLevelviewport().scroll(0, VIEWPORT_INC);
            for (int i = 0; i < data.getcarstack().size(); i++) {
                data.getcarstack().get(i).setY( data.getcarstack().get(i).getY() - VIEWPORT_INC);
            }
        }
    }

    public void respondToLeftRequest() {
        pathXDataModel data = (pathXDataModel) game.getDataModel();

        if (data.getViewport().getViewportX() > 0 && ((pathXMiniGame) game).isCurrentScreenState(LEVEL_SELECT_STATE)) {
            Collection<Sprite> buttonSprites = game.getGUIButtons().values();
            for (Sprite s : buttonSprites) {
                if (s.getSpriteType().getSpriteTypeID() == NODE_TYPE) {
                    s.setX(s.getX() + VIEWPORT_INC);
                    if (s.getX() < 8 || s.getX() > 595 || s.getY() > 437 || s.getY() < 98) {
                        s.setState(pathXSpriteState.INVISIBLE_STATE.toString());
                        s.setEnabled(false);
                    } else if (s.getX() >= 9 || s.getX() <= 595 || s.getY() <= 437 || s.getY() >= 98) {
                        s.setState(pathXSpriteState.VISIBLE_STATE.toString());
                        s.setEnabled(true);
                    }

                }
            }
            data.getViewport().scroll(-VIEWPORT_INC, 0);

        }
        if (data.getLevelviewport().getViewportX() > 0 && ((pathXMiniGame) game).isCurrentScreenState(GAME_STATE)) {
            data.getLevelviewport().scroll(-VIEWPORT_INC, 0);
            for (int i = 0; i < data.getcarstack().size(); i++) {
                data.getcarstack().get(i).setX( data.getcarstack().get(i).getX() + VIEWPORT_INC);
            }
        }
    }

    public void respondToRightRequest() {

        pathXDataModel data = (pathXDataModel) game.getDataModel();

        if (data.getViewport().getViewportX() < 422 && ((pathXMiniGame) game).isCurrentScreenState(LEVEL_SELECT_STATE)) {
            Collection<Sprite> buttonSprites = game.getGUIButtons().values();
            for (Sprite s : buttonSprites) {
                if (s.getSpriteType().getSpriteTypeID() == NODE_TYPE) {
                    s.setX(s.getX() - VIEWPORT_INC);
                    if (s.getX() < 8 || s.getX() > 595 || s.getY() > 437 || s.getY() < 98) {
                        s.setState(pathXSpriteState.INVISIBLE_STATE.toString());
                        s.setEnabled(false);
                    } else if (s.getX() >= 9 || s.getX() <= 595 || s.getY() <= 437 || s.getY() >= 98) {
                        s.setState(pathXSpriteState.VISIBLE_STATE.toString());
                        s.setEnabled(true);
                    }

                }
            }
            data.getViewport().scroll(VIEWPORT_INC, 0);
        }
        if (data.getLevelviewport().getViewportX() < 610 && ((pathXMiniGame) game).isCurrentScreenState(GAME_STATE)) {
            data.getLevelviewport().scroll(VIEWPORT_INC, 0);
            for (int i = 0; i < data.getcarstack().size(); i++) {
                data.getcarstack().get(i).setX(((int) data.getcarstack().get(i).getX()) - VIEWPORT_INC);
            }
        }

    }

    public void respondToCheckboxRequest() {
        if (game.getGUIButtons().get(CHECK_BOX_TYPE).getState().equals(pathXSpriteState.VISIBLE_STATE.toString())) {
            game.getGUIButtons().get(CHECK_BOX_TYPE).setState(pathXSpriteState.SELECTED_STATE.toString());
        } else {
            game.getGUIButtons().get(CHECK_BOX_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        }
    }

    public void respondToCheckboxRequest2() {
        if (game.getGUIButtons().get(CHECK_BOX_TYPE2).getState().equals(pathXSpriteState.VISIBLE_STATE.toString())) {
            game.getGUIButtons().get(CHECK_BOX_TYPE2).setState(pathXSpriteState.SELECTED_STATE.toString());
        } else {
            game.getGUIButtons().get(CHECK_BOX_TYPE2).setState(pathXSpriteState.VISIBLE_STATE.toString());
        }
    }

    public void respondToCloseDialogueRequest() {
        game.getGUIDecor().get(DIALOGUE_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        game.getGUIButtons().get(CLOSE_BUTTON_TYPE3).setState(pathXSpriteState.INVISIBLE_STATE.toString());

    }

    public void respondToLeftBarRequest() {
        if (game.getGUIButtons().get(BAR_TYPE).getX() > 105) {
            game.getGUIButtons().get(BAR_TYPE).setX(game.getGUIButtons().get(BAR_TYPE).getX() - VIEWPORT_INC);
        }
    }

    public void respondToRightBarRequest() {
        if (game.getGUIButtons().get(BAR_TYPE).getX() < 495) {
            game.getGUIButtons().get(BAR_TYPE).setX(game.getGUIButtons().get(BAR_TYPE).getX() + VIEWPORT_INC);
        }
    }

    public void respondToKeyPress(int keyCode) {
        pathXDataModel data = (pathXDataModel) game.getDataModel();
        Viewport vp = data.getViewport();
        if (keyCode == KeyEvent.VK_UP) {
            respondToUpRequest();

        } else if (keyCode == KeyEvent.VK_LEFT) {
            respondToLeftRequest();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            respondToDownRequest();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            respondToRightRequest();

        }
    }
}
