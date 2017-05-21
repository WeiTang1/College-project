package pathx.data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wei
 */
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Sprite;
import mini_game.SpriteType;
import mini_game.Viewport;
import pathx.ui.pathXMiniGame;
import static pathx.pathXConstants.*;
import mini_game.Viewport;
import pathx.ThePathX;
import pathx.ThePathX.pathXPropertyType;
import pathx.ui.pathXCars;
import pathx.ui.pathXIntersections;
import pathx.ui.pathXSpriteState;
import pathx.ui.pathXroad;
import properties_manager.PropertiesManager;

public class pathXDataModel extends MiniGameDataModel {

    private MiniGame miniGame;
    private String currentLevel;
    private Viewport Levelviewport;
    private pathXLevel level;
    private pathXCars player;
    pathXIntersections selectedIntersection;
    pathXroad selectedRoad;
    EditMode editMode;
    int lastMouseX;
    int lastMouseY;
    private ArrayList<pathXCars> carstack;
    public ArrayList<pathXroad> roads;
    private boolean stop;

    // WE'LL USE THIS WHEN WE'RE ADDING A NEW ROAD
    pathXIntersections startRoadIntersection;

    public pathXDataModel(MiniGame initMiniGame) {

        miniGame = initMiniGame;

        viewport = new Viewport();
        viewport.setGameWorldSize(GAMEWORLDWIDTH, GAMEWORLDWIDTH);
        viewport.setNorthPanelHeight(91);
        viewport.setScreenSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        viewport.setViewportSize(VIEWPORTWIDTH, VIEWPORTHEIGHT);
        viewport.updateViewportBoundaries();
        Levelviewport = new Viewport();
        Levelviewport.setScreenSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Levelviewport.setGameWorldSize(LEVELGAMEWORLDWIDTH, LEVELGAMEWORLDHEIGHT);
        Levelviewport.setViewportSize(LEVELVIEWPORTWIDTH, LEVELVIEWPORTHEIGHT);
        level = new pathXLevel();
        carstack = new ArrayList();
        roads = new ArrayList();
        stop = false;
    }

    public void newLevel() {
        Levelviewport = new Viewport();
        Levelviewport = new Viewport();
        Levelviewport.setScreenSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Levelviewport.setGameWorldSize(LEVELGAMEWORLDWIDTH, LEVELGAMEWORLDHEIGHT);
        Levelviewport.setViewportSize(LEVELVIEWPORTWIDTH, LEVELVIEWPORTHEIGHT);
        carstack = new ArrayList();
        roads = new ArrayList();
        stop = false;
        unselectEverything();
    }

    public void setPlayer(pathXCars car) {
        player = car;
    }

    public void Stop() {
        stop = !stop;
    }

    public boolean getstop() {
        return stop;
    }

    public pathXIntersections getStartingLocation() {
        return level.startingLocation;
    }

    public ArrayList<pathXCars> getcarstack() {
        return carstack;
    }

    public pathXIntersections getDestination() {
        return level.destination;
    }

    public pathXroad getSelectedRoad() {
        return selectedRoad;
    }

    public EditMode getEditMode() {
        return editMode;
    }

    public Iterator intersectionsIterator() {
        ArrayList<pathXIntersections> intersections = level.getIntersections();
        return intersections.iterator();
    }

    public Iterator roadsIterator() {
        ArrayList<pathXroad> roads = level.roads;
        return roads.iterator();
    }

    public pathXIntersections getStartRoadIntersection() {
        return startRoadIntersection;
    }

    public boolean isRectInsideViewport(int x1, int y1, int x2, int y2) {

        if (x1 < 0) {
            return false;
        }

        if (y1 < 11) {
            return false;
        }
        if (x2 > 496) {
            return false;
        }
        if (y2 > 468) {
            return false;
        }
        return true;

    }

    public boolean isCircleBoundingBoxInsideViewport(int centerX, int centerY, int radius) {
        return isRectInsideViewport(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    public boolean isSelectedIntersection(pathXIntersections testIntersection) {
        return testIntersection == selectedIntersection;
    }

    public boolean isSelectedRoad(pathXroad testRoad) {
        return testRoad == selectedRoad;
    }

    public boolean isDestination(pathXIntersections testInt) {
        return testInt == level.destination;
    }

    public boolean isStartingLocation(pathXIntersections testInt) {
        return testInt == level.startingLocation;
    }

    public pathXLevel getlevel() {
        return level;
    }

    public void setlevel(pathXLevel levels) {
        level = levels;
    }

    public Viewport getLevelviewport() {
        return Levelviewport;
    }

    public pathXCars getplayer() {
        return player;
    }

    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) {
        x = x + this.getLevelviewport().getViewportX();
        y = y + this.getLevelviewport().getViewportY();
        for (int i = 0; i < this.getlevel().getIntersections().size(); i++) {
            if (x > this.getlevel().getIntersections().get(i).getX() + 117
                    && y > this.getlevel().getIntersections().get(i).getY() - 20
                    && x < this.getlevel().getIntersections().get(i).getX() + 157
                    && y < this.getlevel().getIntersections().get(i).getY() + 20) {
                selectedIntersection = this.getlevel().getIntersections().get(i);
            }

        }
        if (selectedIntersection != null) {
            pathXroad road = null;
            for (int i = 0; i < this.getlevel().getRoads().size(); i++) {

                boolean bo1 = this.getcarstack().get(0).getcurrentIntersection().equals(this.getlevel().getRoads().get(i).getNode1())
                        || this.getcarstack().get(0).getcurrentIntersection().equals(this.getlevel().getRoads().get(i).getNode2());
                boolean bo2 = selectedIntersection.equals(this.getlevel().getRoads().get(i).getNode1())
                        || selectedIntersection.equals(this.getlevel().getRoads().get(i).getNode2());

                if (bo1 && bo2) {

                    if (this.getlevel().getRoads().get(i).isOneWay()) {
                        if (this.getlevel().getRoads().get(i).getNode1().equals(this.getcarstack().get(0).getcurrentIntersection())) {
                            selectedRoad = this.getlevel().getRoads().get(i);
                        } else {
                            selectedRoad = null;
                        }

                    } else {
                        selectedRoad = this.getlevel().getRoads().get(i);
                    }
                }

            }
            this.getcarstack().get(0).setmovingToTarget(true);
            this.getcarstack().get(0).settargetIntersection(selectedIntersection);
//          
        }

    }

    public void startMovingToTarget(pathXCars car, pathXroad road) {
        // LET ITS POSITIONG GET UPDATED
        car.setmovingToTarget(true);
        float targetX = car.gettargetIntersection().getX();
        float targetY = car.gettargetIntersection().getY();

        // CALCULATE THE ANGLE OF THE TRAJECTORY TO THE TARGET
        float diffX = targetX - car.getcurrentIntersection().getX();
        float diffY = targetY - car.getcurrentIntersection().getY();
        double smallx = diffX * road.getSpeedLimit() / 1000.0;
        double smally = diffY * road.getSpeedLimit() / 1000.0;

        float disX = targetX - car.getX() - Levelviewport.getViewportX();
        float disY = targetY - car.getY() - Levelviewport.getViewportY();

        double realdistance = Math.sqrt((double) ((disX * disX) + (disY * disY)));

        if (realdistance < 10 || realdistance < 20) {
            car.setX(car.gettargetIntersection().getX() - Levelviewport.getViewportX());
            car.setY(car.gettargetIntersection().getY() - Levelviewport.getViewportY());
            car.setcurrentIntersection(car.gettargetIntersection());
            car.setmovingToTarget(false);
            if (car == player) {
                unselectEverything();
            } else {
                car.setroad(null);
            }

        } else {
            double x = (double) car.getX() + smallx;
            car.setX((float) x);
            double y = (double) car.getY() + smally;
            car.setY((float) y);
        }
    }

    public void findpathforpolice(pathXCars car) {
        if (!car.isMovingToTarget()) {
            int indexofcurrentintersection = level.getIntersections().indexOf(car.getcurrentIntersection());
            int indexofIntersection = -1;

            pathXIntersections target = null;

            while (car.getroad() == null) {
                do {
                    indexofIntersection = (int) (Math.random() * level.getIntersections().size());

                } while (indexofcurrentintersection == indexofIntersection);

                target = level.getIntersections().get(indexofIntersection);
                car.settargetIntersection(target);
                //find the road
                for (int i = 0; i < level.getRoads().size(); i++) {
                    boolean bo1 = level.getRoads().get(i).getNode1().equals(car.getcurrentIntersection())
                            || level.getRoads().get(i).getNode2().equals(car.getcurrentIntersection());
                    boolean bo2 = level.getRoads().get(i).getNode2().equals(target) || level.getRoads().get(i).getNode1().equals(target);

                    if (bo1 && bo2) {
                        car.setroad(level.getRoads().get(i));
                    }
                }
            }
            car.setmovingToTarget(true);
        }
    }

    @Override
    public void reset(MiniGame game) {
    }

    @Override
    public void updateAll(MiniGame game) {
    }

    @Override
    public void updateDebugText(MiniGame game) {
    }

    public void setCurrentLevel(String initCurrentLevel) {
        currentLevel = initCurrentLevel;
    }

    public boolean isNothingSelected() {
        return editMode == EditMode.NOTHING_SELECTED;
    }

    public boolean isIntersectionSelected() {
        return editMode == EditMode.INTERSECTION_SELECTED;
    }

    public boolean isIntersectionDragged() {
        return editMode == EditMode.INTERSECTION_DRAGGED;
    }

    public boolean isRoadSelected() {
        return editMode == EditMode.ROAD_SELECTED;
    }

    public boolean isAddingIntersection() {
        return editMode == EditMode.ADDING_INTERSECTION;
    }

    public boolean isAddingRoadStart() {
        return editMode == EditMode.ADDING_ROAD_START;
    }

    public double calculateDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        double diffXSquared = Math.pow(x1 - x2, 2);
        double diffYSquared = Math.pow(y1 - y2, 2);
        return Math.sqrt(diffXSquared + diffYSquared);
    }

    public pathXIntersections findIntersectionAtCanvasLocation(int canvasX, int canvasY) {
        // CHECK TO SEE IF THE USER IS SELECTING AN INTERSECTION
        for (pathXIntersections i : level.intersections) {
            double distance = calculateDistanceBetweenPoints(i.x, i.y, canvasX + Levelviewport.getViewportX(), canvasY + Levelviewport.getViewportY());
            if (distance < INTERSECTION_RADIUS) {
                // MAKE THIS THE SELECTED INTERSECTION
                return i;
            }
        }
        return null;
    }

    // init cars
    public void initCar() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(ThePathX.pathXPropertyType.PATH_IMG);
        SpriteType sT;
        String playerImageFileName = imgPath + props.getProperty(pathXPropertyType.IMAGE_PLAYER);
        BufferedImage playerImage = miniGame.loadImage(playerImageFileName);
        String zombieImageFileName = imgPath + props.getProperty(pathXPropertyType.IMAGE_ZOMBIE);
        BufferedImage zombieImage = miniGame.loadImage(zombieImageFileName);
        String banditImageFileName = imgPath + props.getProperty(pathXPropertyType.IMAGE_BANDIT);
        BufferedImage banditImage = miniGame.loadImage(banditImageFileName);
        String policeImageFileName = imgPath + props.getProperty(pathXPropertyType.IMAGE_POLICE);
        BufferedImage policeImage = miniGame.loadImage(policeImageFileName);
        pathXCars newCar;
        sT = new SpriteType(PLAYER_CAR_SPRITE_TYPE);
        addSpriteType(sT);
        sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), playerImage);
        player = new pathXCars(sT, level.getStartingLocation().getX(), level.getStartingLocation().getY(), 0, 0, pathXSpriteState.VISIBLE_STATE.toString(), 0);
        player.setcurrentIntersection(level.getStartingLocation());
        carstack.add(player);
        for (int i = 0; i < level.getNumPolice(); i++) {

            sT = new SpriteType(POLICE_CAR_SPRITE_TYPE);
            addSpriteType(sT);
            sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), policeImage);
            newCar = new pathXCars(sT, 0, 0, 0, 0, pathXSpriteState.VISIBLE_STATE.toString(), i + 1);
            int indexintersection = 0;
            do {
                indexintersection = (int) (Math.random() * level.getIntersections().size());
            } while (indexintersection == 0 || indexintersection == level.getIntersections().size());
            newCar.setcurrentIntersection(level.getIntersections().get(indexintersection));
            newCar.setX(level.getIntersections().get(indexintersection).getX());
            newCar.setY(level.getIntersections().get(indexintersection).getY());

            carstack.add(newCar);
        }
        for (int i = 0; i < level.getNumZombies(); i++) {

            sT = new SpriteType(ZOMBIE_CAR_SPRITE_TYPE);
            addSpriteType(sT);
            sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), zombieImage);
            newCar = new pathXCars(sT, 0, 0, 0, 0, pathXSpriteState.VISIBLE_STATE.toString(), i + 1);
            int indexintersection = 0;
            do {
                indexintersection = (int) (Math.random() * level.getIntersections().size());
            } while (indexintersection == 0 || indexintersection == level.getIntersections().size());
            newCar.setcurrentIntersection(level.getIntersections().get(indexintersection));
            newCar.setX(level.getIntersections().get(indexintersection).getX());
            newCar.setY(level.getIntersections().get(indexintersection).getY());

            carstack.add(newCar);
        }
        for (int i = 1; i <= level.getNumBandits(); i++) {
            sT = new SpriteType(BANDIT_CAR_SPRITE_TYPE);
            addSpriteType(sT);
            sT.addState(pathXSpriteState.VISIBLE_STATE.toString(), banditImage);
            newCar = new pathXCars(sT, 0, 0, 0, 0, pathXSpriteState.VISIBLE_STATE.toString(), i + 1);
            int indexintersection = 0;
            do {
                indexintersection = (int) (Math.random() * level.getIntersections().size());
            } while (indexintersection == 0 || indexintersection == level.getIntersections().size());
            newCar.setcurrentIntersection(level.getIntersections().get(indexintersection));
            newCar.setX(level.getIntersections().get(indexintersection).getX());
            newCar.setY(level.getIntersections().get(indexintersection).getY());

            carstack.add(newCar);
        }

    }

    public void setSelectedRoad(pathXroad r) {
        selectedRoad = r;
    }

    public void unselectEverything() {
        selectedIntersection = null;
        selectedRoad = null;
        startRoadIntersection = null;
        miniGame.getCanvas().repaint();
    }

}
