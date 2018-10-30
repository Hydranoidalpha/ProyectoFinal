/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.registroempleado;

import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.MainApp;
import edu.snpp.proyectofinal.entidades.Cargo;
import edu.snpp.proyectofinal.entidades.Empleado;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class TablaEmpleadoFXMLController implements Initializable {

    private static final Logger LOG = Logger.getLogger(TablaEmpleadoFXMLController.class.getName());

    @FXML
    private TableView<Empleado> lista;
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    
    @FXML
    private TableColumn<Empleado, String> nombre;
    @FXML
    private TableColumn<Empleado, String> apellido;
    @FXML
    private TableColumn<Empleado, String> direccion;
    @FXML
    private TableColumn<Empleado, Number> ci;
    @FXML
    private TableColumn<Empleado, String> tel;
    @FXML
    private TableColumn<Empleado, Cargo> cargo;
    @FXML
    private JFXTextField buscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarempleados();
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ci.setCellValueFactory(new PropertyValueFactory<>("ci"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        cargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        cargo.setCellFactory((TableColumn<Empleado, Cargo>c)-> new CargoTableCell());

    }    

    @FXML
    private void onActiononActualizar(ActionEvent event) {
        cargarempleados();
    }

    @FXML
    private void onActiononAgregar(ActionEvent event) {
        this.cargarModulo("/fxml/registroempleado/RegistroEmpleadoFXML.fxml", "Registro Empleado", 1);
        cargarempleados();
    }

    @FXML
    private void onActiononModificar(ActionEvent event) {
        this.cargarModulo("/fxml/registroempleado/RegistroEmpleadoFXML.fxml", "Registro Empleado", 2);
        cargarempleados();
    }

    @FXML
    private void onActiononEliminar(ActionEvent event)
        {
        EntityManager em=emf.createEntityManager();
        lista.getSelectionModel().getSelectedItem();
        Alert e= new Alert(Alert.AlertType.CONFIRMATION);
        e.setTitle("Eliminar");
        e.setHeaderText("¿Desea eliminar el empleado seleccionado");
        Optional<ButtonType> result = e.showAndWait();
        if(result.get()== ButtonType.OK){
        em.getTransaction().begin();
        em.remove(em.merge(lista.getSelectionModel().getSelectedItem()));
        em.getTransaction().commit();
        cargarempleados();
        }
        }
    

    @FXML
    private void onActiononBuscar(ActionEvent event) {
        buscar();
    }
    
    private void cargarempleados(){
      EntityManager em= emf.createEntityManager();
      TypedQuery<Empleado> q= em.createQuery("SELECT tm FROM Empleado tm", Empleado.class);
      lista.getItems().clear();
      lista.getItems().addAll(q.getResultList());
    }
    private void buscar(){
        EntityManager em= emf.createEntityManager();
        String b= buscar.getText();
      TypedQuery<Empleado> q= em.createQuery("SELECT e FROM Empleado e WHERE LOWER(e.nombre) LIKE :txt OR LOWER(e.apellido) LIKE :txt OR SQL('CAST(? AS CHAR(11))',e.ci) LIKE :txt",Empleado.class);
      q.setParameter("txt", "%"+b.toLowerCase()+"%");
      lista.getItems().clear();
      lista.getItems().addAll(q.getResultList());
    }

    @FXML
    private void onKeyPressedBuscar(KeyEvent event) {
        buscar();
    }
    private void cargarModulo(String direccionFXML, String tituloPestania, int modo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResourceAsStream(direccionFXML));
            //sirve para modificar 
            RegistroEmpleadoFXMLController re= loader.getController();
            if(modo==2){
            re.cargarempleado(lista.getSelectionModel().getSelectedItem());
            }
            //hasta aca
            Tab t=new Tab();
            t.setText(tituloPestania);
            t.setContent(root);
            MainApp.VENTANAPRINCIPAL.TabPane.getTabs().add(t);
            MainApp.VENTANAPRINCIPAL.TabPane.getSelectionModel().select(t);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al cargar modulo", ex);
            Alert errDlg=new Alert(Alert.AlertType.ERROR);
            errDlg.setTitle("Error al cargar módulo");
            errDlg.setHeaderText("Error al cargar módulo: '"+tituloPestania+"'. Archivo: '"+direccionFXML+"'.");
            errDlg.setContentText(ex.getMessage());
            errDlg.showAndWait();
        }
    }
}
