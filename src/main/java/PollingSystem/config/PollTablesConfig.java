package PollingSystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "poll.tables")
public class PollTablesConfig {

    private String users;
    private String questions;
    private String answers;

    public PollTablesConfig(String users, String questions, String answers) {
        this.users = users;
        this.questions = questions;
        this.answers = answers;
    }

    public String getUsers() {
        return this.users;
    }
    public String getQuestions() {
        return this.questions;
    }
    public String getAnswers() {
        return this.answers;
    }
}
