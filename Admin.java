import java.io.*;
import java.util.Scanner;

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
        BufferedReader br = null;
        String tmpString;
        try {
            br = new BufferedReader(new FileReader(Main.path_admins.toString()));
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
                    Main.adminList.add(tmpAdminAdd);
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

        for (Admin val : Main.adminList) {
            if(val.GetLogin() == accLogin)
            {
                System.out.print("Enter your Pin code: ");
                boolean passStatus = false;
                for(int i = 3; i > 0; i--)
                {
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println(String.format("Enter your pin... (you have %d attemp(s) left)"));
                    int tmpCode = keyboard.nextInt();
                    if(tmpCode == val._pinCode)
                    {
                        System.out.print("Logged in");
                        passStatus = true;
                        break;
                    }
                }
                if (!passStatus)
                {
                    System.out.println("Wrong password, try later");
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
