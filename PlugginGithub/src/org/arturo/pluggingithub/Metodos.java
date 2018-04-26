package org.arturo.pluggingithub;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

/**
 * Esta clase contiene los metodos necesarios para que funcione el programa
 *
 * @author Arturo
 */
public class Metodos{

    /**
     * Metodo que permite introducir la direccion url del repositorio
     *
     * @param mensaje Mensaje que se desea mostrar
     * @return Direccion del repositorio
     */
    public static String urlRep(String mensaje){
        String url=JOptionPane.showInputDialog(mensaje);
        return url;
    }

    /**
     * Metodo que permite introducir la ruta de un archivo en el ordenador
     *
     * @param mensaje Mensaje que se desea mostrar
     * @return Ruta del archivo
     */
    public static String rutaArchivo(String mensaje){
        String ruta=JOptionPane.showInputDialog(mensaje);
        return ruta;
    }

    /**
     * Metodo que permite añadir un mensaje de commit
     *
     * @return Devuelve el mensaje deseado para el commit
     */
    public static String mensCommit(){
        String mCommit=JOptionPane.showInputDialog("Indroduce un mensaje para el commit");
        return mCommit;
    }

    /**
     * Metodo que permite crear un repositorio suin tener que entrar en github
     */
    public static void crearRepositorio(){
        try{
            DatosUsuario.loging();
            DatosUsuario.nombreRepo();
            DatosUsuario.descripcion();
            GitHub github=GitHub.connectUsingPassword(DatosUsuario.getUser(), DatosUsuario.getPass());
            GHRepository builder=github.createRepository(DatosUsuario.getNombreRep(), DatosUsuario.getDescr(), " ", true);
        }catch(IOException ex){
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite clonar un repositorio
     */
    public static void clonarRepositorio(){

        try{
            Git.cloneRepository()
                    .setURI(urlRep("URL del repositorio a clonar"))
                    .setDirectory(new File(rutaArchivo("Ruta de la carpeta para el repositorio clonado")))
                    .call();
        }catch(GitAPIException ex){
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite hacer un add y un commit a un archivo
     */
    public static void commit(){
        String ruta=rutaArchivo("indroduce la ruta del archivo para el commit");
        String mens=mensCommit();
        try{
            FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
            Repository repository=repositoryBuilder.setGitDir(new File(ruta))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git=new Git(repository);
            AddCommand add=git.add();
            add.addFilepattern(ruta).call();
            CommitCommand commit=git.commit();
            commit.setMessage(mens).call();
        }catch(IOException ex){
            System.out.println("Error:"+ex);
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }

    }

    /**
     * Metodo que inicializa un repositorio en la carpeta deseada
     */
    public static void inicializarRepositorio(){
        String ruta=rutaArchivo("Ruta de la carpeta para el repositorio");
        InitCommand repositorio=new InitCommand();
        try{
            repositorio.setDirectory(new File(ruta)).call();
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }
    }

    /**
     * Metodo que permite hacer un push del codigo comentado a un repositorio
     * creado
     */
    public static void push(){
        String url=urlRep("URL del repositorio");
        String repositorio=rutaArchivo("Ruta de la carpeta del repositorio");
        String contrasena=DatosUsuario.getUser();
        String usuario=DatosUsuario.getPass();
        try{
            FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
            Repository repository=repositoryBuilder.setGitDir(new File(repositorio))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git=new Git(repository);

            RemoteAddCommand remoteAddCommand=git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(url));
            remoteAddCommand.call();

            PushCommand pushCommand=git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(usuario, contrasena));
            pushCommand.call();

        }catch(IOException ex){
            System.out.println("Error: "+ex);
        }catch(URISyntaxException ex){
            System.out.println("Error: "+ex);
        }catch(GitAPIException ex){
            System.out.println("Error: "+ex);
        }
    }
}
