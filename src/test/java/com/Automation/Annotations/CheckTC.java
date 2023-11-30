package com.Automation.Annotations;


	

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

	import javax.net.ssl.SSLHandshakeException;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	public class CheckTC {

	    static String browser;
	    static String SerachKeyword;
	    static String SearchUrl;
	    static int value;
	    static int count;
	    static int min;
	    static int max;
	    static String ChromePath = "src//test//resources//chromedriver.exe";
	    static List<String> urls = new ArrayList<>();
	    @BeforeTest
	    @Parameters({"SheetName"})
	   // @BeforeTest
	    public void getData(String SheetName) throws EncryptedDocumentException, IOException {
	    	 getDataFromExcelSheet(SheetName);
	    }

	  /*  @BeforeTest
	    @Parameters({ "browser" })
	    public void setup(String browser) {
	        // Set up your browser or any other setup required before the test
	        CheckTC.browser = browser;
	    }*/

	    @Test
	    public void executeTest() throws Exception {
	    	try {
	       // getDataFromExcelSheet("Sheet1");
	        value = Range(min, max);
	        System.out.println(urls.size());
	        System.out.println("Prac.....");
	        System.out.println(browser);
	        count = (urls.size()) + 1;

	        if (browser.equalsIgnoreCase("chrome")) {
	            String scriptPath = "src\\test\\resources\\Chrome.ps1";
	            String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;
	            Runtime.getRuntime().exec(command);
	        } else if (browser.equalsIgnoreCase("edge")) {
	            String scriptPath = "src\\test\\resources\\edge.ps1";
	            String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;
	            Runtime.getRuntime().exec(command);
	        } else if (browser.equalsIgnoreCase("Firefox")) {
	            String scriptPath = "src\\test\\resources\\Firefox.ps1";
	            String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + scriptPath;
	            Runtime.getRuntime().exec(command);
	        } else {
	            URI uri = new URI("https://www.google.com/");
	            Desktop.getDesktop().browse(uri);
	        }

	        Robot robot = new Robot();
	        Thread.sleep(2000);
	        for (char c : SerachKeyword.toCharArray()) {
	            robot.delay(1000);
	            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	            robot.delay(2000);
	            robot.keyPress(keyCode);
	            robot.keyRelease(keyCode);
	        }
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        Thread.sleep(5000);

	        Document searchPageDoc = Jsoup.connect("https://www.google.com/search?q=" + SerachKeyword).get();

	        Elements searchResults = searchPageDoc.select("div.g");

	        for (Element result : searchResults) {
	            Element titleElement = result.selectFirst("h3");
	            Element linkElement = result.selectFirst("a");

	            if (titleElement != null && linkElement != null) {
	                String title = titleElement.text();
	                String link = linkElement.attr("href");
	                robot.delay(1000);
	                System.out.println(link);
	                System.out.println(SearchUrl);
	                if (link.equalsIgnoreCase(SearchUrl)) {
	                    OpenNewLink(link);
	                    FalseClickInBase(link);
	                    ClosingBrowserPages();
	                    ClearCookiesAndCache();
	                    NetworkReset();
	                    break;
	                } else {
	                    System.out.println("Couldnt find matched link");
	                }
	            }

}}
	        catch(Exception e) {
	        	e.printStackTrace();
	        	System.out.println("error in executeTest method");
	        }}
	    private static  int Range(int max,int min) {
			int num=(int) ((Math.random()*(max-min))+min);
			
			System.out.println(num);
			return num;
		}
	    private static void NetworkReset() throws IOException {
			// TODO Auto-generated method stub
			String FilePath="src\\test\\resources\\NetworkReset.ps1";
			String command = "src\\test\\resources\\powershell.exe -ExecutionPolicy Bypass -File " + FilePath;
			Runtime.getRuntime().exec(command);
			System.out.println("Network Reset is completed");
			
		}
	    private static void FalseClickInRef(String url) throws AWTException, IOException, InterruptedException, URISyntaxException {
			
	    	try{
	    		Robot robot = new Robot();
	    	
			 browserSelectiontoOpenUrl(browser,url);
			try {
				Document PageData=Jsoup.connect(url).get();
			
					//System.out.println("Entered into Try block");
				
				Element Reqdata=PageData.getElementsByAttributeValue("class", "chapternav-label05").first();
				System.out.println(Reqdata);
			
				//Element Reqdata=PageData.selectFirst("//span[@class='chapternav-label']");
				  
				      // Simulate a key press
				     // Robot robot = new Robot();
				      robot.keyPress(KeyEvent.VK_ENTER);
				      robot.keyRelease(KeyEvent.VK_ENTER);
				  
				  }
				 
				  catch(SSLHandshakeException exception) {
					
					 Thread.sleep(2000);
					 long startTime = System.currentTimeMillis();
		                long endTime =  (startTime + (value * 1000));
		            	 

		            	    while (System.currentTimeMillis() < endTime) {
		            	        robot.keyPress(KeyEvent.VK_DOWN);
		            	        robot.keyRelease(KeyEvent.VK_DOWN);
		            	        robot.delay(1000);
		            	        robot.keyPress(KeyEvent.VK_DOWN);
		            	        robot.keyRelease(KeyEvent.VK_DOWN);
					//OpenAndScrollNewUrl(String url);
			// TODO Auto-generated method stub
			
		}}}
	    catch(Exception e) {
	    	e.printStackTrace();
	    	System.out.println("There was some issue inFalseClickInRef Method ");
	    }
	    }
	    private static void  ClearCookiesAndCache() throws AWTException, InterruptedException {
	    	try {
			System.out.println("it will clear the cookies and cache of the browser");
			Thread.sleep(1000);
			Robot r1=new Robot();
	    	r1.keyPress(KeyEvent.VK_CONTROL);
	    	r1.keyPress(KeyEvent.VK_SHIFT);
	    	r1.keyPress(KeyEvent.VK_DELETE);
	    	r1.keyRelease(KeyEvent.VK_CONTROL);
	    	r1.keyRelease(KeyEvent.VK_SHIFT);
	    	r1.keyRelease(KeyEvent.VK_DELETE);
	    	r1.delay(2000);
	    	r1.keyPress(KeyEvent.VK_ENTER);
	    	r1.keyRelease(KeyEvent.VK_ENTER);
	    	count=1;
	    	while(count>=0) {
	    		System.out.println(count);
	    	r1.keyPress(KeyEvent.VK_CONTROL);
			r1.keyPress(KeyEvent.VK_W);
		    r1.keyRelease(KeyEvent.VK_CONTROL);
		    r1.keyRelease(KeyEvent.VK_W);
		    r1.delay(1000);
		    count--;}
	    	System.out.println("cleared Cookies and cache");}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		System.out.println("ClearCookiesAndCache Method got failed");
	    	}
	    		
	    	}
	    	
	    	// TODO Auto-generated method stub  
			
		
		
		private static void ClosingBrowserPages() throws AWTException {
			try {
			System.out.println("All opened tabs will be closed now");
			//Robot r2=new Robot();
			Robot r=new Robot();
			System.out.println(count);
			while(count>0) {
			System.out.println(count);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_W);
		    r.keyRelease(KeyEvent.VK_CONTROL);
		    r.keyRelease(KeyEvent.VK_W);
		    r.delay(1000);
		    count--;
		    
		    
			System.out.println("Closing ");
			
			// TODO Auto-generated method stub
			}}
			catch(Exception e) {
				System.out.println("ClosingBrowserPages method got failed");
				e.printStackTrace();
				
				
			}
		}
	    private static void FalseClickInBase(String link) throws Exception {
			//System.out.println("Entered into False Click Method");
	    	try {
			try {
			 Robot robot = new Robot();
			Document PageData=Jsoup.connect(link).get();
			Element Reqdata=PageData.getElementsByAttributeValue("class", "chapternav-label05").first();
			System.out.println(Reqdata);
		
				//System.out.println("Entered into Try block");
			
				//Element Reqdata=PageData.selectFirst("//span[@class='chapternav-label']");
			
			      
			      robot.keyPress(KeyEvent.VK_ENTER);
			      robot.keyRelease(KeyEvent.VK_ENTER);
			      
			  
			  }
			  catch(SSLHandshakeException exception){
				  System.out.println("Element not found");
				  for(String url:urls) {
					  
						 System.out.println(url);
						  FalseClickInRef(url);}
				  System.out.println("All urls are opened once");
				   }
			  
 }
	    	catch(Exception e) {
	    		System.out.println("FalseClickInBase method failed");
	    		e.printStackTrace();
	    	}}
	public static void getDataFromExcelSheet(String SheetName) throws EncryptedDocumentException, IOException {
		try {
		FileInputStream fis=new FileInputStream("src\\test\\resources\\ClientData.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet(SheetName);
		//int CellCount=sh.getLeftCol();
		//System.out.println(CellCount);
		Row row;
		//int CellCount=row.getPhysicalNumberOfCells();
		//System.out.println(CellCount);
		for(int i=3;i<=sh.getLastRowNum();i++) {
		System.out.println(i);
			System.out.println(sh.getLastRowNum());
			
		 row = sh.getRow(i);
		 int CellCount=row.getPhysicalNumberOfCells();
				 System.out.println(CellCount);
		// System.out.println(row);
		 SerachKeyword=row.getCell(1).getStringCellValue();
		 browser = row.getCell(0).getStringCellValue();
		 System.out.println(browser);
	     SearchUrl=row.getCell(2).getStringCellValue();
	     min=(int) row.getCell(7).getNumericCellValue();
	     max=(int) row.getCell(8).getNumericCellValue();
	     System.out.println(SerachKeyword);
	     for(int j=3;j<=CellCount-3;j++) {
	    	 urls.add(row.getCell(j).getStringCellValue());
	         System.out.println("Fetching refurls");
	         
	    	 
	     }
	    break;
	 
		
	     }}
		
		catch(Exception e) {
			System.out.println("getDataFromExcelSheet fot failed");
		e.printStackTrace();}}
		
	private static void OpenNewLink(String link) throws IOException, URISyntaxException, InterruptedException, AWTException {
		try {
		Robot r1=new Robot();
		browserSelectiontoOpenUrl(browser,link);
		System.out.println("in openlink");
		System.out.println(browser);
		/*Desktop.isDesktopSupported();
	
          Desktop.getDesktop().browse(new URI(link));}*/
            Thread.sleep(5000);
           
            	//int count=0;
            	
            	//Robot r1=new Robot();
            	Thread.sleep(1000);
            	System.out.println(value);
            	long startTime = System.currentTimeMillis();
                long endTime =  (startTime + (value * 1000));
            	 

            	    while (System.currentTimeMillis() < endTime) {
            	        r1.keyPress(KeyEvent.VK_DOWN);
            	        r1.keyRelease(KeyEvent.VK_DOWN);
            	        r1.delay(1000);
            	        r1.keyPress(KeyEvent.VK_DOWN);
            	        r1.keyRelease(KeyEvent.VK_DOWN);
            	       
            	        }
            	  
	}
		catch(Exception e) {
			System.out.println("OpenNewLink method got executed");
		}
		}
            	    
            	   
		private static void browserSelectiontoOpenUrl( String browser,String link) {
			try {
				  String[] command = {"cmd", "/c", "start", browser, link};

		          // Execute the command
		          Process process = new ProcessBuilder(command).start();

		          // Optionally, you can wait for the process to finish
		          int exitCode = process.waitFor();
		          System.out.println(browser+" browser opened with exit code: " + exitCode);
			} catch (IOException | InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
