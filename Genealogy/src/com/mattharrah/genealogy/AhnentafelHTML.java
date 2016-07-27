package com.mattharrah.genealogy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.gedcom4j.model.FamilyChild;
import org.gedcom4j.model.Gedcom; 
import org.gedcom4j.model.Individual; 
import org.gedcom4j.model.IndividualEvent; 
import org.gedcom4j.model.IndividualEventType; 
import org.gedcom4j.parser.GedcomParser; 
import org.gedcom4j.parser.GedcomParserException; 
import org.gedcom4j.query.Finder; 


/**
 * Creates simple Ahnentafel HTML report from a GEDCOM.
 * @author Bill Sundstrom  
 * @author frizbog1
 */
public class AhnentafelHTML {
		/**
		* A class that holds a person along with their ahnentafel number      *
		* @author Bill Sundstrom
		* @author frizbog1
		*/
		public static class NumberedPerson {
				/**
				 * Ahnentafel number for this person
				 */
				public int number;
				/**
				 * The person
				 */
				public Individual person;
				/**
				 * Constructor          
				 * 
				 * @param number
				 *   ahnentafel number for this person
				 * @param person
				 *   the person
				 */
				public NumberedPerson(int number, Individual person) {
					this.number = number;
					this.person = person;
				}
				@Override
				public String toString() {
					return number + ": " + person;
				}
			}
			/**
			 * The queue of people to be listed in the report
			 */
		private static List<NumberedPerson> queue = new ArrayList<NumberedPerson>();
			/**
			 * The list of people who have been reported already
			 */
		private static List<NumberedPerson> reported = new ArrayList<NumberedPerson>();
			/**
             * The report buffer
             */
		public static StringBuilder reportBuf = new StringBuilder("");
			
			/**
			 *        The main method
			 * 
			 *  @param args
			 *        command-line arguments - ignored
			 *  @throws GedcomParserException
			 *         if the file can't be parsed
			 *  @throws IOException
			 *         if the file can't be read
			 */  		

		public static void main(String[] args) throws IOException,
				GedcomParserException {
			// Load the gedcom file
			GedcomParser parser = new GedcomParser();
			parser.load("C:\\Users\\GJWood\\workspace\\Genealogy\\edgar.ged");

			// Stop if there were parser errors
			if (!parser.errors.isEmpty()) {
				for (String s : parser.errors) {
					System.err.println(s);
				}
				return;
			}
			Gedcom gedcom = parser.gedcom;
			
			// Find first reported person in the file
			Finder f = new Finder(gedcom);
			List<Individual> matches = f.findByName("Wood", "Graham John");
			Individual individual = matches.get(0);
			
			// Assuming there is only one person with that name in the gedcom
			NumberedPerson firstPerson = new NumberedPerson(1, individual);
			queue.add(firstPerson);
			
			reportBuf.append("<HTML>\n<HEAD>\n<TITLE>Ahnentafel Report</TITLE>\n");
			reportBuf.append("</HEAD>\n<body>\n<h1>Ahnentafel Report</h1>\n\n");
			
			// As long as there are people in the queue, keep reporting
			while (!queue.isEmpty()) {
				reportNextQueuePerson();
			}
			reportBuf.append("</BODY>\n</HTML>\n");
			System.out.println(reportBuf); 
		}
		
	    /**
	     *  Write out the full entry for a person
	     *
         * @param numberedPerson      
	     *  the person in the report, with their ahnentafel number
	     */		
		protected static void addNumberedPerson(NumberedPerson numberedPerson) {
			// Write their person number and name to HTML buffer
			if (numberedPerson.number > 0) {
				reportBuf.append("<p>" + numberedPerson.number + ". ");
			}
			reportBuf.append(numberedPerson.person.formattedName());
			
			addBirthInfo(numberedPerson.person);
			addDeathInfo(numberedPerson.person);
			
			reportBuf.append("</p>\n");
	    } 
	   
		/**
		 * Produce report on the next person in the queue of people being reported.
		 *  As we process each person, we may add more people to the queue.
		 */
		protected static void reportNextQueuePerson() {
			// Take this person off the queue and put them in the reported list
			NumberedPerson p = queue.get(0);
			int fathersNumber = 2 * p.number;
			int mothersNumber = fathersNumber + 1;
			queue.remove(0);
			reported.add(p);
			addNumberedPerson(p);
			// Check the parents and add them to the queue
			for (FamilyChild f : p.person.familiesWhereChild) {
				Individual husband = f.family.husband;
				NumberedPerson father = new NumberedPerson(fathersNumber, husband);
				// Add the father if they haven't been seen already
				if (husband != null && !queue.contains(father)
						&& !reported.contains(father)) {
					queue.add(father);
				}
				Individual wife = f.family.wife;
				NumberedPerson mother = new NumberedPerson(mothersNumber, wife);
				// Add the mother if they haven't been seen already
				if (wife != null && !queue.contains(mother)
						&& !reported.contains(mother)) {
			
				queue.add(mother);
				}
			}
		}
		
		/**
		 * Write the birth info for the supplied person
		 * @param person
		 * the person whose birth info is being printed
		 */
		private static void addBirthInfo(Individual person) {
			List<IndividualEvent> birthDates = person
					.getEventsOfType(IndividualEventType.BIRTH);
			for (IndividualEvent bd : birthDates) {
				reportBuf.append(" was born");
				if (bd.date != null && bd.date.trim().length() > 0) {
					reportBuf.append(" " + bd.date);
				}
				if (bd.place != null && bd.place.placeName != null) {
					reportBuf.append(" in " + bd.place.placeName);
				}
				reportBuf.append(". ");
				break;
			}
		}
	    /**
	     * Write the death info for the supplied person
	     * @param person      
	     * the person whose death info is being printed
	     */
		private static void addDeathInfo(Individual person) {
		List<IndividualEvent> deathDates = person
				.getEventsOfType(IndividualEventType.DEATH);
		for (IndividualEvent bd : deathDates) {
			if (person.sex == null) {
				reportBuf.append("Died");
			}
			if (bd.date != null && bd.date.trim().length() > 0) {
				reportBuf.append(" " + bd.date);
				}
			if (bd.place != null && bd.place.placeName != null) {
				reportBuf.append(" in " + bd.place.placeName);
				}
			reportBuf.append(".");
				break;
		}
	}	
}  


