import java.io.File;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**************************************************************
 Project handles several important features of the UseCase editor
 program, including adding, removing, and saving UseCases that have
 been created by the User.
 @author Wesley Krug, Gabriel Steponovich,
 Michael Brecker, Halston Raddatz
 @version Winter 2015
 *****************************************************************/
public class Project {
    /**An ArrayList of UseCases that this Project owns.**/
    private ArrayList<UseCase> useCases;

    /**Default value for projectName.**/
    private String projectName = "Project";

    /**
     * An array list of glossary items. 
     */
    private ArrayList<Glossary> glossary;
    
    /**
     * An array list of Actor items
     */
     private ArrayList<Actor> actors;
    /*****************************************************************
     Builds an ArrayList of UseCase objects.
     *****************************************************************/
    public Project() {
        useCases = new ArrayList<UseCase>();
        glossary = new ArrayList<Glossary>();
        actors = new ArrayList<Actor>();
    }

    /*****************************************************************
     Handles functionality associated with adding a new UseCase object
     to the UseCase array.
     @param uc - UseCase to be added to array.
     *****************************************************************/
    public final void addUsecase(final UseCase uc) {
        for (UseCase uc2 : useCases) {
            if (uc2.getID().equals(uc.getID())) {
                useCases.remove(uc2);
                useCases.add(uc);
                return;
            }
        }
        useCases.add(uc);
    }
    
    /**\
     * Add a actor item to the actors array list.
     *
     * @param a is the glossary item to add.
     */
    public final void addActor(final Actor a) {
        for (Actor actor : actors) {
            if (actor.getName().equals(a.getName())) {
                actors.remove(actor);
                actors.add(a);
                return;
            }
        }
        actors.add(a);
    }
    
    public final Actor getActor(String name) {
    	Actor a = null;
    	for (Actor actor: actors) {
    		if (actor.getName().equals(name)) {
    			a = actor;
    		}
    	}
    	return a;
    }

    /**\
     * Add a glossary item to the glossary array list.
     *
     * @param g is the glossary item to add.
     */
    public final void addGlossaryItem(final Glossary g) {
        for (Glossary gloss : glossary) {
            if (gloss.getWord().equals(g.getWord())) {
                glossary.remove(gloss);
                glossary.add(g);
                return;
            }
        }
        glossary.add(g);
    }

    /**
     * Gets a desired term from the glossary.
     *
     * @param word is the name of the term wanted
     * @return a Glossary object with term name and definition
     */
    public final Glossary getGlossaryItem(final String word) {
        for (Glossary gloss : glossary) {
            if (gloss.getWord().equals(word)) {
                return gloss;
            }
        }
        return null;
    }

    /**
     * This returns a vector containing all the terms in
     * the glossary.
     *
     * @return terms is a vector of terms.
     */
    public final Vector<String> getTerms() {
        Vector<String> terms = new Vector<String>();
        for (Glossary gloss : glossary) {
            terms.add(gloss.getWord());
        }
        return terms;
    }
    
    public final ArrayList<Glossary> getWords() {
    	return glossary;
    }


     public boolean removeWord(Glossary g) {
    	 return glossary.remove(g);
     }
     

    /*****************************************************************
     Returns the UseCase with the requested ID.
     @param id - String representation of UseCase ID being requested.
     @return UseCase usecase of the given id to be returned.
     *****************************************************************/
    public final UseCase getUsecase(final String id) {
        for (UseCase uc : useCases) {
            if (uc.getID().equals(id)) {
                return uc;
            }
        }
        return null;
    }

    public Actor fromString(String actor){
    	ArrayList<String> items = new ArrayList<String>(Arrays.asList(actor.split("\\s*,\\s*")));
    	Actor nActor;
    	if(items.size()>=2){
    	nActor = new Actor(items.get(0),items.get(1));
    	}else if(items.size()>=1){
    		nActor = new Actor(items.get(0),"");
    	}else{
    		nActor = new Actor("???","");
    	}
    	for(int i=2; i <items.size(); i++){
    		nActor.addUsecase(getUsecase(items.get(i)));
    	}
    	return nActor;
    }
    
    /*****************************************************************
     Handles functionality associated with removing a UseCase object
     from the array.
     @param currentUseCase - UseCase to be removed from array.
     @return boolean returns true if remove was successful, false if not.
     *****************************************************************/
    public final boolean removeUsecase(final UseCase currentUseCase) {
        return useCases.remove(currentUseCase);

    }

    /*****************************************************************
     Pulls the IDs from UseCase array for use elsewhere in program.
     @return ids - vector of Strings holding IDs of UseCase objects.
     *****************************************************************/
    public final Vector<String> getIDs() {
        Vector<String> ids = new Vector<String>();
        for (UseCase uc : useCases) {
            ids.add(uc.getID());

        }
        return ids;
    }

    public final ArrayList<Actor> getActors() {
        return actors;
    }

    /*****************************************************************
     Allows user to set the name of their project.
     @param name - String representation of desired project name.
     *****************************************************************/
    public final void setProjectName(final String name) {
        projectName = name;
    }

    /*****************************************************************
     Gets the project name of the UseCase currently being accessed.
     @return projectName - String representation of project name.
     *****************************************************************/
    public final String getProjectName() {
        return projectName;
    }

    /*****************************************************************
    Handles save functionality for program to allow operator
    to save their UseCase for future use. Uses XML as primary fileType.
    @param directory - String representation of file directory in which
    the file will be saved.
    *****************************************************************/
   public final void saveToXML(final String directory) {
	   try {
           DocumentBuilderFactory docFactory =
                   DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
           Document doc = docBuilder.newDocument();
           Element rootElement = doc.createElement("project"); //Root(project)
           rootElement.setAttribute("name", this.projectName);
           doc.appendChild(rootElement);
           for (UseCase uc : useCases) {
               Element usecase = doc.createElement("usecase");
               rootElement.appendChild(usecase);
               usecase.setAttribute("ID", uc.getID());
               Element name = doc.createElement("name");
               if (!uc.getName().isEmpty()) {
                   name.appendChild(doc.createTextNode(uc.getName()));
               } else {
                   name.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((name));

               Element id = doc.createElement("id");
               if (!uc.getID().isEmpty()) {
                   id.appendChild(doc.createTextNode(uc.getID()));
               } else {
                   id.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((id));

               Element desc = doc.createElement("description");
               if (!uc.getDescription().isEmpty()) {
                   desc.appendChild(doc.createTextNode(uc.getDescription()));
               } else {
                   desc.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((desc));

               Element pActors = doc.createElement("primary-actors");
               if (!uc.getPrimaryActors().isEmpty()) {
                   pActors.appendChild(doc.createTextNode(
                           uc.getPrimString()));
               } else {
                   pActors.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((pActors));

               Element sActors = doc.createElement("supporting-actors");
               if (!uc.getSupportingActors().isEmpty()) {
                   sActors.appendChild(doc.createTextNode(
                           uc.getSupString()));
               } else {
                   sActors.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((sActors));

               Element triggers = doc.createElement("trigger");
               if (!uc.getTriggers().isEmpty()) {
                   triggers.appendChild(doc.createTextNode(
                           uc.getTriggers()));
               } else {
                   triggers.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((triggers));

               Element preConditions = doc.createElement("preconditions");
               if (!uc.getPreconditions().isEmpty()) {
                   preConditions.appendChild(doc.createTextNode(
                           uc.getPreconditions()));
               } else {
                   preConditions.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((preConditions));

               Element pFlow = doc.createElement("primary-flow");
               if (!uc.getPrimaryflow().isEmpty()) {
                   for (int i = 0; i < uc.getPrimaryflow().size(); i++) {
                       Element pFlowStep = doc.createElement("pstep");
                       Element pFlowStepTitle = doc.createElement("ptitle");

                       pFlowStepTitle.appendChild(doc.createTextNode(
                               uc.getPrimaryflow().get(i).getText()));
                       pFlowStep.appendChild(pFlowStepTitle);

                       Element pFlowStepNumAltSteps = doc.createElement("numaltsteps");
                           pFlowStepNumAltSteps.appendChild(doc.createTextNode(
                                   String.valueOf(uc.getPrimaryflow().get(i).getNumAltFlows())));
                       pFlowStep.appendChild(pFlowStepNumAltSteps);

                       pFlow.appendChild(pFlowStep);
                   }
               } else {
                   pFlow.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((pFlow));

               Element altFlow = doc.createElement("alternate-flow");
               if (!uc.getAlternativeflow().isEmpty()) {
                   for (int i = 0; i < uc.getAlternativeflow().size(); i++) {
                       Element altStep = doc.createElement("astep");
                       Element altStepTitle = doc.createElement("atitle");

                       altStepTitle.appendChild(doc.createTextNode(
                               uc.getAlternativeflow().get(i).getText()));
                       altStep.appendChild(altStepTitle);

                       Element altStepParent = doc.createElement("parent-step");
                       altStepParent.appendChild(doc.createTextNode(
                               uc.getAlternativeflow().get(i).getParentFlowStep().toString()));
                       altStep.appendChild(altStepParent);

                       Element altStepActions = doc.createElement("actions");
                       for (int j = 0; j < uc.getAlternativeflow().get(i).getActions().size(); j++) {
                           Element altStepAct = doc.createElement("action");
                           altStepAct.appendChild(doc.createTextNode(
                                   uc.getAlternativeflow().get(i).getActions().get(j)));
                           altStepActions.appendChild(altStepAct);
                       }
                       altStep.appendChild(altStepActions);

                       altFlow.appendChild(altStep);
                   }
               } else {
                   altFlow.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((altFlow));

               Element minGuarantee = doc.createElement("minimal-guarantee");
               if (!uc.getMinimalGuaruntees().isEmpty()) {
                   minGuarantee.appendChild(doc.createTextNode(
                           uc.getMinimalGuaruntees()));
               } else {
                   minGuarantee.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((minGuarantee));

               Element sucGuarantee = doc.createElement("success-guarantee");
               if (!uc.getSuccessGuarantees().isEmpty()) {
                   sucGuarantee.appendChild(doc.createTextNode(
                           uc.getSuccessGuarantees()));
               } else {
                   sucGuarantee.appendChild(doc.createTextNode(" "));
               }
               usecase.appendChild((sucGuarantee));

               Element Actors = doc.createElement("actors");
               rootElement.appendChild(Actors);
               for (Actor actor : actors) {
                   Element Actor = doc.createElement("actor");
                   Actors.appendChild((Actor));
                   Actor.appendChild(doc.createTextNode(actor.toString()));
               }

               Element dictionary = doc.createElement("dictionary");
               rootElement.appendChild(dictionary);
               for (Glossary gloss : glossary) {
                   Element term = doc.createElement("term");
                   dictionary.appendChild((term));
                   Element word = doc.createElement("word");
                   if (!gloss.getWord().isEmpty()) {
                       word.appendChild(doc.createTextNode(gloss.getWord()));
                   } else {
                       word.appendChild(doc.createTextNode(" "));
                   }
                   term.appendChild((word));

                   Element def = doc.createElement("definition");
                   if (!gloss.getDefinition().isEmpty()) {
                       def.appendChild(doc.createTextNode(gloss.getDefinition()));
                   } else {
                       def.appendChild(doc.createTextNode(" "));
                   }
                   term.appendChild((def));
               }

               TransformerFactory transformerFactory =
                       TransformerFactory.newInstance();
               Transformer transformer = transformerFactory.newTransformer();
               transformer.setOutputProperty(OutputKeys.INDENT, "yes");
               transformer.setOutputProperty(
                       "{http://xml.apache.org/xslt}indent-amount", "2");
               DOMSource source = new DOMSource(doc);
               StreamResult result = new StreamResult(
                       new File(directory + "\\" + this.getProjectName() + ".ucp"));
               transformer.transform(source, result);
           }
       } catch (ParserConfigurationException e) {
           e.printStackTrace();
       } catch (TransformerConfigurationException e) {
           e.printStackTrace();
       } catch (TransformerException e) {
           e.printStackTrace();
       }
   }


       @Override
       public final String toString () {
           return "Project{"
                   + "UseCases=" + useCases
                   + ", ProjectName='" + projectName
                   + '\'' + '}';
       }
   /*****************************************************************
    Handles load functionality for program to allow operator
    to load their UseCase from a past use. Uses XML as primary fileType.
    @param filename - String representation of file name to be loaded
    *****************************************************************/
   public final boolean loadFromXML(final String filename) {
       try {
           File file = new File(filename);
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();

           if (!filename.contains(".ucp")) {
               JOptionPane.showMessageDialog(null, "Invalid file type.\n", "Error", JOptionPane.ERROR_MESSAGE);
               return false;
           }

           Document doc = db.parse(file);
           doc.getDocumentElement().normalize();

           this.setProjectName(doc.getDocumentElement().getAttribute("name"));

           NodeList nodeList = doc.getElementsByTagName("usecase");

           //Grab info to fill into usecase
           for (int i = 0; i < nodeList.getLength(); i++) {
               UseCase u = new UseCase();
               Node fstNode = nodeList.item(i);
               if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                   //Grab Name and set it to u.Name
                   Element element = (Element) fstNode;
                   NodeList nameNmElmtLst
                           = element.getElementsByTagName("name");
                   Element nameNmElmnt
                           = (Element) nameNmElmtLst.item(0);
                   NodeList nameNm
                           = nameNmElmnt.getChildNodes();
                   u.setName(String.valueOf(nameNm.item(0).getNodeValue()));
                   //Grab ID and set it to u.ID
                   NodeList idNmElmntLst = element.getElementsByTagName("id");
                   Element idNmElmnt = (Element) idNmElmntLst.item(0);
                   NodeList idNm = idNmElmnt.getChildNodes();
                   u.setID(String.valueOf(idNm.item(0).getNodeValue()));
                   //Grab Description and set it to u.Description
                   NodeList descNmElmntLst =
                           element.getElementsByTagName("description");
                   Element descNmElmnt =
                           (Element) descNmElmntLst.item(0);
                   NodeList descNm =
                           descNmElmnt.getChildNodes();
                   u.setDescription(String.valueOf(
                           descNm.item(0).getNodeValue()));
                   //Grab Primary Actors and set it to u.primaryActors
                   NodeList pactNmElmntLst
                           = element.getElementsByTagName("primary-actors");
                   Element pactNmElmnt
                           = (Element) pactNmElmntLst.item(0);
                   NodeList pactNm = pactNmElmnt.getChildNodes();
                   u.setPrimaryActor(String.valueOf(
                           pactNm.item(0).getNodeValue()));
                   //Grab Supporting-Actors and set it to u.supportingActors
                   NodeList sActNmElmntLst =
                           element.getElementsByTagName("supporting-actors");
                   Element sActNmElmnt =
                           (Element) sActNmElmntLst.item(0);
                   NodeList sActNm =
                           sActNmElmnt.getChildNodes();
                   u.setSupportingActor(String.valueOf(
                           sActNm.item(0).getNodeValue()));
                   //Grab Trigger and set it to u.Trigger
                   NodeList trigNmElmntLst =
                           element.getElementsByTagName("trigger");
                   Element trigNmElmnt =
                           (Element) trigNmElmntLst.item(0);
                   NodeList trigNm =
                           trigNmElmnt.getChildNodes();
                   u.setTriggers(String.valueOf(
                           trigNm.item(0).getNodeValue()));
                   //Grab Precondition and set it to u.Precondition
                   NodeList preCNmElmntLst =
                           element.getElementsByTagName("preconditions");
                   Element preCNmElmnt =
                           (Element) preCNmElmntLst.item(0);
                   NodeList preCNm =
                           preCNmElmnt.getChildNodes();
                   u.setPreconditions(String.valueOf(
                           preCNm.item(0).getNodeValue()));

                   //Grab Primary Flow and set it to u.primaryFlow

                   Vector<PrimaryFlowStep> tempPFlow = new Vector<PrimaryFlowStep>(1,1);

                   NodeList pFlowNmElmntLst =
                           element.getElementsByTagName("pstep");
                   for (int j = 0; j < pFlowNmElmntLst.getLength(); j++) {
                       PrimaryFlowStep tempPStep = new PrimaryFlowStep();
                       Node fstNode2 = pFlowNmElmntLst.item(j);
                       if (fstNode2.getNodeType() == Node.ELEMENT_NODE) {
                           Element element1 = (Element) fstNode2;

                           NodeList wordNmElmLst = element1.getElementsByTagName(
                                   "ptitle");
                           Element wordNmElmnt = (Element) wordNmElmLst.item(0);
                           NodeList wordNm = wordNmElmnt.getChildNodes();
                           tempPStep.setText(String.valueOf(wordNm.item(0
                           ).getNodeValue()));
                           NodeList defNmElmtLst = element1.getElementsByTagName(
                                   "numaltsteps");
                           Element defNmElmnt = (Element) defNmElmtLst.item(0);
                           NodeList defNm = defNmElmnt.getChildNodes();
                           tempPStep.setNumAltFlows(Integer.parseInt(String.valueOf(defNm.item(0
                           ).getNodeValue())));
                       }
                       tempPFlow.add(tempPStep);
                   }
                   u.setPrimaryflow(tempPFlow);

                   //Grab Alternate Flow and set it to u.alternateFlow
                   Vector<AlternateFlowStep> tempAFlow = new Vector<AlternateFlowStep>(1,1);

                   NodeList aFlowNmElmntLst =
                           element.getElementsByTagName("astep");
                   for (int j = 0; j < aFlowNmElmntLst.getLength(); j++) {
                       AlternateFlowStep tempAStep = new AlternateFlowStep();
                       Node fstNode2 = aFlowNmElmntLst.item(j);
                       if (fstNode2.getNodeType() == Node.ELEMENT_NODE) {
                           Element element1 = (Element) fstNode2;

                           NodeList wordNmElmLst = element1.getElementsByTagName(
                                   "atitle");
                           Element wordNmElmnt = (Element) wordNmElmLst.item(0);
                           NodeList wordNm = wordNmElmnt.getChildNodes();
                           tempAStep.setText(String.valueOf(wordNm.item(0
                           ).getNodeValue()));
                           NodeList defNmElmtLst = element1.getElementsByTagName(
                                   "parent-step");
                           Element defNmElmnt = (Element) defNmElmtLst.item(0);
                           NodeList defNm = defNmElmnt.getChildNodes();
                           for (int k = 0; k < u.getPrimaryflow().size(); k++) {
                               if (u.getPrimaryflow().get(k).getText().equals(defNm.item(0)
                               .getNodeValue())) {
                                   tempAStep.setParentFlowStep(u.getPrimaryflow().get(k));
                               }
                           }
                           NodeList actionsList = element1.getElementsByTagName(
                                   "actions");
                           Element actions = (Element) actionsList.item(0);
                           NodeList actionVect = actions.getElementsByTagName("action");
                           for (int k = 0; k < actionVect.getLength(); k++) {
                               tempAStep.addAction(actionVect.item(k).getFirstChild().getNodeValue());
                           }
                       }
                       tempAFlow.add(tempAStep);
                   }
                   u.setAlternativeflow(tempAFlow);

                   //Grab Minimal Guarantee and set it to u.minimalGuarantee
                   NodeList mingNmElmntLst =
                           element.getElementsByTagName("minimal-guarantee");
                   Element mingNmElmnt =
                           (Element) mingNmElmntLst.item(0);
                   NodeList mingNm =
                           mingNmElmnt.getChildNodes();
                   u.setMinimalGuarantees(String.valueOf(
                           mingNm.item(0).getNodeValue()));
                   //Grab Success Guarantee and set it to u.successGuarantee
                   NodeList sucGNmElmntLst =
                           element.getElementsByTagName("success-guarantee");
                   Element sucGNmElmnt =
                           (Element) sucGNmElmntLst.item(0);
                   NodeList sucGNm =
                           sucGNmElmnt.getChildNodes();
                   u.setSuccessGuarantees(String.valueOf(
                           sucGNm.item(0).getNodeValue()));
               }
               this.addUsecase(u);
           }

           NodeList nodeList3 = doc.getElementsByTagName("actor");
           actors = new ArrayList<Actor>();
           for (int i = 0; i < nodeList3.getLength(); i++) {
               Node fstNode = nodeList3.item(i);
               Element element = (Element) fstNode;
               NodeList actnd = element.getChildNodes();
               String temp = (String.valueOf(actnd.item(0
               ).getNodeValue()));
               this.addActor(fromString(temp));
           }
           NodeList nodeList2 = doc.getElementsByTagName("term");
           for (int i = 0; i < nodeList2.getLength(); i++) {
               Glossary gloss = new Glossary();
               Node fstNode2 = nodeList2.item(i);
               if (fstNode2.getNodeType() == Node.ELEMENT_NODE) {
                   Element element = (Element) fstNode2;

                   NodeList wordNmElmLst = element.getElementsByTagName(
                           "word");
                   Element wordNmElmnt = (Element) wordNmElmLst.item(0);
                   NodeList wordNm = wordNmElmnt.getChildNodes();
                   gloss.setWord(String.valueOf(wordNm.item(0
                   ).getNodeValue()));
                   NodeList defNmElmtLst = element.getElementsByTagName(
                           "definition");
                   Element defNmElmnt = (Element) defNmElmtLst.item(0);
                   NodeList defNm = defNmElmnt.getChildNodes();
                   gloss.setDefinition(String.valueOf(defNm.item(0
                   ).getNodeValue()));
               }
               this.addGlossaryItem(gloss);
           }

       } catch (ParserConfigurationException e) {
           e.printStackTrace();

       } catch (SAXException e) {
           JOptionPane.showMessageDialog(null, "Invalid file type.\n", "Error", 1);
           return false;
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "No file selected.\n", "Error", 1);
           return false;

       }
       return true;
   }
   public void setActors(ArrayList<Actor> actors2) {
       // TODO Auto-generated method stub
       actors = actors2;

   }

   public void deleteActor(Actor a) {
       // TODO Auto-generated method stub
       actors.remove(a);
       for(UseCase uc: useCases){
           uc.removeActor(a.getName());
       }
   }
}
