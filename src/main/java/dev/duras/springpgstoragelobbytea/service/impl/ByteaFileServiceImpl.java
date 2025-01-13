package dev.duras.springpgstoragelobbytea.service.impl;

import dev.duras.springpgstoragelobbytea.model.ByteaFile;
import dev.duras.springpgstoragelobbytea.repo.ByteaFileRepository;
import dev.duras.springpgstoragelobbytea.service.ByteaFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ByteaFileServiceImpl implements ByteaFileService {

    private final ByteaFileRepository byteaFileRepository;

    public ByteaFileServiceImpl(ByteaFileRepository byteaFileRepository) {
        this.byteaFileRepository = byteaFileRepository;
    }

    @Override
    public ByteaFile storeFile(MultipartFile file) throws IOException {
        ByteaFile byteaFile = new ByteaFile();
        byteaFile.setFilename(file.getOriginalFilename());
        byteaFile.setData(file.getBytes());

        return byteaFileRepository.save(byteaFile);
    }

    @Override
    public Optional<ByteaFile> getFile(Long id) {
        return byteaFileRepository.findById(id);
    }
}
