package ru.anvarzhonov.task2.process;

import org.apache.commons.lang3.RandomStringUtils;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Даны два потока по 1000 элементов: первый содержит случайную букву, второй — случайную цифру.
 * Сформировать поток, каждый элемент которого объединяет элементы из обоих потоков.
 * Например, при входных потоках (A, B, C) и (1, 2, 3) выходной поток — (A1, B2, B3
 *
 * @author - Anvarzhonov Z.T. IKBO-20-19 on 19.10.2022 - 13:37
 */
public class CombineTwoFlowsTransformer implements FlowTransformer<String> {
    @Override
    public List<String> transformFlow() {
        List<String> randomStrings = fillArray();
        List<Integer> randomNumbers = FlowTransformer.fillArray();
        List<String> result = new ArrayList<>();

        System.out.println("Входной массив букв:" + randomStrings);
        System.out.println("Входной массив цифр: " + randomNumbers);

        Observable<String> stringsFlow = Observable.from(randomStrings);
        Observable<Integer> numbersFlow = Observable.from(randomNumbers);

        Observable.zip(stringsFlow, numbersFlow, (f1, f2) -> f1 + f2)
            .subscribe(result::add);

        return result;
    }

    private List<String> fillArray() {
        List<String> array = new ArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array.add(RandomStringUtils.randomAlphabetic(1));
        }
        return array;
    }
}
