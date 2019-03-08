/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class VolzetException extends IllegalArgumentException {

    public VolzetException() {
        this("Deze activiteit is volzet.");
    }

    public VolzetException(String string) {
        super(string);
    }

}
