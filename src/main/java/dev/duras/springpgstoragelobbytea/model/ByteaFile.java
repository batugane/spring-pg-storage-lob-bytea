package dev.duras.springpgstoragelobbytea.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bytea_files")
public class ByteaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Column(name = "file_data")
    private byte[] data;


    public ByteaFile() {
    }

    public ByteaFile(String filename, byte[] data) {
        this.filename = filename;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
