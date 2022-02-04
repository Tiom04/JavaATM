import java.lang.System;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

public abstract class Account {
    public String accLogin;
    public String accountType;
    public String GetLogin(){ return accLogin;}
    public String GetType(){ return accountType;}

    public Account()
    {

    }
    public Account(String log)
    {
        accLogin = log;
    }
    public abstract void Login();
    public abstract void Register(String login);
    public abstract String ToString();
}
