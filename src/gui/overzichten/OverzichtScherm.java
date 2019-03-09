///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui.overzichten;
//
//import domein.controllers.AdminController;
//import gui.BeginSchermFlo;
//import java.util.ArrayList;
//import java.util.List;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
//import javafx.scene.Cursor;
//import javafx.scene.Parent;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//
///**
// *
// * @author florianlanduyt
// */
//public interface OverzichtScherm{
//     List<ComboBox> filters;
//    private Parent parent;
//    private AdminController ac;
//    
//    public OverzichtScherm(Parent parent, AdminController ac){
//        this.parent = parent;
//        this.ac = ac;
//        
//        filters = new ArrayList<>();
//    }
//
//
//    public void buildGui() {
//        filters
//        VBox filterBox = new VBox();
//        
//        for(ComboBox box: filters){
//            box.setPrefHeight(20);
//            box.setPrefWidth(200);
//            
//            filterBox.getChildren().add(box);
//        }
//        
//        
//        
//        
//        this.getChildren().addAll(filters);
//    }
//    
//    public <T> void voegFilterToe(ComboBox<T> box){
//        filters.add(box);
//    }
//    
//    public <T> void voegTabelToe(TableView<T> tv){
//        tabel.getChildren().add(tv);
//    }
//    
//     
//    
//    
//}
