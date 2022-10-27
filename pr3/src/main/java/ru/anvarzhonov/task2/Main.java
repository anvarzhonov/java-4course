package ru.anvarzhonov.task2;

import ru.anvarzhonov.task2.process.CombineTwoFlowsTransformer;
import ru.anvarzhonov.task2.process.FlowSkipFirstThreeDigits;
import ru.anvarzhonov.task2.process.FlowTransformer;
import ru.anvarzhonov.task2.process.NumbersToSquareNumbersFlow;

import java.util.List;

/**
 * 2 вариант. Задания 2.1.1, 2.2.1, 2.3.1
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 12:18
 */
public class Main {
    public static void main(String[] args) {
        List<FlowTransformer> services = List.of(
//            new NumbersToSquareNumbersFlow()
//            new FlowSkipFirstThreeDigits()
//            new CombineTwoFlowsTransformer()
        );

        services.forEach(s -> {     
            List result = s.transformFlow();
            System.out.println(result);
        });
    }
}
