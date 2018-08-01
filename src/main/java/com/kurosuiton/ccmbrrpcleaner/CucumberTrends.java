package com.kurosuiton.ccmbrrpcleaner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Artem_Velichkin
 */
public class CucumberTrends {

    private LinkedList<String> buildNumbers;

    private ArrayList<Long> passedFeatures;

    private ArrayList<Long> failedFeatures;

    private ArrayList<Long> totalFeatures;

    private ArrayList<Long> passedScenarios;

    private ArrayList<Long> failedScenarios;

    private ArrayList<Long> totalScenarios;

    private ArrayList<Long> passedSteps;

    private ArrayList<Long> failedSteps;

    private ArrayList<Long> skippedSteps;

    private ArrayList<Long> pendingSteps;

    private ArrayList<Long> undefinedSteps;

    private ArrayList<Long> totalSteps;

    private ArrayList<Long> durations;

    void deleteBuildInfo(int index) {
        buildNumbers.remove(index);
        passedFeatures.remove(index);
        failedFeatures.remove(index);
        totalFeatures.remove(index);
        passedScenarios.remove(index);
        failedScenarios.remove(index);
        totalScenarios.remove(index);
        passedSteps.remove(index);
        failedSteps.remove(index);
        skippedSteps.remove(index);
        pendingSteps.remove(index);
        undefinedSteps.remove(index);
        totalSteps.remove(index);
        durations.remove(index);
    }

    LinkedList<String> getBuildNumbers() {
        return buildNumbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        getString(sb, "buildNumbers", buildNumbers);
        getString(sb, "passedFeatures", passedFeatures);
        getString(sb, "failedFeatures", failedFeatures);
        getString(sb, "totalFeatures", totalFeatures);
        getString(sb, "passedScenarios", passedScenarios);
        getString(sb, "failedScenarios", failedScenarios);
        getString(sb, "totalScenarios", totalScenarios);
        getString(sb, "passedSteps", passedSteps);
        getString(sb, "failedSteps", failedSteps);
        getString(sb, "skippedSteps", skippedSteps);
        getString(sb, "pendingSteps", pendingSteps);
        getString(sb, "undefinedSteps", undefinedSteps);
        getString(sb, "totalSteps", totalSteps);
        getString(sb, "durations", durations);
        sb.reverse().delete(0, 2).reverse().append("\n").append("}");
        return sb.toString();
    }

    private <T> void getString(StringBuilder sb, final String name, List<T> list) {
        sb.append("  \"").append(name).append("\" : [ ");
        for (T item : list) {
            if (item instanceof String) {
                sb.append("\"").append(item).append("\", ");
            } else {
                sb.append(item).append(", ");
            }
        }
        if (list.size() > 0) {
            sb.reverse().delete(0, 2).reverse().append(" ");
        }
        sb.append("],\n");
    }
}
