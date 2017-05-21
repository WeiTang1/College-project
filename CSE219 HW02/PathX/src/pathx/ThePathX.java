/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import pathx.file.pathXlevelloadingmanager;
import static pathx.pathXConstants.*;
import pathx.ui.pathXMiniGame;

import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;
import xml_utilities.XMLUtilities;

/**
 *
 * @author Wei
 */
public class ThePathX {

    static pathXMiniGame miniGame = new pathXMiniGame();

    public static void main(String[] args) {

        try {

            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            String appTitle = props.getProperty(pathXPropertyType.TEXT_TITLE_BAR_GAME);
            String gameFlavorFile = props.getProperty(pathXPropertyType.FILE_GAME_PROPERTIES);
            props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);

            miniGame.initMiniGame(appTitle, FPS, WINDOW_WIDTH-6, WINDOW_HEIGHT-11);
            miniGame.startGame();

        } catch (InvalidXMLFileFormatException ixmlffe) {
        }

    }

    public enum pathXPropertyType {

        TEXT_TITLE_BAR_GAME,
        FILE_GAME_PROPERTIES,
        PATH_IMG,
        IMAGE_BACKGROUND_LEVELSELECT,
        IMAGE_BACKGROUND_MENUE,
        IMAGE_BACKGROUND_SETTINGS,
        IMAGE_BUTTON_PLAY,
        IMAGE_BUTTON_RESET,
        IMAGE_BUTTON_SETTING,
        IMAGE_BUTTON_HELP,
        IMAGE_WINDOW_ICON,
        IMAGE_BUTTON_STOP,
        IMAGE_BUTTON_START,
        TEXT_TITLE_BAR_ERROR,
        IMAGE_BUTTON_CLOSE,
        IMAGE_BUTTON_HOME,
        IMAGE_BACKGROUND_HELP,
        IMAGE_MAP,
        IMAGE_BUTTON_UP,
        IMAGE_BUTTON_DOWN,
        IMAGE_BUTTON_LEFT,
        IMAGE_BUTTON_RIGHT,
        IMAGE_NODE_WHITE,
        IMAGE_NODE_GREEN,
        IMAGE_NODE_RED,
        IMAGE_BACKGROUND_GAME,
        
        IMAGE_PLAYER,
        IMAGE_ZOMBIE,
        IMAGE_BANDIT,
        IMAGE_POLICE,
        // new code to add
        IMAGE_DIALOGUE,
        IMAGE_CHECKED,
        IMAGE_CHECK,
        IMAGE_BAR,
        LEVEL_OPTIONS,
        TEXT_ERROR_LOADING_LEVEL,
    }
}
