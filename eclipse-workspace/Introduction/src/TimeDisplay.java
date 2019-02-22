// TimeDisplay.java
// Student starting version of the Lab02 assignment.
// The Time Display Program – Starting with seconds (or milliseconds)
// display the hours, minutes, and seconds.
public class TimeDisplay {
	
	public static void main(String args[]) {
	 
		System.out.println("Lab02: The Time Display Program.\n");
		long starting_time = 100000L;
		long milliseconds = starting_time%1000;
		long total_seconds = starting_time/1000;
		long seconds = total_seconds%60;
		long total_minutes = total_seconds/60;
		long minutes = total_minutes%60;
		long total_hours = total_minutes/60;
		System.out.println("Starting seconds: " + starting_time);
		System.out.println("Hours: " + total_hours);
		System.out.println("Minutes: " + minutes);
		System.out.println("Seconds: " + seconds);
		System.out.println("Milliseconds: " + milliseconds);
 }

}