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
package org.jefrajames.graphqldemo.merchant.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jefrajames.graphqldemo.merchant.entity.Merchant;

/**
 * In this demo, this class is technically over-engineered. It is here to
 * illustrate the decoupling between the APIs and the service implementation.
 *
 *
 * @author jefrajames
 */
@ApplicationScoped
public class MerchantService {

    @PersistenceContext(name = "MerchantDS")
    EntityManager em;

    public Merchant findById(long merchantId) {
        return em.find(Merchant.class, merchantId);
    }

    public List<Merchant> findAll() {
        return (List<Merchant>) em.createQuery("SELECT c FROM Merchant c", Merchant.class)
                .getResultList();
    }

    @Transactional
    public Merchant createMerchant(Merchant merchant) {
        if (merchant.getId() == null) {
            em.persist(merchant);
            return merchant;
        } else {
            return em.merge(merchant);
        }

    }

    @Transactional
    public boolean deleteMerchant(long id) {
        Merchant m = em.find(Merchant.class, id);
        if ( m==null )
        return false;
            em.remove(m);
        return true;
    }

}
