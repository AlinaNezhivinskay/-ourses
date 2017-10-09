import java.util.Date;
public class Borrower extends AClient {
 private Credit credit;
 
 public Borrower(long id,String name,String address,int phoneNumber,String passportData, Credit credit)
 {
   super(id, name, address, phoneNumber, passportData);
   System.out.println("Borrower");
   this.credit=credit;
 }
 
 public Credit getCredit()
   {
     return credit;
   }
 public void setCredit(Credit credit)
   {
     this.credit=credit;
   }
 
 
 public void makePayment(double amount)
   {
     credit.deductAmount(amount);
   }
 public void extendTerm(Date date)
   {
     credit.setDate(date);
   }
}