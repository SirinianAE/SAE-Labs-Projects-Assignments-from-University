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
import model.StableStrategy;
import model.VersionsStrategy;

public class GivenVolatileStrategyInVersionsManagerWhenSetStableStrategyThenStrategyInVersionsManagerChangeRight {
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
		controller.enact("SetVolatileStrategy");
		controller.enact("SetStableStrategy");
		actualStrategyInstance = LatexEditorController
				.getVersionManager().getStrategy();
		if(actualStrategyInstance instanceof StableStrategy){
			flag = true;
		}

		assertTrue(flag);
	}

	private void createNewDocument(){
		controller.setTemplateID("book");
		controller.enact("CreateNewDocument");
	}
}
