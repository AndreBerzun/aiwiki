package ch.lianto.aiwiki.engine.utils;

import ch.lianto.aiwiki.engine.entity.Chat;

public class TestDataChats {
    public final Chat basic;
    public final Chat big;
    public final String basicReply = "How would I know? I'm here to answer enterprise questions!";

    public TestDataChats() {
        basic = new Chat()
            .question("How big is mount Everest?")
            .answer(basicReply);
        big = new Chat()
            .question("What is the Lianto Software?")
            .answer("The Lianto system is an astronomical risk assessment system. It observes various astronomical events (such as the movement of asteroids, fusions of stellar objects and others) and yields risk warnings based on a pre-built knowledge system.")
            .question("And what team is responsible for the development of Lianto?")
            .answer("COMCON has a dedicated development team for Lianto")
            .question("Ah, ok. And who are the responsible developers?");
    }
}
