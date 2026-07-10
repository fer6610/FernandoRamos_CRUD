package SystemITR.JosueGuinea2A.Departamentos.Controller;

import SystemITR.JosueGuinea2A.Departamentos.DTO.DepartamentoDTO;
import SystemITR.JosueGuinea2A.Departamentos.Service.DepartamentosService;
import SystemITR.JosueGuinea2A.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@lombok.extern.slf4j.Slf4j
@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {


    /**
     * Nuevo recursos : Ingresar información -> POST
     * Obtener recursos: GET
     * Actualizar recursos: PUT / PATCH
     * Eliminar recursos: DELETE
     */
    @PostMapping
    public ResponseEntity<ApiResponse<DepartamentoDTO>> nuevoDepartamento(@Valid @RequestBody DepartamentoDTO json){
        try{

            if (dto != null){
                log.info("Nuevo departamento registrado: " + dto);
                ApiResponse<DepartamentoDTO> respuesta = new ApiResponse<>(true, "Datos ingresados exitosamente", json);
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Intento de insercion fallido: " + json);
            ApiResponse<DepartamentoDTO> respuestaFallida = new ApiResponse<>(false, "Datos ingresados exitosamente", json);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);

        }catch (Exception e){
            e.printStackTrace();
            ApiResponse<DepartamentoDTO> respuesta = new ApiResponse<>(false, "El proceso no se pudo completar", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DepartamentoDTO>>> obtenerDatos(){
        try{
            List<DepartamentoDTO> lsita = services.obtenerTodo();
            if (lsita != null){
                log.info("Datos de departamnetos consultados existosamente");
                ApiResponse<List<DepartamentoDTO>> respuestaExito = new ApiResponse<>(true, "Datos encontrado", lista);
                return ResponseEntity.ok(respuestaExito);

            }
        }
        log.info("Datos no encontrados");
        ApiResponse<List<DepartamentoDTO>> respuestaNoEncontrada = new ApiResponse<>(true, "Datos encontrado", lista);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestaNoEncontrada);
    }catch (Exception e){
        log.error("El proceso presento un fallo inesperado, consulte con el administrador.");
        e.printStackTrace();
        ApiResponse<List<DepartamentoDTO>> respuesta = new ApiResponse<>(false, "El proceso no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
}

