/*
 * Authors
 * Name: Emmanouil Bachlitzanakis
 * Name: Sirinian Aram Emmanouil
 */


package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import controller.LatexEditorController;

public class LatexEditorView {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel templatePanel;
	private JPanel fullPathPanel;
	private JScrollPane scrollPane;
	LatexEditorController controller = new LatexEditorController();
	private String textToBeReplaced = "<-->";
	private JTextArea latexEditorTextArea;
	private JButton rollbackButton;
	private JTextArea fullPathTextArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LatexEditorView window = new LatexEditorView();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LatexEditorView() {
		initialize();
	}

	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBackground(Color.GRAY);
		mainFrame.setBounds(100, 100, 800, 492);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));

		mainPanel = new JPanel();
		mainFrame.getContentPane().add(mainPanel,
				"name_19908697302727");
		mainPanel.setVisible(false);

		templatePanel = new JPanel();
		templatePanel.setBackground(Color.GRAY);
		templatePanel.setForeground(Color.BLACK);
		mainFrame.getContentPane().add(templatePanel,
				"name_19915551712854");
		templatePanel.setLayout(null);
		templatePanel.setVisible(false);

		fullPathPanel = new JPanel();
		fullPathPanel.setBackground(Color.GRAY);
		mainFrame.getContentPane().add(fullPathPanel,
				"name_1301018656570");
		fullPathPanel.setLayout(null);
		fullPathPanel.setVisible(true);

		GridBagLayout mainPanelGridBagLayout =
				createMainPanelGridBagLayout();
		mainPanel.setLayout(mainPanelGridBagLayout);
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints menuBarGridBagConstraints =
				createMenuBarGridBagConstraints();
		JMenu fileMenu = createFileMenu();
		JMenuItem newMenuItem = createNewMenuItem();
		latexEditorTextArea = new JTextArea();
		JMenuItem loadMenuItem = createLoadMenuItem();
		JMenuItem saveMenuItem = createSaveMenuItem();
		JMenu editMenu = createEditMenu();
		JMenuItem addTableMenuItem = createAddTableMenuItem();
		JMenuItem addFigureMenuItem = createAddFigureMenuItem();
		JMenuItem addSubsectionMenuItem =
				createAddSubsectionMenuItem();
		JMenuItem addSectionMenuItem = createAddSectionMenuItem();
		JMenu addEnumerationListMenu = createAddEnumerationListMenu();
		JMenuItem addEnumerationListItemizeMenuItem =
				createAddEnumerationListItemizeMenuItem();
		JMenuItem addEnumerationListEnumerateMenuItem =
				createAddEnumerationListEnumerateMenuItem();
		JMenuItem addSubsubsectionMenuItem =
				createAddSubsubsectionMenuItem();
		JMenuItem addChapterMenuItem = createAddChapterMenuItem();
		latexEditorTextArea
			.addKeyListener(createEditDocumentKeyAdapter());
		JMenu versionsMenu = createVersionsMenu();
		rollbackButton = createRollbackButton();
		JMenuItem volatileStrategyMenuItem =
				createVolatileStrategyMenuItem();
		JMenuItem stableStrategyMenuItem =
				createStableStrategyMenuItem();
		JMenuItem disableVersionsManagementMenuItem =
				createDisableVersionsManagementMenuItem();
		latexEditorTextArea.setBounds(0, 0, 784, 429);
		scrollPane = createScrollPane();
		GridBagConstraints scrollPaneGridBagContraints =
				createScrollPaneGridBagContraints();
		JButton articleButton = createArticleButton();
		JButton reportButton = createReportButton();
		JButton letterButton = createLetterButton();
		JButton bookButton = createBookButton();
		JButton skipButton = createSkipButton();
		JLabel chooseTemplateLabel = createChooseTemplateLabel();
		fullPathTextArea = createFullPathTextArea();
		JButton fullPathEnterButton = createFullPathEnterButton();
		JLabel fullPathLabel = createFullPathLabel();

		mainPanel.add(menuBar, menuBarGridBagConstraints);
		mainPanel.add(scrollPane, scrollPaneGridBagContraints);
		templatePanel.add(articleButton);
		templatePanel.add(reportButton);
		templatePanel.add(letterButton);
		templatePanel.add(bookButton);
		templatePanel.add(skipButton);
		templatePanel.add(chooseTemplateLabel);
		fullPathPanel.add(fullPathTextArea);
		fullPathPanel.add(fullPathEnterButton);
		fullPathPanel.add(fullPathLabel);

		menuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(editMenu);
		editMenu.add(addSectionMenuItem);
		editMenu.add(addSubsectionMenuItem);
		editMenu.add(addSubsubsectionMenuItem);
		editMenu.add(addChapterMenuItem);
		editMenu.add(addTableMenuItem);
		editMenu.add(addEnumerationListMenu);
		addEnumerationListMenu
			.add(addEnumerationListItemizeMenuItem);
		addEnumerationListMenu
			.add(addEnumerationListEnumerateMenuItem);
		editMenu.add(addFigureMenuItem);
		menuBar.add(versionsMenu);
		versionsMenu.add(volatileStrategyMenuItem);
		versionsMenu.add(stableStrategyMenuItem);
		versionsMenu.add(disableVersionsManagementMenuItem);
		menuBar.add(rollbackButton);
	}

	private GridBagLayout createMainPanelGridBagLayout(){
		GridBagLayout mainPanelGridBagLayout = new GridBagLayout();
		mainPanelGridBagLayout.columnWidths = new int[]{784, 0};
		mainPanelGridBagLayout.rowHeights = new int[]{26, 427, 0};
		mainPanelGridBagLayout.columnWeights = new double[]{1.0,
				Double.MIN_VALUE};
		mainPanelGridBagLayout.rowWeights = new double[]{0.0, 1.0,
				Double.MIN_VALUE};
		return mainPanelGridBagLayout;
	}

	private GridBagConstraints createMenuBarGridBagConstraints(){
		GridBagConstraints menuBarGridBagConstraints =
				new GridBagConstraints();
		menuBarGridBagConstraints.fill = GridBagConstraints.BOTH;
		menuBarGridBagConstraints.insets = new Insets(0, 0, 5, 0);
		menuBarGridBagConstraints.gridx = 0;
		menuBarGridBagConstraints.gridy = 0;
		return menuBarGridBagConstraints;
	}

	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
		return fileMenu;
	}

	private JMenuItem createNewMenuItem(){
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		newMenuItem
			.addActionListener(createNewMenuItemActionListener());
		return newMenuItem;
	}

	private JMenuItem createLoadMenuItem(){
		JMenuItem loadMenuItem = new JMenuItem("Load");
		loadMenuItem
			.addActionListener(createLoadMenuItemActionListener());
		loadMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		return loadMenuItem;
	}

	private JMenuItem createSaveMenuItem(){
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		saveMenuItem
			.addActionListener(createSaveMenuItemActionListener());
		return saveMenuItem;
	}

	private JMenu createEditMenu(){
		JMenu editMenu = new JMenu("Edit");
		editMenu.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
		return editMenu;
	}

	private JMenuItem createAddTableMenuItem(){
		JMenuItem addTableMenuItem = new JMenuItem("Add Table");
		addTableMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addTableMenuItem
			.addActionListener(
					createAddTableMenuItemActionListener());
		return addTableMenuItem;
	}

	private JMenuItem createAddFigureMenuItem(){
		JMenuItem addFigureMenuItem = new JMenuItem("Add Figure");
		addFigureMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addFigureMenuItem
			.addActionListener(
					createAddFigureMenuItemActionListener());
		return addFigureMenuItem;
	}

	private JMenuItem createAddSubsectionMenuItem(){
		JMenuItem addSubsectionMenuItem =
				new JMenuItem("Add Subsection");
		addSubsectionMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addSubsectionMenuItem
			.addActionListener(
					createAddSubsectionMenuItemActionListener());
		return addSubsectionMenuItem;
	}

	private JMenuItem createAddSectionMenuItem(){
		JMenuItem addSectionMenuItem = new JMenuItem("Add Section");
		addSectionMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addSectionMenuItem
			.addActionListener(
					createAddSectionMenuItemActionListener());
		return addSectionMenuItem;
	}

	private JMenu createAddEnumerationListMenu(){
		JMenu addEnumerationListMenu =
				new JMenu("Add Enumeration List ");
		addEnumerationListMenu
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		return addEnumerationListMenu;
	}

	private JMenuItem createAddEnumerationListItemizeMenuItem(){
		JMenuItem addEnumerationListItemizeMenuItem =
				new JMenuItem("Dots");
		addEnumerationListItemizeMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addEnumerationListItemizeMenuItem
			.addActionListener(
			createAddEnumerationListItemizeMenuItemActionListener());
		return addEnumerationListItemizeMenuItem;
	}

	private JMenuItem createAddEnumerationListEnumerateMenuItem(){
		JMenuItem addEnumerationListEnumerateMenuItem =
				new JMenuItem("Numbers");
		addEnumerationListEnumerateMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addEnumerationListEnumerateMenuItem
			.addActionListener(
			createAddEnumerationListEnumerateMenuItemActionListener(
			));
		return addEnumerationListEnumerateMenuItem;
	}

	private JMenuItem createAddSubsubsectionMenuItem(){
		JMenuItem addSubsubsectionMenuItem =
				new JMenuItem("Add Subsubsection");
		addSubsubsectionMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addSubsubsectionMenuItem
			.addActionListener(
			createAddSubsubsectionMenuItemActionListener());
		return addSubsubsectionMenuItem;
	}

	private JMenuItem createAddChapterMenuItem(){
		JMenuItem addChapterMenuItem = new JMenuItem("Add Chapter");
		addChapterMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		addChapterMenuItem
			.addActionListener(
					createAddChapterMenuItemActionListener());
		return addChapterMenuItem;
	}

	private JMenu createVersionsMenu(){
		JMenu versionsMenu = new JMenu("Versions");
		versionsMenu
			.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
		return versionsMenu;
	}

	private JButton createRollbackButton(){
		JButton rollbackButton = new JButton("Undo Typing");
		rollbackButton
			.addActionListener(createRollbackButtonActionListener());
		rollbackButton.setVisible(false);
		return rollbackButton;
	}

	private JMenuItem createVolatileStrategyMenuItem(){
		JMenuItem volatileStrategyMenuItem =
				new JMenuItem("Volatile");
		volatileStrategyMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		volatileStrategyMenuItem
			.addActionListener(
			createVolatileStrategyMenuItemActionListener());
		return volatileStrategyMenuItem;
	}

	private JMenuItem createStableStrategyMenuItem(){
		JMenuItem stableStrategyMenuItem = new JMenuItem("Stable");
		stableStrategyMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		stableStrategyMenuItem
			.addActionListener(
			createStableStrategyMenuItemActionListener());
		return stableStrategyMenuItem;
	}

	private JMenuItem createDisableVersionsManagementMenuItem(){
		JMenuItem disableVersionsManagementMenuItem =
				new JMenuItem("Disable");
		disableVersionsManagementMenuItem
			.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		disableVersionsManagementMenuItem
			.addActionListener(
			createDisableVersionsManagementMenuItemActionListener());
		return disableVersionsManagementMenuItem;
	}

	private JScrollPane createScrollPane(){
		JScrollPane scrollPane = new JScrollPane(latexEditorTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVisible(true);
		return scrollPane;
	}

	private GridBagConstraints createScrollPaneGridBagContraints(){
		GridBagConstraints scrollPaneGridBagContraints =
				new GridBagConstraints();
		scrollPaneGridBagContraints.fill = GridBagConstraints.BOTH;
		scrollPaneGridBagContraints.gridx = 0;
		scrollPaneGridBagContraints.gridy = 1;
		return scrollPaneGridBagContraints;
	}

	private JButton createArticleButton(){
		JButton articleButton = new JButton("Article");
		articleButton
			.addActionListener(createArticleButtonActionListener());
		articleButton
			.setFont(
			new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		articleButton.setBounds(316, 79, 97, 25);
		return articleButton;
	}

	private JButton createReportButton(){
		JButton reportButton = new JButton("Report");
		reportButton
			.addActionListener(createReportButtonActionListener());
		reportButton
			.setFont(
			new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		reportButton.setBounds(316, 115, 97, 25);
		return reportButton;
	}

	private JButton createLetterButton(){
		JButton letterButton = new JButton("Letter");
		letterButton
			.addActionListener(createLetterButtonActionListener());
		letterButton
			.setFont(
			new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		letterButton.setBounds(316, 149, 97, 25);
		return letterButton;
	}

	private JButton createBookButton(){
		JButton bookButton = new JButton("Book");
		bookButton
			.addActionListener(createBookButtonActionListener());
		bookButton
			.setFont(
			new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		bookButton.setBounds(316, 185, 97, 25);
		return bookButton;
	}

	private JButton createSkipButton(){
		JButton skipButton = new JButton("Skip");
		skipButton
			.addActionListener(createSkipButtonActionListener());
		skipButton.setFont(new Font("Stencil", Font.PLAIN, 13));
		skipButton.setBounds(316, 221, 97, 25);
		return skipButton;
	}

	private JLabel createChooseTemplateLabel(){
		JLabel chooseTemplateLabel = new JLabel("Template:");
		chooseTemplateLabel.setForeground(Color.BLACK);
		chooseTemplateLabel
			.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		chooseTemplateLabel.setBounds(142, 39, 97, 31);
		return chooseTemplateLabel;
	}

	private JTextArea createFullPathTextArea(){
		JTextArea fullPathTextArea = new JTextArea();
		fullPathTextArea.setBounds(207, 207, 236, 22);
		return fullPathTextArea;
	}

	private JButton createFullPathEnterButton(){
		JButton fullPathEnterButton = new JButton("Enter");
		fullPathEnterButton
			.addActionListener(
			createFullPathEnterButtonActionListener());
		fullPathEnterButton.setBounds(513, 208, 89, 23);
		return fullPathEnterButton;
	}

	private JLabel createFullPathLabel(){
		JLabel fullPathLabel = new JLabel("File name (full path):");
		fullPathLabel
			.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		fullPathLabel.setBounds(205, 140, 154, 41);
		return fullPathLabel;
	}

	private ActionListener createNewMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fullPathPanel.setVisible(true);
				mainPanel.setVisible(false);
				templatePanel.setVisible(false);
			}
		};
	}

	private ActionListener createLoadMenuItemActionListener(){
		return new ActionListener() {
			private File f;

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				f = chooser.getSelectedFile();
				if(f != null){
					String filename = f.getAbsolutePath();
					controller.setFilePath(filename);
					controller.enact("LoadDocument");
					latexEditorTextArea
						.setText(LatexEditorController.getDocument()
						.getContents());
				}
			}
		};
	}

	private ActionListener createSaveMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.enact("SaveDocument");
			}
		};
	}

	private ActionListener createAddTableMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddTable");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createAddFigureMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddFigure");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());

			}
		};
	}

	private ActionListener
	createAddSubsectionMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddSubsection");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createAddSectionMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddSection");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener
	createAddEnumerationListItemizeMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID(
						"AddEnumerationListItemize");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener
	createAddEnumerationListEnumerateMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID(
						"AddEnumerationListEnumerate");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(
						LatexEditorController.getDocument()
						.getContents());
			}
		};
	}

	private ActionListener
	createAddSubsubsectionMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddSubsubsection");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createAddChapterMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latexEditorTextArea.replaceSelection("");
				latexEditorTextArea.insert(textToBeReplaced,
						latexEditorTextArea.getCaretPosition());
				String newContents = latexEditorTextArea.getText();
				controller.setTextToBeReplaced(textToBeReplaced);
				controller.setContents(newContents);
				controller.setLatexCommandID("AddChapter");
				controller.enact("AddLatex");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private KeyAdapter createEditDocumentKeyAdapter(){
		return new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent e) {
				String inputS = latexEditorTextArea.getText();
				controller.setDocumentContents(inputS);
				controller.enact("EditDocument");
			}
		};
	}

	private ActionListener createRollbackButtonActionListener(){
		String messageWhenRollbackToPreviousVersionIsNotPossible =
				"VersionsManager is disabled, rollback can't be done.";

		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.enact("RollbackToPreviousVersion");
				if(controller.isRollbackToPreviousVersionPossible()){
					latexEditorTextArea.setText(LatexEditorController
							.getDocument().getContents());
				}else{
					JOptionPane.showMessageDialog(mainPanel,
					messageWhenRollbackToPreviousVersionIsNotPossible);
				}

			}
		};
	}

	private ActionListener
	createVolatileStrategyMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.enact("EnableVersionsManagement");
				controller.enact("SetVolatileStrategy");
				rollbackButton.setVisible(true);
				String inputS = latexEditorTextArea.getText();
				controller.setDocumentContents(inputS);
				controller.enact("EditDocument");
			}
		};
	}

	private ActionListener
	createStableStrategyMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.enact("EnableVersionsManagement");
				controller.enact("SetStableStrategy");
				rollbackButton.setVisible(true);
				String inputS = latexEditorTextArea.getText();
				controller.setDocumentContents(inputS);
				controller.enact("EditDocument");
			}
		};
	}

	private ActionListener
	createDisableVersionsManagementMenuItemActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.enact("DisableVersionsManagement");
				rollbackButton.setVisible(false);
			}
		};
	}

	private ActionListener createArticleButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				templatePanel.setVisible(false);
				controller.setTemplateID("article");
				controller.enact("CreateNewDocument");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createReportButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				templatePanel.setVisible(false);
				controller.setTemplateID("report");
				controller.enact("CreateNewDocument");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createLetterButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				templatePanel.setVisible(false);
				controller.setTemplateID("letter");
				controller.enact("CreateNewDocument");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createBookButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				templatePanel.setVisible(false);
				controller.setTemplateID("book");
				controller.enact("CreateNewDocument");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createSkipButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				templatePanel.setVisible(false);
				controller.setTemplateID("blank");
				controller.enact("CreateNewDocument");
				latexEditorTextArea.setText(LatexEditorController
						.getDocument().getContents());
			}
		};
	}

	private ActionListener createFullPathEnterButtonActionListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(false);
				fullPathPanel.setVisible(false);
				templatePanel.setVisible(true);
				String savaki = fullPathTextArea.getText();
				controller.setFilePath(savaki);
			}
		};
	}
}
