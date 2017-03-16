package org.model;



/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long phone;
     private String password;
     private Integer clock;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(Long phone, String password, Integer clock) {
        this.phone = phone;
        this.password = password;
        this.clock = clock;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return this.phone;
    }
    
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClock() {
        return this.clock;
    }
    
    public void setClock(Integer clock) {
        this.clock = clock;
    }
   








}