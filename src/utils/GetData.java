package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetData {
	public static List<String> getCsvData(String dataPath) {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		List<String> list = new ArrayList<String>();
		try {
			fileInputStream = new FileInputStream(new File(dataPath));
			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			while(bufferedReader.ready()) {
				list.add(bufferedReader.readLine());
			}
			list.remove(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static String getJsonData(String dataPath) {
		StringBuilder jsonBuilder = new StringBuilder();
		try (FileInputStream fileInputStream = new FileInputStream(new File(dataPath));
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				jsonBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        return jsonBuilder.toString();
	}
	
	public static String[] getNowData() {
		String[] dataList = null;
		try {
			Document document = Jsoup.connect("https://rate.bot.com.tw/xrt/flcsv/0/day").get();
			String body = document.body().text();
			dataList = body.split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	public static String[] getPastData(String name, String dayTime) {
		String[] dataList = null;
		try {
			Document document = Jsoup.connect("https://rate.bot.com.tw/xrt/flcsv/0/"+dayTime+"/"+name).get();
			String body = document.body().text();
			dataList = body.split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
}
