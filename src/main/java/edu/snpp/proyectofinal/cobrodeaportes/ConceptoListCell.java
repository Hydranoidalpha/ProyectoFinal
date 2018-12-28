/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.cobrodeaportes;

import edu.snpp.proyectofinal.entidades.Concepto;
import javafx.scene.control.ListCell;

/**
 *
 * @author fredybogado
 */
public class ConceptoListCell extends ListCell <Concepto> {
    @Override
    protected void updateItem(Concepto item,boolean empty){
        if(!empty){
            this.setText(item.getConcepto());
        }
        else
        {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
