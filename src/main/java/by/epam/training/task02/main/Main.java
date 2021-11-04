package by.epam.training.task02.main;


import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Oven;
import by.epam.training.task02.entity.Refrigerator;
import by.epam.training.task02.entity.criteria.*;
import by.epam.training.task02.service.ApplianceService;
import by.epam.training.task02.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        ServiceFactory factory = ServiceFactory.getInstance();
        ApplianceService service = factory.getApplianceService();

        SearchParameter ovenParameter = new SearchParameter(SearchCriteria.Oven.CAPACITY, new Range<Double>(33d, 36d));
        SearchParameter ovenParameter1 = new SearchParameter(SearchCriteria.AnyAppliance.WEIGHT, new Range<Double>(0d, 10d));
        SearchParameter ovenParameter2 = new SearchParameter(SearchCriteria.AnyAppliance.WEIGHT, new SingularValue<Double>(18d));
        Criteria criteriaOven = new Criteria("Two ovens");
        criteriaOven.add(ovenParameter, ovenParameter1, ovenParameter2);

        printListToConsole(service.find(criteriaOven));
        System.out.println("-------------------------------------");

        SearchParameter wiredParameter = new SearchParameter(SearchCriteria.WiredAppliance.POWER_CONSUMPTION, new Range<Integer>(0, 150));
        Criteria criteriaWiredOnly = new Criteria("All wired appliances" +
                " with power consumption under 150 watt");

        criteriaWiredOnly.add(wiredParameter);
        printListToConsole(service.find(criteriaWiredOnly));
        System.out.println("-------------------------------------");

        SearchParameter laptopParameter1 = new SearchParameter(SearchCriteria.Laptop.OS, new SingularValue<String>("Windows"));
        SearchParameter laptopParameter2 = new SearchParameter(SearchCriteria.Laptop.MEMORY_ROM, new SingularValue<Integer>(8000));

        Criteria laptopCriteria = new Criteria("Windows laptops with ROM of 8000");
        laptopCriteria.add(laptopParameter1, laptopParameter2);
        printListToConsole(service.find(laptopCriteria));

        System.out.println("-----------------------------");

        Appliance oven = new Oven(15, 50, 50, 50, 1000, 42);
        Appliance fridge = new Refrigerator(15, 80, 210, 60, 300, 280, 700);

        List<Appliance> listToWrite = new ArrayList<>();
        listToWrite.add(oven);
        listToWrite.add(fridge);

        System.out.println("Correct writing operation - Gonna be true: " + service.write(listToWrite));
        //u gotta check the XML file in DOMParser.SOURCE_PATH
        //but believe me, it did write everything down
        System.out.println("-----------------------------");

        Criteria emptyCriteria = new Criteria("Empty!");
        System.out.println("'Tis gonna be null: " + service.find(emptyCriteria) + " "
                + emptyCriteria.getGroupSearchName());
        System.out.println("-----------------------------");

        Criteria paramMismatchCriteria = new Criteria("Mismatched!");
        SearchParameter mismatched = new SearchParameter(SearchCriteria.AnyAppliance.WIDTH,
                new SingularValue<>("very heavy"));
        paramMismatchCriteria.add(mismatched);
        System.out.println("'Tis gonna be null: " + service.find(paramMismatchCriteria) + " "
                + paramMismatchCriteria.getGroupSearchName());
        System.out.println("-----------------------------");

        Criteria tooBigValueCriteria = new Criteria("Big value!");
        SearchParameter tooBig = new SearchParameter(SearchCriteria.AnyAppliance.WEIGHT,
                new SingularValue<>(Double.MAX_VALUE));
        tooBigValueCriteria.add(tooBig);
        System.out.println("'Tis gonna be null: " + service.find(tooBigValueCriteria) + " "
                + tooBigValueCriteria.getGroupSearchName());
        System.out.println("-----------------------------");

        List<Appliance> emptyListToWrite = new ArrayList<>();
        System.out.println("Incorrect write - 'Tis gonna be false: " + service.write(emptyListToWrite));
        System.out.println("-----------------------------");

        Criteria correctCriteria = new Criteria("Its correct, but there are no such appliances");
        correctCriteria.add(new SearchParameter(SearchCriteria.Laptop.OS, new SingularValue<>("Unix")));
        System.out.println("'Tis gonna be empty: " + service.find(correctCriteria) + " "
                + correctCriteria.getGroupSearchName());


    }

    private static void printListToConsole(List<?> list) {
        for (Object element : list) {
            System.out.println(element.toString());
        }
    }

}
