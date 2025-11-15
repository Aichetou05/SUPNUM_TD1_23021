package mr.supnum.server_manager.repository;

import  java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mr.supnum.server_manager.entity.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findByName(String name);
}
