package com.spring.dto;

// Entity'lerimi direkt api'lar üzerinden dış dünyaya açmak istemeyiz ve bu yüzden Dto kullanırız.

import lombok.Data;
import java.util.List;

@Data           // @Data bize paket olarak @Getter @Setter @ToString @EqualsAndHashCode'u berbaerinde getirir.
public class KisiDto {

    private long id;

    private String adi;

    private String soyadi;

    private List<String> adresler;
}
