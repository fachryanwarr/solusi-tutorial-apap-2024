package apap.tutorial.manpromanpro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "alamat", columnDefinition = "TEXT", nullable = false)
    private String alamat;

    @Column(name = "tanggal_berdiri", nullable = false)
    private Date tanggalBerdiri;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false)
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Proyek> listProyek = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new java.util.Date();
        this.updatedAt = new java.util.Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new java.util.Date();
    }
}
