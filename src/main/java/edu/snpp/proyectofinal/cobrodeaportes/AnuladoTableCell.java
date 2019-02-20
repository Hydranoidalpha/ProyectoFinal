/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.cobrodeaportes;

import edu.snpp.proyectofinal.entidades.DetalleCaja;
import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import javafx.scene.control.TableCell;

/**
 *
 * @author fredybogado
 */
public class AnuladoTableCell extends TableCell<MovimientoAporte, DetalleCaja> {
    
    @Override
    protected void updateItem(DetalleCaja item, boolean empty){
        if(!empty&& item!=null){
            if(item.getAnulado().equals(true)){
                this.setText("Si");
            }
            else{
                this.setText("No");
            }
        }
        else{
            this.setText("");
        }
        super.updateItem(item, empty);
    }
    
}
