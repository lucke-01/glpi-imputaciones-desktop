package es.alfatec.imputaciones.desktop.cmd;

import java.io.File;

import es.alfatec.imputaciones.desktop.core.Constantes;
import es.alfatec.imputaciones.desktop.core.ProcesadorImputaciones;
import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import es.alfatec.imputaciones.desktop.util.UserConfigUtil;
import lombok.Data;

@Data
public class ProcesadorCmd {
	private String rutaFicheroCsv;
	private File ficheroConfiguracionXml = new File(Constantes.USER_CONFIG_FILE);
	
	private ProcesadorCmd() {
	}
	
	public static ProcesadorCmd inicializaProcesadorCmd(String args[]) {
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("Se require fichero csv para procesa");
		}
		ProcesadorCmd procesadorCmd = new ProcesadorCmd();
		procesadorCmd.setRutaFicheroCsv(new File(args[0]).getPath());
		if (args.length > 1) {
			procesadorCmd.setFicheroConfiguracionXml(new File(args[1]));
		}
		return procesadorCmd;
	}
	
	public void procesaFicheroCsv() {
        UserConfig userConfig = UserConfigUtil.getUserConfig(ficheroConfiguracionXml,rutaFicheroCsv);
        
        ProcesadorImputaciones procesadorImputaciones = new ProcesadorImputaciones(userConfig);
        procesadorImputaciones.procesarImputaciones();
	}
}
