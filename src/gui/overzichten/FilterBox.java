/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overzichten;

import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class FilterBox extends VBox {
    private List<ComboBox> nodigeFilters;
    
    public FilterBox(List<Filter> filters){
        MaakFilters(filters);
    }

    private void MaakFilters(List<Filter> filters) {
        for(Filter f: filters){
            
        }
    }
}
