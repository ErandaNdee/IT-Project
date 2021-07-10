



public class Customer {
    
    private String NIC;
    private String Name;
    private String Address;
    private String Mobile;
    private String Email;
    
    public Customer(String NIC, String Name, String Address, String Mobile, String Email){
        
        this.NIC = NIC;
        this.Name = Name;
        this.Address = Address;
        this.Mobile = Mobile;
        this.Email = Email;
    
    }
    
    public String getNIC(){
    
        return NIC;
    }
    public String getName(){
    
        return Name;
    }
    public String getAddress(){
    
        return Address;
    }
    public String getMobile(){
    
        return Mobile;
    }
    public String getEmail(){
    
        return Email;
    }
}
