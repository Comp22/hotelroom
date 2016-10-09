/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

/**
 *
 * @author User
 */
public class SqlRoom {
    private String bedtype;
    private String roomnumber;
    private String cost;

    public void setBedtype(String bedtype) {
        this.bedtype = bedtype;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getBedtype() {
        return bedtype;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public String getCost() {
        return cost;
    }

public SqlRoom(String roomnumber, String cost, String bedtype){
 this.bedtype=bedtype;
 this.roomnumber=roomnumber;
 this.cost = cost;
}



}
