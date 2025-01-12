package dev.duras.springpgstoragelobbytea.controller;


import dev.duras.springpgstoragelobbytea.model.ByteaFile;
import dev.duras.springpgstoragelobbytea.model.LobFile;
import dev.duras.springpgstoragelobbytea.repo.ByteaFileRepository;
import dev.duras.springpgstoragelobbytea.repo.LobFileRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {


    private final ByteaFileRepository byteaFileRepository;

    private final LobFileRepository lobFileRepository;


    public FileController(ByteaFileRepository byteaFileRepository, LobFileRepository lobFileRepository) {
        this.byteaFileRepository = byteaFileRepository;
        this.lobFileRepository = lobFileRepository;
    }

    // -------------------------------
    // 1) Upload to BYTEA
    // -------------------------------
    @PostMapping("/bytea")
    public ResponseEntity<String> uploadByteaFile(@RequestParam("file") MultipartFile file) throws IOException {
        ByteaFile byteaFile = new ByteaFile();
        byteaFile.setFilename(file.getOriginalFilename());
        byteaFile.setData(file.getBytes());

        ByteaFile saved = byteaFileRepository.save(byteaFile);
        return ResponseEntity.ok("Uploaded to BYTEA with ID: " + saved.getId());
    }

    // -------------------------------
    // 2) Upload to LOB
    // -------------------------------
    @PostMapping("/lob")
    public ResponseEntity<String> uploadLobFile(@RequestParam("file") MultipartFile file) throws IOException {
        LobFile lobFile = new LobFile();
        lobFile.setFilename(file.getOriginalFilename());
        lobFile.setData(file.getBytes());

        LobFile saved = lobFileRepository.save(lobFile);
        return ResponseEntity.ok("Uploaded to LOB with ID: " + saved.getId());
    }

    // -------------------------------
    // 3) Download from BYTEA
    // -------------------------------
    @GetMapping("/bytea/{id}")
    public ResponseEntity<ByteArrayResource> downloadByteaFile(@PathVariable Long id) {
        ByteaFile byteaFile = byteaFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + id));

        ByteArrayResource resource = new ByteArrayResource(byteaFile.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + byteaFile.getFilename() + "\"")
                .body(resource);
    }

    // -------------------------------
    // 4) Download from LOB
    // -------------------------------
    @GetMapping("/lob/{id}")
    public ResponseEntity<ByteArrayResource> downloadLobFile(@PathVariable Long id) {
        LobFile lobFile = lobFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + id));

        ByteArrayResource resource = new ByteArrayResource(lobFile.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + lobFile.getFilename() + "\"")
                .body(resource);
    }
}
