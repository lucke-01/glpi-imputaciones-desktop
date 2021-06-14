/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alfatec.imputaciones.desktop.xmlCustomSerializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import es.alfatec.imputaciones.util.modelo.FormatoExportacion;
import java.io.IOException;

/**
 *
 * @author ricardo
 */
public class FormatoExportacionSerializer extends StdSerializer<FormatoExportacion> {
    public FormatoExportacionSerializer() {
        super(FormatoExportacion.class, true);
    }
    @Override
    public void serialize(FormatoExportacion formatoExportacion, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(formatoExportacion.name());
    }
    
}
