import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.Response
import com.jayway.restassured.specification.RequestSpecification

class VenueRequests {
    private static String API_VERSION = 'application/vnd.com.kontakt+json;version=10'
    private static String CONTENT_TYPE = 'application/x-www-form-urlencoded'

    private static RequestSpecification baseRequest() {
        RestAssured.baseURI = System.getProperty('baseUrl')
        RequestSpecification request = RestAssured.given()
        return request
                .header('Api-Key', System.getProperty('apiKey'))
                .header('Accept', API_VERSION)
                .header('Content-Type', CONTENT_TYPE)
                .basePath('venue/')
    }

    static Response createVenue(String name, String description) {
        baseRequest()
                .log().all()
                .param('name', name)
                .param('description', description)
                .post('create')
                .andReturn()
    }

    static Response getVenues() {
        baseRequest()
                .log().all()
                .get()
                .andReturn()
    }

    static Response deleteVenue(String venueId) {
        baseRequest()
                .log().all()
                .param('venueId', venueId)
                .post('delete')
                .andReturn()
    }
}
