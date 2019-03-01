/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class AdminController {

    private BeheerController beheerController;
    private List<Admin> admins;
    private Admin aangemeldeAdmin;

    public AdminController() {
        beheerController = new BeheerController();

        admins = new ArrayList<>(beheerController.geefAdmins());
        aangemeldeAdmin = null;
    }

    public Admin getAangemeldeAdmin() {
        return aangemeldeAdmin;
    }

    public void setAangemeldeAdmin(Admin admin) {
        Admin a = admins
                .stream()
                .filter(adm -> adm.equals(admin))
                .findAny()
                .orElse(null);
        aangemeldeAdmin = a;
    }

    public boolean isAangemeld(Admin admin) {
        return aangemeldeAdmin.equals(admin);
    }

    public boolean adminBestaat(Admin admin) {
        Admin a = admins
                .stream()
                .filter(adm -> adm.equals(admin))
                .findAny()
                .orElse(null);
        return a != null;
    }
}
