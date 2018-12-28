/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.anularrecibo;

import edu.snpp.proyectofinal.entidades.Concepto;
import edu.snpp.proyectofinal.entidades.DetalleCaja;
import javafx.scene.control.TableCell;

/**
 *
 * @author fredybogado
 */
public class ConceptoTableCell extends TableCell<DetalleCaja, Concepto> {
    @Override
    protected void updateItem(Concepto item, boolean empty){
        if(!empty && item!=null){
            this.setText(item.getConcepto());
        }
        else{
            this.setText("");
        }
        super.updateItem(item, empty);
   } 
    
}
