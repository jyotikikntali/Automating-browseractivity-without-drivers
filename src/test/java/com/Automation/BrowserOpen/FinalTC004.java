  package com.Automation.BrowserOpen;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FinalTC004 {
	static String browser;
	static String SerachKeyword;
	static String SearchUrl;
	static String RefUrl1;
	static int value;
	static int count;
	static int min;
	static int max;
	static String ChromePath="src//test//resources//chromedriver.exe";
	static List<String> urls = new ArrayList<String>();  
	public static void main(String[] args) throws EncryptedDocumentException, IOException, URISyntaxException, InterruptedException, AWTException {
		getDataFromExcelSheet("Sheet1");} 
		
	
	private static void getDataFromExcelSheet(String SheetName) throws IOException, URISyntaxException, AWTException, InterruptedException {
		FileInputStream fis=new FileInputStream("src\\test\\resources\\Samples.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet(SheetName);
	Row row;
		for(int i=1;i<=sh.getLastRowNum();i++) {
		System.out.println(sh.getLastRowNum());
			row = sh.getRow(i);
		 int CellCount=row.getPhysicalNumberOfCells();
				
				 System.out.println(CellCount);
		
		 SerachKeyword=row.getCell(1).getStringCellValue();
		 browser = row.getCell(0).getStringCellValue();
		 
		 System.out.println(browser);
	     SearchUrl=row.getCell(2).getStringCellValue();
	     System.out.println(SearchUrl);
	   //String check=  row.getCell(6).getStringCellValue();
	  // System.out.println(check);
	     min=(int) row.getCell(6).getNumericCellValue();
	     max=(int) row.getCell(7).getNumericCellValue();
	     
	     System.out.println(SerachKeyword);
	     for(int j=3;j<=CellCount-3;j++) {
	    	 urls.add(row.getCell(j).getStringCellValue());
	         System.out.println("Fetching refurls");
	    	 
	     }
	     value= Range(min,max);}
			selectBrowserToSearch(browser);}


private static void selectBrowserToSearch(String browser) throws IOException, URISyntaxException, AWTException, InterruptedException {

	//System.out.println(RefUrl1);
	if(browser.equalsIgnoreCase("chrome")) {
		String scriptPath="src\\test\\resources\\Chrome.ps1";//this will be the path of bash file
		String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;//this line is for running
		//String file="src\\test\\resources\\Browser.ps1";
		Runtime.getRuntime().exec(command);
		
	}
	else if(browser.equalsIgnoreCase(" Microsoft edge")){
		String scriptPath="src\\test\\resources\\edge.ps1";
		String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;
		//String file="src\\test\\resources\\Browser.ps1";
		Runtime.getRuntime().exec(command);
		
		
	}
	else if(browser.equalsIgnoreCase("Firefox")){
		
	String scriptPath="src\\test\\resources\\Firefox.ps1";
		String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;
		//String file="src\\test\\resources\\Browser.ps1";
		Runtime.getRuntime().exec(command);
		
}
	else {
		  URI uri = new URI("https://www.google.com/");
	        Desktop.getDesktop().browse(uri);
	        
	}
	passValueToSearch();
		// TODO Auto-generated method stub
		
	}
private static void passValueToSearch() throws IOException, AWTException, InterruptedException {
	Robot robot = new Robot();
    for (char c : SerachKeyword.toCharArray()) {
    	
    	//vtechys
    	robot.delay(1000);
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
       robot.delay(2000);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }
 // Press Enter to start the search
 robot.keyPress(KeyEvent.VK_ENTER);
 robot.keyRelease(KeyEvent.VK_ENTER);


    	

    // Step 3: Wait for Google search results to load
    Thread.sleep(10000); // Adjust the delay as needed (5 seconds in this example)
Document searchPageDoc=Jsoup.connect("https://www.google.com/search?q=" + SerachKeyword).get();
    // Step 4: Extract and print search results using Jsoup
   
   // Document searchPageDoc = Jsoup.connect("https://www.google.com/search?q=" + searchKeyword).get();
   Elements searchResults = searchPageDoc.select("div.g"); // Google search result elements


for (Element result : searchResults) {
        Element titleElement = result.selectFirst("h3");
        Element linkElement = result.selectFirst("a");

        if (titleElement != null && linkElement != null) {
            String title = titleElement.text();
            String link = linkElement.attr("href");
            robot.delay(1000);
           if(link.equalsIgnoreCase(SearchUrl)) {
        	   System.out.println(link);
        	   OpenNewLink(link);
        	   }}}}
	private static void OpenNewLink(String link) throws InterruptedException, AWTException {
		Robot r1=new Robot();
	browserSelectiontoOpenUrl(browser,link);
	System.out.println(browser);
	/*Desktop.isDesktopSupported();

      Desktop.getDesktop().browse(new URI(link));}*/
        Thread.sleep(5000);
       
        	//int count=0;
        	
        	//Robot r1=new Robot();
        	Thread.sleep(3000);
        	long startTime = System.currentTimeMillis();
            long endTime =  (startTime + (value * 1000));
        	 

        	    while (System.currentTimeMillis() < endTime) {
        	        r1.keyPress(KeyEvent.VK_DOWN);
        	        r1.keyRelease(KeyEvent.VK_DOWN);
        	        r1.delay(3000);
        	        r1.keyPress(KeyEvent.VK_DOWN);
        	        r1.keyRelease(KeyEvent.VK_DOWN);}}
        	       
	// TODO Auto-generated method stub
	

	private static void browserSelectiontoOpenUrl(String browser, String link) throws InterruptedException {
		try {
			  String[] command = {"cmd", "/c", "start", browser, link};

	          // Execute the command
	          Process process = new ProcessBuilder(command).start();

	          // Optionally, you can wait for the process to finish
	          int exitCode = process.waitFor();
	          System.out.println(browser+" browser opened with exit code: " + exitCode);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		// TODO Auto-generated method stub
		
	}
	// TODO Auto-generated method stub
	

private static int Range(int min, int max) {
	int num=(int) ((Math.random()*(max-min))+min);
	
	System.out.println(num);
	return num;
}}
