package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Unstop on 01.05.2016.
 */
public class GithubTests {
@Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("d31fd50c05cbc1f490d7a84f3e7817510e16fe89");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("entarolex", "java_training")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String >().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
