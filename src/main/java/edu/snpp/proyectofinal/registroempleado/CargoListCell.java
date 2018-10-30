/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.registroempleado;

import edu.snpp.proyectofinal.entidades.Cargo;
import javafx.scene.control.ListCell;

/**
 *
 * @author fredybogado
 */
public class CargoListCell extends ListCell <Cargo> {
    @Override
    protected void updateItem(Cargo item,boolean empty){
        if(!empty){
            this.setText(item.getNombre());
        }
        else
        {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}
