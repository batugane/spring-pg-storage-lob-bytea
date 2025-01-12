package dev.duras.springpgstoragelobbytea.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lob_files")
public class LobFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file_data")
    private byte[] data;

    public LobFile() {
    }

    public LobFile(String filename, byte[] data) {
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
