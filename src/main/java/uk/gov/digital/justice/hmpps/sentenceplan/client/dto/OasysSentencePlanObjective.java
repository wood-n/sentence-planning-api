package uk.gov.digital.justice.hmpps.sentenceplan.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OasysSentencePlanObjective {

    private List<OasysCriminogenicNeed> oasysCriminogenicNeeds;
    private List<OasysIntervention> oasysInterventions;
    private OasysObjectiveMeasure oasysObjectiveMeasure;
    private OasysRefElement objectiveType;
    private String objectiveCode;
    private String objectiveDescription;
    private String howMeasured;
    private String objectiveHeading;
    private String objectiveComment;
    private LocalDateTime createdDate;
}
