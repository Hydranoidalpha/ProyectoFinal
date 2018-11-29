/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.generarcobrodeaportes;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Alumno;
import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 * FXML Controller class
 *
 * @author fredybogado
 */
import javafx.scene.control.Alert.AlertType;
public class GenerarcobrodeaportesFXMLController implements Initializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXCheckBox check1;
    @FXML
    private JFXTextField alumno;
    private JFXListView<Alumno> listv = new JFXListView<>();

    JFXPopup popup = new JFXPopup();
    private boolean mostrar = true;

    Alumno alu;
    @FXML
    private JFXComboBox<String> desdemes;
    @FXML
    private JFXComboBox<String> hastames;
    @FXML
    private JFXComboBox<Integer> desdeanho;
    @FXML
    private JFXComboBox<Integer> hastaanho;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargaralu();
        // para cargar los alumnos en el popup
        popup.setPopupContent(listv);
        listv.setCellFactory((ListView<Alumno> a) -> new AlumnoListCell());
        listv.setPrefWidth(250);
        listv.setPrefHeight(250);

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

        this.desdemes.getItems().addAll("Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre");
        this.hastames.getItems().addAll("Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre");
        Calendar c = new GregorianCalendar();
        int anio = c.get(Calendar.YEAR);
        this.desdeanho.getItems().addAll(anio);
        this.hastaanho.getItems().addAll(anio, anio + 1);
        
    }

    @FXML
    private void onActiononGenerar(ActionEvent event) {
        List<Alumno> lst = new LinkedList<>();
        if (this.check1.isSelected()) {
            lst.addAll(this.cargaralu());
        } else {
            lst.add(alu);
        }
        EntityManager em = emf.createEntityManager();
        int a = this.desdeanho.getSelectionModel().getSelectedIndex();
        int a1 = this.hastaanho.getSelectionModel().getSelectedIndex();
        String m = this.desdemes.getSelectionModel().getSelectedItem();
        int nrom = this.convertirmes(m);

        String m1 = this.hastames.getSelectionModel().getSelectedItem();
        int nrom1 = this.convertirmes(m1);

     
        for (Alumno al : lst) {
               Calendar cDesde = new GregorianCalendar(desdeanho.getValue(), this.convertirmes(this.desdemes.getValue()) - 1, 1);
               Calendar cHasta = new GregorianCalendar(this.hastaanho.getValue(), this.convertirmes(this.hastames.getValue()) - 1, 1);
            while (cDesde.before(cHasta) || cDesde.equals(cHasta)) {
                
                if (cDesde.get(Calendar.MONTH) != Calendar.DECEMBER && cDesde.get(Calendar.MONTH) != Calendar.JANUARY) {
                    int mes= cDesde.get(Calendar.MONTH) + 1;
                    int anho= cDesde.get(Calendar.YEAR);
                    if(!this.verificarpendiente(al, mes, anho))
                    {
                    MovimientoAporte ma = new MovimientoAporte();
                    ma.setAlumno(al);
                    ma.setMes(mes);
                    ma.setPendiente(true);
                    ma.setAnho(anho);
                    em.getTransaction().begin();
                    em.persist(ma);
                    em.getTransaction().commit();
                    }
                }
                cDesde.add(Calendar.MONTH, 1);
            }
        }
        alumno.clear();
        desdemes.getSelectionModel().clearSelection();
        desdeanho.getSelectionModel().clearSelection();
        hastames.getSelectionModel().clearSelection();
        hastaanho.getSelectionModel().clearSelection();
    }

    @FXML
    private void onActiononCerrar(ActionEvent event) {
        Alert s= new Alert(AlertType.CONFIRMATION);
        s.setTitle("Salir");
        s.setHeaderText("¿Realmente desea salir?");
        Optional<ButtonType> result = s.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                System.exit(0);
            } 
            else 
            {
            }
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

    private int convertirmes(String mes) {
        int nro = 0;
        switch (mes) {
            case "Febrero":
                nro = 2;
                break;
            case "Marzo":
                nro = 3;
                break;
            case "Abril":
                nro = 4;
                break;
            case "Mayo":
                nro = 5;
                break;
            case "Junio":
                nro = 6;
                break;
            case "Julio":
                nro = 7;
                break;
            case "Agosto":
                nro = 8;
                break;
            case "Septiembre":
                nro = 9;
                break;
            case "Octubre":
                nro = 10;
                break;
            case "Noviembre":
                nro = 11;
                break;
            default:
                System.out.println("error");
                break;
        }
        return nro;
    }

    @FXML
    private void OnActionOnGenerarparatodos(ActionEvent event) {
        this.alumno.setDisable(this.check1.isSelected());
    }

    private List<Alumno> cargaralu() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Alumno> q = em.createQuery("SELECT tm FROM Alumno tm WHERE tm.activo=1", Alumno.class);
        return q.getResultList();
    }
    private boolean verificarpendiente(Alumno a, int Mes,int Anho) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<MovimientoAporte> q= em.createQuery("SELECT tm FROM MovimientoAporte tm WHERE tm.alumno = :al AND tm.mes = :mes AND tm.anho = :an", MovimientoAporte.class);
        q.setParameter("al" , a);
        q.setParameter("mes" , Mes);
        q.setParameter("an" , Anho);
        return !q.getResultList().isEmpty();
    }
}
