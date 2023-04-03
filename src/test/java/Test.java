import org.python.util.PythonInterpreter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        // 定义CSV文件路径
        String csvFilePath = "E:\\file\\code\\bishe\\DeepLineDP\\output\\loss\\DeepLineDP\\activemq-loss_record.csv";

        // 创建CsvMapper对象
        CsvMapper csvMapper = new CsvMapper();

        // 定义CSV文件格式
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("epoch")
                .addColumn("train_loss")
                .addColumn("valid_loss")
                .build()
                .withHeader();

        // 读取CSV文件，返回List
        List<Object> list = csvMapper.readerFor(Object.class)
                .with(csvSchema)
                .readValues(new File(csvFilePath))
                .readAll();

        // 创建ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将List转换为JSON格式字符串
        String jsonString = objectMapper.writeValueAsString(list);

        // 输出JSON格式字符串
        System.out.println(jsonString);
    }
}
