package uk.gov.digital.justice.hmpps.sentenceplan.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.digital.justice.hmpps.sentenceplan.api.ActionStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressEntity implements Serializable {

    private ActionStatus status;
    private LocalDateTime created;
    private String createdBy;

    public ProgressEntity(ActionStatus actionStatus, String createdBy) {
        this.status = actionStatus;
        this.created = LocalDateTime.now();
        this.createdBy = createdBy;
    }
}
