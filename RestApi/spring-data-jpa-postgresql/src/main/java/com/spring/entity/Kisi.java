package com.spring.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kisi")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})          // İki adresin birbirine eşit olma durumu
@ToString
public class Kisi {
    @Id
    @SequenceGenerator(name="seq_kisi", allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 100, name="adi")
    private String adi;

    @Column(length = 100, name="soyadi")
    private String soyadi;

    @OneToMany           // OneToMany-> Bir kişi birden fazla adrese gidebilir      OneToOne->  Bir kişi birden fazla adrese gidebilir   ManyToOne-> Birçok kişi  bir adrese gidebilir.
    @JoinColumn(name="kisi_adres_id")    // kisi_aders_id adlı sütun ile iki tabloyu join edeceğiz
    private List<Adres> adresleri;       // Join işlemi
}