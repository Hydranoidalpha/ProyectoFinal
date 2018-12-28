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
public class MesTableCell extends TableCell<MovimientoAporte, Integer> {
    @Override
    protected void updateItem(Integer item, boolean empty){
        if(!empty && item!=null){
            switch (item){
                case 2:
                    this.setText("Febrero");
                    break;
                case 3:
                    this.setText("Marzo");
                    break;
                case 4:
                    this.setText("Abril");
                    break;
                case 5:
                    this.setText("Mayo");
                    break;
                case 6:
                    this.setText("Junio");
                    break;
                case 7:
                    this.setText("Julio");
                    break;
                case 8:
                    this.setText("Agosto");
                    break;
                case 9:
                    this.setText("Septiembre");
                    break;
                case 10:
                    this.setText("Octubre");
                    break;
                case 11:
                    this.setText("Noviembre");
                    break;
            }
        }else{
            this.setText("");
        }
    }
    
}
