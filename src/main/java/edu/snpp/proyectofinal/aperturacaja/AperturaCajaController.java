/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.aperturacaja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.DetalleCaja;
import edu.snpp.proyectofinal.entidades.MovimientoCaja;
import edu.snpp.proyectofinal.login.LoginController;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AperturaCajaController implements Initializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXButton AperturaCaja;
    @FXML
    private JFXTextField monto;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cajahabilitada();
        if(this.cajahabilitada()!=null){
            this.AperturaCaja.setText("Cierre Caja");
        }else{
            this.AperturaCaja.setText("Apertura Caja");
        }
    }    

    @FXML
    private void onActiononAperturaCaja(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        if(this.AperturaCaja.getText().equals("Apertura Caja")){
        
        MovimientoCaja mc= new MovimientoCaja();
        mc.setAperturacaja(Integer.parseInt(monto.getText()));
        Calendar c= new GregorianCalendar();
        mc.setFecha(c.getTime());
        mc.setHabilitado(true);
        mc.setEmpleado(LoginController.EMPLEADO);
        AperturaCaja.setText("Cierre Caja");
        em.getTransaction().begin();
        em.persist(mc);
        em.getTransaction().commit();
        JFXSnackbar sb= new JFXSnackbar(pane);
        sb.show("El proceso se ha realizado con éxito", 5000);
        monto.clear();
        monto.setEditable(false);
    }
        else{
            MovimientoCaja mc= this.cajahabilitada();
            int totalEntrada=0;
            int totalSalida=0;
            int aperturacaja=0;
            for(DetalleCaja detalleCaja:this.getDetalles(mc)){
                totalEntrada=totalEntrada+detalleCaja.getEntrada();
                totalSalida=totalSalida+detalleCaja.getSalida();
                aperturacaja=mc.getAperturacaja();
            }         
            int cierrecaja=aperturacaja + totalEntrada - totalSalida;
            mc.setCierrecaja(cierrecaja);
            mc.setHabilitado(false);
            mc.setTotalentrada(totalEntrada);
            mc.setTotalsalida(totalSalida);
            AperturaCaja.setText("Apertura Caja");
            monto.setEditable(true);
            em.getTransaction().begin();
            em.merge(mc);//Movimiento Caja se tiene que actualizar, no registrar uno nuevo
            em.getTransaction().commit();
            JFXSnackbar sb= new JFXSnackbar(pane);
            sb.show("El proceso se ha realizado con éxito", 5000);
        }
    }
    private MovimientoCaja cajahabilitada(){
         EntityManager em = emf.createEntityManager();
         TypedQuery<MovimientoCaja> q= em.createQuery("SELECT tm FROM MovimientoCaja tm WHERE tm.habilitado =true", MovimientoCaja.class);
         List<MovimientoCaja> lst=q.getResultList();
         if(lst.isEmpty()){
             return null;
         }else{
             return lst.get(0);
         }
    }
    //Aca verificas un registro nomas
    //Puede que hayan muchos anulados. lo que tenes que hacer es una consulta que te traiga dodos
    //los detalles que no esten anulados y eso sumas
    //Tambien tiene que ser solo del movimiento de caja activo o sino va a traer todo
    private List<DetalleCaja> getDetalles(MovimientoCaja mc){
        EntityManager em = emf.createEntityManager();
         TypedQuery<DetalleCaja> q= em.createQuery("SELECT tm FROM DetalleCaja tm WHERE tm.caja=:mc AND tm.anulado=false", DetalleCaja.class);
         q.setParameter("mc", mc);
         return q.getResultList();
    }
}
