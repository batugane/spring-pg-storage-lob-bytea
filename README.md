# Spring Boot PostgreSQL Storage (LOB & BYTEA)

A minimal Spring Boot project demonstrating how to store and retrieve files from a PostgreSQL database using two approaches:

1. **BYTEA Columns**
2. **OID-based Large Objects (LOB)**

## Getting Started

1. **Clone or download** this repository.
2. **Configure** your PostgreSQL connection in `application.properties`.
3. **Build and run** the Spring Boot app:

4. **Test** using [Postman](https://www.postman.com/downloads/) or `curl`:
    - **Upload to BYTEA**:
      ```bash
      POST /files/bytea
      form-data: file=@path/to/file
      ```
    - **Upload to LOB**:
      ```bash
      POST /files/lob
      form-data: file=@path/to/file
      ```
    - **Download** (replace `{id}` with the returned ID):
      ```
      GET /files/bytea/{id}
      GET /files/lob/{id}
      ```

## Layers

- **Controller**: Handles HTTP requests (upload/download).
- **Service**: Encapsulates business logic (store/retrieve files).
- **Repository**: Uses JPA to interact with PostgreSQL.