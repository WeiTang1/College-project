/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import pathx.ThePathX;
import pathx.data.pathXDataModel;
import pathx.data.pathXLevel;
import static pathx.pathXConstants.*;
import pathx.ui.pathXCars;
import pathx.ui.pathXIntersections;
import pathx.ui.pathXMiniGame;
import pathx.ui.pathXroad;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Wei
 */
public class pathXFileManager {

    private pathXMiniGame miniGame;

    public pathXFileManager(pathXMiniGame initMiniGame) {
        miniGame = initMiniGame;
    }

    public pathXLevel loadLevel(String levelFiles) {
        File levelFile = new File(levelFiles);
        pathXLevel levelToLoad = new pathXLevel();

        try {

            // WE'LL FILL IN SOME OF THE LEVEL OURSELVES
            levelToLoad.reset();

            // LET'S USE A FAST LOADING TECHNIQUE. WE'LL LOAD ALL OF THE
            // BYTES AT ONCE INTO A BYTE ARRAY, AND THEN PICK THAT APART.
            // THIS IS FAST BECAUSE IT ONLY HAS TO DO FILE READING ONCE
            byte[] bytes = new byte[Long.valueOf(levelFile.length()).intValue()];
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            FileInputStream fis = new FileInputStream(levelFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // HERE IT IS, THE ONLY READY REQUEST WE NEED
            bis.read(bytes);
            bis.close();
            
            // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
            DataInputStream dis = new DataInputStream(bais);

            // NOTE THAT WE NEED TO LOAD THE DATA IN THE SAME
            // ORDER AND FORMAT AS WE SAVED IT
            // FIRST READ THE ALGORITHM NAME TO USE FOR THE LEVEL
            String levelName = dis.readUTF();
            levelToLoad.setLevelName(levelName);

            // THEN GET THE BACKGROUND IMAGE NAME
            String bgImageName = dis.readUTF();
            levelToLoad.setBackgroundImageFileName(bgImageName);

            // THEN LET'S LOAD THE LIST OF ALL THE INTERSECTIONS
            loadIntersectionsList(dis, levelToLoad);
            ArrayList<pathXIntersections> intersections = levelToLoad.getIntersections();

            // AND NOW CONNECT ALL THE REGIONS TO EACH OTHER
            loadRoadsList(dis, levelToLoad);

            // LOAD THE START INTERSECTION
            int startId = dis.readInt();
            String startImageName = dis.readUTF();
            pathXIntersections startingIntersection = intersections.get(startId);
            levelToLoad.setStartingLocation(startingIntersection);
            levelToLoad.setStartingLocationImageFileName(startImageName);

            // LOAD THE DESTINATION
            int destId = dis.readInt();
            String destImageName = dis.readUTF();
            levelToLoad.setDestination(intersections.get(destId));
            levelToLoad.setDestinationImageFileName(destImageName);

            // LOAD THE MONEY
            int money = dis.readInt();
            levelToLoad.setMoney(money);

            // LOAD THE NUMBER OF POLICE
            int numPolice = dis.readInt();
            levelToLoad.setNumPolice(numPolice);

            // LOAD THE NUMBER OF BANDITS
            int numBandits = dis.readInt();
            levelToLoad.setNumBandits(numBandits);

            // LOAD THE NUMBER OF ZOMBIES
            int numZombies = dis.readInt();
            levelToLoad.setNumZombies(numZombies);

        } catch (IOException e) {
            // LEVEL DIDN'T LOAD PROPERLY
        }
        // LEVEL LOADED PROPERLY
        return levelToLoad;
    }

    private void loadIntersectionsList(DataInputStream dis, pathXLevel levelToLoad)
            throws IOException {
        // GET THE NUMBER OF INTERSECTIONS
        int numIntersections = dis.readInt();

        // FIRST GET THE INTERSECTIONS LIST
        ArrayList<pathXIntersections> intersections = levelToLoad.getIntersections();

        // AND THEN GO THROUGH AND ADD ALL THE LISTED REGIONS
        for (int i = 0; i < numIntersections; i++) {
            // GET THEIR DATA FROM THE DOC
            int x = dis.readInt();
            int y = dis.readInt();
            boolean isOpen = dis.readBoolean();

            // NOW MAKE AND ADD THE INTERSECTION
            pathXIntersections newIntersection = new pathXIntersections(x, y);
            newIntersection.open = isOpen;
            intersections.add(newIntersection);
        }
    }

    private void loadRoadsList(DataInputStream dis, pathXLevel levelToLoad)
            throws IOException {
        // FIRST GET THE ROADS AND INTERSECTIONS LISTS
        ArrayList<pathXroad> roads = levelToLoad.getRoads();
        ArrayList<pathXIntersections> intersections = levelToLoad.getIntersections();

        // THEN GET THE NUMBER OF ROADS
        int numRoads = dis.readInt();

        // AND THEN GO THROUGH AND ADD ALL THE LISTED ROADS
        for (int i = 0; i < numRoads; i++) {
            // GET THEIR DATA FROM THE DOC
            int int_id1 = dis.readInt();
            int int_id2 = dis.readInt();
            boolean oneWay = dis.readBoolean();
            int speedLimit = dis.readInt();

            // NOW MAKE AND ADD THE ROAD
            pathXroad newRoad = new pathXroad();
            newRoad.setNode1(intersections.get(int_id1));
            newRoad.setNode2(intersections.get(int_id2));
            newRoad.setOneWay(oneWay);
            newRoad.setSpeedLimit(speedLimit);
            roads.add(newRoad);
        }
    }

}
