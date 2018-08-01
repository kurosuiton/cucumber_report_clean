package com.kurosuiton.ccmbrrpcleaner;

import com.google.gson.Gson;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Artem_Velichkin
 */
class Cleaner {

    private final Jenkins jenkins;

    Cleaner(Jenkins jenkins) {
        this.jenkins = jenkins;
    }

    void cleanCucumberReports() {
        File[] jobs = jenkins.getJobs();
        for (File job : jobs) {
            if (!job.isDirectory()) {
                continue;
            }
            deleteExistsBuildsFromCucumberTrendsJson(job);
        }
    }

    private  void deleteExistsBuildsFromCucumberTrendsJson(File job) {
        String cucumberTrendsJsonPath = job.getAbsolutePath() + "/cucumber-reports/cucumber-trends.json";
        if (new File(cucumberTrendsJsonPath).exists()) {
            System.out.println(job.getAbsolutePath());
            List<String> buildNumbersList = jenkins.getBuildNumbersList(job);
            CucumberTrends cucumberTrends = new Gson().fromJson(FileUtils.fileReader(cucumberTrendsJsonPath), CucumberTrends.class);
            LinkedList<String> buildNumbers = cucumberTrends.getBuildNumbers();
            for (Object object : buildNumbers.toArray()) {
                if (!buildNumbersList.contains(object)) {
                    cucumberTrends.deleteBuildInfo(buildNumbers.indexOf(object));
                }
            }
            saveCucumberTrendsJson(cucumberTrendsJsonPath, cucumberTrends);
        }
    }

    private  void saveCucumberTrendsJson(String cucumberTrendsJsonPath, CucumberTrends cucumberTrends) {
        String cucumberTrendsFinalJson = cucumberTrends.toString();
        System.out.println(cucumberTrendsFinalJson);
        FileUtils.writeToFile(cucumberTrendsJsonPath, cucumberTrendsFinalJson);
    }
}
