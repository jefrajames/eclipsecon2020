/*
 * Copyright 2020 jefrajames.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jefrajames.graphqldemo.score.boundary;

import java.io.StringReader;
import java.net.URI;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.UriInfo;
import org.jefrajames.graphqldemo.score.entity.Score;

/**
 * Class in charge of decorating score domain objects with HATEOAS links.
 * 
 * @author jefrajames
 */
@ApplicationScoped
public class ScoreHateoasDecorator {
    
    Jsonb jsonb;

    @PostConstruct
    public void postConstruct() {
        jsonb = JsonbBuilder.create();
    }

    @PreDestroy
    public void preDestroy() {
        if (jsonb != null) {
            try {
                jsonb.close();
            } catch (Exception ignore) {
            }
        }
    }
    
    public URI getScoresUri(UriInfo uriInfo, String idScore) {
        return uriInfo.getBaseUriBuilder()
                .path(ScoreResource.class)
                .path(ScoreResource.class, "getScores")
                .build(idScore);      
    }

    private JsonObject scoreToJson(Score score) {
        String jsonAsString = jsonb.toJson(score);
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonAsString))) {
            return jsonReader.readObject();
        }       
    }
    
    private JsonArrayBuilder scoresToJsonBuilder(UriInfo uriInfo, List<Score> scores) {

        JsonArrayBuilder peopleBuilder = Json.createArrayBuilder();
        scores.forEach(s -> {
            peopleBuilder.add(scoreToJson(s));
        });

        return peopleBuilder;
    }

    public JsonArray addLinksToScores(UriInfo uriInfo, String idNumber, List<Score> scores) {

        JsonArrayBuilder scoresBuilder = scoresToJsonBuilder(uriInfo, scores);

        JsonObject self = Json.createObjectBuilder().add("self", getScoresUri(uriInfo, idNumber).toString()).build();
        JsonArray linksArray = Json.createArrayBuilder().add(self).build();
        JsonObject linksObject = Json.createObjectBuilder().add("_links", linksArray).build();
        scoresBuilder.add(linksObject);

        return scoresBuilder.build();
    }
    
}
