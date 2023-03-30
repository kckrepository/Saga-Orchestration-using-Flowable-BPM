package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name="detail_trx")
public class DetailTrx {
    @Id
    @Column(columnDefinition = "uuid", name = "id")
    private UUID id;

    @Column(name = "type_trx")
    private TypeTrx typeTrx;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "amount")
    private BigDecimal amount;
}
