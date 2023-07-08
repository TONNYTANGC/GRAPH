/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package graph;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.control.Label;

public class AlgoVisualizer {

    private Graph graph;
    private boolean visited[];
    private Timeline visualizer;
    private boolean isRunning = false;

    public AlgoVisualizer(Graph g) {
        graph = g;
    }

    public Timeline getVisualizer() {
        return visualizer;
    }

    public void setVisualizer(Timeline visualizer) {
        this.visualizer = visualizer;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void runBFS(int startNode) {
        isRunning = true;
        LinkedList<Integer> vnodelist = new LinkedList<>();
        visited = new boolean[graph.size()];

        graph.setDisable(true);

        vnodelist.add(startNode);
        vnodelist.add(startNode);
        visited[startNode] = true;
        graph.getNode(startNode).getShape().setFill(Color.ORANGE);

        KeyFrame bfsKeyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (vnodelist.size() > 1) {
                //deque an entry from queue and process it    
                //the poll() method retrieves and removes the head (first element) of this list  
                graph.getNode(vnodelist.poll()).getShape().setStrokeWidth(0);
                int currentNode = vnodelist.peek();

                graph.getNode(currentNode).getShape().setFill(Color.GREY); //set color 
                graph.getNode(currentNode).getShape().setStrokeWidth(5);
                //checks the next node is visited or not 
                for (AdjacentNode adjnode : graph.getAdjacentNodes(currentNode)) {
                    if (!visited[adjnode.getAdjNode().getNodeID()]) {
                        //if the above if-statement returns true, visits the node   
                        visited[adjnode.getAdjNode().getNodeID()] = true;
                        //adds the visited node in the vnodelist  
                        vnodelist.add(adjnode.getAdjNode().getNodeID());
                        adjnode.getAdjNode().getShape().setFill(Color.ORANGE);

                    }
                }

            } else {
                stop();
                isRunning = false;
                graph.getNode(vnodelist.poll()).getShape().setStrokeWidth(0);
            }
        });
        visualizer = new Timeline(bfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);
        visualizer.setAutoReverse(false);
        visualizer.play();

    }

    public void runDFS(int startNode) {
        isRunning = true;
        //Stack<Integer> vnodelist = new Stack<>();
        LinkedList<Integer> vnodelist = new LinkedList<>();
        visited = new boolean[graph.size()];

        graph.setDisable(true);
        //vnodelist.push(startNode);
        vnodelist.add(startNode);

        KeyFrame dfsKeyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (!vnodelist.isEmpty()) {
                int currentNode = vnodelist.peek();
                visited[currentNode] = true;

                if (vnodelist.size() > 1) {
                    graph.getNode(vnodelist.get(vnodelist.size() - 2)).getShape().setStrokeWidth(0);
//                    graph.getNode(currentNode).getShape().setStrokeWidth(5);
//                    graph.getNode(currentNode).getShape().setStroke(Color.BLUE);
                }

                int i;
                for (i = 0; i < graph.getAdjacentNodes(currentNode).size(); i++) {
                    Node node = graph.getAdjacentNodes(currentNode).get(i).getAdjNode();
                    if (!visited[node.getNodeID()]) {
                        vnodelist.push(node.getNodeID());
                        graph.getNode(currentNode).getShape().setFill(Color.ORANGE);
                        break;
                    }
                }
                if (i == graph.getAdjacentNodes(currentNode).size()) { //visited node 
                    int node = vnodelist.pop();
                    graph.getNode(node).getShape().setFill(Color.GREY);
                    graph.getNode(node).getShape().setStrokeWidth(0);
                }
            } else {
                stop();

            }
        });
        visualizer = new Timeline(dfsKeyFrame);
        visualizer.setCycleCount(Animation.INDEFINITE);

        visualizer.play();
    }

    void play() {
        if (!isRunning) {
            isRunning = true;
            visualizer.play();
        }
    }

    void stop() {
        if (isRunning) {
            graph.setDisable(false);
            graph.resetNodesColor();
            visualizer.stop();
            isRunning = false;
        }
    }
}

////    void pause() {
////        if (isRunning) {
////            isRunning = false;
////            visualizer.pause();
////        }
//    }

