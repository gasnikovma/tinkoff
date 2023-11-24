package edu.project3.model;

import java.time.OffsetDateTime;

public record LogRecord(String remoteAddress, String remoteUser, OffsetDateTime localTime, String request,
                        Integer status, Integer bodyBites, String httpRefer, String httpUserAgent){

}

