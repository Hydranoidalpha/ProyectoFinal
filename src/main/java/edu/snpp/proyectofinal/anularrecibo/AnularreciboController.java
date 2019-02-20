 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.anularrecibo;

import com.jfoenix.controls.JFXSnackbar;
import edu.snpp.proyectofinal.entidades.Concepto;
import edu.snpp.proyectofinal.entidades.DetalleCaja;
import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class AnularreciboController implements Initializable {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");

    @FXML
    private TableView<DetalleCaja> tabla;
    @FXML
    private TableColumn<DetalleCaja, Date> hora;
    @FXML
    private TableColumn<DetalleCaja, Integer> entrada;
    @FXML
    private TableColumn<DetalleCaja, Concepto> concepto;
    @FXML
    private TableColumn<DetalleCaja, String> descipcion;
    @FXML
    private TableColumn<DetalleCaja, Integer> recibo;
    @FXML
    private TableColumn<DetalleCaja, Boolean> anulado;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargardetalle();
        
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        entrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        descipcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        recibo.setCellValueFactory(new PropertyValueFactory<>("nrorecibo"));
        concepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        concepto.setCellFactory((TableColumn<DetalleCaja, Concepto> c) -> new ConceptoTableCell());
        anulado.setCellValueFactory(new PropertyValueFactory<> ("anulado"));
        anulado.setCellFactory((TableColumn<DetalleCaja, Boolean> b) -> new AnuladoTableCell());
        
        this.hora.setCellFactory((TableColumn<DetalleCaja, Date> dc) -> {
            return new TableCell<DetalleCaja, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    if (!empty && item!=null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm aaa");
                        this.setText(sdf.format(item));
                    } else {
                        this.setText("");
                    }
                    super.updateItem(item, empty);
                }
            };
        });
        
    }    
    
    

    @FXML
    private void onActiononAnular(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        DetalleCaja dc = this.tabla.getSelectionModel().getSelectedItem();//Creo que aca solo tomaria 1 item
        dc.setAnulado(true);
        
        em.getTransaction().begin();
        em.merge(dc); 
        for(MovimientoAporte ma: dc.getMovimientoAporteList()){
            ma.setPendiente(true);
            em.merge(ma);
        }
        
        em.getTransaction().commit();
        JFXSnackbar sb= new JFXSnackbar(pane);
        sb.show("El proceso se ha realizado con éxito", 5000);
        }
        

    @FXML
    private void onActiononActualizar(ActionEvent event) {
        this.cargardetalle();
    }
    
    private void cargardetalle() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<DetalleCaja> q = em.createQuery("SELECT tm FROM DetalleCaja tm", DetalleCaja.class);
        tabla.getItems().clear();
        tabla.getItems().addAll(q.getResultList());
    }
}
