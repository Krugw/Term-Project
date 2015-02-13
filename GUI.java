import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener {
	private JMenuBar menus;
	private JMenu fileMenu;
	private JMenu actionMenu;

	// fileMenu
	private JMenuItem LoadItem;
	private JMenuItem New_project;
	private JMenuItem exitItem;
	private JMenuItem saveItem;

	// account Menu
	private JMenuItem AddUseCase;
	private JMenuItem editUseCase;
	private JMenuItem helpUseCase;

	private JFrame frame;
	private JButton new_project, edit, load;
	private JPanel panel, panel2, panel_1;

	private JLabel success_input, minimal_input, alternativeFlow_input;
	private JLabel primaryFlow_input, preconditions_input, triggers_input;
	private JLabel prim_actors_input, description_input, ID_input;
	private JLabel name_input;

	private Project CurrentProject = new Project();
	private UseCaseEditor UCE;
	private UseCase CurrentUseCase;
	private ArrayList<String> ids;
	private String file;

	private CreateDialog Dialog;
	private JComboBox<UseCase> ComboBox;

	private LoadFileBox loadFile;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}

	public void UCE_Utility() {
		UCE.addSaveListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseCase uc = UCE.getUC();
				System.out.println(uc.getPreconditions());
				save(uc);
			}
		});
	}

	private void initialize() {
		/** File menu */
		fileMenu = new JMenu("File");
		LoadItem = new JMenuItem("Load");
		New_project = new JMenuItem("New Project");
		exitItem = new JMenuItem("Exit");
		saveItem = new JMenuItem("Save");

		fileMenu.add(LoadItem);
		fileMenu.add(New_project);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);

		saveItem.addActionListener(this);
		New_project.addActionListener(this);
		LoadItem.addActionListener(this);
		exitItem.addActionListener(this);

		/** action menu */
		actionMenu = new JMenu("Action");
		AddUseCase = new JMenuItem("New Usecase");
		editUseCase = new JMenuItem("Edit");
		helpUseCase = new JMenuItem("Help");
		
		AddUseCase.addActionListener(this);
		editUseCase.addActionListener(this);
		helpUseCase.addActionListener(this);
		
		actionMenu.add(AddUseCase);
		actionMenu.add(editUseCase);
		actionMenu.add(helpUseCase);

		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** Menu bar */
		menus = new JMenuBar();
		ids = new ArrayList<String>();

		panel_1 = new JPanel();
		panel_1.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 10;

		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(10, 2, 0, 0));

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
		JLabel fill1 = new JLabel();
		panel2.add(fill1);
		JLabel fill2 = new JLabel();
		panel2.add(fill2);
		JLabel fill3 = new JLabel();
		panel2.add(fill3);
		JLabel fill4 = new JLabel();
		panel2.add(fill4);
		JLabel fill5 = new JLabel();
		panel2.add(fill5);
		panel2.add(new_project);
		panel2.add(load);
		JLabel fill6 = new JLabel();
		panel2.add(fill6);
		JLabel fill7 = new JLabel();
		panel2.add(fill7);
		JLabel fill8 = new JLabel();
		panel2.add(fill8);
		JLabel fill9 = new JLabel();
		panel2.add(fill9);
		JLabel fill = new JLabel();
		panel2.add(fill);

		panel = new JPanel();
		panel.setVisible(false);

		JLabel space = new JLabel();
		panel.add(space);

		name_input = new JLabel("Name");
		panel.add(name_input);

		JLabel ID = new JLabel();
		panel.add(ID);

		ID_input = new JLabel("Id");
		panel.add(ID_input);

		JLabel description = new JLabel();
		description = new JLabel("Description");
		panel.add(description);

		JScrollPane sp3 = new JScrollPane();
		panel.add(sp3);

		description_input = new JLabel("description");
		sp3.setViewportView(description_input);

		JLabel prim_actors = new JLabel("Primary Actors");
		panel.add(prim_actors);
		JScrollPane sp4 = new JScrollPane();
		panel.add(sp4);

		prim_actors_input = new JLabel("prim_actors");
		sp4.setViewportView(prim_actors_input);

		JLabel triggers = new JLabel("Triggers");
		panel.add(triggers);
		JScrollPane sp5 = new JScrollPane();
		sp5.setToolTipText("");
		panel.add(sp5);

		triggers_input = new JLabel("triggers");
		sp5.setViewportView(triggers_input);

		JLabel preconditions = new JLabel("Precondidtions");
		panel.add(preconditions);
		JScrollPane sp6 = new JScrollPane();
		panel.add(sp6);

		preconditions_input = new JLabel("preconditions");
		sp6.setViewportView(preconditions_input);

		JLabel primaryFlow = new JLabel("Primary Flow");
		panel.add(primaryFlow);
		JScrollPane sp7 = new JScrollPane();
		panel.add(sp7);

		primaryFlow_input = new JLabel("primaryFlow");
		sp7.setViewportView(primaryFlow_input);

		JLabel alternativeFlow = new JLabel("Alternative Flow");
		panel.add(alternativeFlow);
		JScrollPane sp8 = new JScrollPane();
		panel.add(sp8);

		alternativeFlow_input = new JLabel("alternativeFlow");
		sp8.setViewportView(alternativeFlow_input);

		JLabel minimal = new JLabel("Minimual Guarentees");
		panel.add(minimal);
		JScrollPane sp9 = new JScrollPane();
		panel.add(sp9);

		minimal_input = new JLabel("Minimal");
		sp9.setViewportView(minimal_input);

		JLabel success = new JLabel("Success Guearentees");
		panel.add(success);
		JScrollPane sp10 = new JScrollPane();
		panel.add(sp10);

		success_input = new JLabel("Success");
		sp10.setViewportView(success_input);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

		edit = new JButton("Edit");
		edit.setVisible(false);
		panel_2.add(edit);
		edit.addActionListener(this);
	}

	public void save(UseCase uc) {
		CurrentUseCase = uc;
		CurrentProject.addUsecase(CurrentUseCase);
		ids = CurrentProject.Getids();
		CurrentProject.saveToXML(file);
		edit.setVisible(true);
		createComboBox();
		display();
	}

	public void display() {
		panel.setVisible(true);
		panel2.setVisible(false);
		menus.add(fileMenu);
		menus.add(actionMenu);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridLayout g1_panel = new GridLayout(10, 2);
		g1_panel.setVgap(2);
		g1_panel.setHgap(2);
		panel.setLayout(g1_panel);
		edit.setVisible(true);
		if (CurrentUseCase != null) {
			success_input.setText(CurrentUseCase.getSuccessGuarantees());
			minimal_input.setText(CurrentUseCase.getMinimalGuaruntees());
			alternativeFlow_input.setText(CurrentUseCase.getAlternativeflow());
			primaryFlow_input.setText(CurrentUseCase.getPrimaryflow());
			preconditions_input.setText(CurrentUseCase.getPreconditions());
			triggers_input.setText(CurrentUseCase.getTriggers());
			prim_actors_input.setText(CurrentUseCase.getPrimaryActors());
			description_input.setText(CurrentUseCase.getDescription());
			ID_input.setText(CurrentUseCase.getID());
			name_input.setText(CurrentUseCase.getName());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddUseCase) {
			UCE = new UseCaseEditor();
			UCE.setVisible(true);
			UCE_Utility();
		}
		if (e.getSource() == new_project || e.getSource() == New_project) {
			Dialog = new CreateDialog();
			file = Dialog.getDirectory();
			CurrentProject.setProjectName(Dialog.getFileName());
			ids = CurrentProject.Getids();
			System.out.println(CurrentProject.GetProjectName());
			System.out.println(file);
			CurrentProject.saveToXML(file);
			createComboBox();
			display();
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
		if (helpUseCase == e.getSource()) {

			JOptionPane.showMessageDialog(null,
					"For Future Usage\n Version 1.0",
					"Version Information", JOptionPane.INFORMATION_MESSAGE);

		}
		if (e.getSource() == load || e.getSource() == LoadItem) {
			loadFile = new LoadFileBox();
			file = loadFile.getFileSelected();
			CurrentProject.loadFromXML(file);
			ids = CurrentProject.Getids();
			if(!ids.isEmpty())
			CurrentUseCase = CurrentProject.GetUsecase(ids.get(0));
			createComboBox();
			display();

		}
	}

	public void createComboBox() {

		Vector<UseCase> useCases = new Vector<UseCase>();
		if (!ids.isEmpty()) {
			for (int i = 0; i < ids.size(); i++) {
				String id = ids.get(i);
				useCases.add(CurrentProject.GetUsecase(id));
			}
		}
		MyComboBoxModel myModel = new MyComboBoxModel(useCases);
		ComboBox = new JComboBox<UseCase>(myModel);
		ComboBox.addActionListener(this);
		panel_1.removeAll();
		panel_1.add(ComboBox, BorderLayout.WEST);
	}
}
