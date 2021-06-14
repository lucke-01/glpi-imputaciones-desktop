package es.alfatec.imputaciones.desktop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.alfatec.imputaciones.desktop.cmd.ProcesadorCmd;

//TODO: tarea maven para compilar con todas las dependencias en un solo jar
//TODO: tarea maven para copiar un configuracion.xml de ejemplo
//TODO: fijarse en fuifi imputados para todo lo de maven
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) {
        log.info("Inicio Procesamiento");
        
        if (args.length > 0) {
        	ProcesadorCmd procesadorCmd = ProcesadorCmd.inicializaProcesadorCmd(args);
        	procesadorCmd.procesaFicheroCsv();
        } else {
        	throw new UnsupportedOperationException("Aun no se ha implementado la interfaz grafica");
        }
    }
}
