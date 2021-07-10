/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shanuka Prabodha
 */
public class Employee {
    
    private String id;
    private String name;
    private String nic;
    private String phone;
    private String email;
    private int age;
    private double bsalary;
    private String edate;
    private byte[] photo;
    private String gender;
    private String designation;
    private String path;
    
    
    public Employee(String id,String name, String nic,int age,String gender,String phone,String email,String edate,String designation ,double bsalary,byte[] photo,String path){
    
        this.id=id;
        this.name=name;
        this.nic=nic;
        this.phone=phone;
        this.email=email;
        this.bsalary=bsalary;
        this.edate=edate;
        this.age=age;
        this.photo=photo;
        this.gender=gender;
        this.designation=designation;
        this.path=path;
    
    
    }

    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the bsalary
     */
    public double getBsalary() {
        return bsalary;
    }

    /**
     * @param bsalary the bsalary to set
     */
    public void setBsalary(double bsalary) {
        this.bsalary = bsalary;
    }

    /**
     * @return the edate
     */
    public String getEdate() {
        return edate;
    }

    /**
     * @param edate the edate to set
     */
    public void setEdate(String edate) {
        this.edate = edate;
    }

    /**
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    
}
