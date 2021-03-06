package gui;

import domein.controllers.AdminController;
import gui.BeherenLesmateriaal.BeherenLesMateriaal;
import gui.beherenActiviteit.ActiviteitenBeherenScherm;
import gui.beherenLid.LidBeherenScherm;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class BeginSchermFlo extends BorderPane {
    private AdminController ac;
    private MenuKnoppen mk;
    private AppHeader header;
    private LinksPane links;
    
    
    private boolean ingelogd;//voor de menubar te disabelen
    private String soortMenu;
    
    public BeginSchermFlo(AdminController ac, boolean ingelogd, String soortMenu){
        this.ingelogd = ingelogd; 
        this.ac = ac;
        this.mk = new HoofdmenuKnoppen(this);
        this.soortMenu = soortMenu;
        buildGui();
    }
    
    private void buildGui() {
        maakHeader();
        maakLinkerPane();
        maakCenterPane();
    }
    
    public void maakHeader() {
        header = new AppHeader(soortMenu, ac, ingelogd, this);
        this.setTop(header);
    }

    private void maakLinkerPane() {
        links = new LinksPane(mk, ac, this, ingelogd);
        this.setLeft(links);

    }

    public void maakCenterPane() {
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        ImageView imgBeginScherm = new ImageView("images/BeginSchermImage.png");
        imgBeginScherm.setFitHeight(500);
        imgBeginScherm.setFitWidth(500);
        center.getChildren().add(imgBeginScherm);
        
        Label titel = new Label("Taijitan Yoshin Ryu");
        titel.getStyleClass().add("titel");
        center.getChildren().add(titel);
        
        header.maakOverzichtTitle("");
        
        this.setCenter(center);
    }

    
    public void toonOverzichtenMenu(){
        MenuKnoppen overzichMenuKnoppen = new OverzichtMenuKnoppen(this, ac);
        this.setLeft(new LinksPane(overzichMenuKnoppen, ac, this, ingelogd));
        header.setMenuTitle("Overzichten");
    }
    
    
    public void beheerLid(){
        this.setCenter(new LidBeherenScherm(this, ac, "Beheren Lid"));
    }
    
    public void beheerActiviteiten(){
        this.setCenter(new ActiviteitenBeherenScherm(this, ac, "Beheren Activiteit"));
    }
    
    public void beheerLesmateriaal(){
        this.setCenter(new BeherenLesMateriaal(this, ac, "Beheren lesmateriaal"));
    }
    
    public <T> void ToonOverzichtenScherm(T scherm) {
        this.setCenter(null);
        this.setCenter((Node) scherm);
    }
    
    public void maakOverzichtTitle(String soortOverzicht){
        header.maakOverzichtTitle(soortOverzicht);
    }
    
    //Andere methodes 
    public void setIngelogd(boolean waarde){
        this.ingelogd = waarde;
    }
    
    public void setMenuTitle(String menuTitle){
        header.setMenuTitle(menuTitle);
    }
    

}
