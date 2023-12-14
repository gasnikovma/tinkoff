package edu.project3.printer;

import edu.project3.model.Statistics;

public interface Printer {

    <T> String print(Statistics<T> statistics);
}
