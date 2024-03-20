package org.project.myapp.models;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 10, nullable = true)
    private String phoneNumber;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    private boolean active;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "facebook_account_Id")
    private int facebookAccountId;

    @Column(name = "google_account_Id")
    private int googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  org.project.myapp.models.Role roleId;





}
