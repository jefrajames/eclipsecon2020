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

import java.util.List;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jefrajames.graphqldemo.merchant.control.MerchantService;
import org.jefrajames.graphqldemo.merchant.entity.Merchant;

/**
 *
 * @author jefrajames
 */
@Path("merchants")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Merchant Contract API", description = "Expose Merchant contracts related data")
public class MerchantResource {

    @Context
    UriInfo uriInfo;

    @Inject
    MerchantHateoasDecorator hateoasDecorator;

    @Inject
    MerchantService merchantService;

    @GET
    @Path("/{id}")
    @Operation(description = "Get a merchant by its id")
    @APIResponse(responseCode = "200", description = "Merchant found", content = @Content(mediaType = "application/json"))
    @APIResponse(responseCode = "404", description = "Merchant not found", content = @Content(mediaType = "text/plain"))
    public Response findById(@PathParam("id") @Parameter(description = "Merchant id", required = true, example = "1") long id) {

        Merchant merchant = merchantService.findById(id);

        if (merchant == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Merchant with id " + id + " not found").type(MediaType.TEXT_PLAIN).build();
        }

        // Turns the domain object into a Json object with Hateoas links
        JsonObject merchantAsJson = hateoasDecorator.addLinksToMerchant(uriInfo, merchant);

        return Response.ok(merchantAsJson).build();
    }

    @GET
    @Operation(description = "Get the list of all merchants")
    @APIResponse(responseCode = "200", description = "Merchant list", content = @Content(mediaType = "application/json"))
    public Response findAll() {
        List<Merchant> merchants = merchantService.findAll();
        
        // Turns the list of marchants into a JsonArray with Hateoas links
        JsonArray merchantsAsJson = hateoasDecorator.addLinksToMerchants(uriInfo, merchants);
        
        return Response.ok(merchantsAsJson).build();
    }

}
