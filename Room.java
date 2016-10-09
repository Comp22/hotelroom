package javafxapplication4;

public class Room {

    
	private String roomnumber;
        private String cost;
        private String totalcost;
        private String type;
	private String checkindate;
	private String checkoutdate;
        private int booking;        
	
	
	public Room(String roomnumber,String cost, String totalcost,String type, String checkindate, String checkoutdate,int booking){
		this.roomnumber = roomnumber;
                this.cost = cost;
                this.totalcost = totalcost;
                this.type = type;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
                this.booking = booking;
	}
        public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    public String getTotalcost() {
        return totalcost;
    }

    public int getBooking() {
        return booking;
    }
	
        public void setType(String type){
 		this.type = type;
	}
	public String getType(){
		return type;
	}
        public void setCost(String cost){
 		this.cost = cost;
	}
	public String getCost(){
		return cost;
	}
        public void setRoomnumber(String roomnumber){
		this.roomnumber = roomnumber;
	}
	public String getRoomnumber(){
		return roomnumber;
	}
	public String getCheckindate() {
		return checkindate;
	}
	public void setCheckindate(String checkindate) {
		this.checkindate = checkindate;
	}
	public String getCheckoutdate() {
		return checkoutdate;
	}
	public void setCheckoutdate(String checkoutdate) {
		this.checkoutdate = checkoutdate;
	}
}
