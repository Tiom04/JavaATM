import sun.invoke.empty.Empty;

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
            Scanner keyboard = new Scanner(System.in);
            try{
                File directory = new File("C:\\tmp\\java");
                if(!directory.exists()){
                    directory.mkdirs();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

            if(currentAccount.GetType() == "Admin")
            {
                Admin currentAdmin = new Admin();
                currentAdmin = (Admin)currentAccount;
                AdminMenu(currentAdmin);
            }
            else
            {
                Customer currentCustomer = new Customer();
                currentCustomer = (Customer)currentAccount;
                if(currentCustomer.status != "Blocked")
                {
                    CustomerMenu(currentCustomer);
                }
                else
                {
                    System.out.println("Your account is currenty blocked\nCreate unblock request? (Y/N)");
                    String option = keyboard.nextLine();
                    if(option == "Y" || option == "y")
                    {
                        System.out.print("Write your unblock request message here: ");
                        String comment = keyboard.nextLine();
                        UnblockRequest unblock = new UnblockRequest(comment, currentCustomer);
                        unblockRequestList.add(unblock);
                        System.out.println("Unblock request added, please wait for admins to see it");
                    }
                    else if(option == "N" || option == "n")
                    {
                        System.out.println("oko, bye");
                    }
                    else{
                        System.out.println("Wrong input");
                    }
                }
            }


            SaveAll(adminList, customerList, unblockRequestList);
        }

    private static void SaveAll(HashSet<Admin> admList, HashSet<Customer> custList, HashSet<UnblockRequest> unblReqList) {
            BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path_admins.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Admin val : admList) {
            try {
                bw.write(val.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter(path_customers.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Customer val : custList) {
            try {
                bw.write(val.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter(path_unblocks.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (UnblockRequest val: unblReqList) {
            try {
                bw.write(val.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CustomerMenu(Customer currentCustomer) {
            System.out.println("Customer menu goes here");
    }

    private static void AdminMenu(Admin currentAdmin) {
        System.out.println("Admin menu goes here");
    }

    private static Account LoginOrRegister(){
            System.out.print("Enter your login to sign in or register: ");
            Scanner keyboard = new Scanner(System.in);
            String loginTmp = keyboard.nextLine();
            boolean containFlag = false;
            Account currentAccount;
            String tmpString;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path_admins.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            do {
                tmpString = br.readLine();
                if(tmpString == null || tmpString == "") {
                    continue;
                }
                Admin tmpAdminAdd = new Admin(br.readLine());
                tmpAdminAdd._pinCode = Integer.parseInt(br.readLine());
                if(tmpAdminAdd != null) {
                    adminList.add(tmpAdminAdd);
                }
            } while (tmpString != null);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Admin val : adminList) {
            if(val.GetLogin() == loginTmp)
            {
                containFlag = true;
                val.Login();//goes to login inside the class
                return val;
            }
        }

        try {
            br = new BufferedReader(new FileReader(path_customers.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            do{
               tmpString = br.readLine();
               if(tmpString == null || tmpString == ""){
                   continue;
               }
            }while(tmpString != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Customer val : customerList) {
            if(val.GetLogin()==loginTmp)
            {
                containFlag = true;
                val.Login();//goes to customer login
                return val;
            }
        }
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
