//Este es un controlador REST que maneja solicitudes relacionadas a los equipos de futbol
package com.dux.pruebatecnica.controllers;

//Importa clase EquipoService que maneja logica de negocio de los equipos
import com.dux.pruebatecnica.controllers.request.EquipoRequestDTO;
import com.dux.pruebatecnica.controllers.response.EquipoResponseDTO;
import com.dux.pruebatecnica.data.models.Equipo;
import com.dux.pruebatecnica.service.EquipoService;
//Importa enumeración HttpStatus que contiene códigos de estado HTTP estándar

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
/*Importa clase ResponseEntity que representa la respuesta HTTP. 

Las partes de esta respuesta que el servidor envía al cliente son:

Código de Estado (Statud Code - Resultado de la solicitud HTTP):
    200 OK: Solicitud Exitosa.
    201 Created: Solicitud exitosa y se creó nuevo recurso.
    400 Bad Request: Solicitud no entendible debido a sintaxis incorrecta
    404 Not Found: Recurso solicitado no encontrado en el servidor
    500 Internal Server Error: Servidor encontró condición inesperada que le impidió completar la solicitud
Encabezados (Headers - Metadatos adicionales que dan informacion sobre la respuesta y controlan cómo se debe manejar):
    Content-Type: Especifica tipo de contenido del cuerpo de la respuesta (por ejemplo, application/json para JSON).
    Content-Length: Indica longitud del cuerpo de la respuesta en bytes.
    Location: Especifica la URL del recurso recién creado en respuestas 201 Created.
    Set-Cookie: Configura una cookie en el navegador del cliente.
Los encabezados se encuentran en líneas separadas dentro de la respuesta HTTP, después de la línea que contiene el código de estado.
Cuerpo (Body - Contenido real de la respuesta HTTP):
    Puede contener cualquier tipo de dato como texto, HTML, JSON, XML, imágenes, etx.
    Proporciona datos solicitados o cualquier mensaje adicional que el servidor desee enviar al cliente
    Ej.: una solicitud GET exitosa devuelve datos de una BD, el cuerpo puede contener datos solicitados en formato JSON o XML
    El cuerpo se encuentra después de los encabezados separado de ellos por una línea en blanco.
    En las respuestas JSON el cuerpo contendrá los datos estructurados que el cliente solicitó o la información de error*/
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }


    @Operation(summary = "Consulta de Todos los Equipos",
            description = "Este endpoint devuelve la lista de todos los equipos de fútbol registrados en la base de datos.")
    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        return ResponseEntity.ok(equipos);
    }

    @Operation(summary = "Búsqueda de Equipos por Nombre", description = "Endpoint que devuelve la lista de equipos cuyos nombres (sea nombre, liga o país) contienen el valor proporcionado en el parámetro de búsqueda. /equipos/buscar?nombre={valor} ")
    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> buscarEquiposPorNombreLigaOPais(@RequestParam String valor) {
        List<Equipo> equipos = equipoService.buscarEquiposPorNombreLigaOPais(valor);
        return ResponseEntity.ok(equipos);
    }



    @Operation(summary = "Consulta de un Equipo por ID", description = "Endpoint que devuelve la información del equipo correspondiente al ID proporcionado")
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
    }

    @Operation(summary = "Eliminación de un Equipo por ID", description = "Endpoint para eliminar por ID un equipo registrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarEquipo(@PathVariable Long id) {
        try {
            equipoService.eliminarEquipo(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                String mensajeError = "Equipo no encontrado";
                Map<String, Object> responseBody = new LinkedHashMap<>();
                responseBody.put("mensaje", mensajeError);
                responseBody.put("codigo", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            throw ex;
        }
    }



    @Operation(summary = "Creación de un equipo", description = "Endpoint para crear un nuevo equipo de futbol")
    @PostMapping
    public ResponseEntity<EquipoResponseDTO> crearEquipo(@Validated @RequestBody EquipoRequestDTO equipoRequestDTO) {
        Equipo equipo = new Equipo(equipoRequestDTO.getNombre(), equipoRequestDTO.getLiga(), equipoRequestDTO.getPais());
        Equipo nuevoEquipo = equipoService.guardarEquipo(equipo);
        EquipoResponseDTO equipoResponseDTO = new EquipoResponseDTO(nuevoEquipo.getId(), nuevoEquipo.getNombre(), nuevoEquipo.getLiga(), nuevoEquipo.getPais());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoResponseDTO);
    }

    @Operation(summary = "Actualización de Información de un Equipo por ID", description = "Endpoint para actualizar los datos del equipo especificado por ID")
    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponseDTO> actualizarEquipo(@PathVariable Long id, @Validated @RequestBody EquipoRequestDTO equipoRequestDTO) {
        Equipo equipoActualizado = new Equipo(equipoRequestDTO.getNombre(), equipoRequestDTO.getLiga(), equipoRequestDTO.getPais());
        equipoService.actualizarEquipo(id, equipoActualizado);
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        EquipoResponseDTO equipoResponseDTO = new EquipoResponseDTO(equipo.getId(), equipo.getNombre(), equipo.getLiga(), equipo.getPais());
        return ResponseEntity.ok(equipoResponseDTO);
    }


}

