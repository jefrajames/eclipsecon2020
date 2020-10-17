package org.jefrajames.graphqldemo.merchant.entity;

import javax.persistence.Embeddable;
import org.eclipse.microprofile.graphql.Description;

@Embeddable
@Description("Postal address")
public class PostalAddress {
    
    public String number;
    
    public String street;
    
    public String city;
    
    public String country;
    
}
