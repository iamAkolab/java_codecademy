public class Bank{
    private CheckingAccount accountOne;
  private CheckingAccount accountTwo;
  
  public Bank(){
    accountOne = new CheckingAccount("Zeus", 100, "1");
    accountTwo = new CheckingAccount("Hades", 200, "2");
    myAccount = new CheckingAccount("Mike", 300, "3");
  }
  
  public static void main(String[] args){
    Bank bankOfGods = new Bank();
    System.out.println(bankOfGods.accountOne.getBalance());
    bankOfGods.accountOne.setBalance(5000);
    System.out.println(bankOfGods.accountOne.getBalance());
    System.out.println(bankOfGods.accountOne.getMonthlyInterest());
  }
}
