package es.alfatec.imputaciones.desktop.util;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import es.alfatec.imputaciones.desktop.xmlCustomSerializers.FormatoExportacionDeserializer;
import es.alfatec.imputaciones.desktop.xmlCustomSerializers.FormatoExportacionSerializer;
import es.alfatec.imputaciones.util.modelo.FormatoExportacion;

public class XmlUtilParser {
    public static XmlMapper getMainXmlMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        /*
        Alternativa a usar xmlMapper.registerModule(new JavaTimeModule())
        */
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        LocalDateDeserializer localDateDesSerializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        module.addSerializer(LocalDate.class, localDateSerializer);
        module.addDeserializer(LocalDate.class, localDateDesSerializer);
        
        //serializadores para enum:  FormatoExportacion
        module.addSerializer(FormatoExportacion.class, new FormatoExportacionSerializer());
        module.addDeserializer(FormatoExportacion.class, new FormatoExportacionDeserializer());
        
        XmlMapper xmlMapper = new XmlMapper(module);
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        /*el modulo: JavaTimeModule: agrega varios Deserializer y serializer al xmlmapper
        como esto: addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
         y este: addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
        para establecer un formato de fecha custom seria agregar los serializer a mano y cambiar los formatos
        o tambien agregar esto en las propiedades:
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate fechaInicio;
        por algun motivo el JsonFormat funciona tambien en xml. extranio si: ?¿?¿?¿?¿?¿?¿
        */
        //xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper;
    }
}
