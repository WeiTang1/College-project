/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.file;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import xml_utilities.InvalidXMLFileFormatException;
import xml_utilities.XMLUtilities;

/**
 *
 * @author Wei
 */
public class pathXlevelloadingmanager {

    private static pathXlevelloadingmanager singleton = null;
    private HashMap<String, ArrayList<String>> intersections;
    private HashMap<ArrayList<String>, ArrayList<String>> road;
    private HashMap<String, String> startIntersection;
    private HashMap<String, String> destinationIntersection;
    private HashMap<String, String> mpbz;
    private XMLUtilities xmlUtil;
    private HashMap<String, String> property;

    public static final String INTERSECTIONS = "intersections";
    public static final String INTERSECTION = "intersection";
    public static final String MONEY = "money";
    public static final String POLICE = "police";
    public static final String ZOMBIE = "zombie";
    public static final String BANDIT = "bandit";
    public static final String ROADS = "roads";
    public static final String ROAD = "road";
    public static final String DATA_PATH_PROPERTY = "DATA_PATH";

    private pathXlevelloadingmanager() {
        intersections = new HashMap();
        startIntersection = new HashMap();
        road = new HashMap();
        destinationIntersection = new HashMap();
        mpbz = new HashMap();
        property = new HashMap();
        xmlUtil = new XMLUtilities();
    }
    
    
    public HashMap getintersections(){
        return intersections;
    }
    public HashMap getroad(){
        return road;
    }
    public HashMap getmpbz(){
        return mpbz;
    }
    public HashMap getstartIntersection(){
        return startIntersection;
    }
    public HashMap getDestinationIntersection(){
        return destinationIntersection;
    }
    public static pathXlevelloadingmanager getpathXLevelLoadingManager() {
        if (singleton == null) {
            singleton = new pathXlevelloadingmanager();
        }
        return singleton;
    }

    public void addProperty(String propert, String value) {
        String propName = property.toString();
        property.put(propName, value);
    }

    public String getProperty(Object propType) {
        String propName = propType.toString();
        return property.get(propName);
    }

    public void loadPropertie(String xmlDataFile, String xmlSchemaFile)
            throws InvalidXMLFileFormatException {
        try {
            

            String dataPath = "./data/";
//            // ADD THE DATA PATH
//            xmlDataFile = dataPath + xmlDataFile;
            xmlSchemaFile = dataPath + xmlSchemaFile;
//            // FIRST LOAD THE FILE
            
            Document docs = xmlUtil.loadXMLDocument(xmlDataFile, xmlSchemaFile);

            // NOW LOAD ALL THE PROPERTIES
            Node intersectionNode = xmlUtil.getNodeWithName(docs, "intersections");
            ArrayList<Node> interNodes = xmlUtil.getChildNodesWithName(intersectionNode, "intersection");
            for (Node n : interNodes) {
                NamedNodeMap attributes = n.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    ArrayList<String> al = new ArrayList();
                    Node att = attributes.getNamedItem("id");
                    String attName = attributes.getNamedItem("id").getTextContent();
                    String attOpen = attributes.getNamedItem("open").getTextContent();
                    String attX = attributes.getNamedItem("x").getTextContent();;
                    String attY = attributes.getNamedItem("y").getTextContent();;
                    al.add(attName);
                    al.add(attOpen);
                    al.add(attX);
                    al.add(attY);
                    intersections.put(attName, al);
                }
            }
            Node roadNode = xmlUtil.getNodeWithName(docs, "roads");
            ArrayList<Node> roadNodes = xmlUtil.getChildNodesWithName(roadNode, "road");
            for (Node n : roadNodes) {
                NamedNodeMap attributes = n.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    ArrayList<String> key = new ArrayList();
                    ArrayList<String> al = new ArrayList();
                    Node att = attributes.getNamedItem("int_id1");
                    String attid1 = attributes.getNamedItem("int_id1").getTextContent();
                    String attid2 = attributes.getNamedItem("int_id2").getTextContent();
                    String attoneway = attributes.getNamedItem("one_way").getTextContent();;
                    String attspeedlimit = attributes.getNamedItem("speed_limit").getTextContent();;
                    key.add(attid1);
                    key.add(attid2);
                    al.add(attoneway);
                    al.add(attspeedlimit);
                    road.put(key, al);
                }
            }
            Node startNode = xmlUtil.getNodeWithName(docs, "start_intersection");
            NamedNodeMap attributes = startNode.getAttributes();
            Node att = attributes.getNamedItem("id");
            String attid = attributes.getNamedItem("id").getTextContent();
            String attimage = attributes.getNamedItem("image").getTextContent();
            startIntersection.put(attid, attimage);

            Node endNode = xmlUtil.getNodeWithName(docs, "destination_intersection");
            NamedNodeMap attributesend = endNode.getAttributes();
            Node attend = attributesend.getNamedItem("id");
            String attidend = attributesend.getNamedItem("id").getTextContent();
            String attimageend = attributesend.getNamedItem("image").getTextContent();
            destinationIntersection.put(attidend, attimageend);

            Node moneyNode = xmlUtil.getNodeWithName(docs, "money");
            NamedNodeMap attributemoney = moneyNode.getAttributes();
            String attamount = attributemoney.getNamedItem("amount").getTextContent();
            mpbz.put("money", attamount);

            Node policeNode = xmlUtil.getNodeWithName(docs, "police");
            NamedNodeMap attributepolice = policeNode.getAttributes();
            String num = attributepolice.getNamedItem("num").getTextContent();
            mpbz.put("police", num);

            Node banditnode = xmlUtil.getNodeWithName(docs, "bandits");
            NamedNodeMap attributebandits = banditnode.getAttributes();
            String numb = attributebandits.getNamedItem("num").getTextContent();
            mpbz.put("bandit", numb);

            Node zombienode = xmlUtil.getNodeWithName(docs, "zombies");
            NamedNodeMap attributezombies = zombienode.getAttributes();
            String numz = attributezombies.getNamedItem("num").getTextContent();
            mpbz.put("zombie", numz);
            
            Node imagenode = xmlUtil.getNodeWithName(docs,"level");
            NamedNodeMap arrtibuteimage = imagenode.getAttributes();
            String imag = arrtibuteimage.getNamedItem("image").getTextContent();
            mpbz.put("image", imag);
      
            String name = arrtibuteimage.getNamedItem("name").getTextContent();
            mpbz.put("name", name);

        } // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
        catch (InvalidXMLFileFormatException ixmlffe) {
        }

    }
}
