package com.example.merchant.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class MerchantProduct {
    @Id
    @GeneratedValue(generator = "seq_gen_alias")
    @GenericGenerator(name="seq_gen_alias",strategy = "increment")
    private long merchantId;
    private long productId;

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @ManyToMany
    @JoinTable(name="merchantproduct",joinColumns = {
            @JoinColumn(name = "mid")},
            inverseJoinColumns = {
                    @JoinColumn(name="pid")
            })
    private Set<Merchant> merchantSet;

    public Set<Merchant> getMerchantSet() {
        return merchantSet;
    }

    public void setMerchantSet(Set<Merchant> merchantSet) {
        this.merchantSet = merchantSet;
    }


}
