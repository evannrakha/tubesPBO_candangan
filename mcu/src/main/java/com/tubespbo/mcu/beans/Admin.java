package com.tubespbo.mcu.beans;

import jakarta.persistence.*;

@Entity
public class Admin extends User {
    
    public Admin(){
        super();
    }
    public Admin(int id, String nama, String gender, String password, int umur){
        super(id, nama, gender, password, umur);
    }

}
