/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gft.assetstream.assetenricher.business.domain;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Dailton Santana de Almeida
 */
@NoArgsConstructor
@Data
@ToString
public class Asset {
    private String id;
    private BigDecimal value;
}
