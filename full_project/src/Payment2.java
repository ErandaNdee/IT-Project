/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Charith
 */
public class Payment2 {

    private int paymentid;
    private String roomid;
    private String date;
    private String time;
    private String hallid;
    private String txt_type;
    private float amount;
    
    public Payment2(int paymentid, String roomid, String date, String time, String hallid, String txt_type, float amount){
        this.paymentid = paymentid;
        this.roomid = roomid;
        this.date = date;
        this.time = time ;
        this.hallid = hallid;
        this.txt_type = txt_type;
        this.amount = amount;
    
    }

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHallid() {
        return hallid;
    }

    public void setHallid(String hallid) {
        this.hallid = hallid;
    }

    public String getTxt_type() {
        return txt_type;
    }

    public void setTxt_type(String txt_type) {
        this.txt_type = txt_type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    

    
}
