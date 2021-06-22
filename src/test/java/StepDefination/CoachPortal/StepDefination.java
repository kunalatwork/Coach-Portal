package StepDefination.CoachPortal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class StepDefination {

	WebDriver driver;
	Properties prop;

	@Before()
	public void setup() throws IOException {
		prop=new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\lakhan\\eclipse-workspace\\CoachPortal\\Data\\Config.properties");
		prop.load(fis);

		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lakhan\\eclipse-workspace\\CoachPortal\\Browsers\\"
					+ "chromedriver.exe");
			driver = new ChromeDriver();
		}

		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
		}

		if(browser.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", "");
			driver = new EdgeDriver();
		}


	}

	@Given("^Launch browser and Enter url$")
	public void launch_browser_and_Enter_url() throws Throwable {
		driver.get("http://testingcoachportal.advancesfe.com/");
		driver.manage().window().maximize();
		driver.getTitle();
	}

	@Then("^Enter valid username and password$")
	public void enter_valid_username_and_password() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("admin@healthcareinteraction.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("test@12");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='submit']")).click();
	}

	@Then("^Enter First name and last name$")
	public void enter_First_name_and_last_name() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@data-bind='value: $root.FirstName']")).sendKeys("CoachFirstname");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='main-section']/div[@id='right-section']/div[2]/form[1]/div"
				+ "[1]/div[1]/div[1]/div[1]/div[4]/input[1]")).sendKeys("CoachLastname");
	}

	@Then("^Select Gender and is coach option$")
	public void select_Gender_and_is_coach_option() throws Throwable 
	{
		Thread.sleep(1000);
		String Sex = prop.getProperty("Sex");
		WebElement chooseSex = driver.findElement(By.id("Sex"));
		Select selectSex = new Select(chooseSex);
		List<WebElement> getgender = selectSex.getOptions();
		int optionSize = getgender.size();
		for(int i=0; i<optionSize; i++) {
			String alloptions = getgender.get(i).getText();

			if(alloptions.equalsIgnoreCase(Sex)) {
				selectSex.selectByVisibleText(Sex);
			}
		}


		Thread.sleep(2000);
		String IsCoach = prop.getProperty("IsCoach");

		WebElement chooseIsCoach = driver.findElement(By.xpath("//body/div[@id='main-section']/div[@id='right-section']"
				+ "/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[4]/select[1]"));
		Select SelectIsCoach = new Select(chooseIsCoach);
		List<WebElement> getCoachOptn = SelectIsCoach.getOptions();
		int itemCount = getCoachOptn.size();
		System.out.println(itemCount);
		for(int j = 0; j <itemCount; j++)
		{
			String get = getCoachOptn.get(j).getText();
			if(get.equalsIgnoreCase(IsCoach)) 
			{
				SelectIsCoach.selectByVisibleText(IsCoach);		
			}
		}
	}


	@Then("^Enter email and password$")
	public void enter_email_and_password() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='main-section']/div[@id='right-section']/div[2]/"
				+ "form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/input[1]")).sendKeys("kunal@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='main-section']/div[@id='right-section']/div[2]/fo"
				+ "rm[1]/div[1]/div[1]/div[3]/div[1]/div[4]/input[1]")).sendKeys("123456789");

	}

	@Then("^Add Onboarding date and status$")
	public void add_Onboarding_date_and_status() throws Throwable {
		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("21/06/2021");

		Thread.sleep(1000);
		String Status = prop.getProperty("Status");
		if(Status.equalsIgnoreCase("Active"))
		{
			driver.findElement(By.xpath("//label[@for='active']")).click();
		}
		if(Status.equalsIgnoreCase("Inactive"))
		{
			driver.findElement(By.xpath("//label[@for='inactive']")).click();
		}
		if(Status.equalsIgnoreCase("Delete"))
		{
			driver.findElement(By.xpath("//label[@for='delete']")).click();
		}
	}

	@Then("^Click on Coach save button$")
	public void click_on_Coach_save_button() throws Throwable {
		//		 driver.findElement(By.xpath("//body/div[@id='main-section']/div[@id='right-section']"
		//		 		+ "/div[2]/form[1]/div[1]/div[2]/input[1]")).click();
	}

	@Given("^Select Project Management tab$")
	public void select_Project_Management_tab() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Project Management')]")).click();
	}

	@Then("^Enter Project Name and Select client$")
	public void enter_Project_Name_and_Select_client() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@data-bind='value: $root.ProjectName']")).sendKeys("ProjectName");

		Thread.sleep(1000);
		String Client = prop.getProperty("Client");
		WebElement ChooseClient  = driver.findElement(By.xpath("//select[@id='selectTeam']"));
		Select SelectClient = new Select(ChooseClient);
		List<WebElement> GetAllClient =  SelectClient.getOptions();
		int clientLen = GetAllClient.size();
		for(int i=0; i<clientLen; i++) {
			String showClient = GetAllClient.get(i).getText();
			if(showClient.equalsIgnoreCase(Client))
			{
				SelectClient.selectByVisibleText(Client);
			}
		}
	}

	@Then("^select start and end date$")
	public void select_start_and_end_date() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("21/06/2021");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='datepicker1']")).sendKeys("21/06/2021");
	}

	@Then("^Enter coaching date, PO no\\.$")
	public void enter_coaching_date_PO_no() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@data-bind='value: $root.NoOfCoachingDays']")).sendKeys("10");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@data-bind='value: $root.PONo']")).sendKeys("SDFAS23");
	}

	@Then("^PO date and is expense project$")
	public void po_date_and_is_expense_project() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='datepicker2']")).sendKeys("21/06/2021");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	}

	@Then("^click on Project save button$")
	public void click_on_Project_save_button() throws Throwable {
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//input[@value='Save']")).click();
	}

	@Then("^click on Project configuration$")
	public void click_on_Project_configuration() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Project Configuration']")).click();
	}

	@Then("^Select project name and coach$")
	public void select_project_name_and_coach() throws Throwable {
		Thread.sleep(1000);
		String Project = prop.getProperty("Project");
		WebElement Projects = driver.findElement(By.xpath("//div[@class='content_wrapper']//div[1]//div[1]//div[2]//select[1]"));
		Select SelectProject = new Select(Projects);
		List<WebElement> getAllProject = SelectProject.getOptions();
		int projectSize = getAllProject.size();
		for(int i=0; i<projectSize; i++) {
			String allProject = getAllProject.get(i).getText();
			if(allProject.equalsIgnoreCase(Project)) {
				SelectProject.selectByVisibleText(Project);
			}
		}
		Thread.sleep(2000);
		String Coach = prop.getProperty("Coach");
		WebElement Coaches = driver.findElement(By.xpath("//div[@class='content_wrapper']//div[1]//div[1]//div[4]//select[1]"));
		Select SelectCoach = new Select(Coaches);
		List<WebElement> allCoaches = SelectCoach.getOptions();
		int coachsize = allCoaches.size();
		for(int j=0; j<coachsize; j++) {
			String GetCoach = allCoaches.get(j).getText();
			if(GetCoach.equalsIgnoreCase(Coach)) {
				SelectCoach.selectByVisibleText(Coach);
			}
		}
	}

	@Then("^Representative and franchise$")
	public void representative_and_franchise() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='edit-section']//div[2]//div[1]//div[2]//select[1]"));
		Thread.sleep(1000);
		String franchise = prop.getProperty("franchise");
		WebElement Choosefranchise = driver.findElement(By.xpath("//div[@class='edit-section']//div[2]//div[1]//div[4]//select[1]"));
		Select Selectfranchise = new Select(Choosefranchise);
		List<WebElement> allfranchise = Selectfranchise.getOptions();
		int franchisesize = allfranchise.size();
		for(int j=0; j<franchisesize; j++) {
			String GetCoach = allfranchise.get(j).getText();
			if(GetCoach.equalsIgnoreCase(franchise)) {
				Selectfranchise.selectByVisibleText(franchise);
			}
		}
	}

	@Then("^Select project and enter no of coaching$")
	public void select_project_and_enter_no_of_coaching() throws Throwable {
		Thread.sleep(1000);
		String Product = prop.getProperty("Product");
		List<WebElement> ChooseProduct = driver.findElements(By.xpath("//body/div[@id='main-section']/div[@id='right-section']/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/ul[1]"));
		for(WebElement get: ChooseProduct) {
		if(get.getText().equalsIgnoreCase(Product)) 
			get.click();
			Thread.sleep(2000);
			WebElement chospro = driver.findElement(By.xpath("//select[@id='PName']"));
			Select selectprod = new Select(chospro);
			selectprod.selectByVisibleText("Apple");
     		Thread.sleep(2000);
			selectprod.selectByVisibleText("BMW");
		}
		Thread.sleep(1000);
		WebElement NoOfCoaches = driver.findElement(By.xpath("//input[@class='form-control']"));
		NoOfCoaches.clear();
		Thread.sleep(1000);
		NoOfCoaches.sendKeys("10");
	}

	@Then("^click on save Project button$")
	public void click_on_save_Project_button() throws Throwable {
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//input[@value='Save']")).click();
	}

	@Then("^User expense project configuration$")
	public void user_expense_project_configuration() throws Throwable {
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[normalize-space()='Expense Project Configuration']")).click();
	}

	@Then("^select project name and Coach$")
	public void select_project_name_and_Coach() throws Throwable {
		Thread.sleep(2000);
		String ProjectName = prop.getProperty("ProjectName");
		WebElement Projects = driver.findElement(By.xpath("//select[@id='ddl_project']"));
		Select SelectProject = new Select(Projects);
		List<WebElement> getAllProject = SelectProject.getOptions();
		int projectSize = getAllProject.size();
		for(int i=0; i<projectSize; i++) {
			String allProject = getAllProject.get(i).getText();
			if(allProject.equalsIgnoreCase(ProjectName)) {
				SelectProject.selectByVisibleText(ProjectName);
			}
		}
		Thread.sleep(1000);
		String Coach = prop.getProperty("Coach");
		WebElement Coaches = driver.findElement(By.xpath("//div[@class='content_wrapper']//div[1]//div[1]//div[4]//select[1]"));
		Select SelectCoach = new Select(Coaches);
		List<WebElement> allCoaches = SelectCoach.getOptions();
		int coachsize = allCoaches.size();
		for(int j=0; j<coachsize; j++) {
			String GetCoach = allCoaches.get(j).getText();
			if(GetCoach.equalsIgnoreCase(Coach)) {
				SelectCoach.selectByVisibleText(Coach);
			}
		}
	}

	@Then("^Select franchise and select product$")
	public void select_franchise_and_select_product() throws Throwable {
		Thread.sleep(1000);
		String franchise = prop.getProperty("franchise");
		WebElement Choosefranchise = driver.findElement(By.xpath("//select[@id='ddl_franchise']"));
		Select Selectfranchise = new Select(Choosefranchise);
		List<WebElement> allfranchise = Selectfranchise.getOptions();
		int franchisesize = allfranchise.size();
		for(int j=0; j<franchisesize; j++) {
			String GetCoach = allfranchise.get(j).getText();
			if(GetCoach.equalsIgnoreCase(franchise)) {
				Selectfranchise.selectByVisibleText(franchise);
			}
		}
		
		Thread.sleep(1000);
		String Product = prop.getProperty("Product");
		List<WebElement> ChooseProduct = driver.findElements(By.xpath("//div[@id='s2id_cmb_products']//ul[@class='select2-choices']"));
		for(WebElement get: ChooseProduct) {
		if(get.getText().equalsIgnoreCase(Product)) 
			get.click();
			Thread.sleep(2000);
			WebElement chospro = driver.findElement(By.xpath("//select[@id='cmb_products']"));
			Select selectprod = new Select(chospro);
			selectprod.selectByVisibleText("Apple");
     		Thread.sleep(2000);
			selectprod.selectByVisibleText("BMW");
		}
	}

	@Then("^enter no\\. of coach days and select assigned Activites$")
	public void enter_no_of_coach_days_and_select_assigned_Activites() throws Throwable {
		Thread.sleep(1000);
		WebElement NoOfCoaches = driver.findElement(By.xpath("//input[@class='form-control']"));
		NoOfCoaches.clear();
		Thread.sleep(1000);
		NoOfCoaches.sendKeys("10");
		
		Thread.sleep(1000);
		String Product = prop.getProperty("Product");
		List<WebElement> ChooseProduct = driver.findElements(By.xpath("//div[@id='s2id_cmb_activities']//ul[@class='select2-choices']"));
		for(WebElement get: ChooseProduct) {
		if(get.getText().equalsIgnoreCase(Product)) 
			get.click();
			Thread.sleep(2000);
			WebElement chospro = driver.findElement(By.xpath("//select[@id='cmb_activities']"));
			Select selectprod = new Select(chospro);
			selectprod.selectByVisibleText("Coaching");
   
		}
	}

	@Then("^Select configuration View tab and view hierarchy$")
	public void select_configuration_View_tab_and_view_hierarchy() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Project Configuration View']")).click();
	}

	@Given("^Select Coach Reports tab$")
	public void select_Coach_Reports_tab() throws Throwable {
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[normalize-space()='Coach Reports']")).click();
	}
	
	@Given("^Select On Rate list tab$")
	public void select_On_Rate_list_tab() throws Throwable {
		Thread.sleep(2000);
     driver.findElement(By.xpath("//a[normalize-space()='Rate List']")).click();
	}

	@Then("^Enter Activity name$")
	public void enter_Activity_name() throws Throwable {
		Thread.sleep(2000);
		String ActivityName = prop.getProperty("ActivityName");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(ActivityName);
	}

	@Then("^add Daily and Half day rate of Associate, Director and MD$")
	public void add_Daily_and_Half_day_rate_of_Associate_Director_and_MD() throws Throwable {
		Thread.sleep(2000);
		String AssociateDailyBGP = prop.getProperty("AssociateDailyBGP");
		String AssociateDailyEUR = prop.getProperty("AssociateDailyBGP");
		String AssociateHalfBGP = prop.getProperty("AssociateDailyBGP");
		String AssociateHalfEUR = prop.getProperty("AssociateDailyBGP");
		String DirectorDailyBGP = prop.getProperty("AssociateDailyBGP");
		String DirectorDailyEUR = prop.getProperty("AssociateDailyBGP");
		String DirectorHalfBGP = prop.getProperty("AssociateDailyBGP");
		String DirectorHalfEUR = prop.getProperty("AssociateDailyBGP");
		String MDDailyBGP = prop.getProperty("AssociateDailyBGP");
		String MDDailyEUR = prop.getProperty("AssociateDailyBGP");
		String MDHalfBGP = prop.getProperty("AssociateDailyBGP");
		String MDHalfEUR = prop.getProperty("AssociateDailyBGP");
		
		Thread.sleep(1000);
		WebElement AsDailyBGP = driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]"));
		AsDailyBGP.clear();
		AsDailyBGP.sendKeys(AssociateDailyBGP);
		
		Thread.sleep(1000);
		WebElement AsDailyEUR = driver.findElement(By.xpath("//tbody/tr[3]/td[2]/input[1]"));
		AsDailyEUR.clear();
		AsDailyEUR.sendKeys(AssociateDailyEUR);
		Thread.sleep(1000);
		
		WebElement AsHalfBGP = driver.findElement(By.xpath("//tbody/tr[3]/td[3]/input[1]"));
		AsHalfBGP.clear();
		AsHalfBGP.sendKeys(AssociateHalfBGP);
		Thread.sleep(1000);
		
		WebElement AsHalfEUR = driver.findElement(By.xpath("//tbody/tr[3]/td[4]/input[1]"));
		AsHalfEUR.clear();
		AsHalfEUR.sendKeys(AssociateHalfEUR);
		
		Thread.sleep(1000);
		WebElement DirDailyBGP = driver.findElement(By.xpath("//tbody/tr[4]/td[1]/input[1]"));
		DirDailyBGP.clear();
		DirDailyBGP.sendKeys(DirectorDailyBGP);
		
		Thread.sleep(1000);
		WebElement DirDailyEur = driver.findElement(By.xpath("//tbody/tr[4]/td[2]/input[1]"));
		DirDailyEur.clear();
		DirDailyEur.sendKeys(DirectorDailyEUR);
		
		Thread.sleep(1000);
		WebElement DirHalfBGP = driver.findElement(By.xpath("//tbody/tr[4]/td[3]/input[1]"));
		DirHalfBGP.clear();
		DirHalfBGP.sendKeys(DirectorHalfBGP);
		
		Thread.sleep(1000);
		WebElement DirHalfEUR = driver.findElement(By.xpath("//tbody/tr[4]/td[4]/input[1]"));
		DirHalfEUR.clear();
		DirHalfEUR.sendKeys(DirectorHalfEUR);
		
		Thread.sleep(1000);
		WebElement MDsDailyBGP = driver.findElement(By.xpath("//tbody/tr[6]/td[1]/input[1]"));
		MDsDailyBGP.clear();
		MDsDailyBGP.sendKeys(MDDailyBGP);
		
		Thread.sleep(1000);
		WebElement MDsDailyEUR = driver.findElement(By.xpath("//tbody/tr[6]/td[2]/input[1]"));
		MDsDailyEUR.clear();
		MDsDailyEUR.sendKeys(MDDailyEUR);
		
		Thread.sleep(1000);
		WebElement MDsHalfBGP = driver.findElement(By.xpath("//tbody/tr[6]/td[3]/input[1]"));
		MDsHalfBGP.clear();
		MDsHalfBGP.sendKeys(MDHalfBGP);
		
		Thread.sleep(1000);
		WebElement MDsHalfEUR = driver.findElement(By.xpath("//tbody/tr[6]/td[4]/input[1]"));
		MDsHalfEUR.clear();
		MDsHalfEUR.sendKeys(MDHalfEUR);
		
		
	}

	@Then("^click on Rate save button$")
	public void click_on_Rate_save_button() throws Throwable {
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//input[@value='Save']")).click();
	}

	@Given("^Select Expense report tab$")
	public void select_Expense_report_tab() throws Throwable {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS );
		driver.findElement(By.xpath("//a[normalize-space()='Expense Report']")).click();
	}

	@Given("^Select FV Management tab$")
	public void select_FV_Management_tab() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='FV Management']")).click();
	}

	@Then("^Select project Name and Select rep$")
	public void select_project_Name_and_Select_rep() throws Throwable {
		Thread.sleep(1000);
		String FVProjectName = prop.getProperty("FVProjectName");
		WebElement ChooseClient  = driver.findElement(By.xpath("//select[@id='Project']"));
		Select SelectClient = new Select(ChooseClient);
		List<WebElement> GetAllClient =  SelectClient.getOptions();
		int clientLen = GetAllClient.size();
		for(int i=0; i<clientLen; i++) {
			String showClient = GetAllClient.get(i).getText();
			if(showClient.equalsIgnoreCase(FVProjectName))
			{
				SelectClient.selectByVisibleText(FVProjectName);
			}
		}
		
//		Thread.sleep(1000);
//		String FVRepName = prop.getProperty("FVRepName");
//		WebElement ChooseFVRepName  = driver.findElement(By.xpath("//select[@id='selectTeam']"));
//		Select SelectFVRepName = new Select(ChooseFVRepName);
//		List<WebElement> GetAllFVRepName =  SelectClient.getOptions();
//		int RepLen = GetAllFVRepName.size();
//		for(int j=0; j<RepLen; j++) {
//			String showRep = GetAllClient.get(j).getText();
//			if(showRep.equalsIgnoreCase(FVRepName))
//			{
//				SelectFVRepName.selectByVisibleText(FVRepName);
//			}
//		}
	}

	@Then("^Select Coach and select Date$")
	public void select_Coach_and_select_Date() throws Throwable {

		Thread.sleep(1000);
		String FVCoachName = prop.getProperty("FVCoachName");
		WebElement ChooseClient  = driver.findElement(By.xpath("//select[@id='Coach']"));
		Select SelectClient = new Select(ChooseClient);
		List<WebElement> GetAllClient =  SelectClient.getOptions();
		int clientLen = GetAllClient.size();
		for(int i=0; i<clientLen; i++) {
			String showClient = GetAllClient.get(i).getText();
			if(showClient.equalsIgnoreCase(FVCoachName))
			{
				SelectClient.selectByVisibleText(FVCoachName);
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='datepicker3']")).sendKeys("21/06/2021");

	}

	@Then("^click on submit button$")
	public void click_on_submit_button() throws Throwable {
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
	}

}
