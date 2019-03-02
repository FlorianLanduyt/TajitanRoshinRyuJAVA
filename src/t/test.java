/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t;

import domein.BeheerController;
import domein.OverzichtController;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tybo Vanderstraeten
 */
public class test {

    public static void main(String[] args) {
        // System.out.println(checkRijksregister("99.12.08-173.04"));
        // System.out.println(checkGSM("047744146"));
        Pattern p = Pattern.compile("\\b[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b");
        Matcher m = p.matcher("foobar@gmail.com");

        if (m.find()) {
            System.out.println("Correct!");
        }
    }

    public static boolean checkGSM(String gsm) {
        if (gsm.matches("[0-9]{10}")) {
            return true;
        }
        return false;
    }

    public static String checkRijksregister(String rijksregisterNr) {
        LocalDate geboortedatum = LocalDate.now();
        String ges = "MAN";

        String nrZonderTekens = rijksregisterNr.replaceAll("[.]", "").replaceAll("-", "");
        String gebdatum = nrZonderTekens.substring(1, 7);
        String geslacht = nrZonderTekens.substring(7, 10);
        String controlegetal = nrZonderTekens.substring(10, nrZonderTekens.length()) + nrZonderTekens.charAt(nrZonderTekens.length());

        boolean gebDatumCorrect = false;
        boolean geslachtCorrect = Integer.valueOf(geslacht) % 2 == 0 ? ges.equalsIgnoreCase("VROUW") : ges.equalsIgnoreCase("MAN");
        boolean controleCorrect = false;

        //Checken of geboortedatumdeel correct is
        if (String.valueOf(geboortedatum.getYear()).substring(3, 5).equals(gebdatum.substring(1, 3))) {
            switch (geboortedatum.getMonthValue()) {
                case 10:
                case 11:
                case 12:
                    if (String.valueOf(geboortedatum.getMonthValue()).equals(gebdatum.substring(3, 5))) {
                        switch (geboortedatum.getDayOfMonth()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                if (String.valueOf(geboortedatum.getDayOfMonth()).equals(gebdatum.substring(6, 7))) {
                                    gebDatumCorrect = true;
                                }
                                break;
                            default:
                                if (String.valueOf(geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 7))) {
                                    gebDatumCorrect = true;
                                }
                        }
                    }
                    break;
                default:
                    if (String.valueOf(geboortedatum.getMonthValue()).equals(gebdatum.substring(4, 5))) {
                        switch (geboortedatum.getDayOfMonth()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                if (String.valueOf(geboortedatum.getDayOfMonth()).equals(gebdatum.substring(5, 7))) {
                                    gebDatumCorrect = true;
                                }
                                break;
                            default:
                                if (String.valueOf(geboortedatum.getDayOfMonth()).equals(gebdatum.substring(4, 7))) {
                                    gebDatumCorrect = true;
                                } else {
                                    gebDatumCorrect = false;
                                }
                        }
                    }
            }
        }
        if (geboortedatum.getYear() < 2000) {
            controleCorrect = Integer.valueOf(gebdatum.concat(geslacht)) % 97 == Integer.valueOf(controlegetal);
        } else {
            controleCorrect = Integer.valueOf("2".concat(gebdatum.concat(geslacht))) % 97 == Integer.valueOf(controlegetal);
        }

        if (gebDatumCorrect && geslachtCorrect && controleCorrect) {
            return "Juist";
        } else {
            return "Fout";
        }
    }

}
