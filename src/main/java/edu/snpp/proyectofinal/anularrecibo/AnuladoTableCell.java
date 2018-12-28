/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.anularrecibo;

import edu.snpp.proyectofinal.entidades.DetalleCaja;
import javafx.scene.control.TableCell;

/**
 *
 * @author fredybogado
 */
public class AnuladoTableCell extends TableCell<DetalleCaja, Boolean> {
    @Override
    protected void updateItem(Boolean item, boolean empty){
        if(!empty && item!=null){
            if(item.equals(true)){
                this.setText("Si");
            }else{
                this.setText("No");
            }
            
        }
        else{
            this.setText("");
        }
   } 
    
}
