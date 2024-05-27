package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.ExchangeRate;
import utils.C3p0Util;

public class ExchangeRateDao {
	
	public void InsertExchangeRate(List<ExchangeRate> erList) {
		String sql = "IF NOT EXISTS (SELECT * FROM exchangeRate WHERE (name = ? AND date = ?)) BEGIN INSERT INTO exchangeRate VALUES (?,?,?,?,?,?)END";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(ExchangeRate er : erList) {
				preparedStatement.setString(1, er.getName());
				if(er.getDate()!=null) {
					preparedStatement.setDate(2, er.getDate());
				}else {
					preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));				
				}
				preparedStatement.setString(3, er.getName());
				if(er.getDate()!=null) {
					preparedStatement.setDate(4, er.getDate());
				}else {
					preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));				
				}
				preparedStatement.setFloat(5, er.getBuyCash());
				preparedStatement.setFloat(6, er.getBuySpot());
				preparedStatement.setFloat(7, er.getSellCash());
				preparedStatement.setFloat(8, er.getSellSpot());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.clearBatch();
			System.out.println("新增成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void InsertExchangeRate(List<ExchangeRate> erList, String date) {
		String sql = "IF NOT EXISTS (SELECT * FROM exchangeRate WHERE name = ? AND date = ?) BEGIN INSERT INTO exchangeRate VALUES (?,?,?,?,?,?)END";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(ExchangeRate er : erList) {
				preparedStatement.setString(1, er.getName());
				preparedStatement.setDate(2, java.sql.Date.valueOf(date));
				preparedStatement.setString(3, er.getName());
				preparedStatement.setDate(4, java.sql.Date.valueOf(date));
				preparedStatement.setFloat(5, er.getBuyCash());
				preparedStatement.setFloat(6, er.getBuySpot());
				preparedStatement.setFloat(7, er.getSellCash());
				preparedStatement.setFloat(8, er.getSellSpot());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.clearBatch();
			System.out.println("新增成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void deleteAllData() {
		String sql = "TRUNCATE TABLE exchangeRate";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println("刪除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void deleteExchangeRateByName(String name) {
		String sql = "DELETE FROM exchangeRate WHERE name = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.execute();
			System.out.println("刪除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void deleteExchangeRateByDate(String date) {
		String sql = "DELETE FROM exchangeRate WHERE date = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.execute();
			System.out.println("刪除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void deleteExchangeRateByNameAndDate(String name, String date) {
		String sql = "DELETE FROM exchangeRate WHERE date = ? AND name = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, name);
			preparedStatement.execute();
			System.out.println("刪除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void updateBuyCash(String name, Double buyCash, String date) {
		String sql = "UPDATE exchangeRate SET buy_cash = ? WHERE name = ? AND date = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(1, buyCash);
			preparedStatement.setString(3, date);
			preparedStatement.execute();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void updateBuySpot(String name, Double buySpot, String date) {
		String sql = "UPDATE exchangeRate SET buy_spot = ? WHERE name = ? AND date = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(1, buySpot);
			preparedStatement.setString(3, date);
			preparedStatement.execute();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void updateSellCash(String name, Double sellCash, String date) {
		String sql = "UPDATE exchangeRate SET sell_cash = ? WHERE name = ? AND date = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(1, sellCash);
			preparedStatement.setString(3, date);
			preparedStatement.execute();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public void updateSellSpot(String name, Double sellSpot, String date) {
		String sql = "UPDATE exchangeRate SET sell_spot = ? WHERE name = ? AND date = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			preparedStatement.setDouble(1, sellSpot);
			preparedStatement.setString(3, date);
			preparedStatement.execute();
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement);
		}
	}
	
	public List<ExchangeRate> selectAll() {
		String sql = "SELECT * FROM exchangeRate ORDER BY name,date";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			List<ExchangeRate> list = new ArrayList<ExchangeRate>();
			while (resultSet.next()) {
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(resultSet.getString(1));
				exchangeRate.setDate(resultSet.getDate(2));
				exchangeRate.setBuyCash(resultSet.getFloat(3));
				exchangeRate.setBuySpot(resultSet.getFloat(4));
				exchangeRate.setSellCash(resultSet.getFloat(5));
				exchangeRate.setSellSpot(resultSet.getFloat(6));
				list.add(exchangeRate);
//				System.out.printf("%s\t%s\tBuySpot:%s\tSellSpot:%s\t\n",resultSet.getString(1),resultSet.getDate(2),resultSet.getFloat(4),resultSet.getFloat(6));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	public List<ExchangeRate> selectByName(String name) {
		String sql = "SELECT * FROM exchangeRate WHERE name = ? ORDER BY name,date";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			List<ExchangeRate> list = new ArrayList<ExchangeRate>();
			while (resultSet.next()) {
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(resultSet.getString(1));
				exchangeRate.setDate(resultSet.getDate(2));
				exchangeRate.setBuyCash(resultSet.getFloat(3));
				exchangeRate.setBuySpot(resultSet.getFloat(4));
				exchangeRate.setSellCash(resultSet.getFloat(5));
				exchangeRate.setSellSpot(resultSet.getFloat(6));
				list.add(exchangeRate);
//				System.out.printf("%s\t%s\tBuySpot:%s\tSellSpot:%s\t\n",resultSet.getString(1),resultSet.getDate(2),resultSet.getFloat(4),resultSet.getFloat(6));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	public List<ExchangeRate> selectByDate(String date) {
		String sql = "SELECT * FROM exchangeRate WHERE date = ? ORDER BY name,date";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			resultSet = preparedStatement.executeQuery();
			List<ExchangeRate> list = new ArrayList<ExchangeRate>();
			while (resultSet.next()) {
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(resultSet.getString(1));
				exchangeRate.setDate(resultSet.getDate(2));
				exchangeRate.setBuyCash(resultSet.getFloat(3));
				exchangeRate.setBuySpot(resultSet.getFloat(4));
				exchangeRate.setSellCash(resultSet.getFloat(5));
				exchangeRate.setSellSpot(resultSet.getFloat(6));
				list.add(exchangeRate);
//				System.out.printf("%s\t%s\tBuySpot:%s\tSellSpot:%s\t\n",resultSet.getString(1),resultSet.getDate(2),resultSet.getFloat(4),resultSet.getFloat(6));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement, resultSet);
		}
		return null;
	}
	
	public List<ExchangeRate> selectByNameAndDate(String name, String update) {
		String sql = "SELECT * FROM exchangeRate WHERE name = ? AND date = ? ORDER BY name,date";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = C3p0Util.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, update);
			resultSet = preparedStatement.executeQuery();
			List<ExchangeRate> list = new ArrayList<ExchangeRate>();
			while (resultSet.next()) {
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setName(resultSet.getString(1));
				exchangeRate.setDate(resultSet.getDate(2));
				exchangeRate.setBuyCash(resultSet.getFloat(3));
				exchangeRate.setBuySpot(resultSet.getFloat(4));
				exchangeRate.setSellCash(resultSet.getFloat(5));
				exchangeRate.setSellSpot(resultSet.getFloat(6));
				list.add(exchangeRate);
//				System.out.printf("%s\t%s\tBuySpot:%s\tSellSpot:%s\t\n",resultSet.getString(1),resultSet.getDate(2),resultSet.getFloat(4),resultSet.getFloat(6));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3p0Util.release(connection, preparedStatement, resultSet);
		}
		return null;
	}
	

}
