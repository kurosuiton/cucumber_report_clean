package com.kurosuiton.ccmbrrpcleaner;

/**
 * @author Artem_Velichkin
 */
public class Runner {

    public static void main(String[] args) {
        assert args != null;
        Jenkins jenkins = new Jenkins(args[0]);
        // Удаляет записи из cucumber-trends.json при отсутствии сборок
        new Cleaner(jenkins).cleanCucumberReports();

        // Переустанавливает номера следующих сборок, если вторым параметром подать true
        if (args.length > 1 && Boolean.valueOf(args[1])) {
            jenkins.incrementBuildsNumber();
        }
    }

}
