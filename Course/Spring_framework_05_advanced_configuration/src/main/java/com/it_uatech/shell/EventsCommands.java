package com.it_uatech.shell;

import com.it_uatech.events.EventsPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@Profile("shell")
@ShellComponent
@RequiredArgsConstructor
public class EventsCommands {

    private final EventsPublisher eventsPublisher;

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "stvort") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Publish event command", key = {"p", "pub", "publish"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void publishEvent() {
        eventsPublisher.publish();
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null ? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
