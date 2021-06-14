package es.alfatec.imputaciones.desktop.modelo.userConfig;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import es.alfatec.imputaciones.util.core.Constantes;
import es.alfatec.imputaciones.util.modelo.FormatoExportacion;
import es.alfatec.imputaciones.util.modelo.Imputable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Imputacion implements Imputable {
    @JacksonXmlProperty(isAttribute = true)
    private boolean activo = true;
    @JacksonXmlProperty(isAttribute = true)
    private boolean formatoMinificado = false;
    
    private int numeroHorasDia = Constantes.DEFAULT_HORAS_DIA;
    
    @NotEmpty
    @JacksonXmlProperty(isAttribute = true)
    private String nombre;
    
    @NotEmpty
    private String ficheroImputaciones;
    @NotEmpty
    private String ficheroSalida;
    
    @NotNull
    //private String formatoSalida = FormatoExportacion.EXCEL.name();
    private FormatoExportacion formatoSalida = FormatoExportacion.EXCEL;
    
    @NotNull
    //solo necesario cuando no se especifica el formato de fecha en serializador y deserializador del modulo de xmlParser
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;
    
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private List<LocalDate> vacaciones = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private List<LocalDate> festivos = new ArrayList<>();   

}
