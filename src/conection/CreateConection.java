/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

/**
 *
 * @author chaco
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
//librerias para  manejo de conexion a bd
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateConection {
    static Properties config = new Properties();
    String hostname=null;
    String port=null;
    String database=null;
    String username=null;
    String password=null;
      
      public CreateConection(){
          InputStream in = null;
          //Properties config = new Properties();
          String path = "C:\\Users\\chaco\\OneDrive\\Documentos\\NetBeansProjects\\FacturacionRestaurante\\src\\conection\\dbproduccion.properties";
          
          try{
              in = Files.newInputStream(Paths.get(path));
              config.load(in);
              in.close();
              //Invocar un metodo para leer la llave y valor y asignarlo a las variables globales 
             // loadProperties();
          } catch (IOException ex){
          System.out.println(ex.getMessage());
          }
          loadProperties();
          }
          
          public void loadProperties(){
              this.hostname = config.getProperty("hostname");
              this.port= config.getProperty("port");
              this.database = config.getProperty("database");
              this.username = config.getProperty("username");
              this.password = config.getProperty("password");
              System.out.println(this.hostname);
                            System.out.println(this.database);
                                          System.out.println(this.port);
                                                        System.out.println(this.username);
                                                                      System.out.println(this.password);
          }
          
          public Connection getConnection() throws SQLException{
          Connection conn= null;
          
          String jdbcUrl = "jdbc:postgresql://"+hostname+ ":" + port +"/"+database;
          conn = DriverManager.getConnection(jdbcUrl,username,password);
          System.out.println("Conexion establecida");
          return conn;
          }   
    
}

