package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();
        // ❌ Imperative approach (procedural)
        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
//        females.forEach(System.out::println);

        // ✔ Declarative approach (functional)
        // Filter
        List<Person> filtered = people.stream().filter(person ->
            person.getGender().equals(Gender.FEMALE)
        ).collect(Collectors.toList());
//        filtered.forEach(System.out::println);

        //Sort
        List<Person> sorted = people.stream()
            .sorted(Comparator.comparing(Person::getAge).reversed()) // 내림차순
            .collect(Collectors.toList());
//        sorted.forEach(System.out::println);

        //All match -모든 person 이 조건을 만족하는가?
        boolean allMatch = people.stream()
            .allMatch(person -> person.getAge() > 1);
//        System.out.println(allMatch); //true
        boolean allMatchFalse = people.stream()
            .allMatch(person -> person.getAge() > 40);
//        System.out.println(allMatchFalse); //false

        //Any match - 조건 만족하는 person 이 있는가?
        boolean anyMatch = people.stream()
            .anyMatch(person -> person.getAge() > 10);
//        System.out.println(anyMatch); //true
        boolean anyMatchFasle = people.stream()
            .anyMatch(person -> person.getAge() > 1000);
//        System.out.println(anyMatchFasle); //false

        //None match : 존재하지 않으면 true
        boolean noneMatch = people.stream()
            .noneMatch(person -> person.getName().equals("장혜리"));
//        System.out.println(noneMatch); //true

        boolean noneMatchFalse = people.stream()
            .noneMatch(person -> person.getName().equals("Antonio"));
//        System.out.println(noneMatchFalse); //false

        //Max
        Optional<Person> max = people.stream()
            .max(Comparator.comparing(Person::getAge));
//        max.ifPresent(person -> System.out.println(person));

        //Min
        Optional<Person> min = people.stream()
            .min(Comparator.comparing(Person::getAge));
//        min.ifPresent(person -> System.out.println(person));

        //Group
        Map<Gender, List<Person>> groupBy = people.stream()
            .collect(Collectors.groupingBy(Person::getGender)); // 성별로 grouping

        groupBy.forEach((gender, people1) ->{
            System.out.println(gender);
            people1.forEach(System.out::println);});
    }

    private static List<Person> getPeople() {
        return List.of(
            new Person("Antonio", 20, Gender.MALE),
            new Person("Alina Smith", 33, Gender.FEMALE),
            new Person("Helen White", 57, Gender.FEMALE),
            new Person("Alex Boz", 14, Gender.MALE),
            new Person("Jamie Goa", 99, Gender.MALE),
            new Person("Anna Cook", 7, Gender.FEMALE),
            new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}