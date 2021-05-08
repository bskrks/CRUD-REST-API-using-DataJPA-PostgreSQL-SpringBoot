package com.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="kisi_adres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})          // İki adresin birbirine eşit olma durumu
@ToString
public class Adres implements Serializable {

    @Id                             // Id nin yatılabilmesi için JPA'da @Id anotasyonunun tabloda atanmış olması gerekir ve bunun da auto generator (otomatik artan) olması gerekir.
    @SequenceGenerator(name="seq_kisi_adres", allocationSize = 1)  // allocation ile id nin kaçar kaçar artacağı yazıldı
    @GeneratedValue(generator = "seq_kisi_adres", strategy = GenerationType.SEQUENCE)    // Id bir yerden (sequence genrator'ımızdan) otomatik alınıyorsa bu anotasyon gereklidir.
    private long id;

    @Column(length = 500, name="adres")           // 'insertable'-> kayıt sadece insert'te eklenebilir.  'update default true'-> kayıt eklendikte sonra güncellenemesin.   'nullable default true'-> null değeri alsın mı
    private String adres;

    @Enumerated
    private AdresTip adresTip;

    @Column(name="aktif")
    private Boolean aktif;

    @ManyToOne    //  @ManyToOne(fetch = "FetchType.EAGER" ) veritbanından adres kaydı her select edildiğinde adresin ilgili kişisi de otomatik olarak gelsin anlamı taşır.
    @JoinColumn(name="kisi_adres_id")
    private Kisi kisi;

    public enum AdresTip{
        EV_ADRESI,
        IS_ADRESI,
        DIGER
    }
}
