/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.registroempleado;

import edu.snpp.proyectofinal.entidades.Cargo;
import edu.snpp.proyectofinal.entidades.Empleado;
import javafx.scene.control.TableCell;

/**
 *
 * @author fredybogado
 */
public class CargoTableCell extends TableCell<Empleado, Cargo> {
    @Override
    protected void updateItem(Cargo item, boolean empty){
        if(!empty && item!=null){
            this.setText(item.getNombre());
        }
        else{
            this.setText("");
        }
        super.updateItem(item, empty);
   } 
}
