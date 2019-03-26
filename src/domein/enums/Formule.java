package domein.enums;

import javafx.beans.property.SimpleStringProperty;

public enum Formule {
    DI_DO(true),
    DI_ZA(true),
    WO_ZA(true),
    WO(true),
    ZA(true),
    ACTIVITEIT(false),
    STAGE(false),
    EXAMEN(false),
    PROEF(false),
    UITSTAP(false);
    
    private final boolean isLes;
    private SimpleStringProperty sNaamFormule = new SimpleStringProperty(this.name());
    
    private Formule(boolean isLes){
        this.isLes = isLes;
    }

    public boolean isLes() {
        return isLes;
    }

    
    public SimpleStringProperty naamFormuleProperty(){
        return sNaamFormule;
    }
}
