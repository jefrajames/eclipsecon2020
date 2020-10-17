package org.jefrajames.graphqldemo.score.entity;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.graphql.Description;

import lombok.Data;

@Description("A score value")
@Data
public class Score implements Measurable {

    @JsonbTransient
    private String id;
    
    private ScoreType name;
    private Long value;
    
}
