package dev.duras.springpgstoragelobbytea.service.impl;

import dev.duras.springpgstoragelobbytea.model.LobFile;
import dev.duras.springpgstoragelobbytea.repo.LobFileRepository;
import dev.duras.springpgstoragelobbytea.service.LobFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class LobFileServiceImpl implements LobFileService {

    private final LobFileRepository lobFileRepository;

    public LobFileServiceImpl(LobFileRepository lobFileRepository) {
        this.lobFileRepository = lobFileRepository;
    }

    @Override
    public LobFile storeFile(MultipartFile file) throws IOException {
        LobFile lobFile = new LobFile();
        lobFile.setFilename(file.getOriginalFilename());
        lobFile.setData(file.getBytes());

        return lobFileRepository.save(lobFile);
    }

    @Override
    public Optional<LobFile> getFile(Long id) {
        return lobFileRepository.findById(id);
    }
}
