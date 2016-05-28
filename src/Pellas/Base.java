/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pellas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Pablo Nebot
 */
public class Base {
  


  public   void crearTabla() 
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:pellas.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE   IF NOT EXISTS PELLAS" +
                   "(NOMBRE           TEXT    NOT NULL, " + 
                   " FALTAS            INT     NOT NULL) " ;
                   
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
      public Vector capturaRanking(){
        Vector vectorRanking=new Vector();
        
     
      
             Connection c = null;
    Statement stmt = null;
      try {
          
      c = DriverManager.getConnection("jdbc:sqlite:pellas.db");
      c.setAutoCommit(false);
      

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM pellas order by faltas desc  ;");
            
            while(rs.next())
            {
               
                String texto_nombre=rs.getString("NOMBRE");
                String texto_marcador=rs.getString("faltas");
                   texto_marcador=texto_marcador+"  horas ausentadas";
           String textoporcentaje=rs.getInt("faltas")*100/252+"%";
        
           
               
               
                vectorRanking.add(texto_nombre);
                 vectorRanking.add(texto_marcador);
                  vectorRanking.add(textoporcentaje);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      
        }
  
      return vectorRanking;
        
        
        
    }

public void meterDatos()
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:pellas.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Pablo', 0);"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Marco', 0);"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Luis', 0);"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Jesus', 0);"; 
      stmt.executeUpdate(sql);
       sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Eugenio', 0);"; 
      stmt.executeUpdate(sql);
       sql = "INSERT INTO PELLAS (NOMBRE,FALTAS) " +
                   "VALUES ('Jorge', 0);"; 
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
public  void sumarPellas(String nombre)
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:pellas.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "UPDATE PELLAS set FALTAS = FALTAS+1 where NOMBRE like '%"+nombre+"%';";
      stmt.executeUpdate(sql);
      c.commit();

    
     
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }


  public  int consultarFaltas(String nombre)
  {
    Connection c = null;
    Statement stmt = null;
    int faltas = 0;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:pellas.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT faltas FROM PELLAS where NOMBRE like '%"+nombre+"%';" );
      while ( rs.next() ) {
          faltas = rs.getInt("faltas");
      
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    
    return faltas;

  }
}


    

