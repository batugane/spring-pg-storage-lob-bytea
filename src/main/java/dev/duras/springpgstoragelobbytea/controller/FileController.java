package dev.duras.springpgstoragelobbytea.controller;

import dev.duras.springpgstoragelobbytea.model.ByteaFile;
import dev.duras.springpgstoragelobbytea.model.LobFile;
import dev.duras.springpgstoragelobbytea.service.ByteaFileService;
import dev.duras.springpgstoragelobbytea.service.LobFileService;
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

    private final ByteaFileService byteaFileService;
    private final LobFileService lobFileService;

    public FileController(ByteaFileService byteaFileService, LobFileService lobFileService) {
        this.byteaFileService = byteaFileService;
        this.lobFileService = lobFileService;
    }

    @PostMapping("/bytea")
    public ResponseEntity<String> uploadByteaFile(@RequestParam("file") MultipartFile file) throws IOException {
        ByteaFile saved = byteaFileService.storeFile(file);
        return ResponseEntity.ok("Uploaded to BYTEA with ID: " + saved.getId());
    }

    @PostMapping("/lob")
    public ResponseEntity<String> uploadLobFile(@RequestParam("file") MultipartFile file) throws IOException {
        LobFile saved = lobFileService.storeFile(file);
        return ResponseEntity.ok("Uploaded to LOB with ID: " + saved.getId());
    }

    @GetMapping("/bytea/{id}")
    public ResponseEntity<ByteArrayResource> downloadByteaFile(@PathVariable Long id) {
        ByteaFile byteaFile = byteaFileService.getFile(id)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + id));

        ByteArrayResource resource = new ByteArrayResource(byteaFile.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + byteaFile.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/lob/{id}")
    public ResponseEntity<ByteArrayResource> downloadLobFile(@PathVariable Long id) {
        LobFile lobFile = lobFileService.getFile(id)
                .orElseThrow(() -> new RuntimeException("File not found with ID: " + id));

        ByteArrayResource resource = new ByteArrayResource(lobFile.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + lobFile.getFilename() + "\"")
                .body(resource);
    }
}
