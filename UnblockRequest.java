public class UnblockRequest {
    public Customer customerAccount;
    public String customerComment;

    public UnblockRequest(String comment, Customer customer)
    {
        customerAccount = customer;
        customerComment = comment;
    }
    public String ToString()
    {
        return String.format("%s\n%s\n", customerAccount.GetLogin(), customerComment);
    }
}
