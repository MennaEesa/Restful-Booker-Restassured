package util;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Utilities {
    private static final List<String> MALE_NAMES = new ArrayList<>();
    private static final List<String> FEMALE_NAMES = new ArrayList<>();
    private static final Random RANDOM = new Random();

    // Populate your lists with desired names
    static {
        MALE_NAMES.add("John");
        MALE_NAMES.add("David");
        MALE_NAMES.add("Michael");
        // ...
        FEMALE_NAMES.add("Mary");
        FEMALE_NAMES.add("Emma");
        FEMALE_NAMES.add("Olivia");
        // ...
    }

    public static String getRandomFirstName(boolean preferMale) {
        List<String> names = preferMale ? MALE_NAMES : FEMALE_NAMES;
        return names.get(RANDOM.nextInt(names.size()));
    }


    public static String getSingleJsonData(String jsonFilePath,String jsonField) throws IOException, ParseException, FileNotFoundException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }

    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/testdata.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }
}
