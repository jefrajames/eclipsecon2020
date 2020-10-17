package org.jefrajames.graphqldemo.score.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.json.bind.annotation.JsonbTransient;
import lombok.Data;
import org.eclipse.microprofile.graphql.Description;

@Description("A score value")
@Data
@RegisterForReflection
public class Score implements Measurable {

    @JsonbTransient
    private String id;
    
    private ScoreType name;
    private Long value;
    
}
