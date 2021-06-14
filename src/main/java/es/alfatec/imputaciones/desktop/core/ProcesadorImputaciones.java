package es.alfatec.imputaciones.desktop.core;

import es.alfatec.imputaciones.desktop.modelo.userConfig.Imputacion;
import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import es.alfatec.imputaciones.desktop.util.ValidatorUtil;
import es.alfatec.imputaciones.util.fichero.exportador.ExportadorFicheroFactory;
import es.alfatec.imputaciones.util.fichero.exportador.ExportadorFicheroTareas;
import es.alfatec.imputaciones.util.fichero.extractor.ExtractorFicheroTareas;
import es.alfatec.imputaciones.util.modelo.TareaListado;
import es.alfatec.imputaciones.util.utilidades.FicheroUtil;
import jakarta.validation.ConstraintViolation;
import java.io.File;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcesadorImputaciones {
    private static final Logger log = LogManager.getLogger(ProcesadorImputaciones.class);
    private final UserConfig userConfig;

    public ProcesadorImputaciones(UserConfig userConfig) {
        this.userConfig = userConfig;
    }
    
    public void procesarImputaciones() {
        for (Imputacion imputacion : userConfig.getImputaciones()) {
            try {
                Set<ConstraintViolation<Imputacion>> violationImputacion = checkImputacion(imputacion);
                if (violationImputacion.isEmpty()) {
                    if (imputacion.isActivo()) {
                        //PROCESAMIENTO IMPUTACION
                        procesarImputacion(imputacion);
                    } else {
                        log.info("Omitida imputacion: ("+imputacion.getNombre()+") no activa.");
                    }
                } else {
                    log.warn("Omitida imputacion: ("+imputacion.getNombre()+")\nErrores:\n"+ValidatorUtil.validationsToString(violationImputacion));
                }
            } catch(Exception e) {
                log.error("Fallo al exportacion imputacion: "+imputacion.getNombre()+" mensaje Error: "+e.getMessage(),e);
            }
        }
    }
    public Set<ConstraintViolation<Imputacion>> checkImputacion(Imputacion imputacion) {
        Set<ConstraintViolation<Imputacion>> constraintViolations = ValidatorUtil.getValidator().validate( imputacion );
        return constraintViolations;
    }
    public void procesarImputacion(Imputacion imputacion) {
        File ficheroImputaciones = new File(imputacion.getFicheroImputaciones());
        final String contenidoFichero = FicheroUtil.fileToString(ficheroImputaciones);
        final ExtractorFicheroTareas extractorFicheroTareas = new ExtractorFicheroTareas(contenidoFichero,imputacion);
        TareaListado tareaListado =  extractorFicheroTareas.extraeTareaListado();
        
        String rutaFicheroSalida = userConfig.getDirectorioSalida()+"/"+imputacion.getFicheroSalida();
        ExportadorFicheroTareas exportador = ExportadorFicheroFactory
                .getExportadorFicheroTareas(tareaListado,rutaFicheroSalida , imputacion.getFormatoSalida());
        exportador.exportar();
    }
}
