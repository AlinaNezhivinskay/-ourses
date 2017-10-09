public abstract class AClient {   
   private long id;
   private String name;
   private String address;
   private int phoneNumber;
   private String passportData;

   public AClient(long id,String name,String address,int phoneNumber,String passportData)
   {
     System.out.println("AClient");
     this.id=id;
     this.name=name;
     this.address=address;
     this.phoneNumber=phoneNumber;
     this.passportData=passportData;     
   }

   public long getId()
   {
     return id;
   }
   public void setId(long id)
   {
     this.id=id;
   }
   public String getName()
   {
     return name;
   }
   public void setName(String name)
   {
     this.name=name;
   }
   public String getAddress()
   {
     return address;
   }
   public void setAddress(String address)
   {
     this.address=address;
   }
   public int getPhoneNumber()
   {
     return phoneNumber;
   }
   public void setPhoneNumber(int phoneNumber)
   {
     this.phoneNumber=phoneNumber;
   }
   public String getPassportData()
   {
     return passportData;
   }
   public void setPassportData(String passportData)
   {
     this.passportData=passportData;
   }

   public String viewClient()
   {
     return name+" "+address+" "+phoneNumber+" "+passportData;
   }
}