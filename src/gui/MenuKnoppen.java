/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class MenuKnoppen extends VBox {
    private Parent parent;
    private List<Button> knoppen;
    
    public MenuKnoppen(Parent parent){
        this.parent = parent;
        this.knoppen = new ArrayList<>();
    }
    
    public void buildGui(){
        this.setSpacing(50);
        
        for(Button b: knoppen){
            b.setPrefHeight(20);
            b.setPrefWidth(200);
            b.setAlignment(Pos.BASELINE_LEFT);
            b.setPadding(new Insets(0,5,0,10));
            this.getChildren().add(b);
            b.getStyleClass().add("btn");
            //b.getStyleClass().add("orangeBtn");
            b.getStyleClass().add("mk");
            b.setStyle("-fx-font-size: 16px");
            
            b.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    b.setCursor(Cursor.HAND);
                }
            });
        
        }
    }
    
    public void setKnoppen(List<Button> knoppen) {
        this.knoppen = knoppen;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    public void addKnop(Button button){
        knoppen.add(button);
    }
    
    
    
    
    
}
