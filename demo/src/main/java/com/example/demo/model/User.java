package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    
    // Generate standard getters and setters in your IDE (Right Click -> Source Action -> Generate Getters/Setters)
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
