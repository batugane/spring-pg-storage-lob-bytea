package dev.duras.springpgstoragelobbytea.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lob_files")
public class LobFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    // The @Lob annotation indicates this is (potentially) a large object (BLOB).
    // Depending on the JPA implementation + PostgreSQL dialect,
    // this might map to BYTEA or OID behind the scenes.
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

    // Getters & Setters
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
