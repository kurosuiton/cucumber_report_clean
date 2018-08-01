package com.kurosuiton.ccmbrrpcleaner;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Artem_Velichkin
 */
class Jenkins {

    private File jenkinsJobsPackage;

    Jenkins(String pathForJenkinsHome) {
        jenkinsJobsPackage = new File(pathForJenkinsHome);
    }

    void incrementBuildsNumber() {
        for (File job : getJobs()) {
            incrementBuildNumber(job);
        }
    }

    private void incrementBuildNumber(File job) {
        int lastBuildNumber = getLastBuildNumber(getBuildNumbersList(job)) + 1;
        String filePath = job.getAbsolutePath() + "/nextBuildNumber";
        System.out.println(filePath + " = " + lastBuildNumber);
        FileUtils.writeToFile(filePath, String.valueOf(lastBuildNumber));
    }

    private int getLastBuildNumber(List<String> buildNumbersList) {
        Comparator<String> comp = Comparator.comparing(Integer::valueOf);
        buildNumbersList.sort(comp);
        return buildNumbersList.size() == 0 ? 0 : Integer.valueOf(buildNumbersList.get(buildNumbersList.size() - 1));
    }

    File[] getJobs() {
        File jobsDirectory = new File(jenkinsJobsPackage + "/jobs");
        assert jobsDirectory.exists() && jobsDirectory.isDirectory();
        File[] jobs = jobsDirectory.listFiles();
        assert jobs != null;
        return jobs;
    }

    List<String> getBuildNumbersList(File job) {
        List<String> buildNamesList = new LinkedList<>();
        File builds = new File(job.getAbsoluteFile() + "/builds");
        if (builds.exists() && builds.isDirectory()) {
            JobFilter filter = new JobFilter();
            String[] buildNumbers = builds.list(filter);
            if (buildNumbers != null) {
                buildNamesList = Arrays.asList(buildNumbers);
            }
        }
        return buildNamesList;
    }
}
