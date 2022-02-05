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
        BufferedReader br = null;
        String tmpString;
        try {
            br = new BufferedReader(new FileReader(Main.path_customers.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            do {
                tmpString = br.readLine();

                if(tmpString == null || tmpString == "") {
                    continue;
                }
                try {
                    Customer tmpCustomerAdd = new Customer(br.readLine(), br.readLine(), Double.parseDouble(br.readLine()),
                            br.readLine(), Integer.parseInt(br.readLine()));
                    if(tmpCustomerAdd != null) {
                        Main.customerList.add(tmpCustomerAdd);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } while (tmpString != null);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Customer val : Main.customerList) {
            if(val.GetLogin() == accLogin)
            {
                System.out.print("Enter your Pin code: ");
                boolean passStatus = false;
                for(int i = 3; i > 0; i--)
                {
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println(String.format("Enter your pin... (you have %d attemp(s) left)"));
                    int tmpCode = keyboard.nextInt();
                    if(tmpCode == val.pinCode)
                    {
                        System.out.println("Logged in");
                        val.status = "Active";
                        passStatus = true;
                        break;
                    }
                }
                if(!passStatus) {
                    status = "Blocked";
                    System.out.println("Your account has been blocked due to entering wrong password for 3 times");
                }
                break;
            }
        }
    }

    @Override
    public void Register(String login) {

    }

    @Override
    public String ToString() {
        return null;
    }
}
