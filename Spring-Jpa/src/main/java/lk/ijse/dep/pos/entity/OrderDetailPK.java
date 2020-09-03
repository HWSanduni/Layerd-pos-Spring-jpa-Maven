package lk.ijse.dep.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderDetailPK implements SuperEntity {
    private String orderId;
    private String itemCode;


}
