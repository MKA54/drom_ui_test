package com.drom;

import ReadProperties.ConfigProvider;
import com.drom.core.BaseSeleniumTest;
import com.drom.page.CarsPage;
import com.drom.page.LoginPage;
import com.drom.page.PopularFirmsPage;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class DromTest extends BaseSeleniumTest {
    @Test
    public void filterCars() throws InterruptedException {
        CarsPage carsPage = new CarsPage(ConfigProvider.URL);

        carsPage.getCars();
    }

    @Test
    public void Login() {
        LoginPage loginPage = new LoginPage(ConfigProvider.URL);

        loginPage.getLoginPage();
        loginPage.login();
        loginPage.addСarToFavorites();
    }

    @Test
    public void popularFirms() throws InterruptedException {
        PopularFirmsPage popularFirmsPage = new PopularFirmsPage(ConfigProvider.URL);

        List<String> allFirms = popularFirmsPage.getPopularFirm();

        Map<String, Integer> companiesWithCars = new HashMap<>();
        for (String allFirm : allFirms) {
            String[] splitLine = allFirm.split("\n");

            for (String s : splitLine) {
                int index = s.indexOf("(");
                if (index < 0) {
                    continue;
                }

                String firma = s.substring(0, index).trim();
                String count = s.substring(index + 1, s.length() - 1);

                companiesWithCars.put(firma, Integer.valueOf(count));
            }
        }

        Map<String, Integer> companiesSortedByCountCars = companiesWithCars
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(20)
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("| Фирма | Количество объявления |");
        companiesSortedByCountCars.forEach((firm, count) -> System.out.printf("| %s | %s | \n", firm, count));
    }
}
