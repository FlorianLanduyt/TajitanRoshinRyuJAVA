/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.controllers;

import domein.Admin;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class AdminController {

    private DataController dataController;
    private List<Admin> admins;
    private Admin aangemeldeAdmin;
    private PropertyChangeSupport subject;

    public AdminController() {
        dataController = new DataController();

        admins = new ArrayList<>(dataController.geefAdmins());
        aangemeldeAdmin = null;
        subject = new PropertyChangeSupport(this);
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
        subject.firePropertyChange("aangemeldeAdmin", null, a);
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

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }
}
