package org.jefrajames.graphqldemo.score.boundary;

import java.util.List;
import javax.inject.Inject;
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
import org.jefrajames.graphqldemo.score.control.ScoreService;
import org.jefrajames.graphqldemo.score.entity.Score;

@Path("scores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Payment scoring API", description = "Expose payment scoring data for a given Merchant")
public class ScoreResource {

    @Inject
    ScoreService scoreService;

    @Inject
    ScoreHateoasDecorator hateoasDecorator;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/{scoreId}")
    @Operation(description = "Get a merchant's payment scores")
    @APIResponse(responseCode = "200", description = "Scores found", content = @Content(mediaType = "application/json"))
    @APIResponse(responseCode = "404", description = "Scores not found", content = @Content(mediaType = "text/plain"))
    public Response getScores(@PathParam("scoreId") @Parameter(description = "Merchant Score Identifier", required = true, example = "ms-1") String scoreId) {

        List<Score> scores = scoreService.getScores(scoreId);
        if (scores == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Score with id " + scoreId + " not found").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(hateoasDecorator.addLinksToScores(uriInfo, scoreId, scores)).build();
    }

}
