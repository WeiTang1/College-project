/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

/**
 *
 * @author WTang
 */
public class pathXroad {

    pathXIntersections node1;
    pathXIntersections node2;

    // false IF IT'S TWO-WAY, true IF IT'S ONE WAY
    boolean oneWay;

    // ROAD SPEED LIMIT
    int speedLimit;

    // ACCESSOR METHODS
    public pathXIntersections getNode1() {
        return node1;
    }

    public pathXIntersections getNode2() {
        return node2;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    // MUTATOR METHODS
    public void setNode1(pathXIntersections node1) {
        this.node1 = node1;
    }

    public void setNode2(pathXIntersections node2) {
        this.node2 = node2;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Builds and returns a textual representation of this road.
     */
    @Override
    public String toString() {
        return node1 + " - " + node2 + "(" + speedLimit + ":" + oneWay + ")";
    }
}
