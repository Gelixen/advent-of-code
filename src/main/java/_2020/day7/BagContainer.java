package _2020.day7;

import lombok.Value;

import java.util.List;

@Value
public class BagContainer {
    String container;
    List<Bag> contained;
}
