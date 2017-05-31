package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kj Nam
 * @since 2017-05-30
 */
public class Calculator {
    public int calcSum(String filePath) throws IOException {
        LineCallback<Integer> sumCallback = (line, value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public int calcMultiply(String filePath) throws IOException {
        LineCallback<Integer> multiplyCallback = (line, value) -> value * Integer.valueOf(line);
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }

    public String concatenate(String filePath) throws IOException {
        LineCallback<String> concatenateCallback = (line, value) -> value + line;
        return lineReadTemplate(filePath, concatenateCallback, "");
    }

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) {
        int ret = 0;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filePath))) {
            ret = callback.doSomethingWithReader(br);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public <T>T lineReadTemplate(String filePath, LineCallback<T> callback, T intVal) throws IOException {
        T res = intVal;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
