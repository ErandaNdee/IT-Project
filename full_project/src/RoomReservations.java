



public class RoomReservations {
    
    private int RRID;
    private double Amount;
    private int NoKids;
    private int NoAdults;
    private String Cus_NIC;
    private int RoomNo;
    private String CheckIn;
    private String CheckOut;
    
    public RoomReservations(int RRID, double Amount, int NoKids, int NoAdults, String Cus_NIC, int RoomNo, String CheckIn, String CheckOut){
    
        this.RRID = RRID;
        this.Amount = Amount;
        this.NoKids = NoKids;
        this.NoAdults = NoAdults;
        this.Cus_NIC = Cus_NIC;
        this.RoomNo = RoomNo;
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
    }
    
    public int getRRID(){
    
        return RRID;
    }
    public double getAmount(){
    
        return Amount;
    }
    public int getNoKids(){
    
        return NoKids;
    }
    public int getNoAdults(){
    
        return NoAdults;
    }
    public String getCus_NIC(){
    
        return Cus_NIC;
    }
    public int getRoomNo(){
    
        return RoomNo;
    }
    public String getCheckIn(){
    
        return CheckIn;
    }
    public String getCheckOut(){
    
        return CheckOut; 
    }
    
    
}
