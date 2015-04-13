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
	
    private JLabel labelt;
    private JScrollPane DESCRIPTIONSCROLL;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JScrollPane scrollPane_4;
    private JScrollPane scrollPane_5;
    private JScrollPane scrollPane_6;
    private JScrollPane scrollPane_7;
    private JScrollPane scrollPane_8;
    private JButton Edit;
    private JButton Save;
    private JButton btnNewButton;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
	
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
        space.setBounds(25, 16, 0, 0);
        JLabel space2 = new JLabel();

        space.setBounds(68, 16, 0, 0);

        panel.setLayout(null);
        
        scrollPane_4 = new JScrollPane();
        scrollPane_4.setOpaque(false);
        scrollPane_4.setBorder(null);
        scrollPane_4.setBounds(833, 133, 342, 213);
        panel.add(scrollPane_4);
        alternativeFlowInput = new JTextPane();
        scrollPane_4.setViewportView(alternativeFlowInput);
        alternativeFlowInput.setForeground(Color.WHITE);
        alternativeFlowInput.setBorder(null);
        alternativeFlowInput.setOpaque(false);
        alternativeFlowInput.setText("alternativeFlow");
        alternativeFlowInput.setEditable(false);
        
        scrollPane_3 = new JScrollPane();
        scrollPane_3.setOpaque(false);
        scrollPane_3.setBorder(null);
        scrollPane_3.setBounds(490, 133, 316, 213);
        panel.add(scrollPane_3);
        primaryFlowInput = new JTextPane();
        scrollPane_3.setViewportView(primaryFlowInput);
        primaryFlowInput.setForeground(Color.WHITE);
        primaryFlowInput.setBorder(null);
        primaryFlowInput.setOpaque(false);
        primaryFlowInput.setText("primaryFlow");
        primaryFlowInput.setEditable(false);
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setOpaque(false);
        scrollPane_2.setBorder(null);
        scrollPane_2.setBounds(255, 133, 202, 213);
        panel.add(scrollPane_2);
        supActorsInput = new JTextPane();
        scrollPane_2.setViewportView(supActorsInput);
        supActorsInput.setForeground(Color.WHITE);
        supActorsInput.setBorder(null);
        supActorsInput.setOpaque(false);
        supActorsInput.setText("Supporting Actors");
        supActorsInput.setEditable(false);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setOpaque(false);
        scrollPane_1.setBorder(null);
        scrollPane_1.setBounds(23, 133, 202, 213);
        panel.add(scrollPane_1);
        primActorsInput = new JTextPane();
        scrollPane_1.setViewportView(primActorsInput);
        primActorsInput.setForeground(Color.WHITE);
        primActorsInput.setBorder(null);
        primActorsInput.setOpaque(false);
        primActorsInput.setText("prim_actors");
        primActorsInput.setEditable(false);

        // Create centered project and load button
        panel.add(space);
        panel.add(space2);
        
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
        
        scrollPane_6 = new JScrollPane();
        scrollPane_6.setOpaque(false);
        scrollPane_6.setBorder(null);
        scrollPane_6.setBounds(329, 432, 262, 207);
        panel.add(scrollPane_6);
        preconditionsInput = new JTextPane();
        scrollPane_6.setViewportView(preconditionsInput);
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
        
        scrollPane_5 = new JScrollPane();
        scrollPane_5.setOpaque(false);
        scrollPane_5.setBorder(null);
        scrollPane_5.setBounds(23, 426, 268, 213);
        panel.add(scrollPane_5);
        triggersInput = new JTextPane();
        scrollPane_5.setViewportView(triggersInput);
        triggersInput.setForeground(Color.WHITE);
        triggersInput.setOpaque(false);
        triggersInput.setText("triggers");
        triggersInput.setText("triggers");
        triggersInput.setEditable(false);

        scrollPane_7 = new JScrollPane();
        scrollPane_7.setOpaque(false);
        scrollPane_7.setBorder(null);
        scrollPane_7.setBounds(617, 432, 277, 207);
        panel.add(scrollPane_7);
        successInput = new JTextPane();
        scrollPane_7.setViewportView(successInput);
        successInput.setForeground(Color.WHITE);
        successInput.setBorder(null);
        successInput.setOpaque(false);
        successInput.setText("Success");
        successInput.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 46, 316, 44);
        panel.add(scrollPane);
                scrollPane.setBorder(null);
                scrollPane.setOpaque(false);
                
                        //new input text holders
                        nameInput = new JTextPane();
                        scrollPane.setViewportView(nameInput);
                        nameInput.setForeground(Color.WHITE);
                        nameInput.setOpaque(false);
                        
                                //set default display
                                nameInput.setText("Name");
                                
                                        //disable text input in main menu
                                        nameInput.setEditable(false);
                scrollPane.getViewport().setOpaque(false);
                IDSCROLL.getViewport().setOpaque(false);
                DESCRIPTIONSCROLL.getViewport().setOpaque(false);
                scrollPane_1.getViewport().setOpaque(false);
                scrollPane_2.getViewport().setOpaque(false);
                scrollPane_3.getViewport().setOpaque(false);
                scrollPane_4.getViewport().setOpaque(false);
                scrollPane_5.getViewport().setOpaque(false);
                scrollPane_6.getViewport().setOpaque(false);
                scrollPane_7.getViewport().setOpaque(false);

      
        
        scrollPane_8 = new JScrollPane();
        scrollPane_8.setOpaque(false);
        scrollPane_8.setBorder(null);
        scrollPane_8.setBounds(910, 432, 276, 207);
        panel.add(scrollPane_8);
        
        scrollPane_8.getViewport().setOpaque(false);


        minimalInput = new JTextPane();
        scrollPane_8.setViewportView(minimalInput);
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
        
        Edit = new JButton("save");
        Edit.setVisible(false);
        Edit.setIcon(new ImageIcon(GUI.class.getResource("/resources/editmode.png")));
        Edit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 minimalInput.setEditable(false);
         	      descriptionInput.setEditable(false);
       	      nameInput.setEditable(false);
       	      idInput.setEditable(false);
       	      primaryFlowInput.setEditable(false);
       	      triggersInput.setEditable(false);
       	      preconditionsInput.setEditable(false);
       	      supActorsInput.setEditable(false);
       	      primActorsInput.setEditable(false);
       	      alternativeFlowInput.setEditable(false);
       	      successInput.setEditable(false);
       	      
           		UseCase uc = new UseCase();
                   uc.setName(nameInput.getText());
                   uc.setID(idInput.getText());
                   uc.setDescription(descriptionInput.getText());
                   uc.setPrimaryActors(primActorsInput.getText());
                   uc.setSupportingActors(supActorsInput.getText());
                   uc.setTriggers(triggersInput.getText());
                   uc.setPreconditions(preconditionsInput.getText());
                   uc.setPrimaryflow(primaryFlowInput.getText());
                   uc.setAlternativeflow(alternativeFlowInput.getText());
                   uc.setMinimalGuarantees(minimalInput.getText());
                   uc.setSuccessGuarantees(successInput.getText());
   				save(uc);
   				
   				button_2.setVisible(false);
   				button_1.setVisible(false);
   				btnNewButton.setVisible(false);
   			    button.setVisible(false);
   	
   				
   				Edit.setVisible(false);
   				Save.setVisible(true);
        	      
        		
        	}
        });
        Edit.setBounds(3, 680, 1190, 17);
        panel.add(Edit);

                Save = new JButton("View");
                Save.setIcon(new ImageIcon(GUI.class.getResource("/resources/viewmode.png")));
                Save.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		
              	      minimalInput.setEditable(true);
              	      descriptionInput.setEditable(true);
            	      nameInput.setEditable(true);
            	      idInput.setEditable(true);
            	      primaryFlowInput.setEditable(true);
            	      triggersInput.setEditable(true);
            	      preconditionsInput.setEditable(true);
            	      supActorsInput.setEditable(true);
            	      primActorsInput.setEditable(true);
            	      alternativeFlowInput.setEditable(true);
            	      successInput.setEditable(true);
            	      
            	  	Edit.setVisible(true);
       				Save.setVisible(false);
       				
       				button_2.setVisible(true);
       				button_1.setVisible(true);
       				btnNewButton.setVisible(true);
       			    button.setVisible(true);
                		
                		
                
                		
                	}
                });
                Save.setBounds(3, 680, 1190, 17);
                panel.add(Save);
                
                btnNewButton = new JButton("");
                btnNewButton.setVisible(false);
                btnNewButton.setIcon(new ImageIcon(GUI.class.getResource("/resources/addactor.png")));
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		dialog = new CreateDialog("");
                	}
                });
                btnNewButton.setBounds(73, 357, 89, 23);
                panel.add(btnNewButton);
                
                button = new JButton("");
                button.setVisible(false);
                button.setIcon(new ImageIcon(GUI.class.getResource("/resources/addactor.png")));
                button.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		dialog = new CreateDialog("");
                	}
                });
                button.setBounds(312, 357, 89, 23);
                panel.add(button);
                
                button_1 = new JButton("");
                button_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		dialog = new CreateDialog("");
                	}
                });
                button_1.setVisible(false);
                button_1.setIcon(new ImageIcon(GUI.class.getResource("/resources/addstep.png")));
                button_1.setBounds(605, 357, 89, 23);
                panel.add(button_1);
                
                button_2 = new JButton("");
                button_2.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		dialog = new CreateDialog("");
                	}
                });
                button_2.setVisible(false);
                button_2.setIcon(new ImageIcon(GUI.class.getResource("/resources/addstep.png")));
                button_2.setBounds(970, 357, 89, 23);
                panel.add(button_2);
        
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
