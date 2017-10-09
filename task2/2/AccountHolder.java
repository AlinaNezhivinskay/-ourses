import java.util.*;
public class AccountHolder extends AClient {
 private ArrayList<BankAccount> bankAccounts;
 
 public AccountHolder(long id,String name,String address,int phoneNumber,String passportData)
 {
   super(id, name, address, phoneNumber, passportData);
   System.out.println("AccountHolder");
   bankAccounts=new ArrayList<BankAccount>();
 }
 
 public ArrayList<BankAccount> getBankAccounts()
   {
     return bankAccounts;
   }
 public void setCredit(ArrayList<BankAccount> bankAccounts)
   {
     this.bankAccounts=bankAccounts;
   }
 
 
 public void AddBankAccount(BankAccount bankAccount)
   {
     bankAccounts.add(bankAccount);
   }
 public void withdraw(double amount,long accountNumber)
   {
     for(int i=0;i<bankAccounts.size();i++)
      {
        if(bankAccounts.get(i).getAccountNumber()==accountNumber)
           {
             bankAccounts.get(i).deductBalance(amount);
             break;
           }
      }
   }
 public void put(double amount,long accountNumber)
   {
     for(int i=0;i<bankAccounts.size();i++)
      {
        if(bankAccounts.get(i).getAccountNumber()==accountNumber)
           {
             bankAccounts.get(i).addBalance(amount);
             break;
           }
      }
   }
 public void viewBalance(long accountNumber)
   {
     for(int i=0;i<bankAccounts.size();i++)
      {
        if(bankAccounts.get(i).getAccountNumber()==accountNumber)
           {
             System.out.print(bankAccounts.get(i).getBalance());
             break;
           }
      }
   }
}