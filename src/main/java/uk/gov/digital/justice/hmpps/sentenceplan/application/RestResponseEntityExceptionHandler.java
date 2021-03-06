package uk.gov.digital.justice.hmpps.sentenceplan.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.gov.digital.justice.hmpps.sentenceplan.api.ErrorResponse;
import uk.gov.digital.justice.hmpps.sentenceplan.security.AuthorisationException;
import uk.gov.digital.justice.hmpps.sentenceplan.service.exceptions.CurrentSentencePlanForOffenderExistsException;
import uk.gov.digital.justice.hmpps.sentenceplan.service.exceptions.EntityNotFoundException;
import uk.gov.digital.justice.hmpps.sentenceplan.service.exceptions.NoOffenderAssessmentException;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException e) {
        log.error("EntityNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(404)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handle(ValidationException e) {
        log.error("ValidationException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), BAD_REQUEST);
    }

    @ExceptionHandler(CurrentSentencePlanForOffenderExistsException.class)
    public ResponseEntity<ErrorResponse> handle(CurrentSentencePlanForOffenderExistsException e) {
        log.error("CurrentSentencePlanForOffenderExistsException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), BAD_REQUEST);
    }

    @ExceptionHandler(NoOffenderAssessmentException.class)
    public ResponseEntity<ErrorResponse> handle(NoOffenderAssessmentException e) {
        log.error("NoOffenderAssessmentException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage("Assessment not found for offender")
                .userMessage("Assessment not found for offender").build(), BAD_REQUEST);
    }

    @ExceptionHandler(AuthorisationException.class)
    public ResponseEntity<ErrorResponse> handle(AuthorisationException e) {
        log.error("AuthorisationException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(401)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(500)
                .developerMessage("Internal Server Error. Check Logs")
                .userMessage("An unexpected error has occurred").build(), INTERNAL_SERVER_ERROR);
    }
}
