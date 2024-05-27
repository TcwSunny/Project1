import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.text.DateFormatter;

import dao.ExchangeRateDao;
import model.ExchangeRate;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class JFrameProject1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private JTextField insertFileField;
	private JComboBox<String> insertFileType;
	private JComboBox<String> insertNameField;
	private JFormattedTextField insertDateField;
	private JFormattedTextField insertMonthField;
	
	private JComboBox<String> deleteNameField;
	private JComboBox<String> deleteDateField;
	
	private JTextField updateNumberField;
	private JComboBox<String> updateColumnField;
	private JComboBox<String> updateNameField;
	private JComboBox<String> updateDateField;
	
	private JComboBox<String> selectNameField;
	private JComboBox<String> selectDateField;
	
	private ExchangeRateDao exchangeRateDao = new ExchangeRateDao();
	private ExchangeRateService exchangeRateService = new ExchangeRateService();
	private JTextField outputField;
	private List<ExchangeRate> selectedData;
	private List<ExchangeRate> selectAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameProject1 frame = new JFrameProject1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameProject1() {
		selectAll = exchangeRateDao.selectAll();
		selectedData = exchangeRateDao.selectAll();
		
		setTitle("Project1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 236, 393);
		contentPane.add(tabbedPane);
		
		JPanel insert = new JPanel();
		tabbedPane.addTab("新增", null, insert, null);
		
		JLabel insertDateText = new JLabel("本機檔案/雲端資料-日期");
		insertDateText.setBounds(10, 96, 211, 28);
		insertDateText.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertDateText.setHorizontalAlignment(SwingConstants.CENTER);
		insert.add(insertDateText);
		
		JLabel insertNameText = new JLabel("雲端資料-幣別");
		insertNameText.setBounds(66, 172, 100, 28);
		insertNameText.setHorizontalAlignment(SwingConstants.CENTER);
		insertNameText.setFont(new Font("新細明體", Font.PLAIN, 15));
		insert.add(insertNameText);
		
		JLabel insertFileText = new JLabel("本機檔案-名稱");
		insertFileText.setBounds(66, 20, 100, 28);
		insertFileText.setHorizontalAlignment(SwingConstants.CENTER);
		insertFileText.setFont(new Font("新細明體", Font.PLAIN, 15));
		insert.add(insertFileText);
		
		JLabel insertMonthText = new JLabel("雲端資料-月份");
		insertMonthText.setHorizontalAlignment(SwingConstants.CENTER);
		insertMonthText.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertMonthText.setBounds(66, 248, 100, 28);
		insert.add(insertMonthText);
		
		insertFileField = new JTextField();
		insertFileField.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertFileField.setBounds(10, 48, 131, 28);
		insertFileField.setColumns(10);
		insert.add(insertFileField);
		
		insertFileType = new JComboBox<String>();
		insertFileType.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertFileType.setModel(new DefaultComboBoxModel<String>(new String[] {".csv", ".json"}));
		insertFileType.setBounds(151, 48, 70, 28);
		insert.add(insertFileType);
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        insertDateField = new JFormattedTextField(dateFormatter);
        insertDateField.setFont(new Font("新細明體", Font.PLAIN, 15));
        insertDateField.setText(LocalDate.now().toString());
		insertDateField.setBounds(10, 124, 211, 28);
		insert.add(insertDateField);
		
		insertNameField = new JComboBox<String>();
		insertNameField.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertNameField.setModel(new DefaultComboBoxModel<String>(new String[] {" ", "AUD", "CAD", "CHF", "CNY", "EUR", "GBP", "HKD", "IDR", "JPY", "KRW", "MYR", "NZD", "PHP", "SEK", "SGD", "THB", "USD", "VND", "ZAR"}));
		insertNameField.setBounds(10, 200, 211, 28);
		insert.add(insertNameField);
		
        DateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        DateFormatter monthFormatter = new DateFormatter(monthFormat);
        insertMonthField = new JFormattedTextField(monthFormatter);
		insertMonthField.setFont(new Font("新細明體", Font.PLAIN, 15));
		insertMonthField.setText("2024-04");
		insertMonthField.setBounds(10, 276, 211, 28);
		insert.add(insertMonthField);
		
		JPanel delete = new JPanel();
		tabbedPane.addTab("刪除", null, delete, null);
		delete.setLayout(null);
		
		JLabel deleteNameText = new JLabel("幣別");
		deleteNameText.setHorizontalAlignment(SwingConstants.CENTER);
		deleteNameText.setFont(new Font("新細明體", Font.PLAIN, 15));
		deleteNameText.setBounds(66, 66, 100, 28);
		delete.add(deleteNameText);
		
		JLabel deleteDateText = new JLabel("日期");
		deleteDateText.setHorizontalAlignment(SwingConstants.CENTER);
		deleteDateText.setFont(new Font("新細明體", Font.PLAIN, 15));
		deleteDateText.setBounds(66, 168, 100, 28);
		delete.add(deleteDateText);
		
		deleteNameField = new JComboBox<String>();
		deleteNameField.setFont(new Font("新細明體", Font.PLAIN, 15));
		deleteNameField.setModel(getNameModel());
		deleteNameField.setBounds(10, 104, 211, 28);
		delete.add(deleteNameField);
		
		deleteDateField = new JComboBox<String>();
		deleteDateField.setFont(new Font("新細明體", Font.PLAIN, 15));
		deleteDateField.setModel(getDateModel("ALL"));
		deleteDateField.setBounds(10, 206, 211, 28);
		delete.add(deleteDateField);
		
		deleteNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) deleteNameField.getSelectedItem();
				if(name.equals(" ")) {
					deleteDateField.setModel(getDateModel("ALL"));					
				}else{
					deleteDateField.setModel(getDateModel(name));
				}
			}
		});
		
		JPanel update = new JPanel();
		tabbedPane.addTab("修改", null, update, null);
		update.setLayout(null);
		
		JLabel updateNameText = new JLabel("幣別");
		updateNameText.setHorizontalAlignment(SwingConstants.CENTER);
		updateNameText.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateNameText.setBounds(66, 20, 100, 28);
		update.add(updateNameText);
		
		JLabel dateText = new JLabel("日期");
		dateText.setHorizontalAlignment(SwingConstants.CENTER);
		dateText.setFont(new Font("新細明體", Font.PLAIN, 15));
		dateText.setBounds(66, 96, 100, 28);
		update.add(dateText);
		
		JLabel updateColumnText = new JLabel("欄位");
		updateColumnText.setHorizontalAlignment(SwingConstants.CENTER);
		updateColumnText.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateColumnText.setBounds(66, 172, 100, 28);
		update.add(updateColumnText);
		
		JLabel updateNumberText = new JLabel("數值");
		updateNumberText.setHorizontalAlignment(SwingConstants.CENTER);
		updateNumberText.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateNumberText.setBounds(66, 248, 100, 28);
		update.add(updateNumberText);
		
		updateColumnField = new JComboBox<String>();
		updateColumnField.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateColumnField.setModel(new DefaultComboBoxModel<String>(new String[] {"Buy Cash", "Buy Spot", "Sell Cash", "Sell Spot"}));
		updateColumnField.setBounds(10, 200, 211, 28);
		update.add(updateColumnField);
		
		updateNameField = new JComboBox<String>();
		updateNameField.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateNameField.setBounds(10, 48, 211, 28);
		updateNameField.setModel(getNameModel());
		update.add(updateNameField);
		
		updateDateField = new JComboBox<String>();
		updateDateField.setModel(new DefaultComboBoxModel<String>(new String[] {" "}));
		updateDateField.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateDateField.setBounds(10, 124, 211, 28);
		update.add(updateDateField);
		
		updateNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) updateNameField.getSelectedItem();
				updateDateField.setModel(getDateModel(name));
			}
		});
		
		updateNumberField = new JTextField();
		updateNumberField.setFont(new Font("新細明體", Font.PLAIN, 15));
		updateNumberField.setBounds(10, 276, 211, 28);
		update.add(updateNumberField);
		updateNumberField.setColumns(10);
		
		JPanel select = new JPanel();
		tabbedPane.addTab("查詢", null, select, null);
		select.setLayout(null);
		
		JLabel selectNameText = new JLabel("幣別");
		selectNameText.setHorizontalAlignment(SwingConstants.CENTER);
		selectNameText.setFont(new Font("新細明體", Font.PLAIN, 15));
		selectNameText.setBounds(66, 66, 100, 28);
		select.add(selectNameText);
		
		JLabel selectDateText = new JLabel("日期");
		selectDateText.setHorizontalAlignment(SwingConstants.CENTER);
		selectDateText.setFont(new Font("新細明體", Font.PLAIN, 15));
		selectDateText.setBounds(66, 168, 100, 28);
		select.add(selectDateText);
		
		selectNameField = new JComboBox<String>();
		selectNameField.setFont(new Font("新細明體", Font.PLAIN, 15));
		selectNameField.setModel(getNameModel());
		selectNameField.setBounds(10, 104, 211, 28);
		select.add(selectNameField);

		selectDateField = new JComboBox<String>();
		selectDateField.setFont(new Font("新細明體", Font.PLAIN, 15));
		selectDateField.setModel(getDateModel("ALL"));
		selectDateField.setBounds(10, 206, 211, 28);
		select.add(selectDateField);
		selectNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) selectNameField.getSelectedItem();
				if(name.equals(" ")) {
					selectDateField.setModel(getDateModel("ALL"));					
				}else{
					selectDateField.setModel(getDateModel(name));
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 32, 520, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(getJTableModel(selectAll));
		scrollPane.setViewportView(table);
		
		outputField = new JTextField();
		outputField.setFont(new Font("新細明體", Font.PLAIN, 15));
		outputField.setBounds(256, 367, 300, 36);
		contentPane.add(outputField);
		outputField.setColumns(10);
		
		JButton insertButton = new JButton("新增");
		insertButton.setBounds(66, 318, 100, 36);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = insertFileField.getText();
				String date = insertDateField.getText();
				String name = (String)insertNameField.getSelectedItem();
				String month = insertMonthField.getText();
				String type = (String)insertFileType.getSelectedItem();
				
				if (file.trim().isEmpty()) {
					if (name.trim().isEmpty() && date.trim().equals(LocalDate.now().toString())) {
				        List<ExchangeRate> erData = exchangeRateService.getNowERData();
			            exchangeRateDao.InsertExchangeRate(erData);
				        selectedData = exchangeRateDao.selectAll();
				        table.setModel(getJTableModel(selectedData));
				        JOptionPane.showConfirmDialog(null, "新增成功", "訊息", JOptionPane.DEFAULT_OPTION);
				    }else if(name.trim().isEmpty()) {
				        List<ExchangeRate> erData = exchangeRateService.getPastERData(name, date);
			            exchangeRateDao.InsertExchangeRate(erData,date);
				        selectedData = exchangeRateDao.selectAll();
				        table.setModel(getJTableModel(selectedData));
				        JOptionPane.showConfirmDialog(null, "新增成功", "訊息", JOptionPane.DEFAULT_OPTION);
				    }
				    else if (!month.trim().isEmpty() && !name.trim().isEmpty()){
						List<ExchangeRate> erData = exchangeRateService.getPastERData(name,month);
						exchangeRateDao.InsertExchangeRate(erData);
				        selectedData = exchangeRateDao.selectAll();
				        table.setModel(getJTableModel(selectedData));
				        
				        JOptionPane.showConfirmDialog(null, "新增成功", "訊息", JOptionPane.DEFAULT_OPTION);
				    }else {
				    	JOptionPane.showConfirmDialog(null, "錯誤", "錯誤", JOptionPane.DEFAULT_OPTION);
				    }
				}else {
					if(type.equals(".csv")) {
						if(!date.trim().isEmpty()) {
							List<ExchangeRate> erData = exchangeRateService.getCsvERData(".\\resource\\"+file+".csv");
							exchangeRateDao.InsertExchangeRate(erData,date);
						}else {
							JOptionPane.showConfirmDialog(null, "請輸入日期", "錯誤", JOptionPane.DEFAULT_OPTION);							
						}
					}else {
						List<ExchangeRate> erData = exchangeRateService.getJsonERData(".\\resource\\"+file+".json");
						exchangeRateDao.InsertExchangeRate(erData);
					}
			        selectedData = exchangeRateDao.selectAll();
			        table.setModel(getJTableModel(selectedData));
			        JOptionPane.showConfirmDialog(null, "新增成功", "訊息", JOptionPane.DEFAULT_OPTION);
				}
				insertFileField.setText("");
				updateComboBox();
			}
		});
		insert.setLayout(null);
		insert.add(insertButton);
		
		JButton deleteButton = new JButton("刪除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = (String)deleteDateField.getSelectedItem();
				String name = (String)deleteNameField.getSelectedItem();
				if(date.trim().isEmpty() && name.trim().isEmpty()) {
					exchangeRateDao.deleteAllData();
				}else if(!date.trim().isEmpty() && name.trim().isEmpty()){
					exchangeRateDao.deleteExchangeRateByDate(date);
				}else if(date.trim().isEmpty() && !name.trim().isEmpty()){
					exchangeRateDao.deleteExchangeRateByName(name);
				}else {
					exchangeRateDao.deleteExchangeRateByNameAndDate(name, date);
				}
				selectedData = exchangeRateDao.selectAll();
				table.setModel(getJTableModel(selectedData));
				JOptionPane.showConfirmDialog(null, "刪除成功", "訊息", JOptionPane.DEFAULT_OPTION);
				updateComboBox();
			}
		});
		deleteButton.setBounds(66, 318, 100, 36);
		delete.add(deleteButton);
		
		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = (String) updateDateField.getSelectedItem();
				String name = (String) updateNameField.getSelectedItem();
				String column = (String) updateColumnField.getSelectedItem();
				Double number = null;
				if (!updateNumberField.getText().isEmpty()) {
					number = Double.parseDouble(updateNumberField.getText());					
				}
				
				if(!date.trim().isEmpty() && !name.trim().isEmpty() && !column.trim().isEmpty() && number != null) {
					if(column.equals("Buy Cash")) {
						exchangeRateDao.updateBuyCash(name, number, date);
					}else if (column.equals("Buy Spot")) {
						exchangeRateDao.updateBuySpot(name, number, date);
					}else if (column.equals("Sell Cash")) {
						exchangeRateDao.updateSellCash(name, number, date);
					}else if (column.equals("Sell Spot")) {
						exchangeRateDao.updateSellSpot(name, number, date);
					}
					selectedData = exchangeRateDao.selectAll();
					table.setModel(getJTableModel(selectedData));
					JOptionPane.showConfirmDialog(null, "更新成功", "訊息", JOptionPane.DEFAULT_OPTION);
					updateComboBox();
					updateNumberField.setText("");					
				}else {
					JOptionPane.showConfirmDialog(null, "請輸入完整訊息", "錯誤", JOptionPane.DEFAULT_OPTION);					
				}
			}
		});
		updateButton.setBounds(66, 318, 100, 36);
		update.add(updateButton);
		
		JButton selectButton = new JButton("查詢");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = (String) selectDateField.getSelectedItem();
				String name = (String) selectNameField.getSelectedItem();
				if(date.trim().isEmpty() && name.trim().isEmpty()) {
					selectedData = exchangeRateDao.selectAll();
				}else if (!date.trim().isEmpty() && name.trim().isEmpty()) {
					selectedData = exchangeRateDao.selectByDate(date);
				}else if (date.trim().isEmpty() && !name.trim().isEmpty()) {
					selectedData = exchangeRateDao.selectByName(name);
				}else {
					selectedData = exchangeRateDao.selectByNameAndDate(name, date);
				}
				table.setModel(getJTableModel(selectedData));
			}
		});
		selectButton.setBounds(66, 318, 100, 36);
		select.add(selectButton);
		
		JButton outputCSVButton = new JButton("輸出CSV");
		outputCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = outputField.getText();
				exchangeRateService.outputCSV(selectedData, name);
				JOptionPane.showConfirmDialog(null, "輸出成功", "訊息", JOptionPane.DEFAULT_OPTION);
			}
		});
		outputCSVButton.setBounds(676, 367, 100, 36);
		contentPane.add(outputCSVButton);

		JButton outputJsonButton = new JButton("輸出JSON");
		outputJsonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = outputField.getText();
				exchangeRateService.outputJson(selectedData, name);
				JOptionPane.showConfirmDialog(null, "輸出成功", "訊息", JOptionPane.DEFAULT_OPTION);
			}
		});
		outputJsonButton.setBounds(566, 367, 100, 36);
		contentPane.add(outputJsonButton);
	}
	
	public DefaultComboBoxModel<String> getNameModel() {
		Set<String> nameSet = new TreeSet<>();
		nameSet.add(" "); 
		for (ExchangeRate rate : selectAll) {
			nameSet.add(rate.getName());
		}
		return new DefaultComboBoxModel<String>(nameSet.toArray(new String[0]));
	}
	
	public DefaultComboBoxModel<String> getDateModel(String name) {
		Set<String> dateSet = new TreeSet<>();
		dateSet.add(" "); 
		if(name.equals("ALL")) {
			for (ExchangeRate rate : selectAll) {
				dateSet.add(rate.getDate().toString());
			}
		}else {
			for (ExchangeRate rate : selectAll) {
				if (name.equals(rate.getName())) {
					dateSet.add(rate.getDate().toString());
				}
			}
		}
		return new DefaultComboBoxModel<String>(dateSet.toArray(new String[0]));
	}
	public void updateComboBox() {
		selectAll = exchangeRateDao.selectAll();
		deleteNameField.setModel(getNameModel());
		updateNameField.setModel(getNameModel());
		selectNameField.setModel(getNameModel());

		deleteDateField.setModel(getDateModel("ALL"));
		selectDateField.setModel(getDateModel("ALL"));
	}
	
    public DefaultTableModel getJTableModel(List<ExchangeRate> erList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Date");
        model.addColumn("Buy Cash");
        model.addColumn("Buy Spot");
        model.addColumn("Sell Cash");
        model.addColumn("Sell Spot");
        int rowCount = erList.size();
        for (int i = 0; i < rowCount; i++) {
            ExchangeRate er = erList.get(i);
            Object[] rowData = new Object[6];
            rowData[0] = er.getName();
            rowData[1] = er.getDate();
            rowData[2] = er.getBuyCash();
            rowData[3] = er.getBuySpot();
            rowData[4] = er.getSellCash();
            rowData[5] = er.getSellSpot();
            model.addRow(rowData);
        }
        return model;
    }
}
