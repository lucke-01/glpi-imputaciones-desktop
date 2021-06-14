package test.procesadoCsv;

import es.alfatec.imputaciones.desktop.core.ProcesadorImputaciones;
import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import es.alfatec.imputaciones.desktop.util.UserConfigUtil;
import java.io.File;
import org.junit.jupiter.api.Test;

public class ProcesaFicheroImputacionTest {
    @Test
    public void testFicheroEjemplo_correcto_Abril2020() {
    	System.out.println("testFicheroEjemplo_correcto_Abril2020()");
        String[] args = new String[]{ProcesaFicheroImputacionTest.class.getResource("/tareasCsv/glpi_correcto_abril2021.csv").getPath()};
        File ficheroXml = new File(ProcesaFicheroImputacionTest.class.getResource("/xmlConfig/configuracion_correcto_abril2021.xml").getFile());
        UserConfig userConfig = UserConfigUtil.getUserConfig(ficheroXml,args);
        
        //modificamos la salida de userConfig para el testing
        userConfig.setDirectorioSalida("./src/test/resources/resultadoFicheros");
        
        ProcesadorImputaciones procesadorImputaciones = new ProcesadorImputaciones(userConfig);
        procesadorImputaciones.procesarImputaciones();
    }
    @Test
    public void testFichero_faltanDias_junio2021() {
    	System.out.println("testFichero_faltanDias_junio2021()");
        String[] args = new String[]{ProcesaFicheroImputacionTest.class.getResource("/tareasCsv/glpi_faltanDias_junio2021.csv").getPath()};
        File ficheroXml = new File(ProcesaFicheroImputacionTest.class.getResource("/xmlConfig/configuracion_faltanDias_junio2021.xml").getFile());
        UserConfig userConfig = UserConfigUtil.getUserConfig(ficheroXml,args);
        
        //modificamos la salida de userConfig para el testing
        userConfig.setDirectorioSalida("./src/test/resources/resultadoFicheros");
        
        ProcesadorImputaciones procesadorImputaciones = new ProcesadorImputaciones(userConfig);
        procesadorImputaciones.procesarImputaciones();
    }
}