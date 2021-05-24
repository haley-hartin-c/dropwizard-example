package com.example.helloworld.hibernate;

import javax.persistence.*;


import org.hibernate.annotations.OptimisticLockType;

@Entity
@Table(name = "campsites")
@NamedQueries({
        @NamedQuery(name = "com.example.helloworld.core.hibernate.Campsite.findAll",
                query = "SELECT e FROM Campsite e")
})


public class Campsite  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siteId", unique = true, nullable = false)
    private Integer siteId;

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "location", unique = false, nullable = false, length = 100)
    private String location;
    public Campsite(){

    }
    public Campsite(String name, String location){
        System.out.println("creating site");
        this.name = name;
        this.location = location;

    }

    public int getId(){
        return siteId;
    }
    public String getLocation(){
        return location;
    }
    public String getName(){
        return name;
    }

    public void setId(int id){
        this.siteId=id;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public void setName(String name){
        this.name = name;
    }


}
