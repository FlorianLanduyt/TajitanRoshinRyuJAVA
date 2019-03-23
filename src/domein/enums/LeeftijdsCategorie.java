
package domein.enums;


public enum  LeeftijdsCategorie {
    L6_15("6 - 15"),
    L10_15("10 - 15"),
    L15_PLUS("15+");
    
    private String displayName;
    
    LeeftijdsCategorie(String displayname){
        this.displayName = displayname;
    }

    public String getDisplayName() {
        return displayName;
    }

    
}
