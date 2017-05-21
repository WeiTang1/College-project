/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import mini_game.MiniGame;
import mini_game.Viewport;
import pathx.ThePathX;
import pathx.ThePathX.pathXPropertyType;
import static pathx.ThePathX.pathXPropertyType.PATH_IMG;
import pathx.data.pathXDataModel;
import static pathx.pathXConstants.*;
import properties_manager.PropertiesManager;

/**
 *
 * @author Wei
 */
public class pathXPanel extends JPanel {

    private MiniGame game;
    private pathXDataModel data;
    Ellipse2D.Double recyclableCircle;
    Line2D.Double recyclableLine;
    HashMap<Integer, BasicStroke> recyclableStrokes;
    int triangleXPoints[] = {-ONE_WAY_TRIANGLE_WIDTH / 2, -ONE_WAY_TRIANGLE_WIDTH / 2, ONE_WAY_TRIANGLE_WIDTH / 2};
    int triangleYPoints[] = {ONE_WAY_TRIANGLE_WIDTH / 2, -ONE_WAY_TRIANGLE_WIDTH / 2, 0};
    GeneralPath recyclableTriangle;

    public pathXPanel(MiniGame initGame, pathXDataModel initData) {
        game = initGame;
        data = initData;
        recyclableCircle = new Ellipse2D.Double(0, 0, INTERSECTION_RADIUS * 2, INTERSECTION_RADIUS * 2);
        recyclableLine = new Line2D.Double(0, 0, 0, 0);
        recyclableStrokes = new HashMap();
        for (int i = 1; i <= 10; i++) {
            recyclableStrokes.put(i, new BasicStroke(i * 2));
        }
        recyclableTriangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                triangleXPoints.length);
        recyclableTriangle.moveTo(triangleXPoints[0], triangleYPoints[0]);
        for (int index = 1; index < triangleXPoints.length; index++) {
            recyclableTriangle.lineTo(triangleXPoints[index], triangleYPoints[index]);
        };
        recyclableTriangle.closePath();

    }

    public void renderBackground(Graphics g) {
        Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, bg);
    }

    public void renderSprite(Graphics g, Sprite s) {
        if (!s.getState().equals(pathXSpriteState.INVISIBLE_STATE.toString())) {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(), bgST.getHeight(), null);
        }
    }

    public void renderGUIControls(Graphics g) {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();

        for (Sprite s : decorSprites) {

            if (s.getSpriteType().getSpriteTypeID() == MAP_TYPE) {
                renderMap(g, s);
            }
        }

        // AND NOW RENDER THE BUTTONS
        if (((pathXMiniGame) game).isCurrentScreenState(GAME_STATE)) {
            renderLevelBackground(g);

            renderRoads(g);
            renderIntersections(g);
            renderCars(g);
            renderBackground(g);

        }

        for (Sprite s : decorSprites) {
            if (s.getSpriteType().getSpriteTypeID() == DIALOGUE_TYPE) {
                renderSprite(g, s);
            }
        }
        for (Sprite s : buttonSprites) {
            if (s.getSpriteType().getSpriteTypeID() == NODE_TYPE) {
                renderNode(g, s);
            } else {
                renderSprite(g, s);
            }

        }

    }

    public void renderMap(Graphics g, Sprite s) {
        Viewport vp = data.getViewport();
        if (!s.getState().equals(pathXSpriteState.INVISIBLE_STATE.toString())) {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, 9, 98, 632, 474, vp.getViewportX(), vp.getViewportY(), vp.getViewportX() + VIEWPORTWIDTH, vp.getViewportY() + VIEWPORTHEIGHT, null);
        }

    }

    public void renderNode(Graphics g, Sprite s) {
        Viewport vp = data.getViewport();
        if (s.getState().equals(pathXSpriteState.VISIBLE_STATE.toString())) {

            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(), bgST.getHeight(), null);

        }
    }

    private void renderLevelBackground(Graphics g) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Viewport viewport = data.getLevelviewport();
        String img = props.getProperty(PATH_IMG);
        img += data.getlevel().getBackgroundImageFileName();
        Image backgroundImage = game.loadImage(img);
        g.drawImage(backgroundImage, 138, 10, 632, 468, viewport.getViewportX(), viewport.getViewportY(), viewport.getViewportX() + viewport.getViewportWidth(), viewport.getViewportY() + viewport.getViewportHeight(), null);
    }

    private void renderIntersections(Graphics g2) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        Iterator<pathXIntersections> it = data.intersectionsIterator();
        while (it.hasNext()) {
            pathXIntersections intersection = it.next();
            Viewport viewport = data.getLevelviewport();

            // ONLY RENDER IT THIS WAY IF IT'S NOT THE START OR DESTINATION
            // AND IT IS IN THE VIEWPORT
            if ((!data.isStartingLocation(intersection))
                    && (!data.isDestination(intersection))
                    && data.isCircleBoundingBoxInsideViewport(intersection.x - viewport.getViewportX(), intersection.y - viewport.getViewportY(), INTERSECTION_RADIUS)) {
                // FIRST FILL
                if (intersection.isOpen()) {
                    g2.setColor(OPEN_INT_COLOR);
                } else {
                    g2.setColor(CLOSED_INT_COLOR);
                }
                recyclableCircle.x = intersection.x - (data.getLevelviewport().getViewportX()) - INTERSECTION_RADIUS + LEVELVIEWPORTLEFTMARGIN;
                recyclableCircle.y = intersection.y - (data.getLevelviewport().getViewportY()) - INTERSECTION_RADIUS + LEVELVIEWPORTTOPMARGIN;
                ((Graphics2D) g2).fill(recyclableCircle);

                // AND NOW THE OUTLINE
                if (data.isSelectedIntersection(intersection)) {
                    g2.setColor(HIGHLIGHTED_COLOR);
                } else {
                    g2.setColor(INT_OUTLINE_COLOR);
                }
                Stroke s = recyclableStrokes.get(INT_STROKE);
                ((Graphics2D) g2).setStroke(s);
                ((Graphics2D) g2).draw(recyclableCircle);
            }
        }
        String img = props.getProperty(PATH_IMG);
        img += data.getlevel().getStartingLocationImageFileName();
        Image startImage = game.loadImage(img);
        pathXIntersections startInt = data.getStartingLocation();
        renderIntersectionImage((Graphics2D) g2, startImage, startInt);
        String imgd = props.getProperty(PATH_IMG);
        imgd += data.getlevel().getDestinationImageFileName();
        Image destImage = game.loadImage(imgd);
        pathXIntersections destInt = data.getDestination();
        renderIntersectionImage((Graphics2D) g2, destImage, destInt);
    }

    private void renderIntersectionImage(Graphics2D g2, Image img, pathXIntersections i) {
        Viewport viewport = data.getLevelviewport();

        // CALCULATE WHERE TO RENDER IT
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        int x1 = i.x - (w / 2);
        int y1 = i.y - (h / 2);
        int x2 = x1 + img.getWidth(null);
        int y2 = y1 + img.getHeight(null);

        // ONLY RENDER IF INSIDE THE VIEWPORT
        if (this.isRectInsideViewport(x1, y1, x2, y2)) {
            g2.drawImage(img, x1 - viewport.getViewportX() + LEVELVIEWPORTLEFTMARGIN, y1 - viewport.getViewportY() + LEVELVIEWPORTTOPMARGIN, null);
        }
    }

    public boolean isRectInsideViewport(int x1, int y1, int x2, int y2) {
        Viewport viewport = data.getLevelviewport();

        if (x2 < viewport.getViewportX()) {
            return false;
        }
        if (x1 > (viewport.getViewportX() + viewport.getViewportWidth())) {
            return false;
        }
        if (y2 < viewport.getViewportY()) {
            return false;
        }
        if (y1 > (viewport.getViewportY() + viewport.getViewportHeight())) {
            return false;
        }
        return true;
    }

    private void renderRoads(Graphics g2) {
        // GO THROUGH THE ROADS AND RENDER ALL OF THEM
        Viewport viewport = data.getLevelviewport();
        Iterator<pathXroad> it = data.roadsIterator();
        ((Graphics2D) g2).setStroke(recyclableStrokes.get(INT_STROKE));
        while (it.hasNext()) {
            pathXroad road = it.next();
            if (!data.isSelectedRoad(road)) {
                renderRoad(((Graphics2D) g2), road, INT_OUTLINE_COLOR);
            }
        }
        // NOW DRAW THE LINE BEING ADDED, IF THERE IS ONE
//        if (data.isAddingRoadEnd()) {
//            pathXIntersections startRoadIntersection = data.getStartRoadIntersection();
//            recyclableLine.x1 = startRoadIntersection.x - viewport.getViewportX() + LEVELVIEWPORTLEFTMARGIN;
//            recyclableLine.y1 = startRoadIntersection.y - viewport.getViewportY()+LEVELVIEWPORTTOPMARGIN;
//            recyclableLine.x2 = data.getLastMouseX() - viewport.getViewportX() + LEVELVIEWPORTLEFTMARGIN;
//            recyclableLine.y2 = data.getLastMouseY() - viewport.getViewportY()+LEVELVIEWPORTTOPMARGIN;
//            ((Graphics2D) g2).draw(recyclableLine);
//        }

        // AND RENDER THE SELECTED ONE, IF THERE IS ONE
        pathXroad selectedRoad = data.getSelectedRoad();
        if (selectedRoad != null) {
            renderRoad(((Graphics2D) g2), selectedRoad, HIGHLIGHTED_COLOR);
        }

        // NOW DRAW THE LINE BEING ADDED, IF THERE IS ONE
    }

    private void renderCars(Graphics g) {
        Viewport viewport = data.getLevelviewport();

        for (int i = 0; i < data.getcarstack().size(); i++) {
            Sprite s = data.getcarstack().get(i);
            if (!s.getState().equals(pathXSpriteState.INVISIBLE_STATE.toString())) {
                SpriteType bgST = s.getSpriteType();
                Image img = bgST.getStateImage(s.getState());
                g.drawImage(img, (int) s.getX()+LEVELVIEWPORTLEFTMARGIN-30, (int) s.getY()+LEVELVIEWPORTTOPMARGIN-30, bgST.getWidth(), bgST.getHeight(), null);
            }

        }

    }

    private void renderRoad(Graphics2D g2, pathXroad road, Color c) {
        Viewport viewport = data.getLevelviewport();

        g2.setColor(c);
        int strokeId = road.getSpeedLimit() / 10;

        // CLAMP THE SPEED LIMIT STROKE
        if (strokeId < 1) {
            strokeId = 1;
        }
        if (strokeId > 10) {
            strokeId = 10;
        }
        g2.setStroke(recyclableStrokes.get(strokeId));

        // LOAD ALL THE DATA INTO THE RECYCLABLE LINE
        recyclableLine.x1 = road.getNode1().x - viewport.getViewportX() + LEVELVIEWPORTLEFTMARGIN;
        recyclableLine.y1 = road.getNode1().y - viewport.getViewportY() + LEVELVIEWPORTTOPMARGIN;
        recyclableLine.x2 = road.getNode2().x - viewport.getViewportX() + LEVELVIEWPORTLEFTMARGIN;
        recyclableLine.y2 = road.getNode2().y - viewport.getViewportY() + LEVELVIEWPORTTOPMARGIN;

        // AND DRAW IT
        g2.draw(recyclableLine);

        // AND IF IT'S A ONE WAY ROAD DRAW THE MARKER
        if (road.isOneWay()) {
            this.renderOneWaySignalsOnRecyclableLine(g2);
        }
    }

    private void renderOneWaySignalsOnRecyclableLine(Graphics2D g2) {
        // CALCULATE THE ROAD LINE SLOPE
        double diffX = recyclableLine.x2 - recyclableLine.x1;
        double diffY = recyclableLine.y2 - recyclableLine.y1;
        double slope = diffY / diffX;

        // AND THEN FIND THE LINE MIDPOINT
        double midX = (recyclableLine.x1 + recyclableLine.x2) / 2.0;
        double midY = (recyclableLine.y1 + recyclableLine.y2) / 2.0;

        // GET THE RENDERING TRANSFORM, WE'LL RETORE IT BACK
        // AT THE END
        AffineTransform oldAt = g2.getTransform();

        // CALCULATE THE ROTATION ANGLE
        double theta = Math.atan(slope);
        if (recyclableLine.x2 < recyclableLine.x1) {
            theta = (theta + Math.PI);
        }

        // MAKE A NEW TRANSFORM FOR THIS TRIANGLE AND SET IT
        // UP WITH WHERE WE WANT TO PLACE IT AND HOW MUCH WE
        // WANT TO ROTATE IT
        AffineTransform at = new AffineTransform();
        at.setToIdentity();
        at.translate(midX, midY);
        at.rotate(theta);
        g2.setTransform(at);

        // AND RENDER AS A SOLID TRIANGLE
        g2.fill(recyclableTriangle);

        // RESTORE THE OLD TRANSFORM SO EVERYTHING DOESN'T END UP ROTATED 0
        g2.setTransform(oldAt);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            game.beginUsingData();
            super.paintComponent(g);

            renderBackground(g);
            renderGUIControls(g);
        } finally {
            game.endUsingData();
        }
    }

}
