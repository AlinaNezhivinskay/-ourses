import java.util.Date;
public class BankAccount {
 private long accountNumber;
 private double balance;
 private Date openDate;
 private Date term;
 
 public BankAccount(long accountNumber, double balance, Date openDate, Date term)
 {
   System.out.println("BankAccount");
   this.accountNumber=accountNumber;
   this.balance=balance;
   this.openDate=openDate; 
   this.term=term;
 }
 
 public long getAccountNumber()
   {
     return accountNumber;
   }
 public void setAccountNumber(long accountNumber)
   {
     this.accountNumber=accountNumber;
   }
 public double getBalance()
   {
     return balance;
   }
 public void setBalance(double balance)
   {
     this.balance=balance;
   }
 public Date getOpenDate()
   {
     return openDate;
   }
 public void setOpenDate(Date openDate)
   {
     this.openDate=openDate;
   }
 public Date getTerm()
   {
     return term;
   }
 public void setTerm(Date term)
   {
     this.term=term;
   }
 
 public void addBalance(double amount)
   {
     this.balance+=amount;
   }
 public void deductBalance(double amount)
   {
     this.balance-=amount;
   }
}