import java.util.Date;
public class Credit {
 private double amount;
 private double percentages;
 private Date date;
 private Date term;
 
 public Credit(double amount, double percentages, Date date, Date term)
 {
   System.out.println("Credit");
   this.amount=amount;
   this.percentages=percentages;
   this.date=date; 
   this.term=term;
 }
 
 public double getAmount()
   {
     return amount;
   }
 public void setAmount(double amount)
   {
     this.amount=amount;
   }
 public double getPercentages()
   {
     return percentages;
   }
 public void setPercentages(double percentages)
   {
     this.percentages=percentages;
   }
 public Date getDate()
   {
     return date;
   }
 public void setDate(Date date)
   {
     this.date=date;
   }
 public Date getTerm()
   {
     return term;
   }
 public void setTerm(Date term)
   {
     this.term=term;
   }
 
 public double calculatePayment()
   {
     return (amount*percentages)/100;
   }
 public double calculatePercentages()
   {
     return (amount*percentages)/100;
   }
 public void deductAmount(double amount)
   {
     this.amount-=amount;
   }
}