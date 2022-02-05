public class Admin extends Account{

    public int _pinCode;
    public Admin()
    {
        accountType = "Admin";
    }
    public Admin(String log)
    {
        super(log);
        accountType = "Admin";
    }

    @Override
    public void Login() {
        System.out.println("admin Login goes here");
    }

    @Override
    public void Register(String login) {

    }

    @Override
    public String ToString() {
        return null;
    }
}
