package domein.enums;

import javafx.beans.property.SimpleStringProperty;

public enum Formule {
    DI_DO,
    DI_ZA,
    WO_ZA,
    WO,
    ZA,
    ACTIVITEIT,
    STAGE,
    EXAMEN,
    PROEF,
    UITSTAP;
    
    private SimpleStringProperty sNaamFormule = new SimpleStringProperty(this.name());
    public SimpleStringProperty naamFormuleProperty(){
        return sNaamFormule;
    }
}
