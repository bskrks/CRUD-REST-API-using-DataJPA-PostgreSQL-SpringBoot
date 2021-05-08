package com.spring.service.impl;

import com.spring.dto.KisiDto;
import com.spring.entity.Adres;
import com.spring.entity.Kisi;
import com.spring.repo.AdresRepository;
import com.spring.repo.KisiRepository;
import com.spring.service.KisiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;    // final diyerek değer ataması yapmıyoruz ve bu değişken değer ataması için constructor beklemek zorunda kalıyor.
    private final AdresRepository adresRepository;

    @Override
    @Transactional      // Bu işlemin hepsini veritabanında bir kere uygular
    public KisiDto save(KisiDto kisiDto) {
        // Bir kişi Dto'ya gelip kaydedilmek istendiğinde ;

        Assert.isNull(kisiDto.getAdi(), "Adı alanı zorunludur! ");

        Kisi kisi = new Kisi();
        kisi.setAdi(kisiDto.getAdi());
        kisi.setSoyadi(kisiDto.getSoyadi());
        final Kisi kisiDb = kisiRepository.save(kisi);
        List<Adres> liste = new ArrayList<>();
        kisiDto.getAdresler().forEach(item -> {
            // Herbir elemanın adres nesnesini tanımlatıp ilk defa bir kişi kaydolurken tüm adreslerini alacağız.
            Adres adres = new Adres();
            adres.setAdres(item);
            adres.setAdresTip(Adres.AdresTip.DIGER);
            adres.setAktif(true);
            adres.setKisi(kisiDb);
            liste.add(adres);
        });
        adresRepository.saveAll(liste);
        kisiDto.setId(kisiDb.getId());
        return kisiDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<KisiDto> getAll() {
        List<Kisi> kisiler = kisiRepository.findAll();       // Kişiler kisirepository'den findAll ile getirtilir
        List<KisiDto> kisiDtos = new ArrayList<>();

        kisiler.forEach(it -> {
            KisiDto kisiDto = new KisiDto();
            kisiDto.setId(it.getId());
            kisiDto.setAdi(it.getAdi());
            kisiDto.setSoyadi(it.getSoyadi());
            kisiDto.setAdresler(it.getAdresleri().stream().map(Adres::getAdres)
                    .collect(Collectors.toList()));         // Herbir iter'in birçok adresi var. O adreslerin hepsinin sadece adres kolonlarını bir liste halinde Dto'nun adreslerine doldur.
            kisiDtos.add(kisiDto);
        });
        return kisiDtos;
    }

    @Override
    public Page<KisiDto> getAll(Pageable pageable) {
        return null;
    }
}
