/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.registroempleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Cargo;
import edu.snpp.proyectofinal.entidades.Empleado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class RegistroEmpleadoFXMLController implements Initializable {

    @FXML
    private JFXTextField nombre;
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXTextField apellido;
    @FXML
    private JFXTextField direc;
    @FXML
    private JFXTextField ci;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXComboBox<Cargo> carg;
    public JFXButton guardar;
    boolean emp= false;
    @FXML
    private TextField idempleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarcargo();
        
        carg.setCellFactory((ListView<Cargo> c) -> new CargoListCell());
        carg.setButtonCell(new CargoListCell());
    }    

    @FXML
    private void onActiononGuardar(ActionEvent event) {
        EntityManager em=emf.createEntityManager();
        Empleado e= new Empleado();
        e.setNombre(nombre.getText());
        nombre.clear();
        e.setApellido(apellido.getText());
        apellido.clear();
        
        e.setDireccion(direc.getText());
        direc.clear();
        
        e.setCi(Integer.parseInt(ci.getText()));
        ci.clear();
        
        e.setTelefono(tel.getText());
        tel.clear();
        
        e.setCargo(carg.getSelectionModel().getSelectedItem());
        em.getTransaction().begin();
        if(emp){
            e.setIdempleado(Integer.parseInt(idempleado.getText()));
            em.merge(e);
          }
        else{
            em.persist(e);
        }
        em.getTransaction().commit();
    }
    private void cargarcargo(){
        EntityManager em= emf.createEntityManager();
        TypedQuery<Cargo> q= em.createQuery("SELECT tm FROM Cargo tm", Cargo.class);
        carg.getItems().clear();
        carg.getItems().addAll(q.getResultList());
    }
    public void cargarempleado(Empleado empl){
      idempleado.setText(empl.getIdempleado().toString());
      nombre.setText(empl.getNombre());
      apellido.setText(empl.getApellido());
      direc.setText(empl.getDireccion());
      ci.setText(empl.getCi().toString());
      tel.setText(empl.getTelefono());
      emp= true;
    }

}
