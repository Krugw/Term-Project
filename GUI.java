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
import java.util.ArrayList;
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
	private JMenuItem addUseCase,addWord,addActorItem,delActorItem;
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
	private JPanel panel, panel2, paneL1, panel3;; 
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
	
	//private UseCase uc = new UseCase();
	/**
	 * array of project usecase ids.
	 */
	private Vector<String> ids;
	/**
	 * for directory remembrance.
	 */
	
	private Vector<String> terms;

    private ArrayList<String> pAct = new ArrayList<String>();
	
	private String file;

	/**
	 * For getting project name.
	 */
	  private CreateDialog dialog;
	  private CreateActorDialog dialog2;
	  private AddActor DeleteActor;
	/**
	 * For displaying Usecases.
	 */
	private DynamicTree dTree;
	private DefaultMutableTreeNode project,treePA,treeUC,treeG;
	/**
	 * For loading files.
	 */
	private LoadFileBox loadFile;
	
	private JLabel label;
	
    private JLabel labelt;
    private JScrollPane DESCRIPTIONSCROLL;
    private JScrollPane PrimActors;
    private JScrollPane SupActors;
    private JScrollPane PrimFlow;
    private JScrollPane AltFlow;
    private JScrollPane TriggerScroll;
    private JScrollPane PreconditionsScroll;
    private JScrollPane SuccessScroll;
    private JScrollPane MinimalScroll;
	
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
    	if(!uCE.getflag()){
            currentProject.setActors(uCE.getActors());
            System.out.println("no");
    	}
            save(uCE.getUC());
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
		//File sourceimage = new File("src/icon.png");
		//Image image = null;
        //try {
        //	image = ImageIO.read(sourceimage);
		//} catch (IOException e) {
			// TO Auto-generated catch block
		//	e.printStackTrace();
		//}
        
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
		addActorItem = new JMenuItem("New Actor");
        delActorItem = new JMenuItem("Delete Actor");
		addWord = new JMenuItem("Add Word to Glossary");
		removeUseCase = new JMenuItem("Remove Usecase");
		editUseCase = new JMenuItem("Edit");
		helpUseCase = new JMenuItem("Help");

		addActorItem.addActionListener(this);
        delActorItem.addActionListener(this);
		addUseCase.addActionListener(this);
		addWord.addActionListener(this);
		removeUseCase.addActionListener(this);
		editUseCase.addActionListener(this);
		helpUseCase.addActionListener(this);

		actionMenu.add(addUseCase);
		actionMenu.add(addWord);
		actionMenu.add(addActorItem);
        actionMenu.add(delActorItem);
		actionMenu.add(removeUseCase);
		actionMenu.add(editUseCase);
		actionMenu.add(helpUseCase);

		frame = new JFrame();
		frame.setBounds(new Rectangle(0,0,1366,766));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UseCase Editor - Lite");
		//frame.setIconImage(image);

		
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
		initialize2();
	}
	
	/**
	 * 
	 */
	private void initialize2() {

        panel.setLayout(null);
        
        AltFlow = new JScrollPane();
        AltFlow.setOpaque(false);
        AltFlow.setBorder(null);
        AltFlow.setBounds(833, 133, 342, 213);
        panel.add(AltFlow);
        alternativeFlowInput = new JTextPane();
        AltFlow.setViewportView(alternativeFlowInput);
        alternativeFlowInput.setForeground(Color.WHITE);
        alternativeFlowInput.setBorder(null);
        alternativeFlowInput.setOpaque(false);
        alternativeFlowInput.setText("alternativeFlow");
        alternativeFlowInput.setEditable(false);
        
        PrimFlow = new JScrollPane();
        PrimFlow.setOpaque(false);
        PrimFlow.setBorder(null);
        PrimFlow.setBounds(490, 133, 316, 213);
        panel.add(PrimFlow);
        primaryFlowInput = new JTextPane();
        PrimFlow.setViewportView(primaryFlowInput);
        primaryFlowInput.setForeground(Color.WHITE);
        primaryFlowInput.setBorder(null);
        primaryFlowInput.setOpaque(false);
        primaryFlowInput.setText("primaryFlow");
        primaryFlowInput.setEditable(false);
        
        SupActors = new JScrollPane();
        SupActors.setOpaque(false);
        SupActors.setBorder(null);
        SupActors.setBounds(255, 133, 202, 213);
        panel.add(SupActors);
        supActorsInput = new JTextPane();
        SupActors.setViewportView(supActorsInput);
        supActorsInput.setForeground(Color.WHITE);
        supActorsInput.setBorder(null);
        supActorsInput.setOpaque(false);
        supActorsInput.setText("Supporting Actors");
        supActorsInput.setEditable(false);
        
        PrimActors = new JScrollPane();
        PrimActors.setOpaque(false);
        PrimActors.setBorder(null);
        PrimActors.setBounds(23, 133, 202, 213);
        panel.add(PrimActors);
        primActorsInput = new JTextPane();
        PrimActors.setViewportView(primActorsInput);
        primActorsInput.setForeground(Color.WHITE);
        primActorsInput.setBorder(null);
        primActorsInput.setOpaque(false);
        primActorsInput.setText("prim_actors");
        primActorsInput.setEditable(false);
        
        DESCRIPTIONSCROLL = new JScrollPane();
        DESCRIPTIONSCROLL.setOpaque(false);
        DESCRIPTIONSCROLL.setBorder(null);
        DESCRIPTIONSCROLL.setViewportView(descriptionInput);
        DESCRIPTIONSCROLL.setBounds(700, 48, 475, 44);
        panel.add(DESCRIPTIONSCROLL);
        descriptionInput = new JTextPane();
        DESCRIPTIONSCROLL.setViewportView(descriptionInput);
        descriptionInput.setForeground(Color.WHITE);
        descriptionInput.setBorder(null);
        descriptionInput.setOpaque(false);
        descriptionInput.setEditable(false);
        descriptionInput.setText("description");
        
        PreconditionsScroll = new JScrollPane();
        PreconditionsScroll.setOpaque(false);
        PreconditionsScroll.setBorder(null);
        PreconditionsScroll.setBounds(329, 432, 262, 207);
        panel.add(PreconditionsScroll);
        preconditionsInput = new JTextPane();
        PreconditionsScroll.setViewportView(preconditionsInput);
        preconditionsInput.setForeground(Color.WHITE);
        preconditionsInput.setBorder(null);
        preconditionsInput.setOpaque(false);
        preconditionsInput.setText("preconditions");
        preconditionsInput.setEditable(false);

        
        JScrollPane IDSCROLL = new JScrollPane();
        IDSCROLL.setOpaque(false);
        IDSCROLL.setBorder(null);
        IDSCROLL.setBounds(360, 46, 316, 44);
        panel.add(IDSCROLL);
        idInput = new JTextPane();
        IDSCROLL.setViewportView(idInput);
        idInput.setForeground(Color.WHITE);
        idInput.setOpaque(false);
        idInput.setText("Id");
        idInput.setEditable(false);
        
        TriggerScroll = new JScrollPane();
        TriggerScroll.setOpaque(false);
        TriggerScroll.setBorder(null);
        TriggerScroll.setBounds(23, 426, 268, 213);
        panel.add(TriggerScroll);
        triggersInput = new JTextPane();
        TriggerScroll.setViewportView(triggersInput);
        triggersInput.setForeground(Color.WHITE);
        triggersInput.setOpaque(false);
        triggersInput.setText("triggers");
        triggersInput.setText("triggers");
        triggersInput.setEditable(false);

        SuccessScroll = new JScrollPane();
        SuccessScroll.setOpaque(false);
        SuccessScroll.setBorder(null);
        SuccessScroll.setBounds(617, 432, 277, 207);
        panel.add(SuccessScroll);
        successInput = new JTextPane();
        SuccessScroll.setViewportView(successInput);
        successInput.setForeground(Color.WHITE);
        successInput.setBorder(null);
        successInput.setOpaque(false);
        successInput.setText("Success");
        successInput.setEditable(false);
        
        JScrollPane NameScroll = new JScrollPane();
        NameScroll.setBounds(24, 46, 316, 44);
        panel.add(NameScroll);
                NameScroll.setBorder(null);
                NameScroll.setOpaque(false);
                
                        //new input text holders
                        nameInput = new JTextPane();
                        NameScroll.setViewportView(nameInput);
                        nameInput.setForeground(Color.WHITE);
                        nameInput.setOpaque(false);
                        
                                //set default display
                                nameInput.setText("Name");
                                
                                        //disable text input in main menu
                                        nameInput.setEditable(false);
                NameScroll.getViewport().setOpaque(false);
                IDSCROLL.getViewport().setOpaque(false);
                DESCRIPTIONSCROLL.getViewport().setOpaque(false);
                PrimActors.getViewport().setOpaque(false);
                SupActors.getViewport().setOpaque(false);
                PrimFlow.getViewport().setOpaque(false);
                AltFlow.getViewport().setOpaque(false);
                TriggerScroll.getViewport().setOpaque(false);
                PreconditionsScroll.getViewport().setOpaque(false);
                SuccessScroll.getViewport().setOpaque(false);

      
        
        MinimalScroll = new JScrollPane();
        MinimalScroll.setOpaque(false);
        MinimalScroll.setBorder(null);
        MinimalScroll.setBounds(910, 432, 276, 207);
        panel.add(MinimalScroll);
        
        MinimalScroll.getViewport().setOpaque(false);


        minimalInput = new JTextPane();
        MinimalScroll.setViewportView(minimalInput);
        minimalInput.setForeground(Color.WHITE);
        minimalInput.setBorder(null);
        minimalInput.setOpaque(false);
        minimalInput.setText("Minimal");
        minimalInput.setEditable(false);
        
        labelt = new JLabel("");
        labelt.setBounds(new Rectangle(0, 0, 1366, 766));

        URL iD = GUI.class.getResource(
                "/resources/ID.png");
        
        URL editmode = GUI.class.getResource(
                "/resources/editmode.png");
        labelt.setIcon(new ImageIcon(editmode));
        labelt.setBounds(0, -34, 1366, 766);
        
                label = new JLabel("");
                label.setBounds(new Rectangle(0, 0, 1366, 766));
                
                        label.setIcon(new ImageIcon(iD));
                        label.setBounds(0, -34, 1366, 766);
                        panel.add(label);



  

        edit = new JButton("Edit");
        delete = new JButton("Delete");
        open = new JButton("Open");
        open.addActionListener(this);
        edit.setVisible(false);
        delete.setVisible(false);

        
        
        
        
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
			primActorsInput.setText(currentUseCase.getPrimString());
            supActorsInput.setText(currentUseCase.getSupString());
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
			uCE = new UseCaseEditor(currentProject.getActors());
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
        if(e.getSource() == addActorItem){
        	dialog2 = new CreateActorDialog();
            addActor(dialog2.getActorName(),dialog2.getActorDescription());
            display();
        }
        if(e.getSource() == delActorItem){
        	DeleteActor = new AddActor(currentProject.getActors());
        	deleteActor(DeleteActor.getdeleteActor());
            display();
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
						
						GlossaryDefine glossary2 = new GlossaryDefine(g.getWord(), g.getDefinition());
						glossary2.setVisible(true);
						glossUtility();
					}
				}
			
			}
		}
		catch(Exception ex){
			return;
		}

		if (e.getSource() == edit || e.getSource() == editUseCase) {
			uCE = new UseCaseEditor(currentProject.getActors());
            if (currentUseCase != null) {
            	pAct.clear();
            	pAct.addAll(currentUseCase.getPrimaryActors());
                uCE.setUC(currentUseCase,currentProject.getActors());
            } else {
                UseCase uc = new UseCase();
                uCE.setUC(uc,currentProject.getActors());
            }
            uCE.setVisible(true);
            uceUtility();
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
    public void addActor(String n, String d){
    	if(n != ""){
    	Actor actor = new Actor(n,d);
    	currentProject.addActor(actor);
    	}
    }
    public void deleteActor(Actor a){
    	currentProject.deleteActor(a);
    }
	/**************************************************************
	 Controls dynamic updates of the tree, in order to display
	 the correct (and current) values therein.
	 **************************************************************/
	public final void updatedtree() {
		HashMap<String, DefaultMutableTreeNode> pad = new HashMap<
                String, DefaultMutableTreeNode>();
        HashMap<String, DefaultMutableTreeNode> GD = new HashMap<
                String, DefaultMutableTreeNode>();
        dTree.clear();
        project = dTree.addObject((DefaultMutableTreeNode
                ) null, currentProject.getProjectName(), true);
        treeUC = dTree.addObject(project, "UseCases", true);
        treePA = dTree.addObject(project, "Actors", true);
        treeG = dTree.addObject(project, "Glossary", true);
		for(Actor a: currentProject.getActors()){
    		pad.put(a.getName(), dTree.addObject(treePA,
            a.getName()));
		}
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
                String id = ids.get(i);
                UseCase uc = currentProject.getUsecase(id);
                ArrayList<String> SA = uc.getSupportingActors();
                for(String sa: SA){
                	if (pad.containsKey(sa)) {
                		dTree.addObject(pad.get(sa), uc);
                	}
                }
                ArrayList<String> PA = uc.getPrimaryActors();
                for(String pa: PA){
                if (pad.containsKey(pa)) {
                	if(!SA.contains(pa)){
                    dTree.addObject(pad.get(pa), uc);
                	}
                }
                }
                dTree.addObject(treeUC, uc);
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
