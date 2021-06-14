package test.xmlConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import es.alfatec.imputaciones.desktop.util.UserConfigUtil;
import es.alfatec.imputaciones.desktop.util.ValidatorUtil;
import es.alfatec.imputaciones.desktop.util.XmlUtilParser;
import es.alfatec.imputaciones.util.utilidades.FicheroUtil;
import jakarta.validation.ConstraintViolation;
import java.io.File;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * checkea el parser de configuracion XML a java
 * @author ricardo
 */
public class configuracionParserTest {
    
    @Test
    public void testFicheroEjemplo() throws JsonProcessingException {
        //String[] args = new String[]{"glpi.csv"};
        File ficheroXml = new File(configuracionParserTest.class.getResource("/xmlConfig/configuracion_ejemplo.xml").getFile());
        UserConfig userConfig = UserConfigUtil.getUserConfig(ficheroXml,"glpi.csv");
        
        System.out.println("userConfig testFicheroEjemplo");
        System.out.println(userConfig);
        
        //comprobamos que pase el validador 
        Set<ConstraintViolation<UserConfig>> constraintViolations = ValidatorUtil.getValidator().validate( userConfig );
        assertEquals( 0, constraintViolations.size() );
    }
    @Test
    public void testFicheroEjemplo_validacionDatosMal() throws JsonProcessingException {
        //String[] args = new String[]{};
        File ficheroXml = new File(configuracionParserTest.class.getResource("/xmlConfig/configuracion_ejemplo_validacionDatosMal.xml").getFile());
        UserConfig userConfig = UserConfigUtil.getUserConfig(ficheroXml,"glpi.csv");
        
        System.out.println("userConfig testFicheroEjemplo_validacionDatosMal");
        System.out.println(userConfig);
        
        //comprobamos que pase el validador 
        Set<ConstraintViolation<UserConfig>> constraintViolations = ValidatorUtil.getValidator().validate( userConfig );
        assertEquals(4, constraintViolations.size() );
        
        //vemos los errores de validacion
        System.out.println("VALIDACIONES testFicheroEjemplo_validacionDatosMal(): ");
        System.out.println(ValidatorUtil.validationsToString(constraintViolations));
    }
}
