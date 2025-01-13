package dev.duras.springpgstoragelobbytea.service;

import dev.duras.springpgstoragelobbytea.model.LobFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface LobFileService {
    
    LobFile storeFile(MultipartFile file) throws IOException;

    Optional<LobFile> getFile(Long id);
}
