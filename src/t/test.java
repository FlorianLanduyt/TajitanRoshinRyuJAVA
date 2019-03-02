/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t;

import domein.BeheerController;
import domein.OverzichtController;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class test {

    public static void main(String[] args) {
        OverzichtController oc = new OverzichtController();

        oc.geefOverzichtLeden().forEach(l -> System.out.println(l));
      
    }

}
