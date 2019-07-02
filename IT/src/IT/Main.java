package IT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	static File fisier = new File("fisier.json");

	public static void main(String[] args) {

		System.out.println("Creating the JSON File...");
		if (!fisier.exists()) {
			initialize();
		} else {
			System.out.println("The JSON File already exists!");
		}

		try {
			load();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

	}

	public static void initialize() {
		ActiveProgrammers p1 = new ActiveProgrammers("Viorel", "Cucu", "Java", "08.04.2019", "07.06.2019",45, 61, 20);
		ActiveProgrammers p2 = new ActiveProgrammers("Andrei", "Popescu", "Java", "03.06.2019", "12.07.2019", 40, 30,20);
		ActiveProgrammers p3 = new ActiveProgrammers("David", "Ion", "C#", "31.05.2019", "20.06.2019", 15, 21, 10);
		ActiveProgrammers p4 = new ActiveProgrammers("Maria", "Crin", "C#", "31.05.2019", "22.06.2019", 16, 23, 10);

		// Set total cost for every programmer in charge
		p1.setTotalCost(p1.calcTotalCost());
		p2.setTotalCost(p2.calcTotalCost());
		p3.setTotalCost(p3.calcTotalCost());
		p4.setTotalCost(p4.calcTotalCost());

		/* Add programmer in charge within 2 lists */
		List<ActiveProgrammers> list1 = new ArrayList<ActiveProgrammers>();
		list1.add(p1);
		list1.add(p2);

		List<ActiveProgrammers> list2 = new ArrayList<ActiveProgrammers>();
		list2.add(p3);
		list2.add(p4);

		/* Define project teams */
		ProjectTeam team1 = new ProjectTeam("Team 1", "Java", true, list1);
		ProjectTeam team2 = new ProjectTeam("Team 2", "C#", false, list2);

		/*
		 * Calculate number of days have been consumed and days still in charge
		 */
		long daysConsumed = p1.getWorkedDays() + p2.getWorkedDays() + p3.getWorkedDays() + p4.getWorkedDays();
		long daysInCharge = p1.getTotalDays() + p2.getTotalDays() + p3.getTotalDays() + p4.getTotalDays();

		save(p1, p2, p3, p4, daysConsumed, daysInCharge, team1, team2);

	}

	@SuppressWarnings("unchecked")
	public static void save(ActiveProgrammers p1, ActiveProgrammers p2, ActiveProgrammers p3, ActiveProgrammers p4,
			long daysConsumed, long daysInCharge, ProjectTeam team1, ProjectTeam team2) {

		/* Encoding JSON */
		JSONObject obj = new JSONObject();

		obj.put("name", "IT");
		obj.put("numberOfTeams", 2);
		obj.put("numberOfProgrammers", 4);
		obj.put("daysConsumed", daysConsumed);
		obj.put("daysInCharge", daysInCharge);

		/* Encoding team1 JSON */
		JSONObject p1Obj = new JSONObject();
		p1Obj.put("programmerFirstName", p1.getFirstName());
		p1Obj.put("programmerLastName", p1.getLastName());
		p1Obj.put("programmerTechnology", p1.getTechnology());
		p1Obj.put("programmerStartDate", p1.getStartDate());
		p1Obj.put("programmerEndDate", p1.getEndDate());
		p1Obj.put("programmerWorkedDays", p1.getWorkedDays());
		p1Obj.put("programmerTotalDays", p1.getTotalDays());
		p1Obj.put("programmerSalary", p1.getSalary());
		p1Obj.put("programmerTotalCost", p1.getTotalCost());

		JSONObject p2Obj = new JSONObject();
		p2Obj.put("programmerFirstName", p2.getFirstName());
		p2Obj.put("programmerLastName", p2.getLastName());
		p2Obj.put("programmerTechnology", p2.getTechnology());
		p2Obj.put("programmerStartDate", p2.getStartDate());
		p2Obj.put("programmerEndDate", p2.getEndDate());
		p2Obj.put("programmerWorkedDays", p2.getWorkedDays());
		p2Obj.put("programmerTotalDays", p2.getTotalDays());
		p2Obj.put("programmerSalary", p2.getSalary());
		p2Obj.put("programmerTotalCost", p2.getTotalCost());

		/* Encoding team2 JSON */
		JSONObject p3Obj = new JSONObject();
		p3Obj.put("programmerFirstName", p3.getFirstName());
		p3Obj.put("programmerLastName", p3.getLastName());
		p3Obj.put("programmerTechnology", p3.getTechnology());
		p3Obj.put("programmerStartDate", p3.getStartDate());
		p3Obj.put("programmerEndDate", p3.getEndDate());
		p3Obj.put("programmerWorkedDays", p3.getWorkedDays());
		p3Obj.put("programmerTotalDays", p3.getTotalDays());
		p3Obj.put("programmerSalary", p3.getSalary());
		p3Obj.put("programmerTotalCost", p3.getTotalCost());

		JSONObject p4Obj = new JSONObject();
		p4Obj.put("programmerFirstName", p4.getFirstName());
		p4Obj.put("programmerLastName", p4.getLastName());
		p4Obj.put("programmerTechnology", p4.getTechnology());
		p4Obj.put("programmerStartDate", p4.getStartDate());
		p4Obj.put("programmerEndDate", p4.getEndDate());
		p4Obj.put("programmerWorkedDays", p4.getWorkedDays());
		p4Obj.put("programmerTotalDays", p4.getTotalDays());
		p4Obj.put("programmerSalary", p4.getSalary());
		p4Obj.put("programmerTotalCost", p4.getTotalCost());

		/* Encoding teams within JSONArray */
		JSONArray team1Obj = new JSONArray();
		JSONArray team2Obj = new JSONArray();

		team1Obj.add(p1Obj);
		team1Obj.add(p2Obj);
		team2Obj.add(p3Obj);
		team2Obj.add(p4Obj);

		obj.put(team1.getTeamName(), team1Obj);
		obj.put(team2.getTeamName(), team2Obj);

		/* Write JSON Object to JSON File */
		FileWriter writer;
		try {
			System.out.println("Start writing JSON File...");
			writer = new FileWriter(fisier);
			writer.write(obj.toJSONString());
			System.out.println("Successfully! JSON File is DONE!");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void load() throws java.text.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(fisier));
			JSONObject jsonObject = (JSONObject) obj;

			String nameCompany = (String) jsonObject.get("name");
			long numberOfTeams = (long) jsonObject.get("numberOfTeams");
			long numberOfProgrammers = (long) jsonObject.get("numberOfProgrammers");
			long daysConsumed = (long) jsonObject.get("daysConsumed");
			long daysInCharge = (long) jsonObject.get("daysInCharge");

			/* Create Team 1 and programmers */
			JSONArray jTeam1 = (JSONArray) jsonObject.get("Team 1");
			ActiveProgrammers p1 = new ActiveProgrammers();
			ActiveProgrammers p2 = new ActiveProgrammers();

			/* Read from Team 1 => programmer 1 */
			Object prog1 = jTeam1.get(0);
			String firstProjectName = (String) ((JSONObject) prog1).get("programmerTechnology");
			String prog1FirstName = (String) ((JSONObject) prog1).get("programmerFirstName");
			String prog1LasttName = (String) ((JSONObject) prog1).get("programmerLastName");
			String prog1Technology = (String) ((JSONObject) prog1).get("programmerTechnology");
			String prog1StartDate = (String) ((JSONObject) prog1).get("programmerStartDate");
			String prog1EndDate = (String) ((JSONObject) prog1).get("programmerEndDate");
			long prog1WorkedDays = (long) ((JSONObject) prog1).get("programmerWorkedDays");
			long prog1TotalDays = (long) ((JSONObject) prog1).get("programmerTotalDays");
			long prog1Salary = (long) ((JSONObject) prog1).get("programmerSalary");
			long prog1TotalCost = (long) ((JSONObject) prog1).get("programmerTotalCost");

			/* Set values from Team 1 => programmer 1 */
			p1.setFirstName(prog1FirstName);
			p1.setLastName(prog1LasttName);
			p1.setTechnology(prog1Technology);
			p1.setStartDate(prog1StartDate);
			p1.setEndDate(prog1EndDate);
			p1.setWorkedDays((int) prog1WorkedDays);
			p1.setTotalDays((int) prog1TotalDays);
			p1.setSalary((int) prog1Salary);
			p1.setTotalCost((int) prog1TotalCost);

			/* Read from Team 1 => programmer 2 */
			Object prog2 = jTeam1.get(1);
			String prog2FirstName = (String) ((JSONObject) prog2).get("programmerFirstName");
			String prog2LasttName = (String) ((JSONObject) prog2).get("programmerLastName");
			String prog2Technology = (String) ((JSONObject) prog2).get("programmerTechnology");
			String prog2StartDate = (String) ((JSONObject) prog2).get("programmerStartDate");
			String prog2EndDate = (String) ((JSONObject) prog2).get("programmerEndDate");
			long prog2WorkedDays = (long) ((JSONObject) prog2).get("programmerWorkedDays");
			long prog2TotalDays = (long) ((JSONObject) prog2).get("programmerTotalDays");
			long prog2Salary = (long) ((JSONObject) prog2).get("programmerSalary");
			long prog2TotalCost = (long) ((JSONObject) prog2).get("programmerTotalCost");

			/* Set values from Team 1 => programmer 2 */
			p2.setFirstName(prog2FirstName);
			p2.setLastName(prog2LasttName);
			p2.setTechnology(prog2Technology);
			p2.setStartDate(prog2StartDate);
			p2.setEndDate(prog2EndDate);
			p2.setWorkedDays((int) prog2WorkedDays);
			p2.setTotalDays((int) prog2TotalDays);
			p2.setSalary((int) prog2Salary);
			p2.setTotalCost((int) prog2TotalCost);

			/* Add programmers within a List */
			List<ActiveProgrammers> list1 = new ArrayList<ActiveProgrammers>();
			list1.add(p1);
			list1.add(p2);

			/* Create Team 2 and programmers */
			JSONArray jTeam2 = (JSONArray) jsonObject.get("Team 2");
			ActiveProgrammers p3 = new ActiveProgrammers();
			ActiveProgrammers p4 = new ActiveProgrammers();

			/* Read from Team 2 => programmer 3 */
			Object prog3 = jTeam2.get(0);
			String secondProjectName = (String) ((JSONObject) prog3).get("programmerTechnology");
			String prog3FirstName = (String) ((JSONObject) prog3).get("programmerFirstName");
			String prog3LasttName = (String) ((JSONObject) prog3).get("programmerLastName");
			String prog3Technology = (String) ((JSONObject) prog3).get("programmerTechnology");
			String prog3StartDate = (String) ((JSONObject) prog3).get("programmerStartDate");
			String prog3EndDate = (String) ((JSONObject) prog3).get("programmerEndDate");
			long prog3WorkedDays = (long) ((JSONObject) prog3).get("programmerWorkedDays");
			long prog3TotalDays = (long) ((JSONObject) prog3).get("programmerTotalDays");
			long prog3Salary = (long) ((JSONObject) prog3).get("programmerSalary");
			long prog3TotalCost = (long) ((JSONObject) prog3).get("programmerTotalCost");

			/* Set values from Team 2 => programmer 3 */
			p3.setFirstName(prog3FirstName);
			p3.setLastName(prog3LasttName);
			p3.setTechnology(prog3Technology);
			p3.setStartDate(prog3StartDate);
			p3.setEndDate(prog3EndDate);
			p3.setWorkedDays((int) prog3WorkedDays);
			p3.setTotalDays((int) prog3TotalDays);
			p3.setSalary((int) prog3Salary);
			p3.setTotalCost((int) prog3TotalCost);

			/* Read from Team 2 => programmer 4 */
			Object prog4 = jTeam2.get(1);
			String prog4FirstName = (String) ((JSONObject) prog4).get("programmerFirstName");
			String prog4LasttName = (String) ((JSONObject) prog4).get("programmerLastName");
			String prog4Technology = (String) ((JSONObject) prog4).get("programmerTechnology");
			String prog4StartDate = (String) ((JSONObject) prog4).get("programmerStartDate");
			String prog4EndDate = (String) ((JSONObject) prog4).get("programmerEndDate");
			long prog4WorkedDays = (long) ((JSONObject) prog4).get("programmerWorkedDays");
			long prog4TotalDays = (long) ((JSONObject) prog4).get("programmerTotalDays");
			long prog4Salary = (long) ((JSONObject) prog4).get("programmerSalary");
			long prog4TotalCost = (long) ((JSONObject) prog4).get("programmerTotalCost");

			/* Set values from Team 2 => programmer 4 */
			p4.setFirstName(prog4FirstName);
			p4.setLastName(prog4LasttName);
			p4.setTechnology(prog4Technology);
			p4.setStartDate(prog4StartDate);
			p4.setEndDate(prog4EndDate);
			p4.setWorkedDays((int) prog4WorkedDays);
			p4.setTotalDays((int) prog4TotalDays);
			p4.setSalary((int) prog4Salary);
			p4.setTotalCost((int) prog4TotalCost);

			/* Add programmers within a List */
			List<ActiveProgrammers> list2 = new ArrayList<ActiveProgrammers>();
			list2.add(p3);
			list2.add(p4);

			ProjectTeam team1 = new ProjectTeam("Team 1", "Java", true, list1);
			ProjectTeam team2 = new ProjectTeam("Team 2", "C#", false, list2);

			update(team1, team2);

			save(p1, p2, p3, p4, daysConsumed, daysInCharge, team1, team2);

			try {

				File report = new File("report.txt");
				if (!report.exists()) {
					report.createNewFile();
				} else {
					System.out.println("Report File already exists");
				}

				FileWriter fwReport = new FileWriter(report);
				BufferedWriter bwReport = new BufferedWriter(fwReport);
				StringBuffer contentReport = new StringBuffer();

				contentReport.append(nameCompany + "\r\n\r\n" + nameCompany + " is actually composed of "
						+ numberOfTeams + " project teams, and " + numberOfProgrammers + " programmers.\r\n"
						+ "This month, " + daysConsumed + " days have been consummed by " + numberOfProgrammers
						+ " programmers, and " + daysInCharge + " days still in charge.\n\r\n\r\n\r\n\r"
						+ "Project teams details: \n\r\n\r\n\r\n\r");

				contentReport.append("Project team: " + firstProjectName + "\n\r\n\r\n\r");

				for (ActiveProgrammers p : list1) {
					contentReport.append(p.getLastName() + ", " + p.getFirstName() + ", " + "in charge of "
							+ p.getTechnology() + " from " + p.getStartDate() + " to " + p.getEndDate() + " (duration "
							+ p.getTotalDays() + "), this month : " + p.getWorkedDays() + " days (total cost = "
							+ p.getTotalCost() + ")\n\r\n\r\n\r");
				}

				contentReport.append("\n\rProject team: " + secondProjectName + "\n\r\n\r\n\r");

				for (ActiveProgrammers p : list2) {
					contentReport.append(p.getLastName() + ", " + p.getFirstName() + ", " + "in charge of "
							+ p.getTechnology() + " from " + p.getStartDate() + " to " + p.getEndDate() + " (duration "
							+ p.getTotalDays() + "), this month : " + p.getWorkedDays() + " days (total cost = "
							+ p.getTotalCost() + ")\n\r\n\r\n\r");
				}

				bwReport.write(contentReport.toString());
				bwReport.close();
				fwReport.close();

				System.out.println("Report File is DONE!");
				System.out.println(contentReport);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void update(ProjectTeam t1, ProjectTeam t2) throws java.text.ParseException {

		for (ActiveProgrammers p : t1.getTeam()) {
				if (t1.isFullTime()) {
					p.increaseDuration();
					p.setTotalCost(p.calcTotalCost());
				} else {
					p.increaseDuration();
					p.setTotalCost(p.calcTotalCost() / 2);
				}
			}

		for (ActiveProgrammers p : t2.getTeam()) {
				if (t2.isFullTime() == true) {
					p.increaseDuration();
					p.setTotalCost(p.calcTotalCost());
				} else {
					p.increaseDuration();
					p.setTotalCost(p.calcTotalCost() / 2);
				}
			}
		}
}
