		import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {
	private JFrame frame;
	private JButton create;
	private JButton temp_create;
	private JButton save;
	private JButton edit;
	private JButton load;
	private JPanel panel;
	private JPanel panel2;
	private JLabel success_input;
	private JLabel minimal_input;
	private JLabel alternativeFlow_input;
	private JLabel primaryFlow_input;
	private JLabel preconditions_input;
	private JLabel triggers_input;
	private JLabel prim_actors_input;
	private JLabel description_input;
	private JLabel ID_input;
	private JLabel name_input;
	private project CurrentProject = new project();
	private UseCaseEditor UCE;
	private UseCase CurrentUseCase;
	
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
 public GUI(){
	 initialize();
 }
  public void UCE_Utility(){
		 UCE.addSaveListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e) {
	        	 UseCase uc = UCE.getUC();
	        	 System.out.println(uc.getPreconditions());
	        	 CurrentProject.addUsecase(uc);
	             save(uc);
	         }
	     });
  }
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(10, 2, 0, 0));

		JTree tree = new JTree();
		panel_1.add(tree);

		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		save = new JButton("Save");
		toolBar.add(save);
		save.addActionListener(this);

		load = new JButton("Load");
		toolBar.add(load);
		load.addActionListener(this);

		create = new JButton("Create");
		toolBar.add(create);
		create.addActionListener(this);
		
		panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		GridLayout g1_panel = new GridLayout(3,4);
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
		panel2.add(create);
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
		success_input.setText(uc.getSuccessGuarantees());
		minimal_input.setText(uc.getMinimalGuaruntees());
		alternativeFlow_input.setText(uc.getAlternativeflow());
		primaryFlow_input.setText(uc.getPrimaryflow());
		preconditions_input.setText(uc.getPreconditions());
		triggers_input.setText(uc.getTriggers());
		prim_actors_input.setText(uc.getPrimaryActors());
		description_input.setText(uc.getDescription());
		ID_input.setText(uc.getID());
		name_input.setText(uc.getName());
		CurrentUseCase = uc;
		uc.saveAsText("filename");
		edit.setVisible(true);
		display();
	}
	public void display(){
		panel.setVisible(true);
		panel2.setVisible(false);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridLayout g1_panel = new GridLayout(10, 2);
		g1_panel.setVgap(2);
		g1_panel.setHgap(2);
		panel.setLayout(g1_panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == create) {
			UCE = new UseCaseEditor();
			UCE.setVisible(true);
			UCE_Utility();
		}

		if (e.getSource() == save) {
			if(CurrentUseCase != null)
			save(CurrentUseCase);
		}
		
		if (e.getSource() == edit) {
			if(UCE == null){
				UCE = new UseCaseEditor();
				UCE.setVisible(true);
				UCE_Utility();
			}
			UCE.setVisible(true);
		}
		
		if (e.getSource() == load) {
			UseCase uc = new UseCase();
			uc.loadFromText("filename");
			CurrentUseCase = uc;
			success_input.setText(uc.getSuccessGuarantees());
			minimal_input.setText(uc.getMinimalGuaruntees());
			alternativeFlow_input.setText(uc.getAlternativeflow());
			primaryFlow_input.setText(uc.getPrimaryflow());
			preconditions_input.setText(uc.getPreconditions());
			triggers_input.setText(uc.getTriggers());
			prim_actors_input.setText(uc.getPrimaryActors());
			description_input.setText(uc.getDescription());
			ID_input.setText(uc.getID());
			name_input.setText(uc.getName());
			edit.setVisible(true);
			display();
			
		}
	}
}
