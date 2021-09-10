/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import java.sql.*;

/**
 *
 * @author jeiss
 */
public class ConexionSqlServer {
        private Connection con;
        private String user;
        private String password;
        private Statement stmt;
        private String url;
        private String nombreBD;

        public ConexionSqlServer( String us, String pass){
           user = "jeisson";
           password = "jeisson";
           
           conectar();
        }

        public ConexionSqlServer() {
            this("jeisson","jeisson");
        }

        //Metodo para conectarce a una base de datos
        private void conectar(){
            try{

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
            catch(ClassNotFoundException e){
                System.err.println("'conectarAccess()' Error al intentar cargar Driver. "+e.getMessage());
                e.printStackTrace();
            }

            try{
                /*
                url="jdbc:sqlserver://DESKTOP-PO18RAS\\SQLEXPRESS:60389;"
                        + "database=CARLOS;"
                        + "user=profesor lugo@DESKTOP-PO18RAS;"
                        + "password="+password+";"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
                */
                url="jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=lyjstore";
                con = DriverManager.getConnection(url, user, password);

                con.setAutoCommit(true);
                System.out.println("Conexion exitosa sql server...");
            }catch(Exception e){
                System.out.println("Error al conectarce: "+e);
            }
        }


        //Metodo que permite ejecutar una consulta y retorna un objeto ResulSet con
        //los resultados.
        public ResultSet executeQueryStatement(String cad) {
            ResultSet rs = null;
                try{
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(cad);
                    System.out.println("Consulta realizada...  ");
                    
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            return rs;
        }

        //metodo que permite ejecutar una transaccion de insercion o actualizacion
        //o eliminacion
        public boolean executeUpdateStatement(String cad){
            int r = 0;
            try{
                stmt = con.createStatement();
                r = stmt.executeUpdate(cad);
                System.out.println("Actualizacion realizada...  " + r);
                //con.commit();
                stmt.close();
                return true;
            }catch(Exception ex){
                System.out.println("No se pudo efectuar la grabacion..." + ex);
                return false;
            }
        }

        //Metodo para invocar un procedimiento almacenado
        public void executeProcedure(String cadProc){
            try{

                CallableStatement proc =con.prepareCall("{ call " + cadProc + " }");
                proc.execute();
            }catch (SQLException e)
            {
                System.out.println("Problemas con la invocacion del procedimiento " + cadProc);
            }
        }


        

        //Objeto que cierra la conexion con la base de datos.
        public void closeConecction(){
            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                System.out.println("Error! " + e);
            }
        }


        public void setCon(Connection con) {
            this.con = con;
        }

        public Connection getCon() {
            return con;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setStmt(Statement stmt) {
            this.stmt = stmt;
        }

        public Statement getStmt() {
            return stmt;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setNombreBD(String nombreBD) {
            this.nombreBD = nombreBD;
        }

        public String getNombreBD() {
            return nombreBD;
        }

}
