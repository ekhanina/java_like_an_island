package ru.stqa.island.mantis.model;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;
    private String subject;

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getSummary() {
        return summary;
    }


    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (description != null ? !description.equals(issue.description) : issue.description != null) return false;
        return subject != null ? subject.equals(issue.subject) : issue.subject == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
