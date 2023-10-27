import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreamApi {

    List<TickerData> getTickerDataList () {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then().log().body()
                .extract().jsonPath().getList("data.ticker", TickerData.class);
    }

    @Test
    public void checkCrypto() {

        List<TickerData> usdTickerDataList = getTickerDataList()
                .stream().filter(ticker ->
                        ticker.getSymbol().endsWith("USDT"))
                .collect(Collectors.toList());

    }

    @Test
    public void sortHighToLow(){
        List<TickerData> highToLow = getTickerDataList().stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).collect(Collectors.toList());
        List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());

        Assertions.assertEquals(top10.get(0).getSymbol(),"BTC-USDT");
    }





}
