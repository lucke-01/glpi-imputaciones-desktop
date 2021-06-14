/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alfatec.imputaciones.desktop.xmlCustomSerializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import es.alfatec.imputaciones.util.modelo.FormatoExportacion;
import java.io.IOException;

public class FormatoExportacionDeserializer extends StdDeserializer<FormatoExportacion> {
    public FormatoExportacionDeserializer() {
        super(FormatoExportacion.class);
    }
    @Override
    public FormatoExportacion deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        FormatoExportacion formatoExportacion = null;
        String formatoCadena = p.getValueAsString();
        
        if (formatoCadena != null && !formatoCadena.isEmpty() ) {
            if (FormatoExportacion.EXCEL.name().equalsIgnoreCase(formatoCadena)) {
                formatoExportacion = FormatoExportacion.EXCEL;
            } else if (FormatoExportacion.CSV.name().equalsIgnoreCase(formatoCadena)) {
                formatoExportacion = FormatoExportacion.CSV;
            }
        }
        
        return formatoExportacion;
    }
    
}
