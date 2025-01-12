package dev.duras.springpgstoragelobbytea.repo;


import dev.duras.springpgstoragelobbytea.model.LobFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobFileRepository extends JpaRepository<LobFile, Long> {
}
