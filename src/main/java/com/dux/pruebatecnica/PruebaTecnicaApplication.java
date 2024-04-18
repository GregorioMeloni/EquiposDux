
package com.dux.pruebatecnica;

import com.dux.pruebatecnica.data.models.Equipo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class PruebaTecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaTecnicaApplication.class, args);

        Equipo equipo = new Equipo();

    }
}