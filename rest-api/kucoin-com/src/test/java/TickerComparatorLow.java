import java.util.Comparator;

public class TickerComparatorLow implements Comparator<TickerData> {

    @Override
    public int compare(TickerData o1, TickerData o2) {

        Integer result = Integer.compare(
                Integer.parseInt(o1.getChangeRate()),
                Integer.parseInt(o2.getChangeRate()));

        return result;
    }
}
