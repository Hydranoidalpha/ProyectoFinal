/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.modificarcuenta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.entidades.Empleado;
import edu.snpp.proyectofinal.login.LoginController;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class ModificarCuentaFXMLController implements Initializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXPasswordField conactual;
    @FXML
    private JFXPasswordField connueva;
    @FXML
    private JFXTextField ci;
    @FXML
    private JFXButton guardar;
    @FXML
    private JFXPasswordField verificarcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OnActiononCancelar(ActionEvent event) {
    }

    @FXML
    private void onActiononGuardar(ActionEvent event) {
        {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Empleado> q = em.createQuery("SELECT tm FROM Empleado tm WHERE tm.ci=:ci", Empleado.class);
            int cedula = Integer.parseInt(ci.getText());
            q.setParameter("ci", cedula);
            List<Empleado> rConsulta = q.getResultList();
            if (rConsulta.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText("El Usuario no existe");
                alert.setContentText("Vuelva a ingresar los datos");
                alert.showAndWait();
            } else {
                Empleado e = rConsulta.get(0);
                String contrasenha = this.toSHA256(conactual.getText());

                if ((e.getContrasena()).equals(contrasenha)) {
                        
                    if (this.connueva.getText().equals(verificarcon.getText())) {
                        
                        String c = this.toSHA256(connueva.getText());
                        e.setContrasena(c);
                        em.getTransaction().begin();
                        em.merge(e);
                        em.getTransaction().commit();
                        conactual.clear();
                        connueva.clear();
                        verificarcon.clear();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText("Contraseña");
                        alert.setContentText("La Contraseña a sido modificada");
                        alert.showAndWait();
                    }
                    else
                    {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("Contraseña incorrecta");
                    alert.setContentText("Las contraseñas no coninciden, vuelva a intentarlo");
                    alert.showAndWait();  
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("Contraseña incorrecta");
                    alert.setContentText("Por favor intentelo nuevamente.");
                    alert.showAndWait();
                }
            }
        }
    }

    public String toSHA256(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(source.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.log(Level.SEVERE, "Error al obtener algoritmo sha-256", e);
        }
        return null;
    }
}
