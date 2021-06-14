package es.alfatec.imputaciones.desktop.util;

import es.alfatec.imputaciones.desktop.modelo.userConfig.UserConfig;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorUtil {
    private static final ValidatorFactory validatorFactory;
    private static final Validator validator;
    static {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    public static ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }
    public static Validator getValidator() {
        return validator;
    }
    public static <T> String validationsToString(Set<ConstraintViolation<T>> validations) {
        String errores = "";
        
        for (ConstraintViolation<T> cViolation : validations) {
            errores += "("+cViolation.getPropertyPath()+"): "+cViolation.getMessage()+"\n";
        }
        
        return errores;
    }
}