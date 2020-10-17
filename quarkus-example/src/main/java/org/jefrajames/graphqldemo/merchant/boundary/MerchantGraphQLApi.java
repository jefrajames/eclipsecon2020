package org.jefrajames.graphqldemo.merchant.boundary;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;
import org.jefrajames.graphqldemo.merchant.control.MerchantService;
import org.jefrajames.graphqldemo.merchant.entity.Merchant;
import org.jefrajames.graphqldemo.score.control.ScoreService;
import org.jefrajames.graphqldemo.score.entity.Score;

@ApplicationScoped
@GraphQLApi
@Log
public class MerchantGraphQLApi {

    @Inject
    ScoreService scoreService;
    
    @Inject
    MerchantService merchantService;

    @Query
    @Description("Get a merchant's contract using the Id")
    @Name("merchant")
    public Merchant findById(@Description("'Merchant id") long merchantId) {
        return merchantService.findById(merchantId);
    }
    
    @Query
    @Description("Get the list of all merchant contracts")
    @Name("allMerchants")
    public List<Merchant> findAll() {
        return merchantService.findAll();
    }

    

    // Makes score data available to all merchants
    public List<Score> getScores(@Source Merchant merchant) {
        log.warning("Eclipse demo => calling Payment Score service for merchant " + merchant.id);
        
        if (!merchant.scoreEnabled) {
            return null;
        }
        return scoreService.getScores(merchant.scoreId);
    }

    @Query
    @Description("Simulates a runtime exception")
    public List<Score> errorOnScores(@Source Merchant merchant) {
        throw new RuntimeException("Deliberate exception here!");
    }

    @Mutation
    @Description("Create a merchant contract")
    public Merchant createMerchant(Merchant merchant) {
        return merchantService.createMerchant(merchant);
    }

    @Mutation
    @Description("Delete a merchant by id")
    public boolean deleteMerchant(long merchantId) {
        return merchantService.deleteMerchant(merchantId);
    }

}
