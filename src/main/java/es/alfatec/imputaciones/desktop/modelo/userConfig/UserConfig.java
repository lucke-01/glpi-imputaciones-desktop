package es.alfatec.imputaciones.desktop.modelo.userConfig;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "UserConfig")
public class UserConfig {
    
    private String directorioSalida = ".";
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "imputacion")
    //anotacion @Valid para que compruebe la validacion el array de imputaciones: @notNull etc...
    @Valid
    //lista de imputaciones no puede estar vacia
    @NotEmpty
    private List<Imputacion> imputaciones = new ArrayList<>();
}
