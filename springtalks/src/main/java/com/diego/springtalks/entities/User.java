package com.diego.springtalks.entities;

import com.diego.springtalks.dtos.requests.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"sentMessages", "chatRooms", "password"})
@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @ManyToMany(mappedBy = "participants")
    private List<ChatRoom> chatRooms;

    public User(UserDTO userDTO) {
        this.username = userDTO.username();
        this.email = userDTO.email();
        this.password = userDTO.password();
    }
}