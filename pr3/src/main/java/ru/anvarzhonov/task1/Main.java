package ru.anvarzhonov.task1;

import org.apache.commons.lang3.RandomUtils;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * todo Document type Main
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 11:57
 */
public class Main {
    static final Integer NORMAL_TEMPERATURE_INDICATE = 25;
    static final Integer NORMAL_CO2_INDICATE = 70;

    public static void main(String[] args) {

        Observable<Integer> temperatureFlow = Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
            .map(x -> RandomUtils.nextInt(15, 30));

        Observable<Integer> co2Flow = Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
            .map(x -> RandomUtils.nextInt(30, 100));

        Observable.zip(temperatureFlow, co2Flow, Container::new)
            .subscribe(Main::getSignalization);
    }

    public static void getSignalization(Container container) {
        var temperature = container.getTemperature();
        var co2 = container.getCo2();
        System.out.println("Температура:" + temperature + " || CO2: " + co2);

        if (temperature > NORMAL_TEMPERATURE_INDICATE && co2 > NORMAL_CO2_INDICATE) {
            System.out.printf("Один из датчиков превышает допустимую норму: (Temperature: %s, CO2: %s) \n",
                NORMAL_TEMPERATURE_INDICATE, NORMAL_CO2_INDICATE);
            System.out.println("ALARM!!!");
        } else if (temperature > NORMAL_TEMPERATURE_INDICATE) {
            System.out.printf("Датчик температуры превышает допустимую норму: (Temperature: %s )\n",
                NORMAL_TEMPERATURE_INDICATE);
        } else if (co2 > NORMAL_CO2_INDICATE) {
            System.out.printf("Датчик CO2 превышает допустимую норму: (CO2: %s )\n",
                NORMAL_CO2_INDICATE);
        }
    }
}

class Container {
    Integer temperature;
    Integer co2;

    public Container(Integer temperature, Integer co2) {
        this.temperature = temperature;
        this.co2 = co2;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getCo2() {
        return co2;
    }
}

