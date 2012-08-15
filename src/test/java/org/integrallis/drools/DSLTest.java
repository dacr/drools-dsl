package org.integrallis.drools;

import static org.junit.Assert.assertEquals;

import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.integrallis.drools.junit.BaseDroolsTestCase;
import org.junit.Test;

public class DSLTest extends BaseDroolsTestCase {
	
    public DSLTest() {
		super("rockys_rules.dslr", "say_something.dsl");
	}

	@Test
    public void testPersonMeantTucsonRule() {
	    	Person bob = new Person("Bob", "Tooson", 35);
	    	Person jim = new Person("Jim", "Tuzzon", 25);
	    	Person charlie = new Person("Charlie", "Tucson", 44);
	    	Person fred = new Person("Fred", "too zone", 23);
	    	Person julia = new Person("Julia", "two so on", 45);
	    	Person colin = new Person("Colin", "Pasadena", 19);
	    	
	    	knowledgeSession.insert(bob);
	    	knowledgeSession.insert(jim);
	    	knowledgeSession.insert(charlie);
	    	knowledgeSession.insert(fred);
	    	knowledgeSession.insert(julia);
	    	knowledgeSession.insert(colin);
	    	
	    	knowledgeSession.fireAllRules();
    	
		QueryResults results = knowledgeSession.getQueryResults( "Get all Messages" );
		assertEquals(4, results.size());
		for ( QueryResultsRow row : results ) {
			Message message = (Message) row.get( "message" );
			assertEquals("You probably meant Tucson", message.getMessage());
		}
    }
}