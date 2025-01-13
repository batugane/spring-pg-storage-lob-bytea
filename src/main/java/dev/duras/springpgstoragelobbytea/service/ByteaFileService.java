package dev.duras.springpgstoragelobbytea.service;

import dev.duras.springpgstoragelobbytea.model.ByteaFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ByteaFileService {

    ByteaFile storeFile(MultipartFile file) throws IOException;

    Optional<ByteaFile> getFile(Long id);
}
