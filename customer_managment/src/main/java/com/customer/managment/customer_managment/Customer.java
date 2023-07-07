package com.customer.managment.customer_managment;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "customeer_table")

public class Customer {
	
	private long id;

    private String firstName;

    private String lastName;
 
    private String  DOB;

    private String  NIC;
   
    private String phone;
   
    private String Fmembers;
    
    private String address;
   
    private String city;
  
    private String country;
    
    

    public Customer() {

    }

    public Customer(String firstName, String lastName,  String DOB,String NIC,String phone,
    		String Fmembers,String address,String city,String country) {
    	
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.NIC = NIC;
        this.phone = phone;
        this.Fmembers = Fmembers;
        this.address =  address;
        this.city=city;
        this.country=country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "D_O_B", nullable = false)
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String DOB) {
    	
            this.DOB = DOB;
    	
    }
    
    @Column(name = "N_i_C", nullable = false)
    public String getNIC() {
        return NIC;
    }
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
    @Column(name = "phone_", nullable = false)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "F_members", nullable = false)
    public String getFmembers() {
        return Fmembers;
    }
    public void setFmembers(String Fmembers) {
        this.Fmembers = Fmembers;
    }
    @Column(name = "address_", nullable = false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "city_", nullable = false)
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "country_", nullable = false)
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
