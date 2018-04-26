/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arturo.pluggingithub;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.arturo.pluggingithub.GithubIcon"
)
@ActionRegistration(
        iconBase = "org/arturo/pluggingithub/github.png",
        displayName = "#CTL_GithubIcon"
)
@ActionReference(path = "Toolbars/File", position = 0)
@Messages("CTL_GithubIcon=Github")
public final class GithubIcon implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        String[] menu={"Crear repositorio", "Clonar", "Commit", "Inicializar un repositorio", "Push", "Salir"};
        int opcion=JOptionPane.showOptionDialog(null, "Seleccione una opción", "GitHub", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, null);

        switch(opcion){
            case 0:
                Metodos.crearRepositorio();
                break;
            case 1:
                Metodos.clonarRepositorio();
                break;
            case 2:
                Metodos.commit();
                break;
            case 3:
                Metodos.inicializarRepositorio();
                break;
            case 4:
                Metodos.push();
                break;
            case 5:
                System.exit(0);
        }
    
    }
}
