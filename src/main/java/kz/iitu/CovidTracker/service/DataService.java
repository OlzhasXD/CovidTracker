package kz.iitu.CovidTracker.service;

import kz.iitu.CovidTracker.model.Country;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<Country> allCountry = new ArrayList<>();

    public List<Country> getAllCountry() {
        return allCountry;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 12 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<Country> newCountry = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            Country country = new Country();
            country.setState(record.get("Province/State"));
            country.setCountry(record.get("Country/Region"));
            country.setNumberOfCases(Integer.parseInt(record.get(record.size()-1)));
            country.setDifference(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
            newCountry.add(country);
        }
        this.allCountry = newCountry;
    }
}
