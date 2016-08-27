package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by stannisbaratheon on 26/08/16.
 */
public class Connection {


    private static final String error_line = "\n###########################################################################\n";

    //    private Database connection = null;

    private EntityManager em;
    private EntityManagerFactory emf;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    private ResultSet resultset = null;
    private String last_error = "";
    private boolean connected = false;
    private boolean errors = false;
    private static Logger logger = Logger.getLogger("Database.Connection");
    private static FileHandler fh;
//    private static final String url = "jdbc:Cache://localhost:1972/USER";
//
    public Connection() {
        try {
            fh = new FileHandler("kinepolis.log");
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception var2) {
            logger.info("We could not open the file handler, just using default log");
        }

        logger.info("\n###########################################################################\nInititialising Connection Class\n###########################################################################\n");
    }

    public boolean closeResultset() {
        try {
            em.getTransaction().commit();
            em.close();
            emf.close();
            logger.log(Level.INFO, "\n###########################################################################\nclosing the connection and cleaning up\n###########################################################################\n");
            this.resultset.close();
            this.errors = false;
        } catch (Exception var4) {
            logger.info("No resultset to close!");
        }

        try {
//            this.connection.closeAllObjects();
//            this.connection.close();
//            this.connection.flush();
            this.connected = false;
        } catch (Exception var3) {
            logger.log(Level.WARNING, "Failed to close the connection", var3);
            this.errors = true;
            return false;
        }

        try {
            fh.flush();
            logger.removeHandler(fh);
            fh.close();
        } catch (Exception var2) {
            logger.info("failed to close the log file, or log file never opened");
        }

        return true;
    }

    public boolean createConnection() {
        boolean check = false;
        if(this.connected) {
            check = true;
        } else {

            try {
                emf = Persistence.createEntityManagerFactory("DavidPu");
                em = emf.createEntityManager();
                em.getTransaction().begin();
                this.connected = true;
                check = true;
            } catch (Exception var5) {
                logger.log(Level.SEVERE, "Failed to connect to Database", var5);
                this.connected = false;
                check = false;
            }
        }

        return check;
    }

    public boolean nextResult() {
        try {
            return this.resultset.next();
        } catch (Exception var2) {
            logger.log(Level.SEVERE, "Failed to get next row in resultset", var2);
            this.connected = false;
            this.errors = true;
            return false;
        }
    }

    public int getResultInt(int position) {
        try {
            return this.resultset.getInt(position);
        } catch (Exception var3) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to get int from resultset", var3);
            this.errors = true;
            return 0;
        }
    }

    public Timestamp getResultTimeStamp(int position) {
        try {
            return this.resultset.getTimestamp(position);
        } catch (Exception var3) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to get int from resultset", var3);
            this.errors = true;
            return null;
        }
    }

    public String getResultString(int position) {
        try {
            return this.resultset.getString(position);
        } catch (Exception var3) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to get String from resultset", var3);
            this.errors = true;
            return "";
        }
    }

    public long getResultLong(int position) {
        try {
            return this.resultset.getLong(position);
        } catch (Exception var3) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to get Long from resultset", var3);
            this.errors = true;
            return 0L;
        }
    }

    public Date getResultDate(int position) {
        try {
            return this.resultset.getDate(position);
        } catch (Exception var3) {
            this.connected = false;
            this.errors = true;
            logger.log(Level.SEVERE, "Failed to get Date from resultset", var3);
            return null;
        }
    }

    public boolean generateResultSet(String sqlQuery) {
        try {
//            CacheQuery stmt = new CacheQuery(this.connection, sqlQuery);
//            this.resultset = stmt.execute();
            return true;
        } catch (Exception var4) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to generate resultset", var4);
            this.errors = true;
            return false;
        }
    }

    public boolean executeQuery(String sqlQuery) {
        try {
//            CacheQuery stmt = new CacheQuery(this.connection, sqlQuery);
//            stmt.execute();
            return true;
        } catch (Exception var4) {
            this.connected = false;
            logger.log(Level.SEVERE, "Failed to execute query", var4);
            this.errors = true;
            return false;
        }
    }

//    public Database getConnection() {
//        return this.connection;
//    }

    public String getLastError() {
        return this.last_error;
    }

    public boolean isErrors() {
        return this.errors;
    }

}
