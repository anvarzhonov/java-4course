package ru.anvarzhonov.rsocketclient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * todo Document type PlaneRequest
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 09.11.2022 - 14:10
 */
@Data
@AllArgsConstructor
public class PlaneRequest {
    private String name;
    private int capacityOfPeople;
    private String engineName;
}
