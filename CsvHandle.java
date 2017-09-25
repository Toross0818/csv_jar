package main;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by ss on 2017/9/9.
 */
public class CsvHandle {
    public void writeToCsv(ArrayList<String[]> res, String filePath) {
        try {
            Logger logger = Logger.getLogger("start...");
            logger.info("write csv file...");
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("GBK"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            String[] headers = {};

            csvWriter.writeRecord(headers);
            for (String[] content : res) {
                csvWriter.writeRecord(content);
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> readeCsv(String csvFilePath) throws IOException {
        Logger logger = Logger.getLogger("start...");
        logger.info("read csv file...");
        ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
        CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK"));

        reader.readHeaders();
        while (reader.readRecord()) { //逐行读入除表头的数据
            csvList.add(reader.getValues());
        }
        reader.close();
		
        for(int row=0;row<csvList.size();row++){
            String  cell = csvList.get(row)[1]; //取得第row行第0列的数据
            System.out.println(cell);

        }
        return csvList;
    }
}
