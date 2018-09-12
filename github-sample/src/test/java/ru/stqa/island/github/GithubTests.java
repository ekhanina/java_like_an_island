package ru.stqa.island.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("2dfad825c2fb5e1772eef74d82ce5fd4541eeab0");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ekhanina", "java_like_an_island")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
