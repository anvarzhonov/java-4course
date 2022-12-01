package ru.anvarzhonov.rsocketserver.DTO;

import lombok.Data;

/**
 * todo Document type Plane
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 08.11.2022 - 21:29
 */
@Data
public class Plane {
    private Long id;
    private String name;
    private int capacityOfPeople;
    private String engineName;

    public Plane(String name, int capacityOfPeople, String engineName) {
        this.name = name;
        this.capacityOfPeople = capacityOfPeople;
        this.engineName = engineName;
    }

    public Plane(Long id, String name, int capacityOfPeople, String engineName) {
        this.id = id;
        this.name = name;
        this.capacityOfPeople = capacityOfPeople;
        this.engineName = engineName;
    }
}
