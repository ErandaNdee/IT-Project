/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shanuka Prabodha
 */
public class Menureport {
    
    
    private String menuName;
    private int id;
    private String mdate;
    
    
    
    public Menureport(int id,String menuName,String mdate){
    
        this.menuName=menuName;
        this.id=id;
        this.mdate=mdate;
    }
    

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the mdate
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * @param mdate the mdate to set
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
    
    
    
}
