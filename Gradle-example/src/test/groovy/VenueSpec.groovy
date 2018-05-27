import com.jayway.restassured.path.json.JsonPath
import com.jayway.restassured.response.Response
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import static VenueRequests.*

@Stepwise
class VenueSpec extends Specification {
    @Shared
    String venueId
    @Shared
    String venueName
    @Shared
    String venueDescription

    def 'Should be possible to get list with venues - SmokeTest'(){
        when:
        Response response = getVenues()

        then:
        response.getStatusCode() == 200
    }

    def 'Should create new venue - Regression'() {
        given:
        venueName = 'venue Name'
        venueDescription = 'description'

        when:
        Response response = createVenue(venueName, venueDescription)

        then:
        response.getStatusCode() == 201

        when:
        JsonPath body = response.getBody().jsonPath()
        venueId = body.get('id')

        then:
        body.get('name') == venueName
        body.get('description') == venueDescription
    }

    def 'Created venue should be on list with all venues - Regression'() {
        when:
        Response response = getVenues()

        then:
        response.getStatusCode() == 200
        List listWithVenues = response.getBody().jsonPath().getList('venues')
        listWithVenues.size() >= 1
        listWithVenues.findAll {
            it.get('id') == venueId &&
                    it.get('name') == venueName &&
                    it.get('description') == venueDescription
        }.size() == 1
    }

    def 'Should delete created venue - Regression'() {
        when:
        Response response = deleteVenue(venueId)

        then:
        response.getStatusCode() == 200

        when:
        Response responseGetVenues = getVenues()

        then:
        responseGetVenues.getStatusCode() == 200
        responseGetVenues.body().jsonPath().getList('venues').findAll{
            it.get('id') == venueId &&
                    it.get('name') == venueName &&
                    it.get('description') == venueDescription
        }.size() == 0
    }

}