// Name:Muhammad Fahmy bin Zyenal Ebydean
// Email: fahmyebyyussoff@yahoo.com.sg
// Group A

package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		String csvFile = "C:\\Users\\Pre-Installed User\\Desktop\\vttp_b5_assessment\\vttp_b5_assessment\\task01\\day.csv";
		String line;
		boolean header = true;
		List<RiderData> riderDataList = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			// skip header
			while (header) {
				header = false;
			}
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(",");

				try {
					// parse csv
					int season = Integer.parseInt(columns[0].trim());
					int month = Integer.parseInt(columns[1].trim());
					int holidayValue = Integer.parseInt(columns[2].trim());
					int day = Integer.parseInt(columns[3].trim());
					int weather = Integer.parseInt(columns[4].trim());
					int col8 = Integer.parseInt(columns[8].trim());
					int col9 = Integer.parseInt(columns[9].trim());
					int totalRiders = col8 + col9;
					boolean holiday = (holidayValue == 1); // convert from int to boolean
					riderDataList.add(new RiderData(day, season, totalRiders, weather, holiday, month));

				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}

			// sort list form big to small
			riderDataList.sort(new Comparator<RiderData>() {
				@Override
				public int compare(RiderData r1, RiderData r2) {
					return Integer.compare(r2.totalRiders, r1.totalRiders);
				}
			});

			// filter top5
			List<RiderData> top5Riders = riderDataList.size() > 5 ? riderDataList.subList(0, 5) : riderDataList;

			// output
			for (RiderData riderData : top5Riders) {
				System.out.println("\n\nThe (position) recorded number of cyclists was in "
						+ riderData.getSeason(riderData.season) + " (season), on a " + riderData.getDay(riderData.day)
						+ " (day) in the month of " + riderData.getMonth(riderData.month)
						+ " (month). There were a total of " + riderData.totalRiders
						+ " (total) cyclists. The weather was " + riderData.getWeather(riderData.weather)
						+ " (weather). " + riderData.getDay(riderData.day) + " (day) "
						+ (riderData.holiday ? "was a holiday." : "was not a holiday."));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class RiderData {
		// initialize variable
		int day;
		int season;
		int totalRiders;
		int weather;
		boolean holiday;
		int month;

		// constructor
		public RiderData(int day, int season, int totalRiders, int weather, boolean holiday, int month) {
			this.day = day;
			this.season = season;
			this.totalRiders = totalRiders;
			this.weather = weather;
			this.holiday = holiday;
			this.month = month;
		}

		// methods to convert int to string
		private String getDay(int day) {
			switch (day) {
				case 0:
					return "Sunday";
				case 1:
					return "Monday";
				case 2:
					return "Tuesday";
				case 3:
					return "Wednesday";
				case 4:
					return "Thursday";
				case 5:
					return "Friday";
				case 6:
					return "Saturday";
				default:
					return "Invalid";
			}
		}

		private String getSeason(int season) {
			switch (season) {
				case 1:
					return "Spring";
				case 2:
					return "Summer";
				case 3:
					return "Fall";
				case 4:
					return "Winter";
				default:
					return "Invalid";
			}
		}

		private String getMonth(int month) {
			switch (month) {
				case 1:
					return "January";
				case 2:
					return "February";
				case 3:
					return "March";
				case 4:
					return "April";
				case 5:
					return "May";
				case 6:
					return "June";
				case 7:
					return "July";
				case 8:
					return "August";
				case 9:
					return "September";
				case 10:
					return "October";
				case 11:
					return "November";
				case 12:
					return "December";
				default:
					return "Invalid";
			}
		}

		private String getWeather(int weather) {
			switch (weather) {
				case 1:
					return "Clear, Few clouds, Partly cloudy, Partly cloudy";
				case 2:
					return "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
				case 3:
					return "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
				case 4:
					return "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
				default:
					return "Invalid";
			}
		}
	}

}
