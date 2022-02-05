import java.lang.Object;
import static java.nio.file.StandardOpenOption.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

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
        System.out.println("Customer Login goes here");
    }

    @Override
    public void Register(String login) {

    }

    @Override
    public String ToString() {
        return null;
    }
}
