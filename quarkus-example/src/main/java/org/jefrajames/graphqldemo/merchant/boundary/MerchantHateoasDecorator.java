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
package org.jefrajames.graphqldemo.merchant.boundary;

import java.io.StringReader;
import java.net.URI;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.UriInfo;
import org.jefrajames.graphqldemo.merchant.entity.Merchant;
import org.jefrajames.graphqldemo.score.boundary.ScoreHateoasDecorator;

/**
 * Class in charge of decorating merchant domain objects with HATEOAS links.
 *
 * @author jefrajames
 */
@ApplicationScoped
public class MerchantHateoasDecorator {

    @Inject
    ScoreHateoasDecorator scoreHateoasDecorator;

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

    // Turns a Merchant domain entity into a JsonObjectBuilder 
    private JsonObjectBuilder merchantToJsonBuilder(Merchant merchant) {

        // Any way to avoid converting to a String?
        String jsonMerchant = jsonb.toJson(merchant);
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonMerchant))) {
            JsonObject merchantJson = jsonReader.readObject();
            JsonObjectBuilder merchantBuilder = Json.createObjectBuilder();
            merchantJson.forEach((k, v) -> {
                merchantBuilder.add(k, v);
            });
            return merchantBuilder;
        }
    }

    public URI getMerchantUri(UriInfo uriInfo, long id) {
        return uriInfo.getBaseUriBuilder()
                .path(MerchantResource.class)
                .path(MerchantResource.class, "findById")
                .build(id);
    }

    // Add HATEOAS links to an individual Merchant
    public JsonObject addLinksToMerchant(UriInfo uriInfo, Merchant merchant) {

        JsonObjectBuilder merchantBuilder = merchantToJsonBuilder(merchant);

        JsonObject self = Json.createObjectBuilder().add("self", getMerchantUri(uriInfo, merchant.id).toString()).build();
        JsonArrayBuilder linksBuilder = Json.createArrayBuilder().add(self);

        if (merchant.scoreEnabled) {
            JsonObject scores = Json.createObjectBuilder().add("scores", scoreHateoasDecorator.getScoresUri(uriInfo, merchant.scoreId).toString()).build();
            linksBuilder.add(scores);
        }

        merchantBuilder.add("_links", linksBuilder.build());

        return merchantBuilder.build();

    }

    private URI getMerchantsUri(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(MerchantResource.class)
                .build();
    }

    private JsonArrayBuilder merchantsToJsonBuilder(UriInfo uriInfo, List<Merchant> people) {

        JsonArrayBuilder merchantsBuilder = Json.createArrayBuilder();
        people.forEach(p -> {
            merchantsBuilder.add(addLinksToMerchant(uriInfo, p));
        });

        return merchantsBuilder;
    }

    // Add HATEOAS links to the list of all Merchants
    public JsonArray addLinksToMerchants(UriInfo uriInfo, List<Merchant> merchants) {

        JsonArrayBuilder merchantsBuilder = merchantsToJsonBuilder(uriInfo, merchants);

        JsonObject self = Json.createObjectBuilder().add("self", getMerchantsUri(uriInfo).toString()).build();
        JsonArray linksArray = Json.createArrayBuilder().add(self).build();
        JsonObject linksObject = Json.createObjectBuilder().add("_links", linksArray).build();
        merchantsBuilder.add(linksObject);

        return merchantsBuilder.build();

    }

}
