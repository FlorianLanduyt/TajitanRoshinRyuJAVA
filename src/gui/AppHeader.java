package gui;

import domein.controllers.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AppHeader extends BorderPane implements PropertyChangeListener{
    private String menuTitle;
    private Label lblAanmelden;
    private ImageView afbeelding;
    private LoginForm loginForm;
    
    private AdminController ac;
    private BeginSchermFlo parent;
    
    private boolean isIngelogd;
    
    private GridPane aanmeldenBox;
    
    public AppHeader (String menuTitle, AdminController ac, boolean isIngelogd, BeginSchermFlo parent){
        this.isIngelogd = isIngelogd;
        this.parent = parent;
        this.menuTitle = menuTitle;
        this.ac = ac;
        this.ac.addPropertyChangeListener(this);
        buildGui();
    }

    private void buildGui() {
        this.setMaxHeight(45);
        this.setMinHeight(45);
        this.setPrefHeight(45);
        
        maakAanmeldBox();
        maakMenuTitle();
    }

    private void maakMenuTitle() {
        Label lblMenu = new Label(menuTitle);
        lblMenu.setMinWidth(220);
        lblMenu.setMaxWidth(220);
        lblMenu.setPrefHeight(45);
        lblMenu.setStyle("-fx-font-size: 25px");
        //lblMenu.getStyleClass().add("hoofdMenuLabel");
        lblMenu.getStyleClass().add("bgr");
        
        
        lblMenu.setAlignment(Pos.BASELINE_CENTER);
        this.setLeft(lblMenu);
    }
    
    public void maakOverzichtTitle(String menu) {
        Label lblOverzicht = new Label(menu);
        lblOverzicht.setPrefWidth(300);
        lblOverzicht.setPrefHeight(45);
        lblOverzicht.setStyle("-fx-font-size: 16px");
        //lblOverzicht.getStyleClass().add("bgr");
        this.setAlignment(lblOverzicht, Pos.CENTER_LEFT);
        lblOverzicht.setPadding(new Insets(0,0,0,10));
        this.setCenter(lblOverzicht);
    }

    private void maakAanmeldBox() {
        
        aanmeldenBox = new GridPane();
        BorderPane.setMargin(aanmeldenBox, new Insets(5));
        aanmeldenBox.getStyleClass().add("terugBtn");
        aanmeldenBox.getStyleClass().add("allButtons");
        aanmeldenBox.setPadding(new Insets(5));
        setLabel();
        setAfbeelding();
        
        this.setRight(aanmeldenBox);
        aanmeldenBox.requestFocus();
        
        aanmeldenBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aanmeldenBox.setCursor(Cursor.HAND);
                //aanmeldenBox.getStyleClass().add("hover");
            }
        });
        aanmeldenBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isIngelogd) afmelden(); else aanmelden();
            }
        });
        
    }

    private void setAfbeelding() {
        afbeelding = new ImageView("images/Login.png");
        
        afbeelding.setFitHeight(40);
        afbeelding.setFitWidth(40);
        afbeelding.setStyle("-fx-padding: 5px");
        GridPane.setMargin(afbeelding, new Insets(0,5,0,5));
        
        aanmeldenBox.add(afbeelding, 1, 0);
    }

    private void setLabel() {
        if(!isIngelogd)
            lblAanmelden = new Label("Aanmelden");
        else 
            lblAanmelden = new Label("Welkom, " + ac
                .getAangemeldeAdmin().getGebruikersnaam());
        
        lblAanmelden.setStyle("-fx-font-size: 18px; -fx-text-color: white");
        lblAanmelden.setTextFill(Color.web("white"));
        lblAanmelden.setPadding(new Insets(5));
        aanmeldenBox.add(lblAanmelden, 0, 0);
    }
    
     public void aanmelden(){
        isIngelogd = true;
        //signInVisibility(false);
        loginForm = new LoginForm(ac);
        Scene scene = new Scene(loginForm);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Aanmelden");
        stage.setResizable(false);
        stage.showAndWait();

        setAfbeelding();
    }
    
    private void afmelden() {
        Stage stage = (Stage) (getScene().getWindow());
        double hoogteScherm = stage.getHeight();
        double breedteScherm = stage.getWidth();
        stage.setMaximized(true);
        BeginSchermFlo beginScherm = new BeginSchermFlo(ac, false, "HoofdMenu");
        Scene scene = new Scene(beginScherm, breedteScherm, hoogteScherm);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void setMenuTitle(String menuTitle){
        this.menuTitle = menuTitle;
        maakMenuTitle();
    }
    

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        parent.setIngelogd(!isIngelogd);
        
        lblAanmelden.setText("Welkom, " + ac
                .getAangemeldeAdmin().getGebruikersnaam());
        
    }
    
}
