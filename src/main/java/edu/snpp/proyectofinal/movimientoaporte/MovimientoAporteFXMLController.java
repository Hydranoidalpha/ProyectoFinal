/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.movimientoaporte;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class MovimientoAporteFXMLController implements Initializable {

    @FXML
    private JFXTextField alumno;
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private TableView<MovimientoAporte> tabla;
    @FXML
    private TableColumn colum;
    @FXML
    private TableColumn<MovimientoAporte, Integer> aporte;
    @FXML
    private TableColumn<MovimientoAporte, Date> fechaven;
    @FXML
    private TableColumn<MovimientoAporte, Integer> monto;
    @FXML
    private JFXButton guardar1;
    @FXML
    private JFXButton informe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void onActiononSalir(ActionEvent event) {
    }

    @FXML
    private void OnActionBuscar(ActionEvent event) {
    }

    @FXML
    private void onActiononImprimirInforme(ActionEvent event) {
    }
    
}
