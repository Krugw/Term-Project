import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

/*****************************************************************
GUI creates and manages the main GUI interaction of the program,
including displaying all current values and allowing the user to access
other parts of the program dynamically. Extends the default JFrame
class.
@authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class GUI extends JFrame implements ActionListener {
	private JMenuBar menus;
	private JMenu fileMenu;
	private JMenu actionMenu;

	// fileMenu
	private JMenuItem LoadItem;
	private JMenuItem New_project;
	private JMenuItem exitItem;
	private JMenuItem saveItem;
	private JMenuItem save_as;

	// account Menu
	private JMenuItem AddUseCase;
	private JMenuItem editUseCase;
	private JMenuItem helpUseCase;
	private JMenuItem RemoveUseCase;

	private JFrame frame;
	private JButton new_project, edit, delete, load;
	private JPanel panel, panel2, panel_1;

	private JLabel success_input, minimal_input, alternativeFlow_input;
	private JLabel primaryFlow_input, preconditions_input, triggers_input;
	private JLabel prim_actors_input,sup_actors_input, description_input;
	private JLabel name_input, ID_input;

	private Project CurrentProject = new Project();
	private UseCaseEditor UCE;
	private UseCase CurrentUseCase;
	private Vector<String> ids;
	private String file;

	private CreateDialog Dialog;
	private JComboBox<UseCase> ComboBox;
	private MyComboBoxModel myModel;

	private LoadFileBox loadFile;
	
	/*****************************************************************
	main method, creates the instance of GUI
	*****************************************************************/	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI window = new GUI();					
				window.frame.setVisible(true);
				} 
			
			catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}
	);
}
	/*****************************************************************
	Uses intialize() to build GUI elements necessary for basic
	functionality of UseCase program
	*****************************************************************/
	public GUI() {
		initialize();
	}
	
	/*****************************************************************
	performs minimal operations for functionality of UseCase program
	*****************************************************************/
	public void UCE_Utility() {
		UCE.addSaveListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseCase uc = UCE.getUC();
				System.out.println(uc.getPreconditions());
				save(uc);
			}
		});
	}

	/*****************************************************************
	Builds required GUI elements for program functionality
	*****************************************************************/
	private void initialize() {
		/** File menu */
		fileMenu = new JMenu("File");
		LoadItem = new JMenuItem("Load");
		New_project = new JMenuItem("New Project");
		exitItem = new JMenuItem("Exit");
		saveItem = new JMenuItem("Save");
		save_as = new JMenuItem("Save As");
		/*File sourceimage = new File("src/icon.png");
		Image image = null;
        try {
			image = ImageIO.read(sourceimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		fileMenu.add(LoadItem);
		fileMenu.add(New_project);
		fileMenu.add(saveItem);
		fileMenu.add(save_as);
		fileMenu.add(exitItem);

		saveItem.addActionListener(this);
		save_as.addActionListener(this);
		New_project.addActionListener(this);
		LoadItem.addActionListener(this);
		exitItem.addActionListener(this);

		/** action menu */
		actionMenu = new JMenu("Action");
		AddUseCase = new JMenuItem("New Usecase");
		RemoveUseCase = new JMenuItem("Remove Usecase");
		editUseCase = new JMenuItem("Edit");
		helpUseCase = new JMenuItem("Help");

		AddUseCase.addActionListener(this);
		RemoveUseCase.addActionListener(this);
		editUseCase.addActionListener(this);
		helpUseCase.addActionListener(this);

		actionMenu.add(AddUseCase);
		actionMenu.add(RemoveUseCase);
		actionMenu.add(editUseCase);
		actionMenu.add(helpUseCase);

		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UseCase Editor - Lite");
		//frame.setIconImage(image);

		JScrollPane sp2 = new JScrollPane();
		JScrollPane sp3 = new JScrollPane();
		JScrollPane sp4 = new JScrollPane();
		JScrollPane sp5 = new JScrollPane();
		JScrollPane sp6 = new JScrollPane();
		JScrollPane sp7 = new JScrollPane();
		JScrollPane sp8 = new JScrollPane();
		JScrollPane sp9 = new JScrollPane();
		JScrollPane sp10 = new JScrollPane();
		
		/** Menu bar */
		menus = new JMenuBar();
		ids = new Vector<String>();

		panel_1 = new JPanel();
		panel_1.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 10;

		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(11, 2, 0, 0));

		frame.getContentPane().add(menus, BorderLayout.NORTH);
		load = new JButton("Load");
		load.addActionListener(this);

		new_project = new JButton("New Project");
		new_project.addActionListener(this);

		panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		GridLayout g1_panel = new GridLayout(3, 4);
		g1_panel.setVgap(10);
		g1_panel.setHgap(10);
		panel2.setLayout(g1_panel);

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
		panel2.add(new_project);
		panel2.add(load);
		panel2.add(fill5);
		panel2.add(fill6);
		panel2.add(fill7);
		panel2.add(fill8);
		panel2.add(fill9);

		panel = new JPanel();
		panel.setVisible(false);

		JLabel space = new JLabel();
		JLabel space2 = new JLabel();
		
		JLabel prim_actors = new JLabel("Primary Actors");
		JLabel description = new JLabel("Description");
		JLabel sup_actors = new JLabel("Supporting Actors");
		JLabel triggers = new JLabel("Triggers");
		JLabel preconditions = new JLabel("Precondidtions");
		JLabel primaryFlow = new JLabel("Primary Flow");
		JLabel alternativeFlow = new JLabel("Alternative Flow");
		JLabel minimal = new JLabel("Minimual Guarentees");
		JLabel success = new JLabel("Success Guearentees");

		name_input = new JLabel("Name");
		ID_input = new JLabel("Id");
		description_input = new JLabel("description");
		prim_actors_input = new JLabel("prim_actors");
		sup_actors_input = new JLabel("Supporting Actors");
		triggers_input = new JLabel("triggers");
		preconditions_input = new JLabel("preconditions");
		primaryFlow_input = new JLabel("primaryFlow");
		alternativeFlow_input = new JLabel("alternativeFlow");
		minimal_input = new JLabel("Minimal");
		success_input = new JLabel("Success");
		
		sp2.setViewportView(description_input);
		sp3.setViewportView(prim_actors_input);
		sp4.setViewportView(sup_actors_input);
		sp5.setViewportView(triggers_input);
		sp6.setViewportView(preconditions_input);
		sp7.setViewportView(primaryFlow_input);
		sp8.setViewportView(alternativeFlow_input);
		sp9.setViewportView(minimal_input);
		sp10.setViewportView(success_input);

		
		panel.add(space);
		panel.add(name_input);
		panel.add(space2);
		panel.add(ID_input);
		panel.add(description);
		panel.add(sp2);
		panel.add(prim_actors);
		panel.add(sp3);
		panel.add(sup_actors);
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

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

		edit = new JButton("Edit");
		delete = new JButton("Delete");
		edit.setVisible(false);
		delete.setVisible(false);
		panel_2.add(edit);
		panel_2.add(delete);
		delete.addActionListener(this);
		edit.addActionListener(this);
	}

	/*****************************************************************
	Performs save operations using saveToXML method from the Project
	class. 
	@param UseCase uc - current UseCase being acceessed by user
	*****************************************************************/
	public void save(UseCase uc) {
		CurrentUseCase = uc;
		CurrentProject.addUsecase(CurrentUseCase);
		ids = CurrentProject.Getids();
		CurrentProject.saveToXML(file);
		edit.setVisible(true);
		display();
	}

	/*****************************************************************
	Creates elements required to display current values from within
	the UseCase object. Controls most of the GUI display
	present within the program.
	*****************************************************************/
	public void display() {
		panel.setVisible(true);
		panel2.setVisible(false);
		menus.add(fileMenu);
		menus.add(actionMenu);
		updateCombobox();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridLayout g1_panel = new GridLayout(11, 2);
		g1_panel.setVgap(2);
		g1_panel.setHgap(2);
		panel.setLayout(g1_panel);
		edit.setVisible(true);
		delete.setVisible(true);
		if (CurrentUseCase != null) {
			success_input.setText(CurrentUseCase.getSuccessGuarantees());
			minimal_input.setText(CurrentUseCase.getMinimalGuaruntees());
			alternativeFlow_input.setText(CurrentUseCase.getAlternativeflow());
			primaryFlow_input.setText(CurrentUseCase.getPrimaryflow());
			preconditions_input.setText(CurrentUseCase.getPreconditions());
			triggers_input.setText(CurrentUseCase.getTriggers());
			prim_actors_input.setText(CurrentUseCase.getPrimaryActors());
			sup_actors_input.setText(CurrentUseCase.getSupportingActors());
			description_input.setText(CurrentUseCase.getDescription());
			ID_input.setText(CurrentUseCase.getID());
			name_input.setText(CurrentUseCase.getName());
		}
	}

	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	 **************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddUseCase) {
			UCE = new UseCaseEditor();
			UCE.setVisible(true);
			UCE_Utility();
		}
		if (e.getSource() == save_as) {
			Dialog = new CreateDialog(CurrentProject.GetProjectName());
			file = Dialog.getDirectory();
			CurrentProject.setProjectName(Dialog.getFileName());
			ids = CurrentProject.Getids();
			if (file != null) {
				CurrentProject.saveToXML(file);
				createComboBox();
				display();
			}
		}
		if (e.getSource() == new_project || e.getSource() == New_project) {
			Dialog = new CreateDialog("");
			file = Dialog.getDirectory();
			CurrentProject = new Project();
			CurrentProject.setProjectName(Dialog.getFileName());
			ids = CurrentProject.Getids();
			if (file != null) {
				CurrentProject.saveToXML(file);
				createComboBox();
				display();
			}
		}
		if (e.getSource() == saveItem) {
			if (CurrentUseCase != null)
				save(CurrentUseCase);
		}

		if (e.getSource() == edit || e.getSource() == editUseCase) {
			UCE = new UseCaseEditor();
			if (CurrentUseCase != null) {
				UCE.setUC(CurrentUseCase);
			} else {
				UseCase uc = new UseCase();
				UCE.setUC(uc);
			}
			UCE.setVisible(true);
			UCE_Utility();
		}
		if (e.getSource() == ComboBox) {
			if (ComboBox.getSelectedItem() != null)
				CurrentUseCase = (UseCase) ComboBox.getSelectedItem();
			display();
		}
		if (e.getSource() == exitItem) {
			frame.dispose();
		}
		if (e.getSource() == RemoveUseCase || e.getSource() == delete) {
			if (CurrentProject.RemoveUsecase(CurrentUseCase)) {
				ids = CurrentProject.Getids();
				if (!ids.isEmpty()) {
					CurrentUseCase = CurrentProject.GetUsecase(ids.get(0));
				} else {
					UseCase uc = new UseCase();
					CurrentUseCase = uc;
				}
				display();
			}
		}
		if (helpUseCase == e.getSource()) {
			JOptionPane.showMessageDialog(null,
					"For Future Usage\n Version 1.0", "Version Information",
					JOptionPane.INFORMATION_MESSAGE);

		}
		if (e.getSource() == load || e.getSource() == LoadItem) {
			loadFile = new LoadFileBox();
			file = loadFile.getFileSelected();
			if (file != null) {
				CurrentProject = new Project();
				CurrentProject.loadFromXML(file);
				file = file.substring(0, file.lastIndexOf('\\'));
				ids = CurrentProject.Getids();
				if (!ids.isEmpty())
					CurrentUseCase = CurrentProject.GetUsecase(ids.get(0));
				createComboBox();
				display();
			}

		}
	}

	/**************************************************************
	 Controls dynamic updates of the ComboBox, in order to display
	 the correct (and current) values therein.
	 **************************************************************/
	public void updateCombobox() {
		Vector<UseCase> useCases = new Vector<UseCase>();
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				useCases.add(CurrentProject.GetUsecase(id));
			}
		}
		myModel = new MyComboBoxModel(useCases);
		ComboBox.setModel(myModel);
		ComboBox.getSelectedItem();
	}
	/**************************************************************
	 Creates and initalizes a custom ComboBox for use in modifying
	 and creating UseCase elements.
	 **************************************************************/
	public void createComboBox() {
		if(ComboBox != null)
			panel_1.removeAll();
		Vector<UseCase> useCases = new Vector<UseCase>();
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				useCases.add(CurrentProject.GetUsecase(id));
			}
		}
		myModel = new MyComboBoxModel(useCases);
		ComboBox = new JComboBox<UseCase>(useCases);
		ComboBox.addActionListener(this);
		ComboBox.setEnabled(true);
		panel_1.add(ComboBox, BorderLayout.WEST);
	}
}
