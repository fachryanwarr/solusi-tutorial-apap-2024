package apap.tutorial.manpromanpro.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyek")
public class Proyek {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "deskripsi", columnDefinition = "TEXT", nullable = false)
    private String deskripsi;

    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggalMulai;

    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggalSelesai;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_at", updatable = false)
    private java.util.Date createdAt;

    @Column(name = "updated_at")
    private java.util.Date updatedAt;

    @Column(name = "deleted_at")
    private java.util.Date deletedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private Developer developer;

    @ManyToMany
    @JoinTable(name = "pekerja_proyek", joinColumns = @JoinColumn(name = "id_proyek"),
            inverseJoinColumns = @JoinColumn(name = "id_pekerja"))
    private List<Pekerja> listPekerja;

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
