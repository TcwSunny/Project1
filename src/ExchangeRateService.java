import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.ExchangeRate;
import utils.GetData;


public class ExchangeRateService {
	public List<ExchangeRate> getCsvERData(String datapath) {
		List<String> dataList = GetData.getCsvData(datapath);
		List<ExchangeRate> erList = new ArrayList<ExchangeRate>();
		for(int i=0;i<dataList.size();i++) {
			String[] split = dataList.get(i).split(",");
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setName(split[0]);
			exchangeRate.setBuyCash(Float.parseFloat(split[2]));
			exchangeRate.setBuySpot(Float.parseFloat(split[3]));
			exchangeRate.setSellCash(Float.parseFloat(split[12]));
			exchangeRate.setSellSpot(Float.parseFloat(split[13]));
			erList.add(exchangeRate);
		}
		return erList;
	}
	
	public List<ExchangeRate> getJsonERData(String dataPath) {
		String json = GetData.getJsonData(dataPath);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ExchangeRate>>(){}.getType();
        List<ExchangeRate> exchangeRateList = gson.fromJson(json, listType);
        return exchangeRateList;
	}
	
	public List<ExchangeRate> getNowERData() {
		String[] dataList = GetData.getNowData();
		List<ExchangeRate> erList = new ArrayList<ExchangeRate>();
		for(int i=1;i<dataList.length;i++) {
			String[] split = dataList[i].split(",");
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setName(split[0]);
			exchangeRate.setBuyCash(Float.parseFloat(split[2]));
			exchangeRate.setBuySpot(Float.parseFloat(split[3]));
			exchangeRate.setSellCash(Float.parseFloat(split[12]));
			exchangeRate.setSellSpot(Float.parseFloat(split[13]));
			exchangeRate.setDate(Date.valueOf(LocalDate.now()));
			erList.add(exchangeRate);
		}
		return erList;
	}
	
	public List<ExchangeRate> getPastERData(String name, String dayTime) {
		String[] dataList = GetData.getPastData(name, dayTime);
		List<ExchangeRate> erList = new ArrayList<ExchangeRate>();
		if (name.equals(" ")) {
			for(int i=1;i<dataList.length;i++) {
				String[] split = dataList[i].split(",");
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(split[0]);
				exchangeRate.setBuyCash(Float.parseFloat(split[2]));
				exchangeRate.setBuySpot(Float.parseFloat(split[3]));
				exchangeRate.setSellCash(Float.parseFloat(split[12]));
				exchangeRate.setSellSpot(Float.parseFloat(split[13]));
				exchangeRate.setDate(Date.valueOf(LocalDate.now()));
				erList.add(exchangeRate);
			}
		}else {
			for(int i=1;i<dataList.length;i++) {
				String[] split = dataList[i].split(",");
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(split[1]);
				exchangeRate.setBuyCash(Float.parseFloat(split[3]));
				exchangeRate.setBuySpot(Float.parseFloat(split[4]));
				exchangeRate.setSellCash(Float.parseFloat(split[13]));
				exchangeRate.setSellSpot(Float.parseFloat(split[14]));
				StringBuffer stringBuffer = new StringBuffer(split[0]);
				stringBuffer.insert(6, "-");
				stringBuffer.insert(4, "-");
				String date = stringBuffer.toString();
				exchangeRate.setDate(Date.valueOf(date));
				erList.add(exchangeRate);
			}
		}
		return erList;
	}
    
	public void outputCSV(List<ExchangeRate> list,String fileName) {
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(new File(".\\resource\\"+fileName+".csv"));
			outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.write("Name,Date,Buy Cash,Buy Spot,Sell Cash,Sell Spot");
			for (ExchangeRate er : list) {
				bufferedWriter.newLine();
				bufferedWriter.write(er.getName()+","+er.getDate()+","+er.getBuyCash()+","+er.getBuySpot()+","+er.getSellCash()+","+er.getSellSpot());
			}
			bufferedWriter.close();
			System.out.println("輸出成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileOutputStream.close();
				outputStreamWriter.close();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void outputJson(List<ExchangeRate> list, String fileName) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(".\\resource\\"+fileName+".json"));
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){
			gson.toJson(list,bufferedWriter);
			System.out.println("輸出成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
}
