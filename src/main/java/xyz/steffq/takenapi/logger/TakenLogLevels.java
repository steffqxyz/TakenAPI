package xyz.steffq.takenapi.logger;

public enum TakenLogLevels {

    INFO(""), SUCCESS("<green>"), ERROR("<red>");

    final String color;

    TakenLogLevels(final String color) {
        this.color = color;
    }

}