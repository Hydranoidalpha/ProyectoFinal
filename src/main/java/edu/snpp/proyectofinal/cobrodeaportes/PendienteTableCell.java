/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.cobrodeaportes;

import edu.snpp.proyectofinal.entidades.MovimientoAporte;
import javafx.scene.control.TableCell;

/**
 *
 * @author fredybogado
 */
public class PendienteTableCell extends TableCell<MovimientoAporte, Boolean> {
    
    @Override
    protected void updateItem(Boolean item, boolean empty){
        if(!empty){
            if(item){
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
