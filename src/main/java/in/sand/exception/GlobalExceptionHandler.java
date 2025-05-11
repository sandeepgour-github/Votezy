package in.sand.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleVoteNotAllowedException(VoteNotAllowedException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleMethodArgurmentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errorsMap=new HashMap<>();
		BindingResult bResult=ex.getBindingResult();
		List<FieldError> fieldErrors = bResult.getFieldErrors();
		for(FieldError error:fieldErrors) {
			errorsMap.put(error.getField(),error.getDefaultMessage());
		}
		return new ResponseEntity<Map<String,String>>(errorsMap,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Required request body is missing");
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse error=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
