package Model;

public class CreateBooking {
    public static String getCreateBookingBody(String firstname,String lastname){
        return "{\n" +
                "    \"firstname\" : \""+firstname+"\",\n" +
                "    \"lastname\" : \""+lastname+"\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
    }
}
