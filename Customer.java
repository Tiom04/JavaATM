import java.lang.Object;

public class Customer extends Account{

    public String cardNumber;
    public Integer pinCode;
    public Double amount;
    public String status;

    public Customer()
    {
        accountType = "Customer";
        status = "Active";
    }
    public Customer(String log)
    {
        super(log);
        accountType = "Customer";
        status = "Active";
    }
    public Customer(String login, String cardNum, Double amount, String stat, Integer pass)
    {
        super(login);
        accountType = "Customer";
        cardNumber = cardNum;
        this.amount = amount;
        status = stat;
        pinCode = pass;
    }

    @Override
    public void Login() {

    }

    @Override
    public void Register(String login) {

    }

    @Override
    public String ToString() {
        return null;
    }
}
