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
package org.jefrajames.graphqldemo.merchant.entity;

import java.time.LocalDate;
import java.util.List;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.microprofile.graphql.Description;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author jefrajames
 * @param <BusinessActivity>
 */
@Entity
@Description("Merchant acquiring data")
@Data
@ToString(includeFieldNames = true)
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Description("Brand name")
    private String brandName;

    @Description("Postal Address")
    private PostalAddress postalAddress;

    @Description("List of contacts")
    @ElementCollection(fetch = FetchType.LAZY, targetClass = Contact.class)
    private List<Contact> contacts;

    @JsonbDateFormat("dd/MM/yyyy")
    @Description("Date of contract")
    private LocalDate joinDate;
    
    @Description("Merchant activity")
    @Enumerated(EnumType.STRING)
    private BusinessActivity businessActivity;
    
    @Description("Merchant web site")
    private String webSite;
    
    @Description("Contract status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
     
    @Description("Expected level of service")
    @Enumerated(EnumType.STRING)
    private ServiceLevel serviceLevel;

    @Description("Accepted payment channels")
    @ElementCollection(fetch = FetchType.LAZY, targetClass=PaymentChannel.class)
    @Enumerated(EnumType.STRING)
    private List<PaymentChannel> paymentChannels;
 
    @Description("Accepted card schemes")
    @ElementCollection(fetch = FetchType.LAZY, targetClass=CardScheme.class)
    @Enumerated(EnumType.STRING)
    private List<CardScheme> cardSchemes;

    @Description("Is scoring service enabled?")
    private Boolean scoreEnabled;

    @Description("Id to access the score service")
    private String scoreId;

}
