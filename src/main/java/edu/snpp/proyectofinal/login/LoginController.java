package edu.snpp.proyectofinal.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.snpp.proyectofinal.MainApp;
import static edu.snpp.proyectofinal.MainApp.VENTANAPRINCIPAL;
import edu.snpp.proyectofinal.entidades.Empleado;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class LoginController implements Initializable {
    

    @FXML
    private Label config;
    @FXML
    private JFXButton aceptar;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXPasswordField contrasena;
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
    @FXML
    private JFXTextField ci;
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    private Stage s;
    @FXML
    private ImageView imagen;

    public void setS(Stage s) {
        this.s = s;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image i= new Image(getClass().getResourceAsStream("/imagenes/schoooloftomorrow.png"));
        imagen.setImage(i);
        
    }    

    @FXML
    private void onActiononAceptar(ActionEvent event) {
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
            Empleado e= rConsulta.get(0);
            String contrasenha = this.toSHA256(contrasena.getText());
            
            if(e.getContrasena().equals(contrasenha)) {
                this.s.close();
                FXMLLoader loader=new FXMLLoader();
                try{
                Parent vp = loader.load(getClass().getResourceAsStream("/fxml/VentanaPrincipal.fxml"));
                MainApp.VENTANAPRINCIPAL=loader.getController();
                Stage st=new Stage();
                Scene scene = new Scene(vp);
                scene.getStylesheets().add("/styles/Styles.css");
                st.setTitle("JavaFX and Maven");
                st.setScene(scene);
                st.show();
                }
                catch (IOException ex){
                    LOG.log(Level.SEVERE, "error al abrir página principal", ex);
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

    @FXML
    private void OnActiononCancelar(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cancelar Operación");
        alert.setHeaderText("¿Desea cancelar la operación?");

        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                ci.clear();
                contrasena.clear();
            }       
    }
    public String toSHA256(String source){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(source.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.log(Level.SEVERE, "Error al obtener algoritmo sha-256", e);
        }
        return null;
    }

    @FXML
    private void OnMouseClicked(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader();
           try{
                Parent mc = loader.load(getClass().getResourceAsStream("/fxml/modificarcuenta/ModificarCuenta.fxml"));
                Scene scene = new Scene(mc);
                scene.getStylesheets().add("/styles/Styles.css");
                Stage s= new Stage();
                s.setTitle("JavaFX and Maven");
                s.setScene(scene);
                s.show();
                }
                catch (IOException ex){
                    LOG.log(Level.SEVERE, "error al abrir página principal", ex);
                }  
    }
}
