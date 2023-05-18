package br.com.undefined.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_und_user")
@SequenceGenerator(name = "user", sequenceName = "SQ_TB_USER", allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Long id;
    private String userName;
    private String email;
    private String password;

}