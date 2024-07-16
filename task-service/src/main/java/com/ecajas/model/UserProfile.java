package com.ecajas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    //Se queda como id solamente sin autogenerado ya que tomara el id de Users
    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String dni;

    @OneToOne
    @MapsId // hace que el id de Users se asocie con la entidad UserProfile y tome el id
    @JoinColumn(name = "id")
    private User user;
}
