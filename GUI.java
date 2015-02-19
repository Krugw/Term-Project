
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

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
	private JMenuItem addUseCase;
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
	private JButton newproject, edit, delete, load;
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
	private UseCase currentUseCase;
	/**
	 * array of project usecase ids.
	 */
	private Vector<String> ids;
	/**
	 * for directory remembrance.
	 */
	private String file;

	/**
	 * For getting project name.
	 */
	private CreateDialog dialog;
	/**
	 * For displaying Usecases.
	 */
	private JComboBox<UseCase> comboBox;
	/**
	 * For displaying Usecases.
	 */
	private MyComboBoxModel myModel;
	/**
	 * For loading files.
	 */
	private LoadFileBox loadFile;
	
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
	/**
	 * 
	 */
	public GUI() {
		initialize();
	}
	
	/*****************************************************************
	performs minimal operations for functionality of UseCase program
	*****************************************************************/
	/**
	 * 
	 */
	public final void uceUtility() {
		uCE.addSaveListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				UseCase uc = uCE.getUC();
				System.out.println(uc.getPreconditions());
				save(uc);
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
        //frame.setIconImage(image);
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
		removeUseCase = new JMenuItem("Remove Usecase");
		editUseCase = new JMenuItem("Edit");
		helpUseCase = new JMenuItem("Help");

		addUseCase.addActionListener(this);
		removeUseCase.addActionListener(this);
		editUseCase.addActionListener(this);
		helpUseCase.addActionListener(this);

		actionMenu.add(addUseCase);
		actionMenu.add(removeUseCase);
		actionMenu.add(editUseCase);
		actionMenu.add(helpUseCase);

		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UseCase Editor - Lite");
		//frame.setIconImage(image);

		
		
		/** Menu bar */
		menus = new JMenuBar();
		ids = new Vector<String>();

		paneL1 = new JPanel();
		paneL1.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 10;

		frame.getContentPane().add(paneL1, BorderLayout.WEST);
		paneL1.setLayout(new GridLayout(11, 2, 0, 0));

		frame.getContentPane().add(menus, BorderLayout.NORTH);
		load = new JButton("Load");
		load.addActionListener(this);

		newproject = new JButton("New Project");
		newproject.addActionListener(this);

		panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		GridLayout ganel = new GridLayout(3, 4);
		ganel.setVgap(10);
		ganel.setHgap(10);
		panel2.setLayout(ganel);

		JLabel fill = new JLabel();
		JLabel fill1 = new JLabel();
		JLabel fill2 = new JLabel();
		JLabel fill3 = new JLabel();
		JLabel fill4 = new JLabel();
		JLabel fill5 = new JLabel();
		JLabel fill6 = new JLabel();
		JLabel fill7 = new JLabel();
		JLabel fill8 = new JLabel();
		JLabel fill9 = new JLabel();
		
		panel2.add(fill);
		panel2.add(fill1);
		panel2.add(fill2);
		panel2.add(fill3);
		panel2.add(fill4);
		panel2.add(fill5);
		panel2.add(newproject);
		panel2.add(load);
		panel2.add(fill5);
		panel2.add(fill6);
		panel2.add(fill7);
		panel2.add(fill8);
		panel2.add(fill9);

		panel = new JPanel();
		panel.setVisible(false);
		initialize2();
	}
	/**
	 * 
	 */
	private void initialize2() {
		
		JScrollPane sp2 = new JScrollPane();
		JScrollPane sp3 = new JScrollPane();
		JScrollPane sp4 = new JScrollPane();
		JScrollPane sp5 = new JScrollPane();
		JScrollPane sp6 = new JScrollPane();
		JScrollPane sp7 = new JScrollPane();
		JScrollPane sp8 = new JScrollPane();
		JScrollPane sp9 = new JScrollPane();
		JScrollPane sp10 = new JScrollPane();
		
		JLabel space = new JLabel();
		JLabel space2 = new JLabel();
		
		JLabel primActors = new JLabel("Primary Actors");
		JLabel description = new JLabel("Description");
		JLabel supActors = new JLabel("Supporting Actors");
		JLabel triggers = new JLabel("Triggers");
		JLabel preconditions = new JLabel("Precondidtions");
		JLabel primaryFlow = new JLabel("Primary Flow");
		JLabel alternativeFlow = new JLabel("Alternative Flow");
		JLabel minimal = new JLabel("Minimual Guarentees");
		JLabel success = new JLabel("Success Guearentees");

		//new input text holders
		nameInput = new JTextPane();
		preconditionsInput = new JTextPane();
		triggersInput = new JTextPane();
		idInput = new JTextPane();
		descriptionInput = new JTextPane();
		supActorsInput = new JTextPane();
		primActorsInput = new JTextPane();
		primaryFlowInput = new JTextPane();
		alternativeFlowInput = new JTextPane();
		minimalInput = new JTextPane();
		descriptionInput.setEditable(false);
		successInput = new JTextPane();
		
		//set default display
		nameInput.setText("Name");
		idInput.setText("Id");
		descriptionInput.setText("description");
		primActorsInput.setText("prim_actors");
		supActorsInput.setText("Supporting Actors");
		triggersInput.setText("triggers");
		triggersInput.setText("triggers");
		preconditionsInput.setText("preconditions");
		primaryFlowInput.setText("primaryFlow");
		alternativeFlowInput.setText("alternativeFlow");
		minimalInput.setText("Minimal");
		successInput.setText("Success");
		
		//disable text input in main menu
		nameInput.setEditable(false);
		idInput.setEditable(false);
		primActorsInput.setEditable(false);
		supActorsInput.setEditable(false);
		triggersInput.setEditable(false);
		triggersInput.setEditable(false);
		preconditionsInput.setEditable(false);
		preconditionsInput.setEditable(false);
		primaryFlowInput.setEditable(false);
		alternativeFlowInput.setEditable(false);
		minimalInput.setEditable(false);
		successInput.setEditable(false);
		
		//set scroll panes for inputs
		sp2.setViewportView(descriptionInput);
		sp3.setViewportView(primActorsInput);
		sp4.setViewportView(supActorsInput);
		sp5.setViewportView(triggersInput);
		sp6.setViewportView(preconditionsInput);
		sp7.setViewportView(primaryFlowInput);
		sp8.setViewportView(alternativeFlowInput);
		sp9.setViewportView(minimalInput);
		sp10.setViewportView(successInput);

		// Create centered project and load button
		panel.add(space);
		panel.add(nameInput);
		panel.add(space2);
		panel.add(idInput);
		panel.add(description);
		panel.add(sp2);
		panel.add(primActors);
		panel.add(sp3);
		panel.add(supActors);
		panel.add(sp4);
		panel.add(triggers);
		panel.add(sp5);
		panel.add(preconditions);
		panel.add(sp6);
		panel.add(primaryFlow);
		panel.add(sp7);
		panel.add(alternativeFlow);
		panel.add(sp8);
		panel.add(minimal);
		panel.add(sp9);
		panel.add(success);
		panel.add(sp10);

		JPanel panel3 = new JPanel();
		frame.getContentPane().add(panel3, BorderLayout.SOUTH);

		edit = new JButton("Edit");
		delete = new JButton("Delete");
		edit.setVisible(false);
		delete.setVisible(false);
		panel3.add(edit);
		panel3.add(delete);
		delete.addActionListener(this);
		edit.addActionListener(this);
	}
	/**
	 * Performs save operations using saveToXML method from the Project
	class.
	 * @param uc
	 */
	public final void save(final UseCase uc) {
		currentUseCase = uc;
		currentProject.addUsecase(currentUseCase);
		ids = currentProject.Getids();
		currentProject.saveToXML(file);
		edit.setVisible(true);
		display();
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
		updateCombobox();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridLayout glPanel = new GridLayout(11, 2);
		glPanel.setVgap(2);
		glPanel.setHgap(2);
		panel.setLayout(glPanel);
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
		if (e.getSource() == saveAs) {
			dialog = new CreateDialog(currentProject.GetProjectName());
			file = dialog.getDirectory();
			currentProject.setProjectName(dialog.getFileName());
			ids = currentProject.Getids();
			if (file != null) {
				currentProject.saveToXML(file);
				createComboBox();
				display();
			}
		}
		if (e.getSource() == newproject || e.getSource() == newProject) {
			dialog = new CreateDialog("");
			file = dialog.getDirectory();
			currentProject = new Project();
			currentProject.setProjectName(dialog.getFileName());
			ids = currentProject.Getids();
			if (file != null) {
				currentProject.saveToXML(file);
				createComboBox();
				display();
			}
		}
		if (e.getSource() == saveItem) {
			if (currentUseCase != null) {
				save(currentUseCase);
			}
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
		if (e.getSource() == comboBox) {
			if (comboBox.getSelectedItem() != null) {
				currentUseCase = (UseCase) comboBox.getSelectedItem();
			}
			display();
		}
		if (e.getSource() == exitItem) {
			frame.dispose();
		}
		if (e.getSource() == removeUseCase || e.getSource() == delete) {
			if (currentProject.RemoveUsecase(currentUseCase)) {
				ids = currentProject.Getids();
				if (!ids.isEmpty()) {
					currentUseCase = currentProject.GetUsecase(ids.get(0));
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
				ids = currentProject.Getids();
				if (!ids.isEmpty()) {
					currentUseCase = currentProject.GetUsecase(ids.get(0));
				}
				createComboBox();
				display();
			}

		}
	}

	/**************************************************************
	 Controls dynamic updates of the ComboBox, in order to display
	 the correct (and current) values therein.
	 **************************************************************/
	/**
	 * 
	 */
	public final void updateCombobox() {
		Vector<UseCase> useCases = new Vector<UseCase>();
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				useCases.add(currentProject.GetUsecase(id));
			}
		}
		myModel = new MyComboBoxModel(useCases);
		comboBox.setModel(myModel);
		comboBox.getSelectedItem();
	}
	/**************************************************************
	 Creates and initalizes a custom ComboBox for use in modifying
	 and creating UseCase elements.
	 **************************************************************/
	/**
	 * 
	 */
	public final void createComboBox() {
		if (comboBox != null) {
			paneL1.removeAll();
		}
		Vector<UseCase> useCases = new Vector<UseCase>();
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				useCases.add(currentProject.GetUsecase(id));
			}
		}
		myModel = new MyComboBoxModel(useCases);
		comboBox = new JComboBox<UseCase>(useCases);
		comboBox.addActionListener(this);
		comboBox.setEnabled(true);
		paneL1.add(comboBox, BorderLayout.WEST);
	}
}
