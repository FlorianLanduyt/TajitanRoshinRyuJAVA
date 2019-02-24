/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class AdminController {

    private List<Admin> admins;
    private Admin aangemeldeAdmin;

    public AdminController() {
        admins = new ArrayList<>();
        aangemeldeAdmin = null;
    }

    public Admin getAangemeldeAdmin() {
        return aangemeldeAdmin;
    }

    private void setAangemeldeAdmin(Admin admin) {
        aangemeldeAdmin = admin;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void voegAdminToe(Admin admin) {
        admins.add(admin);
    }

    public void verwijderAdmin(Admin admin) {
        admins.remove(admin);
    }

    public boolean isAangemeld(Admin admin) {
        return aangemeldeAdmin.equals(admin);
    }

    public boolean aanmelden(Admin admin) {
        try {
            setAangemeldeAdmin(admin);
            return true;
        } catch (Exception ex) {
            return true;
        }
    }

    public boolean afmelden(Admin admin) {
        try {
            setAangemeldeAdmin(null);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
