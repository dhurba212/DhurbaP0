package dev.ghimire.entities;





public class Account {
    //private static int idMaker=0;

//Map<Integer, Set<Account>> allAccounts = new HashMap<>();
//Set<Transaction>allTransaction = new HashSet<>();

    private int accountId;
    private int accountType;//account type 1-checking 2-saving 3-investment 4-holidayfund
    private double totalBalance;
    private int clientId;


    public Account() {

    }

    public Account(int accountId, int accountType, double totalBalance, int clientId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.totalBalance= totalBalance;
        this.clientId = clientId;


    }

    public void deposit(double amount)
    {
        if(amount>0)
        {
            this.totalBalance=this.totalBalance+amount;
        }


    }
    public void withdrawl(double amount)
    {
        if(amount>0 && amount<this.totalBalance)
        {
            this.totalBalance=this.totalBalance-amount;
        }


    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType=" + accountType +
                ", totalBalance=" + totalBalance +
                ", ownerId=" + clientId +
                '}';
    }

    //    private enum AccountType{
//        CHECKING,
//        SAVING,
//        INVESTMENT,
//        CREDIT,
//        HOLIDAYSAVING;
//
//    }
}
