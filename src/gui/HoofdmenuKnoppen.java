package gui;

import domein.controllers.AdminController;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class HoofdmenuKnoppen  extends MenuKnoppen {
    private final BeginSchermFlo parent;
    
    //private final List<Button> menuKnoppen;
    
    
    public HoofdmenuKnoppen(BeginSchermFlo parent){
        super((Parent) parent);
        this.parent = parent;
        voegKnoppenToe();
    }
    
    
    private void voegKnoppenToe(){
        //BUTTON OVERZICHTEN
        Button btnOverzichten = new Button("Overzichten");
        btnOverzichten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.toonOverzichtenMenu();
            }
        });
        btnOverzichten.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    parent.toonOverzichtenMenu();
                    }
                }
        });
        super.addKnop(btnOverzichten);
        
        
        //BUTTON BEHEER LEDEN
        Button btnBeheerLid = new Button("Beheer Leden");
        btnBeheerLid.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                parent.beheerLid();
            }
        });
        super.addKnop(btnBeheerLid);
        
        
        //BUTTON BEHEER ACTIVITEITEN
        Button btnBeheerActiviteiten = new Button("Beheer Activiteiten");
        btnBeheerActiviteiten.setOnAction(new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.beheerActiviteiten();
            }
        });
        super.addKnop(btnBeheerActiviteiten);
        
        
        
        //BUTTON BEHEREN LESMATERIALEN 
        Button btnBeherenLesmaterialen = new Button("Beheren Lesmaterialen ");
        btnBeherenLesmaterialen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.beheerLesmateriaal();
            }
        });
        super.addKnop(btnBeherenLesmaterialen);
        
        super.buildGui();
    }
}
