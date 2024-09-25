package apap.tutorial.manpromanpro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pekerja")
public class Pekerja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "usia", nullable = false)
    private int usia;

    @Column(name = "pekerjaan", nullable = false)
    private String pekerjaan;

    @Column(name = "biografi", columnDefinition = "TEXT", nullable = false)
    private String biografi;

    @Column(name = "created_at", updatable = false)
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new java.util.Date();
        this.updatedAt = new java.util.Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new java.util.Date();
    }

    @ManyToMany(mappedBy = "listPekerja", fetch = FetchType.LAZY)
    List<Proyek> listProyek = new ArrayList<>();
}
