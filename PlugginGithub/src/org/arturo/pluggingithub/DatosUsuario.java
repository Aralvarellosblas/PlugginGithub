package org.arturo.pluggingithub;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Clase con los Datos Privados de usuario
 *
 * @author Arturo
 */
public class DatosUsuario{

    private static String user, pass, nombreRep, descr;

    public static String getUser(){
        return user;
    }

    public static void setUser(String user){
        DatosUsuario.user=user;
    }

    public static String getPass(){
        return pass;
    }

    public static void setPass(String pass){
        DatosUsuario.pass=pass;
    }

    public static String getNombreRep(){
        return nombreRep;
    }

    public static void setNombreRep(String nombreRep){
        DatosUsuario.nombreRep=nombreRep;
    }

    public static String getDescr(){
        return descr;
    }

    public static void setDescr(String descr){
        DatosUsuario.descr=descr;
    }

    /**
     * Metodo que permite conectarse con tus credenciales de usuario y
     * contraseña
     */
    public static void loging(){
        JPasswordField pf = new JPasswordField();
        user=JOptionPane.showInputDialog("Introduce el nombre de usuario(Correo)");
        JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        pass= new String(pf.getPassword());
        //pass=JOptionPane.showInputDialog("Introduce la contraseña de usuario");
        
    }

    /**
     * Metodo para nombrar un nuevo repositorio
     *
     * @return Devuelve el nombre del repositorio
     */
    public static String nombreRepo(){
        nombreRep=JOptionPane.showInputDialog("Nombre del repositorio deseado");
        return nombreRep;

    }

    /**
     * Metodo que permite añadir una descripcion al repositorio
     *
     * @return Devuelve la descripcion deseada
     */
    public static String descripcion(){
        descr=JOptionPane.showInputDialog("Descripcion del repositorio");
        return descr;

    }
}
