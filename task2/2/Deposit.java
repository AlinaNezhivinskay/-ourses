import java.util.Date;
public class Deposit {
 private double amount;
 private double interest;
 private Date date;
 
 public Deposit(double amount, double interest, Date date)
 {
   System.out.println("Deposit");
   this.amount=amount;
   this.interest=interest;
   this.date=date; 
 }
 
 public double getAmount()
   {
     return amount;
   }
 public void setAmount(double amount)
   {
     this.amount=amount;
   }
 public double getInterest()
   {
     return interest;
   }
 public void setInterest(double interest)
   {
     this.interest=interest;
   }
 public Date getDate()
   {
     return date;
   }
 public void setDate(Date date)
   {
     this.date=date;
   }
 
 public double calculateInterest()
   {
     return (amount*interest)/100;
   }
 public void addAmount(double amount)
   {
     this.amount+=amount;
   }
 public void deductAmount(double amount)
   {
     this.amount-=amount;
   }
}