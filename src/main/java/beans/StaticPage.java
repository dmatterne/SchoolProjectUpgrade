package beans;

/**
 * Created by David on 25/08/2016.
 */
public class StaticPage {

    private String user_id = "0";
    private DesignWrapper design;
    private Connection con = new Connection();
    private Authentication authentication;

    public StaticPage() {
        this.authentication = new Authentication(this.con);
        this.design = new DesignWrapper(this.authentication);
    }

    public void authorizeAll() {

    }

    public void authorizeAdmin() {

    }

    public void setTitel(String titel) {

    }

    public String wrapDesign(String content) {
        //String lastError = this.con.getLastError();
        //this.con.closeResultset();
        //this.design.setErrorMessage(lastError);
        return this.design.getContent(content, false);
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}


//    private static final long serialVersionUID = 432158943682L;
//    private String user_id = "0";
//    private Connection con = new Connection();
//    private Authentication authentication;
//    private DesignWrapper design;
//    private boolean authenticated;
//
//    public StaticPage() {
//        this.authentication = new Authentication(this.con);
//        this.design = new DesignWrapper(this.authentication);
//    }
//
//    private void authorize() {
//        this.design.setAccess(this.authenticated);
//        this.design.setUserName(this.authentication.getUsrUserName());
//    }
//
//    public void authorizeAll() {
//        this.authentication.setPersonId(this.user_id);
//        this.authenticated = true;
//        this.authorize();
//    }
//
//    public void authorizeAdmin() {
//        this.authentication.setPersonId(this.user_id);
//        this.authenticated = this.authentication.isAdmin();
//        this.authorize();
//    }
//
//    public void authorizeEmployee() {
//        this.authentication.setPersonId(this.user_id);
//        this.authenticated = this.authentication.isEmployee();
//        this.authorize();
//    }
//
//    public void authorizeLoggedIn() {
//        this.authentication.setPersonId(this.user_id);
//        this.authenticated = this.authentication.isLoggedIn();
//        this.authorize();
//    }
//
//    public void setTitel(String titel) {
//        this.design.setTitel(titel);
//    }
//
//    public String wrapDesign(String content) {
//        String lastError = this.con.getLastError();
//        this.con.closeResultset();
//        this.design.setErrorMessage(lastError);
//        return this.design.getContent(content, false);
//    }
//
//    public String getUser_id() {
//        return this.user_id;
//    }
//
//    public void setUser_id(String user_id) {
//        this.user_id = user_id;
//    }