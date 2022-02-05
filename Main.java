import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class Main {

    public static HashSet<Admin> adminList = new HashSet<Admin>();
    public static HashSet<Customer> customerList = new HashSet<Customer>();
    public static HashSet<UnblockRequest> unblockRequestList = new HashSet<UnblockRequest>();

    public static final Path path_customers = Paths.get("C:\\tmp\\java\\CustomerList.txt");
    public static final Path path_admins = Paths.get("C:\\tmp\\java\\AdminList.txt");
    public static final Path path_unblocks = Paths.get("C:\\tmp\\java\\UnblockRequests.txt");
    public static final Path path_transactions = Paths.get("C:\\tmp\\java\\TransactionList.txt");


        public static void main(String[] args) {
            UseATM();

        }

        private static void UseATM(){
            System.out.println("Welcome to my Banking system!\n");
            try {
                File cstFl = new File(path_customers.toString());
                if (!cstFl.exists()) {
                    cstFl.createNewFile();
                }
                File admFl = new File(path_admins.toString());
                if (!admFl.exists()) {
                    admFl.createNewFile();
                }
                File unbFl = new File(path_unblocks.toString());
                if (!unbFl.exists()) {
                    unbFl.createNewFile();
                }
                File trsFl = new File(path_transactions.toString());
                if (!trsFl.exists()) {
                    trsFl.createNewFile();
                }
            }
            catch (java.io.IOException e)
            {
                System.out.println("java io exception");
            }
            Account currentAccount = LoginOrRegister();




        }

    private static Account LoginOrRegister(){
            System.out.print("Enter your login to sign in or register: ");
            Scanner keyboard = new Scanner(System.in);
            String loginTmp = keyboard.nextLine();
            boolean containFlag = false;
            Account currentAccount;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path_admins.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{

            //read string = br.readLine();
            //while line !=null line - readline

        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //check adminlist first, then customerlist. if containFlag is false finally then create new account

        if(!containFlag)
        {
            System.out.print("Creating new account. 1 = ADMIN, 2 = Customer\nChoice: ");
            int optionReg = 0;
            do{
                try{ optionReg = keyboard.nextInt();}
            catch (Exception e){System.out.println("wrong input");}
            }
            while(optionReg > 2 || optionReg < 1);

            currentAccount = optionReg == 1 ? new Admin() : new Customer();
            currentAccount.Register(loginTmp);

            if(currentAccount.GetType()=="Admin")
            {
                adminList.add((Admin)currentAccount);
                System.out.println(String.format("Your id number is %d", adminList.size() - 1));
            }
            else {
                customerList.add((Customer) currentAccount);
                System.out.println(String.format("Your id number is %d", customerList.size() - 1));
            }
            return currentAccount;
        }
        return new Admin();
    }


}
