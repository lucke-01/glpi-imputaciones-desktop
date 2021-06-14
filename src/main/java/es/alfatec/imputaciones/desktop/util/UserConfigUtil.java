package es.alfatec.imputaciones.desktop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.alfatec.imputaciones.desktop.modelo.userConfig.Imputacion;
import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import es.alfatec.imputaciones.util.utilidades.FicheroUtil;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

public class UserConfigUtil {
    
    public static UserConfig getUserConfig(File fileXml,String[] args) {
        UserConfig userConfig = null;
        String ficheroXmlCadena = FicheroUtil.fileToString(fileXml);
        
        XmlMapper xmlMapper = XmlUtilParser.getMainXmlMapper();
        try {
            userConfig = xmlMapper.readValue(ficheroXmlCadena, UserConfig.class);
            estableceArgumentosUserConfig(userConfig,args);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Error al establecer userConfig: "+ex.getMessage());
        }
        return userConfig;
    }
    public static void estableceArgumentosUserConfig(UserConfig userConfig,String[] args) {
        for (int indice = 0; indice < userConfig.getImputaciones().size(); indice++) {
            Imputacion imputacion = userConfig.getImputaciones().get(indice);

            //argumento ficheroImputaciones si no esta en el fichero xml
            if (StringUtils.isEmpty(imputacion.getFicheroImputaciones())) {
                if (args != null && args.length != 0) {
                    imputacion.setFicheroImputaciones(args[0]);
                }
            }
            //nombre si no esta establecido se genera
            if (StringUtils.isEmpty(imputacion.getNombre())) {
                imputacion.setNombre("imputacion_"+indice);
            }
            //estableciendo ficheroSalida si no esta en el fichero xml
            if (StringUtils.isEmpty(imputacion.getFicheroSalida()) && 
                    imputacion.getFechaInicio() != null &&  imputacion.getFechaFin() != null &&
                    imputacion.getFormatoSalida() != null) {
                String fechaInicioFormatoFichero = FechaUtil.localDateToString(imputacion.getFechaInicio(), FechaUtil.FORMATO_FECHA_FICHERO);
                String fechaFinFormatoFichero = FechaUtil.localDateToString(imputacion.getFechaFin(), FechaUtil.FORMATO_FECHA_FICHERO);
                String extension = imputacion.getFormatoSalida().getExtension();
                imputacion.setFicheroSalida(imputacion.getNombre()+"_"+fechaInicioFormatoFichero+"__"+fechaFinFormatoFichero
                        +"."+extension);
            }
        }
    }
}
