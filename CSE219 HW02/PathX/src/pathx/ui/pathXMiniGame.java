package pathx.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import pathx.ThePathX;
import pathx.ThePathX.pathXPropertyType;
import pathx.data.pathXDataModel;
import pathx.file.pathXFileManager;
import pathx.ui.pathXEventHandler;
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * import sorting_hat.data.SortingHatDataModel;
 *
 * import static sorting_hat.SortingHatConstants.*;
 *
 * import sorting_hat.SortingHatConstants; import
 * sorting_hat.TheSortingHat.SortingHatPropertyType; import
 * sorting_hat.file.SortingHatFileManager; import
 * sorting_hat.data.SortingHatRecord;
 *
 */
/**
 *
 * @author Wei
 */
public class pathXMiniGame extends MiniGame {

    private pathXErrorHandler errorHandler;
    private pathXEventHandler eventHandler;
    private pathXFileManager fileManager;
    private String currentScreenState;
    private pathXPanel panel;

    private String money;

    public pathXErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public pathXPanel getPanel() {
        return panel;
    }

    public pathXFileManager getFileManager() {
        return fileManager;
    }

    public boolean isCurrentScreenState(String testScreenState) {
        return testScreenState.equals(currentScreenState);
    }

    public void switchToHomeScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        guiButtons.get(HOME_BUTTON_TYPE).setX(HOMEBUTTON_x);
        guiButtons.get(HOME_BUTTON_TYPE).setY(CLOSEBUTTON_y);
        guiDecor.get(BACKGROUND_TYPE).setState(MENUE_SCREEN_STATE);
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiDecor.get(MAP_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(MAP_TYPE).setEnabled(false);
        guiDecor.get(DIALOGUE_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(DIALOGUE_TYPE).setEnabled(false);
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SETTING_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(SETTING_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(UP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(UP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DOWN_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(DOWN_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(false);
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String level : levels) {
            guiButtons.get(level).setState(pathXSpriteState.INVISIBLE_STATE.toString());
            guiButtons.get(level).setEnabled(false);
        }
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE3).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE3).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE2).setEnabled(false);
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(false);
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE2).setEnabled(false);

        currentScreenState = MENUE_SCREEN_STATE;

    }

    public void switchToGameScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        guiDecor.get(BACKGROUND_TYPE).setState(GAME_STATE);
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTING_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTING_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOME_BUTTON_TYPE).setX(23);
        guiButtons.get(HOME_BUTTON_TYPE).setY(96);
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiDecor.get(MAP_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(MAP_TYPE).setEnabled(false);
        guiDecor.get(DIALOGUE_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiDecor.get(DIALOGUE_TYPE).setEnabled(true);
        guiButtons.get(UP_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(UP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DOWN_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(DOWN_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEFT_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RIGHT_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(true);
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String level : levels) {
            guiButtons.get(level).setState(pathXSpriteState.INVISIBLE_STATE.toString());
            guiButtons.get(level).setEnabled(false);
        }
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE2).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE2).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE3).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE3).setEnabled(true);
        guiButtons.get(CHECK_BOX_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE2).setEnabled(false);
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE2).setEnabled(false);
        currentScreenState = GAME_STATE;
    }

    public void switchToSettingScreen() {
        guiButtons.get(HOME_BUTTON_TYPE).setX(HOMEBUTTON_x);
        guiButtons.get(HOME_BUTTON_TYPE).setY(CLOSEBUTTON_y);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        guiDecor.get(BACKGROUND_TYPE).setState(SETTING_SELECT_STATE);
        guiDecor.get(MAP_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(MAP_TYPE).setEnabled(false);
        guiDecor.get(DIALOGUE_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(DIALOGUE_TYPE).setEnabled(false);
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTING_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTING_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(DOWN_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(DOWN_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE).setEnabled(false);

        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        currentScreenState = LEVEL_SELECT_STATE;
        guiButtons.get(CHECK_BOX_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE2).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE).setEnabled(true);
        guiButtons.get(CHECK_BOX_TYPE2).setEnabled(true);
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(false);
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String level : levels) {
            guiButtons.get(level).setState(pathXSpriteState.INVISIBLE_STATE.toString());
            guiButtons.get(level).setEnabled(false);
        }
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(true);
        guiButtons.get(LEFT_BUTTON_TYPE2).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE2).setEnabled(true);
        guiButtons.get(RIGHT_BUTTON_TYPE2).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE2).setEnabled(true);

    }

    public void switchToLevelSelectScreen() {
        guiButtons.get(HOME_BUTTON_TYPE).setX(HOMEBUTTON_x);
        guiButtons.get(HOME_BUTTON_TYPE).setY(CLOSEBUTTON_y);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        guiDecor.get(BACKGROUND_TYPE).setState(LEVEL_SELECT_STATE);
        guiDecor.get(MAP_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTING_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTING_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(UP_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(UP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(DOWN_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(DOWN_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(LEFT_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(RIGHT_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(false);
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String level : levels) {
            guiButtons.get(level).setState(pathXSpriteState.VISIBLE_STATE.toString());
            guiButtons.get(level).setEnabled(true);
        }
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(false);

        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(CLOSE_BUTTON_TYPE3).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE3).setEnabled(false);
        guiDecor.get(DIALOGUE_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(DIALOGUE_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE2).setEnabled(false);
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE2).setEnabled(false);
        currentScreenState = LEVEL_SELECT_STATE;

    }

    public void switchToHelpScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        guiDecor.get(BACKGROUND_TYPE).setState(HELP_STATE);
        guiButtons.get(HOME_BUTTON_TYPE).setX(HOMEBUTTON_x);
        guiButtons.get(HOME_BUTTON_TYPE).setY(CLOSEBUTTON_y);
        guiButtons.get(PLAY_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SETTING_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(SETTING_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HELP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(HELP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(HOME_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(HOME_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(UP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(UP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(DOWN_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(DOWN_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE).setEnabled(false);

        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String level : levels) {
            guiButtons.get(level).setState(pathXSpriteState.INVISIBLE_STATE.toString());
            guiButtons.get(level).setEnabled(false);
        }

        guiButtons.get(CLOSE_BUTTON_TYPE).setState(pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(CLOSE_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CLOSE_BUTTON_TYPE2).setEnabled(false);
        guiDecor.get(DIALOGUE_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.get(DIALOGUE_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE).setEnabled(false);
        guiButtons.get(CHECK_BOX_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(CHECK_BOX_TYPE2).setEnabled(false);
        guiButtons.get(BAR_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(BAR_TYPE).setEnabled(false);
        guiButtons.get(LEFT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(LEFT_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(RIGHT_BUTTON_TYPE2).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(RIGHT_BUTTON_TYPE2).setEnabled(false);
        guiButtons.get(STOP_BUTTON_TYPE).setState(pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.get(STOP_BUTTON_TYPE).setEnabled(false);
        currentScreenState = HELP_STATE;

    }

    @Override
    public void initAudioContent() {
    }

    @Override
    public void initData() {
        errorHandler = new pathXErrorHandler(window);
        fileManager = new pathXFileManager(this);
        data = new pathXDataModel(this);
    }

    @Override
    public void initGUIControls() {
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(ThePathX.pathXPropertyType.PATH_IMG);
        String windowIconFile = props.getProperty(pathXPropertyType.IMAGE_WINDOW_ICON);
        img = loadImage(imgPath + windowIconFile);
        window.setIconImage(img);

        canvas = new pathXPanel(this, (pathXDataModel) data);

        currentScreenState = MENUE_SCREEN_STATE;
        sT = new SpriteType(BACKGROUND_TYPE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_MENUE));
        sT.addState(MENUE_SCREEN_STATE, img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_LEVELSELECT));
        sT.addState(LEVEL_SELECT_STATE, img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_SETTINGS));
        sT.addState(SETTING_SELECT_STATE, img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_HELP));
        sT.addState(HELP_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, MENUE_SCREEN_STATE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BACKGROUND_GAME));
        sT.addState(GAME_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, MENUE_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);

        //map component
        sT = new SpriteType(MAP_TYPE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_MAP));
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 8, 99, 0, 0, MAP_STATE);
        s.setEnabled(false);
        guiDecor.put(MAP_TYPE, s);

        //button
        String newButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_PLAY);
        sT = new SpriteType(PLAY_BUTTON_TYPE);
        img = loadImage(imgPath + newButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, PLAY_BUTTON_x + MENUEBUTTONOFFSETx, MENUEBUTTON_y + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.put(PLAY_BUTTON_TYPE, s);

        String ResetButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RESET);
        sT = new SpriteType(RESET_BUTTON_TYPE);
        img = loadImage(imgPath + ResetButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, RESET_BUTTON_x + MENUEBUTTONOFFSETx, MENUEBUTTON_y + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.put(RESET_BUTTON_TYPE, s);

        String SettingButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_SETTING);
        sT = new SpriteType(SETTING_BUTTON_TYPE);
        img = loadImage(imgPath + SettingButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, SETTING_BUTTON_x + MENUEBUTTONOFFSETx, MENUEBUTTON_y + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.put(SETTING_BUTTON_TYPE, s);

        String HelpButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HELP);
        sT = new SpriteType(HELP_BUTTON_TYPE);
        img = loadImage(imgPath + HelpButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, HELP_BUTTON_x + MENUEBUTTONOFFSETx, MENUEBUTTON_y + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.put(HELP_BUTTON_TYPE, s);

        String CloseButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE);
        sT = new SpriteType(CLOSE_BUTTON_TYPE);
        img = loadImage(imgPath + CloseButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, CLOSEBUTTON_x, CLOSEBUTTON_y, 0, 0, pathXSpriteState.VISIBLE_STATE.toString());
        guiButtons.put(CLOSE_BUTTON_TYPE, s);

        // new code to add
        sT = new SpriteType(DIALOGUE_TYPE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_DIALOGUE));
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 170, 150, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiDecor.put(DIALOGUE_TYPE, s);

        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BUTTON_CLOSE));
        sT = new SpriteType(CLOSE_BUTTON_TYPE3);
        img = loadImage(imgPath + CloseButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 330, 400, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(CLOSE_BUTTON_TYPE3, s);

        sT = new SpriteType(CLOSE_BUTTON_TYPE2);
        img = loadImage(imgPath + CloseButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 89, 96, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(CLOSE_BUTTON_TYPE2, s);

        String HomeButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_HOME);
        sT = new SpriteType(HOME_BUTTON_TYPE);
        img = loadImage(imgPath + HomeButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, HOMEBUTTON_x, CLOSEBUTTON_y, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(HOME_BUTTON_TYPE, s);

        String UpButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_UP);
        sT = new SpriteType(UP_BUTTON_TYPE);
        img = loadImage(imgPath + UpButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 41 + MENUEBUTTONOFFSETx, 381 + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(UP_BUTTON_TYPE, s);

        String DownButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_DOWN);
        sT = new SpriteType(DOWN_BUTTON_TYPE);
        img = loadImage(imgPath + DownButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 42 + MENUEBUTTONOFFSETx, 411 + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(DOWN_BUTTON_TYPE, s);

        String LeftButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_LEFT);
        sT = new SpriteType(LEFT_BUTTON_TYPE);
        img = loadImage(imgPath + LeftButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 15 + MENUEBUTTONOFFSETx, 394 + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(LEFT_BUTTON_TYPE, s);

        String RightButton = props.getProperty(pathXPropertyType.IMAGE_BUTTON_RIGHT);
        sT = new SpriteType(RIGHT_BUTTON_TYPE);
        img = loadImage(imgPath + RightButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 72 + MENUEBUTTONOFFSETx, 394 + MENUEBUTTONOFFSETy, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(RIGHT_BUTTON_TYPE, s);

        sT = new SpriteType(CHECK_BOX_TYPE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_CHECKED));
        sT.addState(pathXSpriteState.SELECTED_STATE.toString(), img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_CHECK));
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 195, 190, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        s.setEnabled(true);
        guiButtons.put(CHECK_BOX_TYPE, s);

        sT = new SpriteType(CHECK_BOX_TYPE2);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_CHECKED));
        sT.addState(pathXSpriteState.SELECTED_STATE.toString(), img);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_CHECK));
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 195, 240, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        s.setEnabled(true);
        guiButtons.put(CHECK_BOX_TYPE2, s);

        sT = new SpriteType(BAR_TYPE);
        img = loadImage(imgPath + props.getProperty(pathXPropertyType.IMAGE_BAR));
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 105, 330, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        s.setEnabled(true);
        guiButtons.put(BAR_TYPE, s);

        sT = new SpriteType(LEFT_BUTTON_TYPE2);
        img = loadImage(imgPath + LeftButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 105, 370, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        s.setEnabled(true);

        guiButtons.put(LEFT_BUTTON_TYPE2, s);
        sT = new SpriteType(RIGHT_BUTTON_TYPE2);
        img = loadImage(imgPath + RightButton);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        s = new Sprite(sT, 480, 370, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        s.setEnabled(true);
        guiButtons.put(RIGHT_BUTTON_TYPE2, s);

        String STOP = props.getProperty(pathXPropertyType.IMAGE_BUTTON_STOP);
        String START = props.getProperty(pathXPropertyType.IMAGE_BUTTON_START);
        sT = new SpriteType(STOP_BUTTON_TYPE);
        img = loadImage(imgPath + STOP);

        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
        img = loadImage(imgPath + START);
        sT.addState(pathXSpriteState.SELECTED_STATE.toString(), img);
        s = new Sprite(sT, 14, 157, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
        guiButtons.put(STOP_BUTTON_TYPE, s);

        int[] Levelx = {107, 766, 1003, 95, 80, 163, 265, 270, 340, 390, 387, 441, 466, 501, 521, 546, 544, 627, 642, 662};
        int[] Levely = {186, 301, 195, 272, 540, 388, 400, 511, 296, 408, 506, 175, 256, 329, 401, 476, 571, 291, 378, 471};
        String RedNode = props.getProperty(pathXPropertyType.IMAGE_NODE_RED);
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (int i = 0; i < levels.size(); i++) {
            sT = new SpriteType(NODE_TYPE);
            img = loadImage(imgPath + RedNode);
            sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), img);
            s = new Sprite(sT, Levelx[i]+11, Levely[i]+97, 0, 0, pathXSpriteState.INVISIBLE_STATE.toString());
            guiButtons.put(levels.get(i), s);
        }
    }

    @Override
    public void initGUIHandlers() {

        eventHandler = new pathXEventHandler(this);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        guiButtons.get(PLAY_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToPlayRequest();
            }
        });
        guiButtons.get(RESET_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToResetRequest();
            }
        });
        guiButtons.get(SETTING_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToSettingRequest();
            }
        });
        guiButtons.get(HELP_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToHelpRequest();
            }
        });
        guiButtons.get(CLOSE_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToCloseRequest();
            }
        });
        guiButtons.get(HOME_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToHomeRequest();
            }
        });
        guiButtons.get(HELP_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToHelpRequest();
            }
        });
        guiButtons.get(UP_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToUpRequest();
            }
        });
        guiButtons.get(DOWN_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToDownRequest();
            }
        });
        guiButtons.get(LEFT_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToLeftRequest();
            }
        });
        guiButtons.get(RIGHT_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToRightRequest();
            }
        });
        guiButtons.get(STOP_BUTTON_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToStopRequest();
            }
        });

        // initial guiButton handling
        ArrayList<String> levels = props.getPropertyOptionsList(pathXPropertyType.LEVEL_OPTIONS);
        for (String levelFile : levels) {
            Sprite levelButton = guiButtons.get(levelFile);
            levelButton.setActionCommand(PATH_DATA + levelFile);
            levelButton.setActionListener(new ActionListener() {
                Sprite s;

                public ActionListener init(Sprite initS) {
                    s = initS;
                    return this;
                }

                public void actionPerformed(ActionEvent ae) {
                    eventHandler.respondToSelectLevelRequest(s.getActionCommand());
                }
            }.init(levelButton));

        }

        guiButtons.get(CLOSE_BUTTON_TYPE2).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToPlayRequest();
            }
        });

        guiButtons.get(CLOSE_BUTTON_TYPE3).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToCloseDialogueRequest();
            }
        });
        guiButtons.get(CHECK_BOX_TYPE).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToCheckboxRequest();
            }
        });
        guiButtons.get(CHECK_BOX_TYPE2).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToCheckboxRequest2();
            }
        });
        guiButtons.get(LEFT_BUTTON_TYPE2).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToLeftBarRequest();
            }
        });
        guiButtons.get(RIGHT_BUTTON_TYPE2).setActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eventHandler.respondToRightBarRequest();
            }
        });
        this.setKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                eventHandler.respondToKeyPress(ke.getKeyCode());
            }
        });

    }

    @Override
    public void reset() {
        data.reset(this);
    }

    @Override
    public void updateGUI() {
        if (!((pathXDataModel) data).getstop()) {
            if (data.inProgress()) {
                if (((pathXDataModel) data).getSelectedRoad() != null) {
                    ((pathXDataModel) data).startMovingToTarget(((pathXDataModel) data).getplayer(), ((pathXDataModel) data).getSelectedRoad());

                }
                int index = 1;

                for (index = 1; index < 1 + ((pathXDataModel) data).getlevel().getNumPolice() + ((pathXDataModel) data).getlevel().getNumBandits() + ((pathXDataModel) data).getlevel().getNumZombies(); index++) {
                    pathXCars police = ((pathXDataModel) data).getcarstack().get(index);
                    ((pathXDataModel) data).findpathforpolice(police);

                    if (police.isMovingToTarget()) {
                        ((pathXDataModel) data).startMovingToTarget(police, police.getroad());
                    }
                }
            }
        }
    }

}
