package ru.anvarzhonov.rsocketclient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo Document type Plane
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 08.11.2022 - 21:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    private Long id;
    private String name;
    private int capacityOfPeople;
    private String engineName;
}
