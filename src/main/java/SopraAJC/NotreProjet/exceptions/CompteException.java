package SopraAJC.NotreProjet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST,value=HttpStatus.BAD_REQUEST, reason="Compte inexistant")
public class CompteException extends RuntimeException {
}
