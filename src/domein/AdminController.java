/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
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

        //Hardcoded admins
        Admin tybo = new Admin("Tybo", "admin");
        Admin rob = new Admin("Rob", "admin");
        Admin florian = new Admin("Florian", "admin");
        Admin tim = new Admin("Tim", "admin");
        Arrays.stream(new Admin[]{tybo, rob, florian, tim}).forEach(a -> voegAdminToe(a));
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

    public boolean adminBestaat(Admin admin) {
        Admin a = admins
                .stream()
                .filter(adm -> adm.equals(admin))
                .findAny()
                .orElse(null);
        return a != null;
    }
}
