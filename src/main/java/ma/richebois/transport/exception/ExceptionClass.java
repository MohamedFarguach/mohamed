package ma.richebois.transport.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice 
public class ExceptionClass extends ResponseEntityExceptionHandler  {

	
	 @ExceptionHandler(IllegalStateException.class)
		public ResponseEntity<?> handleException(IllegalStateException exception){
			return ResponseEntity.badRequest()
					.body(exception.getMessage());		
		}
		@ExceptionHandler(EntityNotFoundException.class)
		public ResponseEntity<?> handleException(){
			return ResponseEntity
					.notFound()
					.build();	
		}
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleException(Exception exception){
			return ResponseEntity.badRequest()
					.body(exception.getMessage());		
		}
}