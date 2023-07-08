/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package graph;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class AdjacentNode {

    private Node adjNode;
    private Line edge;

    public AdjacentNode(Node adjNode) {
        this.adjNode = adjNode;
    }

    public AdjacentNode(Node adjNode, Line edge) {
        this.adjNode = adjNode;
        this.edge = edge;
    }

    public AdjacentNode(Node adjNode, Line edge, Pane displayPane) {
        this(adjNode, edge);
        drawEdge(displayPane);
    }

    public void drawEdge(Pane displayPane) {
        displayPane.getChildren().add(edge); //add edge between two nodes 
    } 

//    @Override
//    public String toString() {
//        return adjNode.toString();
//    }

    public Node getAdjNode() {
        return adjNode;
    }

    public void setAdjNode(Node adjNode) {
        this.adjNode = adjNode;
    }

    public Line getEdge() {
        return edge;
    }

    public void setEdge(Line edge) {
        this.edge = edge;
    }

}
