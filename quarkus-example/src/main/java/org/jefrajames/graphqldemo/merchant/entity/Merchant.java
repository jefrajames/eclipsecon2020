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

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.ToString;

/**
 *
 * @author jefrajames
 * @param <BusinessActivity>
 */
@Entity
@Description("Merchant acquiring data")
@ToString(includeFieldNames = true)
@RegisterForReflection
public class Merchant extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Description("Brand name")
    public String brandName;

    @Description("Postal Address")
    public PostalAddress postalAddress;

    @Description("List of contacts")
    @ElementCollection(fetch = FetchType.LAZY, targetClass = Contact.class)
    public List<Contact> contacts;

    @JsonbDateFormat("dd/MM/yyyy")
    @Description("Date of contract")
    public LocalDate joinDate;
    
    @Description("Merchant activity")
    @Enumerated(EnumType.STRING)
    public BusinessActivity businessActivity;
    
    @Description("Merchant web site")
    public String webSite;
    
    @Description("Contract status")
    @Enumerated(EnumType.STRING)
    public ContractStatus contractStatus;
     
    @Description("Expected level of service")
    @Enumerated(EnumType.STRING)
    public ServiceLevel serviceLevel;

    @Description("Accepted payment channels")
    @ElementCollection(fetch = FetchType.LAZY, targetClass=PaymentChannel.class)
    @Enumerated(EnumType.STRING)
    public List<PaymentChannel> paymentChannels;
 
    @Description("Accepted card schemes")
    @ElementCollection(fetch = FetchType.LAZY, targetClass=CardScheme.class)
    @Enumerated(EnumType.STRING)
    public List<CardScheme> cardSchemes;

    @Description("Is scoring service enabled?")
    public Boolean scoreEnabled;

    @Description("Id to access the score service")
    public String scoreId;

}
