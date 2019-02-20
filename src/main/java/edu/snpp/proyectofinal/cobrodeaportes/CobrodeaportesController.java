/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.cobrodeaportes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.MainApp;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.Concepto;
import edu.snpp.proyectofinal.entidades.DetalleCaja;
import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import edu.snpp.proyectofinal.entidades.MovimientoCaja;
import edu.snpp.proyectofinal.registroempleado.RegistroEmpleadoFXMLController;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
public class CobrodeaportesController implements Initializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private TableView<MovimientoAporte> tabla;
    @FXML
    private TableColumn<MovimientoAporte, Integer> mes;
    @FXML
    private TableColumn<MovimientoAporte, Integer> anho;
    @FXML
    private TableColumn<MovimientoAporte, Boolean> pendiente;
    @FXML
    private JFXTextField fechacobro;
    @FXML
    private JFXTextField montoingreso;
    @FXML
    private JFXComboBox<Concepto> concepto;
    @FXML
    private JFXTextField nrorecibo;
    @FXML
    private TableColumn<MovimientoAporte, Date> Fechaven;

    private JFXListView<Alumno> listv = new JFXListView<>();

    JFXPopup popup = new JFXPopup();
    private boolean mostrar = true;
    Alumno alu;
    @FXML
    private JFXTextField alumno;
    @FXML
    private TableColumn<MovimientoAporte, Integer> montoaporte;
    @FXML
    private JFXTextField descripcion;
    @FXML
    private TableColumn<MovimientoAporte, Boolean> colum1;
    @FXML
    private TableColumn<MovimientoAporte, DetalleCaja> anulado;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarconcepto();
        concepto.setCellFactory((ListView<Concepto> c) -> new ConceptoListCell());
        concepto.setButtonCell(new ConceptoListCell());

        this.colum1.setCellFactory((TableColumn) -> {
            return new TableCell<MovimientoAporte, Boolean>() {
                CheckBox chSeleccionar = new CheckBox();

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    if (!empty) {

                        FontIcon icono = new FontIcon();
                        chSeleccionar.setGraphic(icono);
                        chSeleccionar.setOnAction((evt) -> {
                            //Aca en el objeto ma estas trayendo la fila actual. El objeto Movimiento Aporte que le corresponde
                            //Cualquier dato que este dentro de ese objeto sacas y cargas igual que el monto
                            //Si es un dato que no esta en esa tabla si tenes que haer una consulta con
                            //el entity manager
                            MovimientoAporte ma = (MovimientoAporte) this.getTableRow().getItem();
                            ma.setPagar(chSeleccionar.isSelected());
                            montoingreso.setText(sumarTotal() + "");// esto es lo que hace que agregue al txf
                            if(chSeleccionar.isSelected()){
                            Calendar c= new GregorianCalendar();
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// para que la fecha del orto salga bien
                            String fecha = format.format(c.getTime());
                            fechacobro.setText(fecha);
                            }
                            else{
                                fechacobro.clear();
                            }
                            if(chSeleccionar.isSelected()){
                                
                                nrorecibo.setText(cargarrecibo() + "");
                            }
                            else{
                                nrorecibo.clear();
                            }
                        });
                        this.setGraphic(chSeleccionar);
                    } else {
                        this.setGraphic(null);
                    }
                    super.updateItem(item, empty);
                }
            };
        });

        // para cargar los alumnos en el popup
        popup.setPopupContent(listv);
        listv.setCellFactory((ListView<Alumno> a) -> new AlumnoListCell());

        this.inicializarListaAlum();
        this.alumno.textProperty().addListener((ObservableValue<? extends String> obs, String anterior, String nuevo) -> {

            if (this.mostrar) {
                this.cargaralumno(nuevo);
                popup.show(alumno, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 2, alumno.getHeight());
            } else {
                this.mostrar = true;
            }
        });
        //hasta aca
        Fechaven.setCellValueFactory(new PropertyValueFactory<>("fechavencimiento"));
        mes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        mes.setCellFactory((TableColumn<MovimientoAporte, Integer> mi) -> new MesTableCell());
        anho.setCellValueFactory(new PropertyValueFactory<>("anho"));
        montoaporte.setCellValueFactory(new PropertyValueFactory<>("monto"));
        pendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        pendiente.setCellFactory((TableColumn<MovimientoAporte, Boolean> ma) -> new PendienteTableCell());
        anulado.setCellValueFactory(new PropertyValueFactory<>("detallecaja"));
        anulado.setCellFactory((TableColumn<MovimientoAporte, DetalleCaja> dc) -> new AnuladoTableCell());

        //para que la fecha salga en datos simples
        this.Fechaven.setCellFactory((TableColumn<MovimientoAporte, Date> ma) -> {
            return new TableCell<MovimientoAporte, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    if (!empty && item!=null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                        this.setText(sdf.format(item));
                    } else {
                        this.setText("");
                    }
                    super.updateItem(item, empty);
                }
            };
        });
    }

    private int sumarTotal() {
        int total = 0;
        for (MovimientoAporte ma : this.tabla.getItems()) {
            if (ma.isPagar()) {
                total = total + ma.getMonto();
            }
        }
        return total;
    }
    private int cargarrecibo() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<DetalleCaja> q = em.createQuery("SELECT tm FROM DetalleCaja tm ORDER BY tm.nrorecibo DESC", DetalleCaja.class);
        List<DetalleCaja> lstDetalles= q.getResultList();        
        if(lstDetalles.isEmpty()){
            return 1;
        }else{
            Integer ultimoNumero=lstDetalles.get(0).getNrorecibo();
            return ultimoNumero+1;
        }
    }

    @FXML
    private void onActiononGrabar(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        DetalleCaja dc = new DetalleCaja();
        
        Calendar c = new GregorianCalendar();
        dc.setCaja(getCajaHabilitada());
        dc.setConcepto(concepto.getValue());
        dc.setDescripcion(descripcion.getText());
        dc.setEntrada(Integer.parseInt(montoingreso.getText()));
        Date d=new Date();
        System.out.println("Timestamp: "+d.getTime());
        dc.setHora(d);
        dc.setNrorecibo(Integer.parseInt(nrorecibo.getText()));
        dc.setSalida(0);
        dc.setNrofac("0");
        dc.setAnulado(false);// asi como aca
        concepto.getSelectionModel().clearSelection();
        descripcion.clear();
        montoingreso.clear();
        nrorecibo.clear();
        
        em.getTransaction().begin();
        em.persist(dc);
        for(MovimientoAporte ma:this.tabla.getItems()){
            if(ma.isPagar()){
                ma.setFechapago(c.getTime());
                ma.setPendiente(false);
                ma.setDetallecaja(dc);
                em.merge(ma);
            }
        }
        em.getTransaction().commit();
        JFXSnackbar sb= new JFXSnackbar(pane);
        sb.show("El proceso se ha realizado con éxito", 5000);

    }

    @FXML
    private void onActiononCancelar(ActionEvent event) {
    }


    @FXML
    private void onActiononActualizar(ActionEvent event) {
        this.cargaraportes(alu);
    }

    private void inicializarListaAlum() {
        //para que agregue al dar click
        listv.getStyleClass().addAll("combo-box-popup");
        listv.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                select(listv);
                e.consume();
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                popup.hide();
                e.consume();
            }
        });
        listv.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                select(listv);
            }
            e.consume();
        });
    }

    private void select(JFXListView<Alumno> listv) {
        mostrar = false;
        alumno.setText(listv.getSelectionModel().getSelectedItem().getNombre() + " " + listv.getSelectionModel().getSelectedItem().getApellido());
        alu = listv.getSelectionModel().getSelectedItem();
        this.cargaraportes(alu);
        this.popup.hide();
    }

    private void cargaralumno(String busc) {
        EntityManager em = emf.createEntityManager();
        if (busc.isEmpty()) {
            TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm", Alumno.class);
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        } else {
            TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm WHERE LOWER(tm.nombre) LIKE :busc", Alumno.class);
            q.setParameter("busc", "%" + busc.toLowerCase() + "%");
            listv.getItems().clear();
            listv.getItems().addAll(q.getResultList());
        }
    }

    private void cargaraportes(Alumno a) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<MovimientoAporte> q = em.createQuery("SELECT tm FROM MovimientoAporte tm WHERE tm.alumno=:alumno", MovimientoAporte.class);
        q.setParameter("alumno", a);
        tabla.getItems().clear();
        tabla.getItems().addAll(q.getResultList());
    }

    private void cargarconcepto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT tm FROM Concepto tm", Concepto.class);
        concepto.getItems().clear();
        concepto.getItems().addAll(q.getResultList());
    }
    private MovimientoCaja getCajaHabilitada(){
        EntityManager em= emf.createEntityManager(); 
        TypedQuery<MovimientoCaja> q= em.createQuery("SELECT m FROM MovimientoCaja m WHERE m.habilitado=true", MovimientoCaja.class);
        List<MovimientoCaja> lst=q.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    
    
}
