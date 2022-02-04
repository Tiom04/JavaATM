import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public final String PATH_CUSTOMERS = "C:\\tmp\\java\\CustomerList.txt",
                        PATH_ADMINS = "C:\\tmp\\java\\AdminList.txt",
                        PATH_UNBLOCKS = "C:\\tmp\\java\\UnblockRequests.txt",
                        PATH_TRANSACTIONS = "C:\\tmp\\java\\TransactionList.txt";
    public static HashSet<Admin> adminList = new HashSet<Admin>();
    public static HashSet<Customer> customerList = new HashSet<Customer>();
    public static HashSet<UnblockRequest> unblockRequestList = new HashSet<UnblockRequest>();


        public static void main(String[] args) {
            UseATM();

        }

        private static void UseATM()
        {

            System.out.println("Welcome to my Banking system!\n");






        }
}
