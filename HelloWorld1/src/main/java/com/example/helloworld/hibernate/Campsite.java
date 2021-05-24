package com.example.helloworld.hibernate;

import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;
import lombok.Data;

@Entity
@Table(name = "campsites")
@NamedQueries({
        @NamedQuery(name = "com.example.helloworld.core.hibernate.Campsite.findAll",
                query = "SELECT e FROM Campsite e")
})

@Data
public class Campsite  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siteId", unique = true, nullable = false)
    private long siteId;

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


}
