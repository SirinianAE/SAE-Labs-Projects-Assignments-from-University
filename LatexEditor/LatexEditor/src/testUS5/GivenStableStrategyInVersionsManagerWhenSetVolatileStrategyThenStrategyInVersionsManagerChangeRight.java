/*
 * Authors
 * Name: Sirinian Aram Emmanouil
 * Name: Emmanouil Bachlitzanakis
 */


package testUS5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LatexEditorController;
import model.VersionsStrategy;
import model.VolatileStrategy;

public class GivenStableStrategyInVersionsManagerWhenSetVolatileStrategyThenStrategyInVersionsManagerChangeRight {
	private LatexEditorController controller;
	private boolean flag;

	@Before
	public void initialize() {
		controller = new LatexEditorController();
		flag = false;
	}

	@Test
	public void test() {
		VersionsStrategy actualStrategyInstance;

		createNewDocument();
		controller.enact("EnableVersionsManagement");
		controller.enact("SetStableStrategy");
		controller.enact("SetVolatileStrategy");
		actualStrategyInstance = LatexEditorController
				.getVersionManager().getStrategy();
		if(actualStrategyInstance instanceof VolatileStrategy){
			flag = true;
		}

		assertTrue(flag);
	}

	private void createNewDocument(){
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
	}
}
