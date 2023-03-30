package org.example.dto;

import lombok.Data;
import org.example.entity.DetailTrx;

import java.util.List;

@Data
public class TrxDto {
    private List<DetailTrx> listTrx;

}
