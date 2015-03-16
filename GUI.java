import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultMutableTreeNode;

import java.net.URL;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;

/*****************************************************************
GUI creates and manages the main GUI interaction of the program,
including displaying all current values and allowing the user to access
other parts of the program dynamically. Extends the default JFrame
class.
Authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * jmenubar.
	 */
	private JMenuBar menus;
	/**
	 * jmenus.
	 */
	private JMenu fileMenu, actionMenu;
	
	/**
	 * jmenu items.
	 */
	private JMenuItem loadItem, newProject;
	/**
	 * jmenu items.
	 */
	private JMenuItem exitItem, saveItem, saveAs;
	/**
	 * add a usecase to project.
	 */
	private JMenuItem addUseCase,addWord;
	/**
	 * menu items for editing saving and removing.
	 */
	private JMenuItem editUseCase, helpUseCase, removeUseCase;

	/**
	 * Frame.
	 */
	private JFrame frame;
	/**
	 * buttons.
	 */
	private JButton open, newproject, edit, delete, load;
	/**
	 * Panels.
	 */
	private JPanel panel, panel2, paneL1; 
	/**
	 * Input fields.
	 */
	private JTextPane successInput, minimalInput, alternativeFlowInput;
	/**
	 * Input fields.
	 */
	private JTextPane primaryFlowInput, preconditionsInput, triggersInput;
	/**
	 * Input fields.
	 */
	private JTextPane primActorsInput, supActorsInput, descriptionInput;
	/**
	 *  Input fields.
	 */
	private JTextPane nameInput, idInput;
	/**
	 * the project being used by user.
	 */
	private Project currentProject = new Project();
	/**
	 *  editing usecases.
	 */
	private UseCaseEditor uCE;
	/**
	 * keeping track of current usecase.
	 */
	
	private GlossaryWord glossary;
	
	private UseCase currentUseCase;
	/**
	 * array of project usecase ids.
	 */
	private Vector<String> ids;
	/**
	 * for directory remembrance.
	 */
	
	private Vector<String> terms;
	
	private String file;

	/**
	 * For getting project name.
	 */
	private CreateDialog dialog;
	/**
	 * For displaying Usecases.
	 */
	private DynamicTree dTree;
	private DefaultMutableTreeNode project,treePA,treeSA,treeUC,treeG;
	/**
	 * For loading files.
	 */
	private LoadFileBox loadFile;
	
	private JLabel label;
	
	/**
	 * main method, creates the instance of GUI.
	 * @param args
	 */
	public static void main(final String[] args) {

		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI window = new GUI();					
				window.frame.setVisible(true);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}
	);
}
	/*
	Uses intialize() to build GUI elements necessary for basic
	functionality of UseCase program
	*****************************************************************/
	public GUI() {
		initialize();
	}
	
	/*****************************************************************
	performs minimal operations for functionality of UseCase program
	*****************************************************************/
	public final void uceUtility() {
		uCE.addSaveListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				UseCase uc = uCE.getUC();
				save(uc);
			}
		});
	}
	
	public final void glossUtility() {
		glossary.addSaveListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e){
				Glossary g = glossary.getGlossaryTerm();
				save(g);
			}
			
		});
	}

	/*****************************************************************
	Builds required GUI elements for program functionality
	*****************************************************************/
	/**
	 * 
	 */
	private void initialize() {
		/** File menu */
		fileMenu = new JMenu("File");
		loadItem = new JMenuItem("Load");
		newProject = new JMenuItem("New Project");
		exitItem = new JMenuItem("Exit");
		saveItem = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		File sourceimage = new File("src/icon.png");
		Image image = null;
        try {
        	image = ImageIO.read(sourceimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		fileMenu.add(loadItem);
		fileMenu.add(newProject);
		fileMenu.add(saveItem);
		fileMenu.add(saveAs);
		fileMenu.add(exitItem);

		saveItem.addActionListener(this);
		saveAs.addActionListener(this);
		newProject.addActionListener(this);
		loadItem.addActionListener(this);
		exitItem.addActionListener(this);

		/** action menu */
		actionMenu = new JMenu("Action");
		addUseCase = new JMenuItem("New Usecase");
		addWord = new JMenuItem("Add Word to Glossary");
		removeUseCase = new JMenuItem("Remove Usecase");
		editUseCase = new JMenuItem("Edit");
		helpUseCase = new JMenuItem("Help");

		addUseCase.addActionListener(this);
		addWord.addActionListener(this);
		removeUseCase.addActionListener(this);
		editUseCase.addActionListener(this);
		helpUseCase.addActionListener(this);

		actionMenu.add(addUseCase);
		actionMenu.add(addWord);
		actionMenu.add(removeUseCase);
		actionMenu.add(editUseCase);
		actionMenu.add(helpUseCase);

		frame = new JFrame();
		frame.setBounds(new Rectangle(0,0,1366,766));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UseCase Editor - Lite");
		frame.setIconImage(image);

		
		ids = new Vector<String>();
		terms = new Vector<String>();
		menus = new JMenuBar();
		
		frame.getContentPane().add(menus, BorderLayout.NORTH);

		paneL1 = new JPanel();
		paneL1.setLayout(new BorderLayout());

		frame.getContentPane().add(paneL1, BorderLayout.WEST);

		
		///HERE POST IT
		panel2 = new JPanel();
				
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		JLabel fill2 = new JLabel();
								
		URL url = GUI.class.getResource(
		            "/resources/bg1.png");
								
								
		fill2.setIcon(new ImageIcon(url));
		fill2.setBounds(1, 0, 1366, 766);
		panel2.setLayout(null);
								
		newproject = new JButton("New Project");
										
										
		URL newProject = GUI.class.getResource(
		              "/resources/NewProject2.png");
										
		URL loadProject = GUI.class.getResource(
		               "/resources/loadproject2.png");
										
		newproject.setIcon(new ImageIcon(newProject));
		newproject.setBackground(Color.DARK_GRAY);
		newproject.setForeground(Color.BLACK);
		newproject.setBounds(450, 443, 213, 53);
		newproject.addActionListener(this);
		panel2.add(newproject);
		load = new JButton("Load Project");
		load.setIcon(new ImageIcon(loadProject));
		load.setBounds(700, 443, 213, 53);
		load.addActionListener(this);
		panel2.add(load);
		panel2.add(fill2);
		
				
		panel = new JPanel();
		panel.setVisible(true);
		initialize2();
	}
	
	/**
	 * 
	 */
	private void initialize2() {
		
		JLabel space = new JLabel();
		space.setBounds(25, 16, 0 , 0);
		JLabel space2 = new JLabel();
		
		space.setBounds(68,16,0,0);
		
		//new input text holders
		nameInput = new JTextPane();
		nameInput.setForeground(Color.WHITE);
		nameInput.setOpaque(false);
		nameInput.setBounds(123,118,502,33);
		idInput = new JTextPane();
		idInput.setForeground(Color.WHITE);
		idInput.setOpaque(false);
		idInput.setBounds(668,118,242,33);
		
		//set default display
		nameInput.setText("Name");
		idInput.setText("Id");
		
		//disable text input in main menu
		nameInput.setEditable(false);
		idInput.setEditable(false);
		
		panel.setLayout(null);

		// Create centered project and load button
		panel.add(space);
		panel.add(nameInput);
		panel.add(space2);
		panel.add(idInput);
		
		
    	minimalInput = new JTextPane();
		minimalInput.setForeground(Color.WHITE);
		minimalInput.setBorder(null);
		minimalInput.setOpaque(false);
		minimalInput.setBounds(123, 565, 434, 43);
		panel.add(minimalInput);
		minimalInput.setText("Minimal");
		minimalInput.setEditable(false);
		successInput = new JTextPane();
		successInput.setForeground(Color.WHITE);
		successInput.setBorder(null);
		successInput.setOpaque(false);
		successInput.setBounds(547, 565, 326, 43);
		panel.add(successInput);
		successInput.setText("Success");
		successInput.setEditable(false);
		alternativeFlowInput = new JTextPane();
		alternativeFlowInput.setForeground(Color.WHITE);
		alternativeFlowInput.setBorder(null);
		alternativeFlowInput.setOpaque(false);
		alternativeFlowInput.setBounds(584, 315, 415, 43);
		panel.add(alternativeFlowInput);
		alternativeFlowInput.setText("alternativeFlow");
		alternativeFlowInput.setEditable(false);
		supActorsInput = new JTextPane();
		supActorsInput.setForeground(Color.WHITE);
		supActorsInput.setBorder(null);
		supActorsInput.setOpaque(false);
		supActorsInput.setBounds(584, 252, 380, 43);
		panel.add(supActorsInput);
		supActorsInput.setText("Supporting Actors");
		supActorsInput.setEditable(false);
		primaryFlowInput = new JTextPane();
		primaryFlowInput.setForeground(Color.WHITE);
		primaryFlowInput.setBorder(null);
		primaryFlowInput.setOpaque(false);
		primaryFlowInput.setBounds(123, 315, 434, 49);
		panel.add(primaryFlowInput);
		primaryFlowInput.setText("primaryFlow");
		primaryFlowInput.setEditable(false);
		primActorsInput = new JTextPane();
		primActorsInput.setForeground(Color.WHITE);
		primActorsInput.setBorder(null);
		primActorsInput.setOpaque(false);
		primActorsInput.setBounds(123, 250, 434, 43);
		panel.add(primActorsInput);
		primActorsInput.setText("prim_actors");
		primActorsInput.setEditable(false);
		preconditionsInput = new JTextPane();
		preconditionsInput.setForeground(Color.WHITE);
		preconditionsInput.setBorder(null);
		preconditionsInput.setOpaque(false);
		preconditionsInput.setBounds(123, 471, 748, 58);
		panel.add(preconditionsInput);
		preconditionsInput.setText("preconditions");
		preconditionsInput.setEditable(false);
		preconditionsInput.setEditable(false);
		descriptionInput = new JTextPane();
		descriptionInput.setForeground(Color.WHITE);
		descriptionInput.setBorder(null);
		descriptionInput.setOpaque(false);
		descriptionInput.setBounds(123, 185, 642, 43);
		panel.add(descriptionInput);
		descriptionInput.setEditable(false);
		descriptionInput.setText("description");
		triggersInput = new JTextPane();
		triggersInput.setForeground(Color.WHITE);
		triggersInput.setOpaque(false);
		triggersInput.setBounds(123, 386, 683, 50);
		panel.add(triggersInput);
		triggersInput.setText("triggers");
		triggersInput.setText("triggers");
		triggersInput.setEditable(false);
		triggersInput.setEditable(false);
		
		label = new JLabel("Success Guearentees");
		label.setBounds(new Rectangle(0, 0, 1366, 766));
		
		URL ID = GUI.class.getResource(
                "/resources/ID.png");
		
		label.setIcon(new ImageIcon(ID));
		label.setBounds(0, -34, 1366, 766);
		panel.add(label);
		

		JPanel panel3 = new JPanel();

		edit = new JButton("Edit");
		delete = new JButton("Delete");
		open = new JButton("Open");
		open.addActionListener(this);
		edit.setVisible(false);
		delete.setVisible(false);
		panel3.add(delete);
		delete.addActionListener(this);
		edit.addActionListener(this);
	}
	
	/**
	 * Performs save operations using saveToXML method from the Project
	 * class.
	 * @param uc
	 */
	public final void save(final UseCase uc) {
		currentUseCase = uc;
		currentProject.addUsecase(currentUseCase);
		ids = currentProject.getIDs();
		currentProject.saveToXML(file);
		edit.setVisible(true);
		display();
	}
	
	public final void save(final Glossary g){
		if(!g.getWord().equals("")) {
			currentProject.addGlossaryItem(g);
			terms = currentProject.getTerms();
			currentProject.saveToXML(file);
			display();
		}
	}

	/*****************************************************************
	Creates elements required to display current values from within
	the UseCase object. Controls most of the GUI display
	present within the program.
	*****************************************************************/
	/**
	 * 
	 */
	public final void display() {
		panel.setVisible(true);
		panel2.setVisible(false);
		menus.add(fileMenu);
		menus.add(actionMenu);
		updatedtree();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridLayout glPanel = new GridLayout(11, 2);
		glPanel.setVgap(2);
		glPanel.setHgap(2);
		panel.setLayout(null);
		edit.setVisible(true);
		delete.setVisible(true);
		if (currentUseCase != null) {
			successInput.setText(currentUseCase.getSuccessGuarantees());
			minimalInput.setText(currentUseCase.getMinimalGuaruntees());
			alternativeFlowInput.setText(currentUseCase.getAlternativeflow());
			primaryFlowInput.setText(currentUseCase.getPrimaryflow());
			preconditionsInput.setText(currentUseCase.getPreconditions());
			triggersInput.setText(currentUseCase.getTriggers());
			primActorsInput.setText(currentUseCase.getPrimaryActors());
			supActorsInput.setText(currentUseCase.getSupportingActors());
			descriptionInput.setText(currentUseCase.getDescription());
			idInput.setText(currentUseCase.getID());
			nameInput.setText(currentUseCase.getName());
		}
	}

	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	 **************************************************************/
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == addUseCase) {
			uCE = new UseCaseEditor();
			uCE.setVisible(true);
			uceUtility();
		}
		
		if(e.getSource() == addWord){
			glossary = new GlossaryWord();
			glossary.setVisible(true);
			glossUtility();
		}
		
		if (e.getSource() == saveAs) {
			dialog = new CreateDialog(currentProject.getProjectName());
			file = dialog.getDirectory();
			currentProject.setProjectName(dialog.getFileName());
			ids = currentProject.getIDs();
			if (file != null) {
				currentProject.saveToXML(file);
				createtree();
				display();
			}
		}
		if (e.getSource() == newproject || e.getSource() == newProject) {
			dialog = new CreateDialog("");
			file = dialog.getDirectory();
			currentProject = new Project();
			currentProject.setProjectName(dialog.getFileName());
			ids = currentProject.getIDs();
			if (file != null) {
				currentProject.saveToXML(file);
				createtree();
				display();
			}
		}
		
		if (e.getSource() == saveItem) {
			if (currentUseCase != null) {
				save(currentUseCase);
			}
		}
		
		//try-catch block to avoid null pointer exception when pressing open
		//with nothing selected...probably should come up with a better fix
		try {
			if (e.getSource() == open && dTree.selectedUsecase() != null){
				currentUseCase = dTree.selectedUsecase();
				display();
			}
		}
		catch(Exception ex){
			return;
		}
		
		//try-catch block to avoid null pointer exception when pressing open
		//with nothing selected...probably should come up with a better fix
		try{
			if (e.getSource() == open && dTree.selectedTerm() != null) {
				
				for(int i=0; i < terms.size(); i++){
					String term = terms.get(i);
				
					if(term.equals(dTree.selectedTerm())){
						Glossary g = currentProject.getGlossaryItem(term);
						JOptionPane.showMessageDialog(frame, g.getWord() + ": " + g.getDefinition());
					}
				}
			
			}
		}
		catch(Exception ex){
			return;
		}

		if (e.getSource() == edit || e.getSource() == editUseCase) {
			uCE = new UseCaseEditor();
			if (currentUseCase != null) {
				uCE.setUC(currentUseCase);
			} else {
				UseCase uc = new UseCase();
				uCE.setUC(uc);
			}
			uCE.setVisible(true);
			uceUtility();
		}
		if (e.getSource() == exitItem) {
			frame.dispose();
		}
		if (e.getSource() == removeUseCase || e.getSource() == delete) {
			if (currentProject.removeUsecase(currentUseCase)) {
				ids = currentProject.getIDs();
				if (!ids.isEmpty()) {
					currentUseCase = currentProject.getUsecase(ids.get(0));
				} else {
					UseCase uc = new UseCase();
					currentUseCase = uc;
				}
				display();
			}
		}
		if (helpUseCase == e.getSource()) {
			JOptionPane.showMessageDialog(null,
					"For Future Usage\n Version 1.0", "Version Information",
					JOptionPane.INFORMATION_MESSAGE);

		}
		if (e.getSource() == load || e.getSource() == loadItem) {
			loadFile = new LoadFileBox();
			file = loadFile.getFileSelected();
			if (file != null) {
				currentProject = new Project();
				currentProject.loadFromXML(file);
				file = file.substring(0, file.lastIndexOf('\\'));
				ids = currentProject.getIDs();
				if (!ids.isEmpty()) {
					currentUseCase = currentProject.getUsecase(ids.get(0));
				}
				terms = currentProject.getTerms();
				createtree();
				display();
			}

		}
	}

	/**************************************************************
	 Controls dynamic updates of the tree, in order to display
	 the correct (and current) values therein.
	 **************************************************************/
	public final void updatedtree() {
		HashMap<String,DefaultMutableTreeNode> PAD = new HashMap<String,DefaultMutableTreeNode>();
		HashMap<String,DefaultMutableTreeNode> SAD = new HashMap<String,DefaultMutableTreeNode>();
		HashMap<String,DefaultMutableTreeNode> GD = new HashMap<String,DefaultMutableTreeNode>();
		dTree.clear();
		project = dTree.addObject((DefaultMutableTreeNode)null, currentProject.getProjectName(),true);
		treeUC = dTree.addObject(project, "UseCases",true);
		treePA = dTree.addObject(project, "Primary Actors",true);
		treeSA = dTree.addObject(project, "Secondary Actors",true);
		treeG = dTree.addObject(project, "Glossary",true);
		
		if (!ids.isEmpty()) {
			System.out.println(treePA.toString());
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				UseCase UC = currentProject.getUsecase(id);
				if(!SAD.containsKey(UC.getSupportingActors())){
					SAD.put(UC.getSupportingActors(),dTree.addObject(treeSA, UC.getSupportingActors()));
					dTree.addObject(SAD.get(UC.getSupportingActors()), UC);
				}else{
					dTree.addObject(SAD.get(UC.getSupportingActors()), UC);
				}
				if(!PAD.containsKey(UC.getPrimaryActors())){
					PAD.put(UC.getPrimaryActors(), dTree.addObject(treePA, UC.getPrimaryActors()));
					dTree.addObject(PAD.get(UC.getPrimaryActors()), UC);
				}else{
					dTree.addObject(PAD.get(UC.getPrimaryActors()), UC);
				}
				dTree.addObject(treeUC, UC);
			}
		}
		
		if (!terms.isEmpty()){
			for(int j = 0; j < terms.size(); j++){
				String term = terms.get(j);
				Glossary g = currentProject.getGlossaryItem(term);
				if(!GD.containsKey(g.getWord())){
					GD.put(g.getWord(), dTree.addObject(treeG, g.getWord()));
					
				}else{
					dTree.addObject(GD.get(g.getWord()), g);
				}
			}
		}
	}
	/**************************************************************
	 Creates and initalizes a custom tree for use in modifying
	 and creating UseCase elements.
	 **************************************************************/
	public final void createtree() {
		if (dTree != null) {
			paneL1.removeAll();
		}
		dTree = new DynamicTree();
		dTree.setSize(100, 200);
		updatedtree();
		paneL1.add(dTree, BorderLayout.CENTER);
		paneL1.add(open, BorderLayout.SOUTH);
	}
}
