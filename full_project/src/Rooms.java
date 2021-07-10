



public class Rooms {
    
    private int roomNumber;
    private String roomType;
    private String bedType;
    private double roomPrice;
    
    public Rooms(int roomNumber, String roomType, String bedType, double roomPrice){
    
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedType = bedType;
        this.roomPrice = roomPrice;
    }
    
    public int getRoomNumber(){
    
        return roomNumber;
    }
    public String getRoomType(){
    
        return roomType;
    }
    public String getBedType(){
    
        return bedType;
    }
    public double getRoomPrice(){
    
        return roomPrice;
    }
}
