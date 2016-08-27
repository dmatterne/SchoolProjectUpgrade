package beans;

import be.david.school.dbmodel.Users;
import database.Authentication;
import database.Connection;
import design.DesignWrapper;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

/**
 * Created by David on 27/08/2016.
 */
public class Login {
    private static final long serialVersionUID = 42396570680506543L;
    private Long prs_id;
    private String usr_name = "";
    private String usr_address = "";
    private String usr_email = "";
    private String usr_tel = "";
    private String usr_sex = "";
    private String usr_username = "";
    private String usr_password = "";
    private String usr_passwords = "";
    private String cache_id;
    private String usr_function = "";
    private String usr_year = "";
    private String usr_month = "";
    private String usr_day = "";
    private String content = "<html><head><title>Register</title></head>";
    private String error_message = "";
    private String cnt_id;
    private Connection con = new Connection();
//    private InputControl input_control;
    private Authentication authentication;
    private DesignWrapper design;
    Date birthday;
    private String user_id;
    private Users usr;

    public Login() {
//        this.input_control = new InputControl(this.con);
        this.authentication = new Authentication(this.con);
        this.design = new DesignWrapper(this.authentication);
    }

    public boolean loginUser() {
        this.authentication.setPersonId(this.user_id);
        boolean authenticated = this.authentication.isLoggedOut();
        this.design.setAccess(authenticated);
        this.design.setAuthentication(this.authentication);
        this.design.setUserName(this.authentication.getUsrUserName());
        if(authenticated) {
            boolean succes = false;
//            String sql = "select usrUserName, usrPassword, ID from Comato.Users where usrUserName=\'" + this.usr_username + "\'";
            if(this.con.createConnection()) {
                TypedQuery<Users> query = con.getEm().createNamedQuery(Users.FIND_LOGIN, Users.class)
                        .setParameter("uname", this.usr_username)
                        .setMaxResults(1);
                usr = query.getSingleResult();
                if(usr != null) {
//                    if(usr.getUsr_password().equals(MD5.hash(this.usr_password))) {
                    if(usr.getUsr_password().equals(this.usr_password)) {
                        this.authentication.setPersonId(String.valueOf(usr.getUsr_id()));
                        this.design.setUserName(this.authentication.getUsrUserName());
                        this.design.setAccess(true);
                        this.content = this.content + "Succesfully Logged In !";
                        succes = true;
                    } else {
                        this.buildForm();
                        this.content = this.content + "<br>Password and Username do not match !!!";
                    }
                } else {
                    this.buildForm();
                    this.content = this.content + "<br>Username not found in Database !";
                }
            }

            return succes;
        } else {
            this.content = this.content + "you are already logged in!";
            return false;
        }
    }

//    public void addUser() {
//        this.authentication.setPersonId(this.user_id);
//        this.design.setAccess(true);
//        this.design.setAuthentication(this.authentication);
//        this.design.setUserName(this.authentication.getUsrUserName());
//        Date birthDay = this.input_control.checkFuture(this.usr_day, this.usr_month, this.usr_year, "birthday");
//        if(this.authentication.isLoggedOut() || !this.usr_password.equals(this.usr_passwords) || !this.usr_password.equals("")) {
//            this.input_control.checkPasswordEquality(this.usr_password, this.usr_passwords, "usr_password", "usr_passwords");
//        }
//
//        this.input_control.checkEmpty(this.usr_username, "usr_username");
//        this.input_control.checkEmpty(this.usr_name, "usr_name");
//        this.input_control.checkEmpty(this.usr_address, "usr_address");
//        this.input_control.checkEmail(this.usr_email, "usr_email");
//        if(this.input_control.isErrors()) {
//            this.buildFormUser();
//        } else {
//            Users usr = null;
//            String sql = "SELECT distinct MAX(usrId) FROM Comato.Users";
//
//            try {
//                if(this.con.createConnection()) {
//                    if(!this.authentication.isLoggedIn()) {
//                        usr = new Users(this.con.getConnection());
//                    }
//
//                    this.con.generateResultSet(sql);
//                    if(this.con.nextResult()) {
//                        usr.setusrID(Long.valueOf(this.con.getResultLong(1) + 1L));
//                    } else {
//                        usr.setusrID(Long.valueOf(1L));
//                    }
//
//                    if(this.authentication.isLoggedIn()) {
//                        this.content = this.content + this.cnt_id;
//                        this.authentication.setcntID(Integer.valueOf(Integer.parseInt(this.cnt_id)));
//                        this.authentication.setusrName(this.usr_name);
//                        this.authentication.setusrAddress(this.usr_address);
//                        this.authentication.setusrEmail(this.usr_email);
//                        this.authentication.setusrTel(Long.valueOf(Long.parseLong(this.usr_tel)));
//                        this.authentication.setusrSex(this.usr_sex);
//                        this.authentication.setusrBirthday(birthDay);
//                        if(!this.usr_password.equals("")) {
//                            ;
//                        }
//
//                        this.authentication.setusrPassword(MD5.hash(this.usr_password));
//                        this.authentication.setusrUserName(this.usr_username);
//                        this.authentication.setusrFunction("Admin");
//                        this.authentication.save();
//                    } else {
//                        this.content = this.content + this.cnt_id;
//                        String e = MD5.hash(this.usr_password);
//                        usr.setcntID(Integer.valueOf(Integer.parseInt(this.cnt_id)));
//                        usr.setusrName(this.usr_name);
//                        usr.setusrAddress(this.usr_address);
//                        usr.setusrEmail(this.usr_email);
//
//                        try {
//                            usr.setusrTel(Long.valueOf(Long.parseLong(this.usr_tel)));
//                        } catch (NumberFormatException var6) {
//                            usr.setusrTel(Long.valueOf(0L));
//                        }
//
//                        usr.setusrSex(this.usr_sex);
//                        usr.setusrBirthday(birthDay);
//                        usr.setusrPassword(e);
//                        usr.setusrUserName(this.usr_username);
//                        usr.setusrFunction("User");
//                        usr.save();
//                        this.con.closeResultset();
//                        this.content = this.content + "<body>New User Registered";
//                    }
//                }
//            } catch (CacheException var7) {
//                this.content = this.content + var7.getMessage();
//            }
//        }
//
//    }

//    public void buildFormUser() {
//        this.authentication.setPersonId(this.user_id);
//        boolean authenticated = !this.authentication.isLoggedIn();
//        this.design.setAccess(authenticated);
//        this.design.setAuthentication(this.authentication);
//        this.design.setUserName(this.authentication.getUsrUserName());
//        if(authenticated) {
//            this.content = this.content + "<h2>Register Here </h2><form name=\"form\" method=\"post\" action=\"index.jsp?module=login&function=add_user\">";
//            Table table = new Table(2);
//            table.addItem("Username");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_username", this.usr_username, Input._TYPE_TEXT));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_username"));
//            table.addItem("Password");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_password", "", Input._TYPE_PASSWORD));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_password"));
//            table.addItem("Retype&nbsp;Password");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_passwords", "", Input._TYPE_PASSWORD));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_passwords"));
//            table.addItem("First&nbsp;&amp;&nbsp;Last&nbsp;Name");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_name", this.usr_name, Input._TYPE_TEXT));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_name"));
//            table.addItem("Address");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_address", this.usr_address, Input._TYPE_TEXT));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_address"));
//            table.addItem("Country");
//            table.addItem(new DropDown("cnt_id", String.valueOf(this.cnt_id), this.con, DropDown._TYPE_COUNTRIES));
//            table.addItem("E-mail");
//            table.addRedStarToLast();
//            table.addItem(new Input("usr_email", this.usr_email, Input._TYPE_TEXT));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_email"));
//            table.addItem("Birthday");
//            table.addRedStarToLast();
//            table.addItem(DropDown.getDateField("usr_day", "usr_month", "usr_year", 0, 100));
//            table.addToLastItem(this.input_control.getErrorMessage("birthday"));
//            table.addItem("Sex");
//            table.addItem("<input name=\"usr_sex\" type=\"radio\" value=\"M\" checked>M &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name=\"usr_sex\" type=\"radio\" value=\"V\">V ");
//            table.addItem("Telephone&nbsp;Number");
//            table.addItem(new Input("usr_tel", String.valueOf(this.usr_tel), Input._TYPE_TEXT));
//            table.addToLastItem(this.input_control.getErrorMessage("usr_tel"));
//            table.addItem("");
//            table.addRequiredPartOne();
//            table.addItem("");
//            table.addRequiredPartTwo();
//            String submit = "";
//            if(this.authentication.isLoggedIn()) {
//                submit = submit + new Input("submit", "Edit", Input._TYPE_SUBMIT);
//            } else {
//                submit = submit + new Input("submit", "Add", Input._TYPE_SUBMIT);
//            }
//
//            table.addItem(submit);
//            table.addItem("");
//            table.nextRow();
//            this.content = this.content + table;
//        }
//
//    }

    public void buildForm() {
        this.authentication.setPersonId(this.user_id);
        boolean authenticated = !this.authentication.isLoggedIn();
        this.design.setAccess(authenticated);
        this.design.setAuthentication(this.authentication);
        this.design.setUserName(this.authentication.getUsrUserName());
        if(authenticated) {
            this.content = this.content + "<form method=\"post\" action=\"index.jsp?module=login&function=loginOk\"><h2>Log In</h2><table border=\"0\"><tr><td width=\"147\">Username</td><td width=\"21\">:</td><td width=\"275\"><input type=\"text\" name=\"usr_username\"></td></tr><tr><td width=\"147\">Password</td><td width=\"21\">:</td><td width=\"275\"><input type=\"password\" name=\"usr_password\"></td></tr></table><br><input type=\"submit\" value=\"Enter\"/>";
        } else {
            this.content = this.content + "You are already logged in!";
        }

    }

//    public void editUser() {
//        this.authentication.setPersonId(this.user_id);
//        boolean authenticated = this.authentication.isAdmin();
//        this.design.setAccess(authenticated);
//        this.design.setUserName(this.authentication.getUsrUserName());
//        this.usr_username = this.authentication.getusrUserName();
//        this.usr_name = this.authentication.getusrName();
//        this.usr_address = this.authentication.getusrAddress();
//        this.cnt_id = this.authentication.getcntID().toString();
//        this.usr_email = this.authentication.getusrEmail();
//        this.birthday = this.input_control.checkFuture(this.usr_day, this.usr_month, this.usr_year, "birthday");
//        this.usr_sex = this.authentication.getusrSex();
//        this.usr_tel = this.authentication.getusrTel().toString();
//        this.input_control = new InputControl(this.con);
//        this.buildFormUser();
//    }

    public String getContent() {
        String lastError = this.con.getLastError();
        boolean error = this.con.isErrors();
        this.con.closeResultset();
        this.design.setErrorMessage(lastError);
        return this.design.getContent(this.content, error);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getUsr_function() {
        return this.usr_function;
    }

    public void setUsr_function(String usr_function) {
        this.usr_function = usr_function;
    }

    public String getUsr_password() {
        return this.usr_password;
    }

    public void setUsr_password(String usr_password) {
        this.usr_password = usr_password;
    }

    public String getUsr_username() {
        return this.usr_username;
    }

    public void setUsr_username(String usr_username) {
        this.usr_username = usr_username;
    }

    public Long getPrs_id() {
        return this.prs_id;
    }

    public void setPrs_id(Long prs_id) {
        this.prs_id = prs_id;
    }

    public String getCnt_id() {
        return this.cnt_id;
    }

    public void setCnt_id(String cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getUsr_day() {
        return this.usr_day;
    }

    public void setUsr_day(String usr_day) {
        this.usr_day = usr_day;
    }

    public String getUsr_email() {
        return this.usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_month() {
        return this.usr_month;
    }

    public void setUsr_month(String usr_month) {
        this.usr_month = usr_month;
    }

    public String getUsr_name() {
        return this.usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_passwords() {
        return this.usr_passwords;
    }

    public void setUsr_passwords(String usr_passwords) {
        this.usr_passwords = usr_passwords;
    }

    public String getUsr_sex() {
        return this.usr_sex;
    }

    public void setUsr_sex(String usr_sex) {
        this.usr_sex = usr_sex;
    }

    public String getUsr_tel() {
        return this.usr_tel;
    }

    public void setUsr_tel(String usr_tel) {
        this.usr_tel = usr_tel;
    }

    public String getUsr_year() {
        return this.usr_year;
    }

    public void setUsr_year(String usr_year) {
        this.usr_year = usr_year;
    }

    public String getUsr_address() {
        return this.usr_address;
    }

    public void setUsr_address(String usr_address) {
        this.usr_address = usr_address;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLoginUser_id() {
        return String.valueOf(usr.getUsr_id());
    }

}
