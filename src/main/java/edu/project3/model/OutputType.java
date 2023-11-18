package edu.project3.model;

public enum OutputType {
    MARKDOWN,
    ADOC;

    public static OutputType getFormat(String type){
        if(type.equalsIgnoreCase("markdown")){
            return OutputType.MARKDOWN;
        }
        if(type.equalsIgnoreCase("ADOC")){
            return OutputType.ADOC;
        }
        throw new IllegalStateException("Incorrect format");

    }
}
