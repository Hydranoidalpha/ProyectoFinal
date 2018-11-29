/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.generarcobrodeaportes;

import edu.snpp.proyectofinal.entidades.Alumno;
import javafx.scene.control.ListCell;

/**
 *
 * @author fredybogado
 */
public class AlumnoListCell extends ListCell <Alumno> {
    @Override
    protected void updateItem(Alumno item,boolean empty){
        if(!empty){
            this.setText(item.getNombre()+" "+item.getApellido());
        }
        else
        {
            this.setText("");
        }
        super.updateItem(item, empty);
    }
}