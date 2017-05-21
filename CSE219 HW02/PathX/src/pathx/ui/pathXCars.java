/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

import java.util.ArrayList;
import mini_game.Sprite;
import mini_game.SpriteType;

/**
 *
 * @author WTang
 */
public class pathXCars extends Sprite {

    private int carId;
    private float x;
    private float y;
    private pathXIntersections currentIntersection;
    private pathXIntersections targetIntersection;
    private boolean movingToTarget;
    private float targetX;
    private float targetY;
    private ArrayList<Integer> Path;
    private int PathIndex;
    private int speedlimit;
    public pathXroad road;
    public pathXCars(SpriteType initSpriteType,
            float initx, float inity, float initVx, float initVy,
            String initState, int initcarId) {
        super(initSpriteType, initx, inity, initVx, initVy, initState);
        carId = initcarId;
        x = initx;
        y = inity;
      
        currentIntersection = null;
        targetIntersection = null;
        movingToTarget = false;
        road = null;
    }
    public pathXroad getroad(){
        return road;
    }
    public void setroad(pathXroad r){
        road = r;
    }
    public boolean getmovingToTarget(){
        return movingToTarget;
    }
    public  void setmovingToTarget(boolean bo){
        movingToTarget = bo;
    }

    public pathXIntersections getcurrentIntersection() {
        return currentIntersection;
    }

    public void setcurrentIntersection(pathXIntersections intersection) {
        currentIntersection = intersection;
    }

    public pathXIntersections gettargetIntersection() {
        return targetIntersection;
    }

    public void settargetIntersection(pathXIntersections intersection) {
        targetIntersection = intersection;
    }

    @Override
    public float getX() {
        return x;

    }

    @Override
    public float getY() {
        return y;
    }

    public void setX(float x1) {
        x = x1;
    }

    public void setY(float y1) {
        y = y1;
    }

    public int getcarId() {
        return carId;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public boolean isMovingToTarget() {
        return movingToTarget;
    }

    public void setinitplace(int initx, int inity) {
        x = initx;
        y = inity;
    }

    public void setTarget(float initTargetX, float initTargetY) {
        targetX = initTargetX;
        targetY = initTargetY;
    }

    public float calculateDistanceToTarget() {
        // GET THE X-AXIS DISTANCE TO GO
        float diffX = targetX - x;

        // AND THE Y-AXIS DISTANCE TO GO
        float diffY = targetY - y;

        // AND EMPLOY THE PYTHAGOREAN THEOREM TO CALCULATE THE DISTANCE
        float distance = (float) Math.sqrt((diffX * diffX) + (diffY * diffY));

        // AND RETURN THE DISTANCE
        return distance;

    }

//    public void startMovingToTarget(pathXroad road, pathXIntersections intersection) {
//        // LET ITS POSITIONG GET UPDATED
//        movingToTarget = true;
//        targetX = intersection.getX();
//        targetY = intersection.getY();
//      
//        // CALCULATE THE ANGLE OF THE TRAJECTORY TO THE TARGET
//        float diffX = targetX - this.getcurrentIntersection().getX();
//        float diffY = targetY - this.getcurrentIntersection().getY();
//        double absdistance = Math.sqrt((double) (diffX * diffX + diffY * diffY));
//        double smallx = diffX / road.getSpeedLimit();
//        double smally = diffY / road.getSpeedLimit();
//
//        double x = (double) this.getX() + smallx;
//        this.setX((float) x);
//        double y = (double) this.getY() + smally;
//        this.setY((float) y);
//
//    }
   

}
