/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

import pathx.ThePathX.pathXPropertyType;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import properties_manager.PropertiesManager;

/**
 *
 * @author Wei
 */
public class pathXErrorHandler {

    private JFrame window;

    public pathXErrorHandler(JFrame initWindow) {
        window = initWindow;

    }

    public void processError(pathXPropertyType errorType) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String errorFeedbackText = props.getProperty(errorType);

        String errorTitle = props.getProperty(pathXPropertyType.TEXT_TITLE_BAR_ERROR);
        JOptionPane.showMessageDialog(window, errorFeedbackText, errorTitle, JOptionPane.ERROR_MESSAGE);

    }
}
