import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.WARN

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d %-4relative [%thread] %-5level %logger{35} [%X{tid}] - %msg%n"
    }
}
appender("FILE", RollingFileAppender) {
    file = "log/server.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "log/server.%d{yyyy-MM-dd}.log"
        maxHistory = 10
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "10MB"
        }
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d %-4relative [%thread] %-5level %logger{35} [%X{tid}] - %msg%n"
    }
}
root(INFO, ["CONSOLE", "FILE"])
logger("kuvaldis", INFO)