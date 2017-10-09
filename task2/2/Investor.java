public class Investor extends AClient {
 private Deposit deposit;
 
 public Investor(long id,String name,String address,int phoneNumber,String passportData, Deposit deposit)
 {
   super(id, name, address, phoneNumber, passportData);
   System.out.println("Investor");
   this.deposit=deposit;
 }
 
 public Deposit getDeposit()
   {
     return deposit;
   }
 public void setDeposit(Deposit deposit)
   {
     this.deposit=deposit;
   }
 
 
 public double getInterest()
   {
     return deposit.calculateInterest();
   }
 public void takeDeposit(double amount)
   {
     deposit.deductAmount(amount);
   }
 public void addDeposit(double amount)
   {
     deposit.addAmount(amount);
   }
}