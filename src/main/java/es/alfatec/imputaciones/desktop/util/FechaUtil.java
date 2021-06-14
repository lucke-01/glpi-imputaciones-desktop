package es.alfatec.imputaciones.desktop.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class FechaUtil {
    public static final String DEFAULT_FECHA_FICHERO = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_FICHERO = "dd_MM_yyyy";
    
    public static String localDateToString(LocalDate localDate) {
        return localDateToString(localDate, DEFAULT_FECHA_FICHERO);
    }
    public static String localDateToString(LocalDate localDate, String formato) {
        return localDate.format(DateTimeFormatter.ofPattern(formato));
    }
}
