package SopraAJC.NotreProjet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST,value=HttpStatus.BAD_REQUEST,reason="données invalides")
public class RessourceException extends RuntimeException{

}
