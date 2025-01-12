package dev.duras.springpgstoragelobbytea.repo;


import dev.duras.springpgstoragelobbytea.model.ByteaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ByteaFileRepository extends JpaRepository<ByteaFile, Long> {
}
