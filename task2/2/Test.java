import java.util.Date;
public class Test {
 public static void main(String[] args)
 {                       
   AClient client1=new Investor(1,"Vasja Petrov","Limozha,25",123456,"AB1234558",new Deposit(200,5,new Date()));
   AClient client2=new Borrower(2,"Dima Ivanov","Ozheshko,15",369852,"AB1478523",new Credit(150,7,new Date(),new Date()));
   AClient client3=new AccountHolder(3,"Sveta Morozova","Kurchatova,60",258963,"AB8597425");
   ((AccountHolder)client3).AddBankAccount(new BankAccount(123456789,150,new Date(),new Date()));
 }
}