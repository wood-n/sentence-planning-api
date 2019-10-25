package uk.gov.digital.justice.hmpps.sentenceplan.jpa.entity;

import lombok.*;
import uk.gov.digital.justice.hmpps.sentenceplan.api.CommentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentencePlanPropertiesEntity implements Serializable {

    private Boolean childSafeguardingIndicated;
    private Boolean complyWithChildProtectionPlanIndicated;
    private List<ActionEntity> steps = new ArrayList<>();
    private Map<CommentType, CommentEntity> comments = new HashMap<>();

    public void addStep(ActionEntity actionEntity) {
        steps.add(actionEntity);
    }

    public void addComment(CommentEntity commentEntity) {
        comments.put(commentEntity.getCommentType(), commentEntity);
    }
}
