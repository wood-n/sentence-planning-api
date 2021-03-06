package uk.gov.digital.justice.hmpps.sentenceplan.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uk.gov.digital.justice.hmpps.sentenceplan.jpa.entity.ObjectiveEntity;
import uk.gov.digital.justice.hmpps.sentenceplan.jpa.entity.ObjectiveStatusEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ApiModel(description = "Objective on a Sentence Plan")
public class ObjectiveDto {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("needs")
    private List<UUID> needs;
    @JsonProperty("actions")
    private List<ActionDto> actions;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("meetsChildSafeguarding")
    private boolean meetsChildSafeguarding;
    @JsonProperty("status")
    private ObjectiveStatus status;
    @JsonProperty("statusChanges")
    private List<ObjectiveStatusEntity> statusChanges;

    public static ObjectiveDto from(ObjectiveEntity objective) {
        var actions = ActionDto.from(objective.getActions().values());
        return new ObjectiveDto(objective.getId(),
                objective.getDescription(),
                objective.getNeeds(),
                actions,
                objective.getPriority(),
                objective.isMeetsChildSafeguarding(),
                objective.getStatus(),
                objective.getStatusChanges());
    }

    public static List<ObjectiveDto> from(Collection<ObjectiveEntity> objectives) {
        return objectives.stream().map(ObjectiveDto::from).collect(Collectors.toList());
    }
}
