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

    public Merchant findById(long merchantId) {
        return Merchant.findById(merchantId);
    }

    public List<Merchant> findAll() {
        return Merchant.findAll().list();
    }

    @Transactional
    public Merchant createMerchant(Merchant merchant) {
        Merchant.persist(merchant);
        return merchant;
    }

    @Transactional
    public Boolean deleteMerchant(Long merchantId) {
        if ( Merchant.findById(merchantId)==null )
            return false;
        
        Merchant.deleteById(merchantId);
        return true;
    }

}
