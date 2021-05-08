package com.spring.repo;

import com.spring.entity.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KisiRepository extends JpaRepository<Kisi, Long>{        // Long burada id'yi temsil ediyor

}
