package Model;

public class Create_Token {
    public static String getCreateTokenBody(String username , String password){
        return "{\n" +
                "    \"username\" : \""+username+"\",\n" +
                "    \"password\" : \""+password+"\"\n" +
                "}";
    }
}
