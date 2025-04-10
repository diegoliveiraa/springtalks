package com.diego.springtalks.repositories;

import com.diego.springtalks.entities.ChatRoom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {

    @EntityGraph(attributePaths = "participants")
    Optional<ChatRoom> findWithParticipantsById(UUID id);

    List<ChatRoom> findByParticipantsIsEmpty();

}
